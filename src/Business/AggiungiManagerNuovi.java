package Business;

import DAO.CarrelloDAO;
import DAO.UtenteDAO;
import Model.FakeDatabase;
import Model.Utenti.Manager;
import Model.Utenti.Utente;
import View.MenuPrincipale;

import javax.swing.*;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;

public class AggiungiManagerNuovi {

    private UtenteDAO UDAO = new UtenteDAO();

    public AggiungiManagerNuovi(){}

    //Aggiungo  un nuovo manager per un punto vendita
    public void aggiungiManager(MenuPrincipale M, int numeroPuntoVendita, int numeroUtente) throws SQLException, NoSuchAlgorithmException {

        try{
            //Prelevo il singolo utente
            FakeDatabase DB = M.getFD();
            List<Utente> utenti = DB.getListaUtenti();
            Utente utente = utenti.get(numeroUtente);


            //Creo un nuovo manager assegnadogli tutti gli attributi che aveva l'utente scelto in precedenza
            Manager manager = new Manager();
            manager.setNome(utente.getNome());
            manager.setNumPuntoVendita(numeroPuntoVendita);
            manager.setCognome(utente.getCognome());
            manager.setEmail(utente.getEmail());
            manager.setEta(utente.getEta());
            manager.setCitta(utente.getCitta());
            manager.setNickname(utente.getNickname());
            manager.setPasswordNOMD5(utente.getPassword());
            manager.setProdottiAcquistati(utente.getProdottiAcquistati());
            manager.setServiziAcquistati(utente.getServiziAcquistati());
            System.out.println(utente.getPassword());
            manager.setTelefono(utente.getTelefono());

            //Rimuovo l'utente dalla mia lista
            utenti.remove(numeroUtente);
            UDAO.removeUtenteOManager(M.getConnection(),M.getFD(),utente.getEmail());

            //Aggiungo il manager creato ora alla mia lista di manager e salvo tutto
            List<Manager> managers = DB.getListamanager();
            managers.add(manager);
            UDAO.addManager(M.getConnection(),M.getFD(),manager);

            DB.setListaUtenti(utenti);
            DB.setListamanager(managers);

            M.SaveFD(DB);
        }catch (Exception E){
            JOptionPane.showMessageDialog(M, "Inserisci un valore valido !");
            E.printStackTrace();
        }




    }

}
