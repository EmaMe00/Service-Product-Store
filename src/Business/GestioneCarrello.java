package Business;

import DAO.CarrelloDAO;
import Model.FakeDatabase;
import Model.Merce.*;
import Model.Utenti.Utente;
import View.MenuPrincipale;
import View.Sottomenu_MenuUtente.VisualizzaCarrelloUtente;

import javax.mail.MessagingException;
import javax.swing.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GestioneCarrello {

    public void RimuoviProdottoDalCarrello(MenuPrincipale M, VisualizzaCarrelloUtente V) throws SQLException {

        try{

            //Recupero l'utente che sta acquistando
            FakeDatabase DB = M.getFD();
            List<Utente> utenti = DB.getListaUtenti();
            Utente utente = utenti.get(DB.getSessione().getNumUtente());

            //Il primo caso lavora sui Prodotti
            if ( utente.getCarrelloProdotti().get(Integer.parseInt(V.getT2())).getPC() == false){

                //Prelevo il prodotto che voglio eliminare
                Prodotti prodotto = (Prodotti) utente.getCarrelloProdotti().get(Integer.parseInt(V.getT2()));

                //Prelevo il punto vendita dove è presente anche quel prodotto
                List<PuntiVendita> puntiVendita = DB.getPuntivendita();
                PuntiVendita puntoVendita = puntiVendita.get(prodotto.getNumPuntoVendita());
                List<IProdotti> prodotti = puntoVendita.getProdotti();

                //Se lo trova aumenta la disponibilità nel punto vendita di 1 poichè stiamo rimuovendo 1 elemento
                for (int i=0;i<prodotti.size();i++){
                    if (prodotti.get(i).getnome().equals(prodotto.getNome())){
                        Prodotti prod = (Prodotti) prodotti.get(i);
                        prod.setDiponivilita(prod.getDiponivilita()+1);
                        prodotti.set(i,prod);
                    }
                }
                //salvo le modifiche nel punto vendita
                puntoVendita.setProdotti(prodotti);
                puntiVendita.set(prodotto.getNumPuntoVendita(),puntoVendita);
                DB.setPuntiVendita(puntiVendita);

                //rimuovo il prodotto dal carrello e salvo
                utente.removeProdottodalCarrello(Integer.parseInt(V.getT2()));
                utenti.set(DB.getSessione().getNumUtente(),utente);
                DB.setListaUtenti(utenti);

                M.SaveFD(DB);
                JOptionPane.showMessageDialog(M, "Prodotto eliminato correttamente dal carrello");

            }else {
                //Il secondo caso lavora sui servizi

                //funziona esattamente come prima ma lavorando su più prodotti che sarebbero il prodottoComposito e tutti i suoi
                //sottoprodotti
                ProdottoComposito prodottoComposito = (ProdottoComposito) utente.getCarrelloProdotti().get(Integer.parseInt(V.getT2()));
                List<PuntiVendita> puntiVendita = DB.getPuntivendita();
                PuntiVendita puntoVendita = puntiVendita.get(prodottoComposito.getNumPuntoVendita());
                List<IProdotti> prodotti = puntoVendita.getProdotti();

                for (int i=0;i<prodotti.size();i++){
                    if (prodotti.get(i).getnome().equals(prodottoComposito.getnome())){
                        ProdottoComposito prodComp = (ProdottoComposito) prodotti.get(i);
                        List<IProdotti> listProdComp = prodComp.getSottoprodotti();
                        for (int j=0;j<listProdComp.size();j++){
                            Prodotti prod = (Prodotti) listProdComp.get(j);
                            prod.setDiponivilita(prod.getDiponivilita()+1);
                            listProdComp.set(j,prod);
                        }
                        prodComp.setSottoprodotti(listProdComp);
                        prodotti.set(i,prodComp);
                    }
                }

                puntoVendita.setProdotti(prodotti);
                puntiVendita.set(prodottoComposito.getNumPuntoVendita(),puntoVendita);
                DB.setPuntiVendita(puntiVendita);

                utente.removeProdottodalCarrello(Integer.parseInt(V.getT2()));
                utenti.set(DB.getSessione().getNumUtente(),utente);
                DB.setListaUtenti(utenti);

                M.SaveFD(DB);
                JOptionPane.showMessageDialog(M, "Prodotto composito eliminato correttamente");

            }

            CarrelloDAO CDAO = new CarrelloDAO();
            CDAO.UpdateCarrello(M.getFD(),M.getConnection());

        }catch(Exception E){
            JOptionPane.showMessageDialog(M, "L'elemento selezionato non esiste !");
        }



    }

    //Funziona come RimuoviProdottiDalCarrello, esattamente come la parte della rimozione dei prodotti
    public void RimuoviServizioDalCarrello(MenuPrincipale M, VisualizzaCarrelloUtente V) throws SQLException {

        try{

            FakeDatabase DB = M.getFD();
            List<Utente> utenti = DB.getListaUtenti();
            Utente utente = utenti.get(DB.getSessione().getNumUtente());

            Servizi servizio = utente.getCarrelloServizi().get(Integer.parseInt(V.getT4()));

            List<PuntiVendita> puntiVendita = DB.getPuntivendita();
            PuntiVendita puntoVendita = puntiVendita.get(servizio.getNumPuntoVendita());
            List<Servizi> servizi = puntoVendita.getServizi();

            for (int i=0;i<servizi.size();i++){

                if (servizi.get(i).getNome().equals(servizio.getNome())){
                    Servizi serv = servizi.get(i);
                    serv.setDiponivilita(serv.getDiponivilita()+1);
                    servizi.set(i,serv);
                }

            }

            puntoVendita.setServizi(servizi);
            puntiVendita.set(servizio.getNumPuntoVendita(),puntoVendita);
            DB.setPuntiVendita(puntiVendita);

            utente.removeServiziodalCarrello(Integer.parseInt(V.getT4()));
            utenti.set(DB.getSessione().getNumUtente(),utente);
            DB.setListaUtenti(utenti);

            M.SaveFD(DB);
            JOptionPane.showMessageDialog(M, "Servizio eliminato correttamente dal carrello");

            CarrelloDAO CDAO = new CarrelloDAO();
            CDAO.UpdateCarrello(M.getFD(),M.getConnection());

        }catch(Exception E){
            JOptionPane.showMessageDialog(M, "L'elemento selezionato non esiste !");
        }



    }

    //Richiama i due metodi incaricati di eliminare prodotti e servizi dal carrello
    public void EliminaTutto(MenuPrincipale M, VisualizzaCarrelloUtente V) throws SQLException {

        FakeDatabase DB = M.getFD();
        List<Utente> utenti = DB.getListaUtenti();
        Utente utente = utenti.get(DB.getSessione().getNumUtente());

        for (int i=0; i<utente.getCarrelloServizi().size();i++){
           EliminaServizioDalCarrello(M,V,0);
        }

        for (int i=0; i<utente.getCarrelloProdotti().size()+1;i++){
            EliminaProdottoDalCarrello(M,V,0);
        }

        CarrelloDAO CDAO = new CarrelloDAO();
        CDAO.UpdateCarrello(M.getFD(),M.getConnection());
    }


    public void EliminaServizioDalCarrello(MenuPrincipale M, VisualizzaCarrelloUtente V,int indice) throws SQLException {

        FakeDatabase DB = M.getFD();
        List<Utente> utenti = DB.getListaUtenti();
        Utente utente = utenti.get(DB.getSessione().getNumUtente());

        Servizi servizio = utente.getCarrelloServizi().get(indice);

        List<PuntiVendita> puntiVendita = DB.getPuntivendita();
        PuntiVendita puntoVendita = puntiVendita.get(servizio.getNumPuntoVendita());
        List<Servizi> servizi = puntoVendita.getServizi();

        for (int i=0;i<servizi.size();i++){

            if (servizi.get(i).getNome().equals(servizio.getNome())){
                Servizi serv = servizi.get(i);
                serv.setDiponivilita(serv.getDiponivilita()+1);
                servizi.set(i,serv);
            }

        }

        puntoVendita.setServizi(servizi);
        puntiVendita.set(servizio.getNumPuntoVendita(),puntoVendita);
        DB.setPuntiVendita(puntiVendita);

        utente.removeServiziodalCarrello(indice);
        utenti.set(DB.getSessione().getNumUtente(),utente);
        DB.setListaUtenti(utenti);

        M.SaveFD(DB);

        CarrelloDAO CDAO = new CarrelloDAO();
        CDAO.UpdateCarrello(M.getFD(),M.getConnection());

    }

    public void EliminaProdottoDalCarrello(MenuPrincipale M, VisualizzaCarrelloUtente V,int indice) throws SQLException {

        FakeDatabase DB = M.getFD();
        List<Utente> utenti = DB.getListaUtenti();
        Utente utente = utenti.get(DB.getSessione().getNumUtente());

        if ( utente.getCarrelloProdotti().get(indice).getPC() == false){

            Prodotti prodotto = (Prodotti) utente.getCarrelloProdotti().get(indice);

            List<PuntiVendita> puntiVendita = DB.getPuntivendita();
            PuntiVendita puntoVendita = puntiVendita.get(prodotto.getNumPuntoVendita());
            List<IProdotti> prodotti = puntoVendita.getProdotti();
            for (int i=0;i<prodotti.size();i++){
                if (prodotti.get(i).getnome().equals(prodotto.getNome())){
                    Prodotti prod = (Prodotti) prodotti.get(i);
                    prod.setDiponivilita(prod.getDiponivilita()+1);
                    prodotti.set(i,prod);
                }
            }
            puntoVendita.setProdotti(prodotti);
            puntiVendita.set(prodotto.getNumPuntoVendita(),puntoVendita);
            DB.setPuntiVendita(puntiVendita);

            utente.removeProdottodalCarrello(indice);
            utenti.set(DB.getSessione().getNumUtente(),utente);
            DB.setListaUtenti(utenti);

            M.SaveFD(DB);

        }else {

            ProdottoComposito prodottoComposito = (ProdottoComposito) utente.getCarrelloProdotti().get(indice);
            List<PuntiVendita> puntiVendita = DB.getPuntivendita();
            PuntiVendita puntoVendita = puntiVendita.get(prodottoComposito.getNumPuntoVendita());
            List<IProdotti> prodotti = puntoVendita.getProdotti();

            for (int i=0;i<prodotti.size();i++){
                if (prodotti.get(i).getnome().equals(prodottoComposito.getnome())){
                    ProdottoComposito prodComp = (ProdottoComposito) prodotti.get(i);
                    List<IProdotti> listProdComp = prodComp.getSottoprodotti();
                    for (int j=0;j<listProdComp.size();j++){
                        Prodotti prod = (Prodotti) listProdComp.get(j);
                        prod.setDiponivilita(prod.getDiponivilita()+1);
                        listProdComp.set(j,prod);
                    }
                    prodComp.setSottoprodotti(listProdComp);
                    prodotti.set(i,prodComp);
                }
            }

            puntoVendita.setProdotti(prodotti);
            puntiVendita.set(prodottoComposito.getNumPuntoVendita(),puntoVendita);
            DB.setPuntiVendita(puntiVendita);

            utente.removeProdottodalCarrello(indice);
            utenti.set(DB.getSessione().getNumUtente(),utente);
            DB.setListaUtenti(utenti);

            M.SaveFD(DB);

        }

        CarrelloDAO CDAO = new CarrelloDAO();
        CDAO.UpdateCarrello(M.getFD(),M.getConnection());

    }

    public void AcquistaTutto(MenuPrincipale M1, VisualizzaCarrelloUtente V) throws SQLException {

        try{

            CreazionePDF pdf = new CreazionePDF(M1);
            try {
                pdf.CreaPDF();
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            String stringa1 = "Prodotti: \n";
            String stringa2 = "\nProdotti Compositi: \n";
            String stringa3 = "\nServizi: \n";
            for (int i=0; i<V.getProdotti().size(); i=i+2){
                stringa1 = stringa1 + V.getProdotti().get(i) + " " + V.getProdotti().get(i+1) + "€ | ";

            }

            for (int i=0; i<V.getProdottiCompositi().size(); i=i+2){
                stringa2 = stringa2 + V.getProdottiCompositi().get(i) + " " + V.getProdottiCompositi().get(i+1) + "€ | ";

            }

            for (int i=0; i<V.getServizi().size(); i=i+2){
                stringa3 = stringa3 + V.getServizi().get(i) + " " + V.getServizi().get(i+1) + "€ | ";

            }

            try {
                GestioneEmail.sendMail(M1.getFD().getSessione().getEmail(),stringa1 + stringa2 + stringa3);
            } catch (MessagingException ex) {
                ex.printStackTrace();
            }

            FakeDatabase DB = M1.getFD();
            List<Utente> utenti = DB.getListaUtenti();
            Utente utente = utenti.get(DB.getSessione().getNumUtente());
            List<IProdotti> carrelloProdotti = utente.getCarrelloProdotti();

            for (int k=0; k<carrelloProdotti.size(); k++){
                if (carrelloProdotti.get(k).getPC()){
                    ProdottoComposito prodottoComposito = (ProdottoComposito) carrelloProdotti.get(k);
                    List<IProdotti> sottoprodotti = prodottoComposito.getSottoprodotti();
                    for (int l=0; l<sottoprodotti.size(); l++){

                        int giapresente=0;
                        if (utente.getProdottiAcquistati().size()==0){
                            utente.addProdottiAcquistati(sottoprodotti.get(l));
                        }else {
                            for (int a=0; a<utente.getProdottiAcquistati().size();a++) {
                                if (sottoprodotti.get(l).getnome().equals(utente.getProdottiAcquistati().get(a).getnome())){
                                    System.out.println("Gia presente");
                                    giapresente++;
                                }
                            }
                            if (giapresente==0){
                                utente.addProdottiAcquistati(sottoprodotti.get(l));
                            }
                        }

                    }

                }else{

                    int giapresente=0;
                    if (utente.getProdottiAcquistati().size()==0){
                        utente.addProdottiAcquistati(carrelloProdotti.get(k));
                    }else {
                        for (int a=0; a<utente.getProdottiAcquistati().size();a++) {
                            if (carrelloProdotti.get(k).getnome().equals(utente.getProdottiAcquistati().get(a).getnome())) {
                                System.out.println("Gia presente");
                                giapresente++;
                            }
                        }
                        if (giapresente==0){
                            utente.addProdottiAcquistati(carrelloProdotti.get(k));
                        }
                    }
                }
            }

            List<Servizi> carrelloServizi = utente.getCarrelloServizi();
            for (int k=0; k<carrelloServizi.size(); k++){

                if (utente.getServiziAcquistati().size()==0){
                    utente.addServiziAcquistati(carrelloServizi.get(k));
                }else{

                    int giaacquistato=0;
                    for (int a=0; a<utente.getServiziAcquistati().size();a++){

                        if (utente.getServiziAcquistati().get(a).getNome().equals(carrelloServizi.get(k).getNome())){
                            System.out.println("Già acquistato!");
                            giaacquistato++;
                        }

                    }
                    if (giaacquistato==0){
                        utente.addServiziAcquistati(carrelloServizi.get(k));
                    }

                }


            }

            utente.setCarrelloServizi(new ArrayList<>());
            utente.setCarrelloProdotti(new ArrayList<>());
            utenti.set(DB.getSessione().getNumUtente(),utente);
            DB.setListaUtenti(utenti);
            M1.SaveFD(DB);

            CarrelloDAO CDAO = new CarrelloDAO();
            CDAO.UpdateCarrello(M1.getFD(),M1.getConnection());
            JOptionPane.showMessageDialog(V, "Generato un PDF con la tua lista !\nTi è stata inviata un email di resoconto,\npotrai ritirare tutto in negozio! Grazie!");

        }catch (Exception E){
            JOptionPane.showMessageDialog(V, "Non è presente nulla nel carrello");
        }


    }

    public void AcquistaProdotto(MenuPrincipale M1, VisualizzaCarrelloUtente V) throws SQLException {

        try {
            String prodottiDaAcquistare = V.getT1();
            String[] prodDaAcquistare = prodottiDaAcquistare.split(" ");

            //Ordino i numeri che rappresentano ciò che si va ad acquistare
            for (int i=0; i<prodDaAcquistare.length-1; i++){
                for (int j=i+1; j<prodDaAcquistare.length; j++){
                    if(prodDaAcquistare[i].compareTo(prodDaAcquistare[j])>0){
                        String temp = prodDaAcquistare[i];
                        prodDaAcquistare[i] = prodDaAcquistare[j];
                        prodDaAcquistare[j] = temp;
                    }
                }
            }

            CreazionePDF pdf = new CreazionePDF(M1);
            try {
                pdf.CreaPDFProdotti(prodottiDaAcquistare);
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            //Creazione Email
            FakeDatabase DB = M1.getFD();

            List<Utente> utenti = DB.getListaUtenti();
            Utente utente = utenti.get(DB.getSessione().getNumUtente());

            List<IProdotti> prodotti = utente.getCarrelloProdotti();
            List<IProdotti> prodottiAcquistati = utente.getProdottiAcquistati();

            String stringa1 = "Prodotti: \n";
            String stringa2 = "\nProdotti Compositi: \n";

            for (int i=0; i<prodDaAcquistare.length; i++){
                if (prodotti.get(Integer.parseInt(prodDaAcquistare[i])).getPC()){
                    stringa2 = stringa2 + prodotti.get(Integer.parseInt(prodDaAcquistare[i])).getnome() + " " + prodotti.get(Integer.parseInt(prodDaAcquistare[i])).getprezzo() + "€ | ";
                }else{
                    stringa1 = stringa1 + prodotti.get(Integer.parseInt(prodDaAcquistare[i])).getnome() + " " + prodotti.get(Integer.parseInt(prodDaAcquistare[i])).getprezzo() + "€ | ";
                }
                //Aggiungo i prodotti acquistati nella lista di prodotti acquistati dell'utente
                if (prodotti.get(Integer.parseInt(prodDaAcquistare[i])).getPC()){

                    ProdottoComposito prodottoComposito = (ProdottoComposito) prodotti.get(Integer.parseInt(prodDaAcquistare[i]));
                    List<IProdotti> sottoprodotti = prodottoComposito.getSottoprodotti();

                    for (int h=0; h<sottoprodotti.size(); h++){
                        int tmp = ControlloDoppioniAcquistatiProdotti(utente.getProdottiAcquistati(),sottoprodotti.get(h));
                        if (tmp==0){
                            prodottiAcquistati.add(sottoprodotti.get(h));
                        }else{
                            System.out.println("Gia Acquistato!");
                        }
                    }

                }else{
                    int tmp = ControlloDoppioniAcquistatiProdotti(utente.getProdottiAcquistati(),prodotti.get(Integer.parseInt(prodDaAcquistare[i])));
                    if (tmp==0){
                        prodottiAcquistati.add(prodotti.get(Integer.parseInt(prodDaAcquistare[i])));
                    }else{
                        System.out.println("Gia Acquistato!");
                    }


                }

            }

            try {
                GestioneEmail.sendMail(DB.getSessione().getEmail(),stringa1 + stringa2);
            } catch (MessagingException ex) {
                ex.printStackTrace();
            }

            for (int i=0; i<prodDaAcquistare.length; i++){
                //Un eliminazione che funziona su un array ordinato
                prodotti.remove(Integer.parseInt(prodDaAcquistare[i])-i);
            }

            for (int i=0; i<prodottiAcquistati.size(); i++){
                System.out.println(prodottiAcquistati.get(i).getnome() + " N: " + prodottiAcquistati.get(i).getNumPuntoVendita());
            }

            utente.setCarrelloProdotti(prodotti);
            utente.setProdottiAcquistati(prodottiAcquistati);
            utenti.set(DB.getSessione().getNumUtente(),utente);
            DB.setListaUtenti(utenti);
            M1.SaveFD(DB);


            CarrelloDAO CDAO = new CarrelloDAO();
            CDAO.UpdateCarrello(M1.getFD(),M1.getConnection());

            JOptionPane.showMessageDialog(V, "Generato un PDF con la tua lista !\nTi è stata inviata un email di resoconto,\npotrai ritirare tutto in negozio! Grazie!");
        }catch (Exception E){
            JOptionPane.showMessageDialog(V, "Ciò che hai selezionato non esiste");
        }


    }

    public void AcquistaServizio(MenuPrincipale M1, VisualizzaCarrelloUtente V) throws SQLException {

        try{

            String serviziDaAcquistare = V.getT3();
            String[] servDaAcquistare = serviziDaAcquistare.split(" ");

            //Ordino i numeri che rappresentano ciò che si va ad acquistare
            for (int i=0; i<servDaAcquistare.length-1; i++){
                for (int j=i+1; j<servDaAcquistare.length; j++){
                    if(servDaAcquistare[i].compareTo(servDaAcquistare[j])>0){
                        String temp = servDaAcquistare[i];
                        servDaAcquistare[i] = servDaAcquistare[j];
                        servDaAcquistare[j] = temp;
                    }
                }
            }

            CreazionePDF pdf = new CreazionePDF(M1);
            try {
                pdf.CreaPDFServizi(serviziDaAcquistare);
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            //Creazione Email
            FakeDatabase DB = M1.getFD();

            List<Utente> utenti = DB.getListaUtenti();
            Utente utente = utenti.get(DB.getSessione().getNumUtente());

            List<Servizi> servizi = utente.getCarrelloServizi();
            List<Servizi> serviziAcquistati = utente.getServiziAcquistati();

            String string1 = "Servizi: \n";

            for (int i=0; i<servDaAcquistare.length;i++){
                string1 = string1 + servizi.get(Integer.parseInt(servDaAcquistare[i])).getNome() + " " + servizi.get(Integer.parseInt(servDaAcquistare[i])).getPrezzo() + "€ | ";

                int giaacquistato = ControlloDoppioniAcquistatiServizi(utente.getServiziAcquistati(),servizi.get(Integer.parseInt(servDaAcquistare[i])));
                if(giaacquistato==0){
                    serviziAcquistati.add(servizi.get(Integer.parseInt(servDaAcquistare[i])));
                }else {
                    System.out.println("Gia Acquistato");
                }

            }

            try {
                GestioneEmail.sendMail(DB.getSessione().getEmail(),string1);
            } catch (MessagingException ex) {
                ex.printStackTrace();
            }

            for (int i=0; i<servDaAcquistare.length; i++){
                //Un eliminazione che funziona su un array ordinato
                servizi.remove(Integer.parseInt(servDaAcquistare[i])-i);
            }

            utente.setCarrelloServizi(servizi);
            utente.setServiziAcquistati(serviziAcquistati);
            utenti.set(DB.getSessione().getNumUtente(),utente);
            DB.setListaUtenti(utenti);
            M1.SaveFD(DB);


            CarrelloDAO CDAO = new CarrelloDAO();
            CDAO.UpdateCarrello(M1.getFD(),M1.getConnection());
            JOptionPane.showMessageDialog(V, "Generato un PDF con la tua lista !\nTi è stata inviata un email di resoconto,\npotrai ritirare tutto in negozio! Grazie!");

        }catch (Exception E){
            JOptionPane.showMessageDialog(V, "Ciò che hai selezionato non esiste");
        }


    }

    public int ControlloDoppioniAcquistatiProdotti(List<IProdotti> prodottiAcquistati, IProdotti prodotto){

        int giaacquistato=0;
        for (int i=0; i<prodottiAcquistati.size();i++){

            if (prodottiAcquistati.get(i).getnome().equals(prodotto.getnome())){
                giaacquistato++;
            }

        }

        return giaacquistato;

    }

    public int ControlloDoppioniAcquistatiServizi(List<Servizi> serviziAcquistati, Servizi servizio){

        int giaacquistato=0;
        for (int i=0; i<serviziAcquistati.size();i++){

            if (serviziAcquistati.get(i).getNome().equals(servizio.getNome())){
                giaacquistato++;
            }

        }

        return giaacquistato;

    }



}
