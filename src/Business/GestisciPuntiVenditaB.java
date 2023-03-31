package Business;

import DAO.PuntiVenditaDAO;
import DAO.UtenteDAO;
import Model.FakeDatabase;
import Model.Merce.IProdotti;
import Model.Merce.Prodotti;
import Model.Merce.PuntiVendita;
import Model.Merce.Servizi;
import Model.Utenti.Manager;
import Model.Utenti.Utente;
import View.MenuAmministratore;
import View.MenuPrincipale;

import javax.swing.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GestisciPuntiVenditaB {

    public void EliminaPV(MenuAmministratore M, String numeroPuntoVendita, MenuPrincipale M1) throws SQLException {

        try {

            String[] puntiVenditaSingoli = numeroPuntoVendita.split(" ");
            List<PuntiVendita> puntivendita = new ArrayList<>(M.getPuntivendita());

            //Eliminazione del punto vendita
            int l=0;
            for (int i=0; i< puntiVenditaSingoli.length;i++){

                puntivendita.remove(Integer.parseInt(puntiVenditaSingoli[i-l]));
                l++;
                M.setPuntivendita(puntivendita);
                FakeDatabase FD = M1.getFD();
                FD.setPuntiVendita(M.getPuntivendita());
                M1.SaveFD(FD);
            }

            //Eliminazione della registrazione a quel punto vendita degli utenti
            for (int i=0; i<M1.getFD().getListaUtenti().size();i++){
                if (Integer.parseInt(numeroPuntoVendita) == M1.getFD().getListaUtenti().get(i).getNumPuntoVendita()){
                    FakeDatabase DB = M1.getFD();
                    List<Utente> utenti = DB.getListaUtenti();
                    Utente utente = utenti.get(i);
                    utente.setNumPuntoVendita(-1);
                    utenti.set(i,utente);
                    DB.setListaUtenti(utenti);
                    M1.SaveFD(DB);
                }

                //Se il punto vendita a cui si è iscritti stava dopo quello che si è eliminato allora la posizione deve scalare di 1
                if (Integer.parseInt(numeroPuntoVendita) < M1.getFD().getListaUtenti().get(i).getNumPuntoVendita()){
                    FakeDatabase DB = M1.getFD();
                    List<Utente> utenti = DB.getListaUtenti();
                    Utente utente = utenti.get(i);
                    utente.setNumPuntoVendita(M1.getFD().getListaUtenti().get(i).getNumPuntoVendita()-1);
                    utenti.set(i,utente);
                    DB.setListaUtenti(utenti);
                    M1.SaveFD(DB);
                }
            }

            // Eliminazione dei manager dal punto vendita
            int l1=0;
            int lenght = M1.getFD().getListamanager().size();
            for (int i=0; i<lenght;i++){
                System.out.println( M1.getFD().getListamanager().get(i-l1).getNome() + " - " + M1.getFD().getListamanager().get(i-l1).getNumPuntoVendita());
                if (Integer.parseInt(numeroPuntoVendita) == M1.getFD().getListamanager().get(i-l1).getNumPuntoVendita()){
                    GestisciManagerNuovi G = new GestisciManagerNuovi();
                    G.RimuoviManager(M1,String.valueOf(i-l1));
                    System.out.println("Sono qui");
                    l1++;
                }else {
                    if (Integer.parseInt(numeroPuntoVendita) < M1.getFD().getListamanager().get(i-l1).getNumPuntoVendita()){
                        //Se il punto vendita di cui si è manager stava dopo quello che si è eliminato allora la posizione deve scalare di 1
                        FakeDatabase DB = M1.getFD();
                        List<Manager> managers = DB.getListamanager();
                        Manager manager = managers.get(i-l1);
                        manager.setNumPuntoVendita(M1.getFD().getListamanager().get(i-l1).getNumPuntoVendita()-1);
                        managers.set(i-l1,manager);
                        DB.setListamanager(managers);
                        M1.SaveFD(DB);
                        System.out.println("Sono qui 1");
                    }
                }

            }

            //Eliminazione degli articoli che gli utenti avevano nel carrello relativi a quel punto vendita
            for (int i=0; i<M1.getFD().getListaUtenti().size();i++){

                FakeDatabase DB = M1.getFD();
                List<Utente> utenti = DB.getListaUtenti();
                Utente utente = utenti.get(i);
                List<IProdotti> carrelloProdotti = utente.getCarrelloProdotti();
                List<Servizi> carrelloServizi = utente.getCarrelloServizi();

                for (int j=0;j<utente.getCarrelloProdotti().size();j++){
                    if (utente.getCarrelloProdotti().get(j).getNumPuntoVendita() == Integer.parseInt(numeroPuntoVendita)){
                        carrelloProdotti.remove(j);
                        System.out.println("Eliminato un prodotto");
                    }else {
                        if (utente.getCarrelloProdotti().get(j).getNumPuntoVendita() > Integer.parseInt(numeroPuntoVendita)){
                            Prodotti prodotto = (Prodotti) carrelloProdotti.get(j);
                            prodotto.setNumPuntoVendita(prodotto.getNumPuntoVendita()-1);
                            carrelloProdotti.set(j,prodotto);
                        }
                    }
                }
                for (int j=0;j<utente.getCarrelloServizi().size();j++){
                    if (utente.getCarrelloServizi().get(j).getNumPuntoVendita() == Integer.parseInt(numeroPuntoVendita)){
                        carrelloServizi.remove(j);
                        System.out.println("Eliminato un servizio");
                    }else {
                        if (utente.getCarrelloServizi().get(j).getNumPuntoVendita() > Integer.parseInt(numeroPuntoVendita)){
                            Servizi servizio = carrelloServizi.get(j);
                            servizio.setNumPuntoVendita(servizio.getNumPuntoVendita()-1);
                            carrelloServizi.set(j,servizio);
                        }
                    }

                }

                utente.setCarrelloProdotti(carrelloProdotti);
                utente.setCarrelloServizi(carrelloServizi);
                utenti.set(i,utente);
                DB.setListaUtenti(utenti);
                M1.SaveFD(DB);

            }

            PuntiVenditaDAO PVDAO = new PuntiVenditaDAO();
            PVDAO.UpdatePuntiVendita(M.getMenuPrincipale().getFD(),M.getMenuPrincipale().getConnection());
            JOptionPane.showMessageDialog(M, "Punto vendita eliminato correttamente!");

        }catch (Exception e){
            JOptionPane.showMessageDialog(M, "Inserisci un punto vendita valido !");
        }
        



    }

    public void IscrivitiAlPV(MenuPrincipale M,String S) throws SQLException {

        if (M.getFD().getSessione().getNumUtente() == -1){
            JOptionPane.showMessageDialog(M, "Solo gli utenti possono iscriversi ad un punto vendita");
        }else {

            if(M.getFD().getListaUtenti().get(M.getFD().getSessione().getNumUtente()).getNumPuntoVendita() == Integer.parseInt(S)){
                FakeDatabase DB = M.getFD();
                List<Utente> utenti = DB.getListaUtenti();
                Utente utente = utenti.get(M.getFD().getSessione().getNumUtente());
                utente.setNumPuntoVendita(-1);
                utenti.set(M.getFD().getSessione().getNumUtente(),utente);
                DB.setListaUtenti(utenti);
                M.SaveFD(DB);
                JOptionPane.showMessageDialog(M, "Disiscritto correttamente dal punto vendita");
            }else {
                FakeDatabase DB = M.getFD();
                List<Utente> utenti = DB.getListaUtenti();
                Utente utente = utenti.get(M.getFD().getSessione().getNumUtente());
                utente.setNumPuntoVendita(Integer.parseInt(S));
                utenti.set(M.getFD().getSessione().getNumUtente(),utente);
                DB.setListaUtenti(utenti);
                M.SaveFD(DB);
                JOptionPane.showMessageDialog(M, "Iscritto correttamente al punto vendita");
            }

        }

        UtenteDAO UDAO = new UtenteDAO();
        UDAO.UpdateUtenti(M.getFD(),M.getConnection());

    }

}
