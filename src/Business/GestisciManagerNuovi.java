package Business;

import DAO.UtenteDAO;
import Model.FakeDatabase;
import Model.Utenti.Manager;
import Model.Utenti.Utente;
import View.MenuPrincipale;

import javax.swing.*;
import java.sql.SQLException;
import java.util.List;

public class GestisciManagerNuovi {

    private UtenteDAO UDAO = new UtenteDAO();

    public GestisciManagerNuovi(){}

    //rimozione manager
    public void RimuoviManager(MenuPrincipale M, String numeroManager) throws SQLException {

        try {

            FakeDatabase DB = M.getFD();
            List<Manager> managers = DB.getListamanager();

            String[] managerSingoli = numeroManager.split(" ");

            int l=0;
            for (int i=0; i<managerSingoli.length;i++){
                Manager manager = managers.get(Integer.parseInt(managerSingoli[i-l]));

                Utente utente = new Utente.Builder(manager.getNome())
                        .setCognome(manager.getCognome())
                        .setNickname(manager.getNickname())
                        .setEmail(manager.getEmail())
                        .setTelefono(manager.getTelefono())
                        .setCitta(manager.getCitta())
                        .setEta(manager.getEta()).build();
               utente.setPasswordNOMD5(manager.getPassword());
               utente.setProdottiAcquistati(manager.getProdottiAcquistati());
               utente.setServiziAcquistati(manager.getServiziAcquistati());

                managers.remove(Integer.parseInt(managerSingoli[i-l]));
                UDAO.removeUtenteOManager(M.getConnection(),M.getFD(),manager.getEmail());
                UDAO.addUtente(M.getConnection(),M.getFD(),utente);
                l++;

                List<Utente> utenti = DB.getListaUtenti();
                utenti.add(utente);

                DB.setListaUtenti(utenti);
                DB.setListamanager(managers);
            }
            M.SaveFD(DB);
            JOptionPane.showMessageDialog(M, "Manager rimosso correttamente!");
        }catch (Exception e){
            JOptionPane.showMessageDialog(M, "Inserisci un valore valido!");
        }



    }

}
