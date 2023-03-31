package Business;

import DAO.RecensioniDAO;
import DAO.VotoDAO;
import Model.FakeDatabase;
import Model.Merce.IProdotti;
import Model.Merce.Prodotti;
import Model.Merce.PuntiVendita;
import Model.Merce.Servizi;
import Model.Recensioni;
import Model.Utenti.Manager;
import Model.Utenti.Utente;
import View.MenuPrincipale;
import View.Sottomenu_MenuUtente.Sottomenu_MenuUtente_VisualizzaPuntiVenditaUtente.Sottomenu_MenuUtente_VisualizzaPuntiVenditaUtente_SingoloPuntoVendita.VisualizzaRecensioni;
import View.Sottomenu_MenuUtente.Sottomenu_MenuUtente_VisualizzaPuntiVenditaUtente.Sottomenu_MenuUtente_VisualizzaPuntiVenditaUtente_SingoloPuntoVendita.VisualizzazioneSingoloProdotto;

import javax.swing.*;
import java.sql.SQLException;
import java.text.DateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class GestioneRecensioni {

    public void AggiungiRecensione(MenuPrincipale M, VisualizzaRecensioni V2, String S, VisualizzazioneSingoloProdotto V1) throws SQLException {
        int tmpOK=0;

        FakeDatabase DB = M.getFD();
        List<Utente> utenti = DB.getListaUtenti();
        List<Manager> manager = DB.getListamanager();
        List<PuntiVendita> puntiVendita = DB.getPuntivendita();
        PuntiVendita puntoVendita = puntiVendita.get(Integer.parseInt(S));

        //---------- UTENTE

        if (M.getFD().getSessione().getNumUtente() != -1){

            Utente utente = utenti.get(M.getFD().getSessione().getNumUtente());

            tmpOK++;
            String s = (String) JOptionPane.showInputDialog(V2,"Inserisci la recensione","Invia",JOptionPane.PLAIN_MESSAGE, null,null,"Recensione");

            if (s != null){

                if (V1.getisProdotto()){

                    List<IProdotti> prodotti = puntoVendita.getProdotti();
                    Prodotti prodotto = (Prodotti) prodotti.get(V2.getNumeroProdotto());
                    int acquistatoOK = 0;

                    for (int i=0; i<utente.getProdottiAcquistati().size();i++){
                        if(utente.getProdottiAcquistati().get(i).getnome().equals(prodotto.getnome()) && utente.getProdottiAcquistati().get(i).getNumPuntoVendita() == prodotto.getNumPuntoVendita()){
                            //questo IF permette di non duplicare la recensione, se tu hai acquistato due volte la stessa cosa verrà scritta comunque una recensione
                            if (acquistatoOK == 0){
                                List<Recensioni> recensioni = prodotto.getRecensioni();
                                LocalDateTime d = LocalDateTime.now();
                                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                                String data = dtf.format(d);
                                Recensioni recensione = new Recensioni(utenti.get(DB.getSessione().getNumUtente()).getNome(),utenti.get(DB.getSessione().getNumUtente()).getCognome(),s,data,utenti.get(DB.getSessione().getNumUtente()).getEmail());
                                recensioni.add(recensione);
                                prodotto.setRecensioni(recensioni);
                                prodotti.set(V2.getNumeroProdotto(),prodotto);
                                puntoVendita.setProdotti(prodotti);
                                puntiVendita.set(Integer.parseInt(S),puntoVendita);
                                DB.setPuntiVendita(puntiVendita);
                                M.SaveFD(DB);
                                acquistatoOK++;
                                JOptionPane.showMessageDialog(V2, "Recensione aggiunta correttamente !");
                            }
                        }
                    }
                    if (acquistatoOK==0){
                        JOptionPane.showMessageDialog(V2, "Devi acquistare prima di poter recensire");
                    }

                }else{

                    List<Servizi> servizi = puntoVendita.getServizi();
                    Servizi servizio = servizi.get(V2.getNumeroServizio());
                    int acquistatoOK = 0;

                    for (int i=0; i<utente.getServiziAcquistati().size();i++){
                        if (utente.getServiziAcquistati().get(i).getNome().equals(servizio.getNome()) && utente.getServiziAcquistati().get(i).getNumPuntoVendita() == servizio.getNumPuntoVendita()){
                            //questo IF permette di non duplicare la recensione, se tu hai acquistato due volte la stessa cosa verrà scritta comunque una recensione
                            if (acquistatoOK == 0){
                                List<Recensioni> recensioni = servizio.getRecensioni();
                                LocalDateTime d = LocalDateTime.now();
                                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                                String data = dtf.format(d);
                                Recensioni recensione = new Recensioni(utenti.get(DB.getSessione().getNumUtente()).getNome(),utenti.get(DB.getSessione().getNumUtente()).getCognome(),s,data,utenti.get(DB.getSessione().getNumUtente()).getEmail());
                                recensioni.add(recensione);
                                servizio.setRecensioni(recensioni);
                                servizi.set(V2.getNumeroServizio(),servizio);
                                puntoVendita.setServizi(servizi);
                                puntiVendita.set(Integer.parseInt(S),puntoVendita);
                                DB.setPuntiVendita(puntiVendita);
                                M.SaveFD(DB);
                                acquistatoOK++;
                                JOptionPane.showMessageDialog(V2, "Recensione aggiunta correttamente !");
                            }

                        }
                    }
                    if (acquistatoOK==0){
                        JOptionPane.showMessageDialog(V2, "Devi acquistare prima di poter recensire");
                    }

                }

            }

            //------------

        }

        if (M.getFD().getSessione().getNumManager() != -1){

            tmpOK++;
            String s = (String) JOptionPane.showInputDialog(V2,"Inserisci la recensione","Invia",JOptionPane.PLAIN_MESSAGE, null,null,"Recensione");

            if (s != null){

                if (V1.getisProdotto()){

                    List<IProdotti> prodotti = puntoVendita.getProdotti();
                    Prodotti prodotto = (Prodotti) prodotti.get(V2.getNumeroProdotto());
                    List<Recensioni> recensioni = prodotto.getRecensioni();
                    LocalDateTime d = LocalDateTime.now();
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                    String data = dtf.format(d);
                    Recensioni recensione = new Recensioni(manager.get(DB.getSessione().getNumManager()).getNome(),manager.get(DB.getSessione().getNumManager()).getCognome(),s,data,manager.get(DB.getSessione().getNumManager()).getEmail());
                    recensioni.add(recensione);
                    prodotto.setRecensioni(recensioni);
                    prodotti.set(V2.getNumeroProdotto(),prodotto);
                    puntoVendita.setProdotti(prodotti);
                    puntiVendita.set(Integer.parseInt(S),puntoVendita);
                    DB.setPuntiVendita(puntiVendita);
                    M.SaveFD(DB);
                    JOptionPane.showMessageDialog(V2, "Recensione aggiunta correttamente !");

                }else{

                    List<Servizi> servizi = puntoVendita.getServizi();
                    Servizi servizio = servizi.get(V2.getNumeroServizio());
                    List<Recensioni> recensioni = servizio.getRecensioni();
                    LocalDateTime d = LocalDateTime.now();
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                    String data = dtf.format(d);
                    Recensioni recensione = new Recensioni(manager.get(DB.getSessione().getNumManager()).getNome(),manager.get(DB.getSessione().getNumManager()).getCognome(),s,data,manager.get(DB.getSessione().getNumManager()).getEmail());
                    recensioni.add(recensione);
                    servizio.setRecensioni(recensioni);
                    servizi.set(V2.getNumeroServizio(),servizio);
                    puntoVendita.setServizi(servizi);
                    puntiVendita.set(Integer.parseInt(S),puntoVendita);
                    DB.setPuntiVendita(puntiVendita);
                    M.SaveFD(DB);
                    JOptionPane.showMessageDialog(V2, "Recensione aggiunta correttamente !");

                }

            }

        }

        if (tmpOK==0){
            JOptionPane.showMessageDialog(V2, "Solo gli utenti o i manager possono scrivere le recensioni");
        }

        RecensioniDAO RDAO = new RecensioniDAO();
        RDAO.UpdateRecensione(M.getFD(),M.getConnection());

    }



    public void EliminaRecensione(MenuPrincipale M, VisualizzaRecensioni V2, String S, VisualizzazioneSingoloProdotto V1) throws SQLException {
        int tmpOK = 0;

        FakeDatabase DB = M.getFD();
        List<PuntiVendita> puntiVendita = DB.getPuntivendita();
        PuntiVendita puntoVendita = puntiVendita.get(Integer.parseInt(S));

        if (M.getFD().getSessione().getNumUtente() != -1){

            tmpOK++;
            String s = (String) JOptionPane.showInputDialog(V2,"Numero della recensione da eliminare: ","Elimina",JOptionPane.PLAIN_MESSAGE, null,null,"0");

            if (V1.getisProdotto()){

                List<IProdotti> prodotti = puntoVendita.getProdotti();
                Prodotti prodotto = (Prodotti) prodotti.get(V2.getNumeroProdotto());
                List<Recensioni> recensioni = prodotto.getRecensioni();

                if (recensioni.get(Integer.parseInt(s)).getEmail().equals(DB.getSessione().getEmail())){
                    recensioni.remove(Integer.parseInt(s));
                    prodotto.setRecensioni(recensioni);
                    prodotti.set(V2.getNumeroProdotto(),prodotto);
                    puntoVendita.setProdotti(prodotti);
                    puntiVendita.set(Integer.parseInt(S),puntoVendita);
                    DB.setPuntiVendita(puntiVendita);
                    M.SaveFD(DB);
                    JOptionPane.showMessageDialog(V2, "Recensione eliminata correttamente");
                }else {
                    JOptionPane.showMessageDialog(V2, "Questa recensione non è tua !");
                }



            }else{

                List<Servizi> servizi = puntoVendita.getServizi();
                Servizi servizio = servizi.get(V2.getNumeroServizio());
                List<Recensioni> recensioni = servizio.getRecensioni();

                if (recensioni.get(Integer.parseInt(s)).getEmail().equals(DB.getSessione().getEmail())){
                    recensioni.remove(Integer.parseInt(s));
                    servizio.setRecensioni(recensioni);
                    servizi.set(V2.getNumeroProdotto(),servizio);
                    puntoVendita.setServizi(servizi);
                    puntiVendita.set(Integer.parseInt(S),puntoVendita);
                    DB.setPuntiVendita(puntiVendita);
                    M.SaveFD(DB);
                    JOptionPane.showMessageDialog(V2, "Recensione eliminata correttamente");
                }else {
                    JOptionPane.showMessageDialog(V2, "Questa recensione non è tua !");
                }

            }

        }

        if (M.getFD().getSessione().getNumManager() != -1){

            tmpOK++;
            String s = (String) JOptionPane.showInputDialog(V2,"Numero della recensione da eliminare: ","Elimina",JOptionPane.PLAIN_MESSAGE, null,null,"0");

            if (V1.getisProdotto()){

                List<IProdotti> prodotti = puntoVendita.getProdotti();
                Prodotti prodotto = (Prodotti) prodotti.get(V2.getNumeroProdotto());
                List<Recensioni> recensioni = prodotto.getRecensioni();


                recensioni.remove(Integer.parseInt(s));
                prodotto.setRecensioni(recensioni);
                prodotti.set(V2.getNumeroProdotto(),prodotto);
                puntoVendita.setProdotti(prodotti);
                puntiVendita.set(Integer.parseInt(S),puntoVendita);
                DB.setPuntiVendita(puntiVendita);
                M.SaveFD(DB);
                JOptionPane.showMessageDialog(V2, "Recensione eliminata correttamente");




            }else{

                List<Servizi> servizi = puntoVendita.getServizi();
                Servizi servizio = servizi.get(V2.getNumeroServizio());
                List<Recensioni> recensioni = servizio.getRecensioni();

                if (recensioni.get(Integer.parseInt(s)).getEmail() == DB.getSessione().getEmailManager()){
                    recensioni.remove(Integer.parseInt(s));
                    servizio.setRecensioni(recensioni);
                    servizi.set(V2.getNumeroProdotto(),servizio);
                    puntoVendita.setServizi(servizi);
                    puntiVendita.set(Integer.parseInt(S),puntoVendita);
                    DB.setPuntiVendita(puntiVendita);
                    M.SaveFD(DB);
                    JOptionPane.showMessageDialog(V2, "Recensione eliminata correttamente");
                }else {
                    JOptionPane.showMessageDialog(V2, "Questa recensione non è tua !");
                }

            }

        }

        if (tmpOK == 0){
            JOptionPane.showMessageDialog(V2, "Solo utenti e manager possono eliminare le recensioni !");
        }

        RecensioniDAO RDAO = new RecensioniDAO();
        RDAO.UpdateRecensione(M.getFD(),M.getConnection());
    }


    public void AggiungiVoto(MenuPrincipale M, VisualizzaRecensioni V2, String S, VisualizzazioneSingoloProdotto V1) throws SQLException {
        if (M.getFD().getSessione().getNumUtente() != -1){

            FakeDatabase DB = M.getFD();
            List<Utente> utenti = DB.getListaUtenti();
            Utente utente = utenti.get(M.getFD().getSessione().getNumUtente());
            List<PuntiVendita> puntiVendita = DB.getPuntivendita();
            PuntiVendita puntoVendita = puntiVendita.get(Integer.parseInt(S));

            if (V1.getisProdotto()){

                int giàScritto=0;
                int giaAcquistato=0;
                List<IProdotti> prodotti = puntoVendita.getProdotti();
                Prodotti prodotto = (Prodotti) prodotti.get(V2.getNumeroProdotto());

                for (int j=0; j<utente.getProdottiAcquistati().size(); j++){
                    if(utente.getProdottiAcquistati().get(j).getnome().equals(prodotto.getnome()) && utente.getProdottiAcquistati().get(j).getNumPuntoVendita() == prodotto.getNumPuntoVendita()){

                        giaAcquistato++;

                        for (int i=0; i < prodotto.getEmailVoto().size();i++){
                            if (prodotto.getEmailVoto().get(i).equals(utente.getEmail())){
                                JOptionPane.showMessageDialog(V2, "Hai già votato una volta non puoi votare ancora");
                                giàScritto++;
                            }
                        }

                        if (giàScritto==0){
                            System.out.println("SONO QUI");
                            String s = (String) JOptionPane.showInputDialog(V2,"Inserisci il voto (MAX 5)","Invia",JOptionPane.PLAIN_MESSAGE, null,null,"5");
                            double tmp = Double.parseDouble(s);
                            if (tmp>5){tmp = 5;}else{if (tmp<0){tmp = 0;}}
                            prodotto.addVoto(tmp);
                            prodotto.addEmailVoto(utente.getEmail());
                            prodotti.set(V2.getNumeroProdotto(),prodotto);
                            puntoVendita.setProdotti(prodotti);
                            puntiVendita.set(Integer.parseInt(S),puntoVendita);
                            DB.setPuntiVendita(puntiVendita);
                            M.SaveFD(DB);
                        }

                    }
                }

                if (giaAcquistato==0){
                    JOptionPane.showMessageDialog(V2, "Devi acquistare prima di poter votare !");
                }



            }else{

                int giàScritto=0;
                int giaAcquistato=0;
                List<Servizi> servizi = puntoVendita.getServizi();
                Servizi servizio = (Servizi) servizi.get(V2.getNumeroServizio());

                for (int j=0; j<utente.getServiziAcquistati().size();j++){
                    if (utente.getServiziAcquistati().get(j).getNome().equals(servizio.getNome()) && utente.getServiziAcquistati().get(j).getNumPuntoVendita() == servizio.getNumPuntoVendita()){

                        giaAcquistato++;

                        for (int i=0; i < servizio.getEmailVoto().size();i++){
                            if (servizio.getEmailVoto().get(i).equals(utente.getEmail())){
                                JOptionPane.showMessageDialog(V2, "Hai già votato una volta non puoi votare ancora");
                                giàScritto++;
                            }
                        }

                        if (giàScritto==0){
                            String s = (String) JOptionPane.showInputDialog(V2,"Inserisci il voto (MAX 5)","Invia",JOptionPane.PLAIN_MESSAGE, null,null,"5");
                            double tmp = Double.parseDouble(s);
                            if (tmp>5){tmp = 5;}else{if (tmp<0){tmp = 0;}}
                            servizio.addVoto(tmp);
                            servizio.addEmailVoto(utente.getEmail());
                            servizi.set(V2.getNumeroServizio(),servizio);
                            puntoVendita.setServizi(servizi);
                            puntiVendita.set(Integer.parseInt(S),puntoVendita);
                            DB.setPuntiVendita(puntiVendita);
                            M.SaveFD(DB);
                        }
                    }
                }

                if (giaAcquistato==0){
                    JOptionPane.showMessageDialog(V2, "Devi acquistare prima di poter votare !");
                }


            }

        }else{
            JOptionPane.showMessageDialog(V2, "Solo gli utenti possono votare");
        }

        VotoDAO VDAO = new VotoDAO();
        VDAO.UpdateVoto(M.getFD(),M.getConnection());

    }



    public void EliminaVoto(MenuPrincipale M, VisualizzaRecensioni V2, String S, VisualizzazioneSingoloProdotto V1) throws SQLException {
        if (M.getFD().getSessione().getNumUtente() != -1) {

            FakeDatabase DB = M.getFD();
            List<Utente> utenti = DB.getListaUtenti();
            Utente utente = utenti.get(M.getFD().getSessione().getNumUtente());
            List<PuntiVendita> puntiVendita = DB.getPuntivendita();
            PuntiVendita puntoVendita = puntiVendita.get(Integer.parseInt(S));

            if (V1.getisProdotto()){

                int eliminato=0;
                List<IProdotti> prodotti = puntoVendita.getProdotti();
                Prodotti prodotto = (Prodotti) prodotti.get(V2.getNumeroProdotto());
                List<String> emailVoto = prodotto.getEmailVoto();
                List<Double> voti = prodotto.getVoto();

                for (int i=0; i < emailVoto.size();i++){
                    if (prodotto.getEmailVoto().get(i).equals(utente.getEmail())){
                        emailVoto.remove(i);
                        voti.remove(i);
                        eliminato++;
                        prodotto.setEmailVoto(emailVoto);
                        prodotto.setVoto(voti);
                        prodotti.set(V2.getNumeroProdotto(),prodotto);
                        puntoVendita.setProdotti(prodotti);
                        puntiVendita.set(Integer.parseInt(S),puntoVendita);
                        DB.setPuntiVendita(puntiVendita);
                        M.SaveFD(DB);
                        JOptionPane.showMessageDialog(V2, "Hai eliminato il tuo voto");
                    }
                }
                if (eliminato==0){
                    JOptionPane.showMessageDialog(V2, "Non hai messo ancora nessun voto");
                }


            } else{
                int eliminato=0;
                List<Servizi> servizi = puntoVendita.getServizi();
                Servizi servizio = servizi.get(V2.getNumeroServizio());
                List<String> emailVoto = servizio.getEmailVoto();
                List<Double> voti = servizio.getVoto();

                for (int i=0; i < emailVoto.size();i++){
                    if (servizio.getEmailVoto().get(i).equals(utente.getEmail())){
                        emailVoto.remove(i);
                        voti.remove(i);
                        eliminato++;
                        servizio.setEmailVoto(emailVoto);
                        servizio.setVoto(voti);
                        servizi.set(V2.getNumeroServizio(),servizio);
                        puntoVendita.setServizi(servizi);
                        puntiVendita.set(Integer.parseInt(S),puntoVendita);
                        DB.setPuntiVendita(puntiVendita);
                        M.SaveFD(DB);
                        JOptionPane.showMessageDialog(V2, "Hai eliminato il tuo voto");
                    }
                }
                if (eliminato==0){
                    JOptionPane.showMessageDialog(V2, "Non hai messo ancora nessun voto");
                }
            }

        }else {
            JOptionPane.showMessageDialog(V2, "Non puoi eliminare nessun voto ! ");
        }

        VotoDAO VDAO = new VotoDAO();
        VDAO.UpdateVoto(M.getFD(),M.getConnection());

    }

}
