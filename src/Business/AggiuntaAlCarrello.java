package Business;

import DAO.CarrelloDAO;
import Model.FakeDatabase;
import Model.Merce.*;
import Model.Utenti.Utente;
import View.MenuPrincipale;

import javax.swing.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AggiuntaAlCarrello {

    public void AggiungiProdottoAlCarrello(MenuPrincipale M, String S, String numSingoloProdotto) throws SQLException {

        //Prelevo il mio prodotto scelto dal carrello
        FakeDatabase DB = M.getFD();
        List<PuntiVendita> puntiVendita = DB.getPuntivendita();
        PuntiVendita puntoVendita = puntiVendita.get(Integer.parseInt(S));
        List<IProdotti> prodotti = puntoVendita.getProdotti();
        IProdotti iprodotto = prodotti.get(Integer.parseInt(numSingoloProdotto));
        Prodotti prodotto = (Prodotti) iprodotto;
        //Controllo la disponibilità del mio prodotto
        if (prodotto.getDiponivilita()==0){
            JOptionPane.showMessageDialog(M, "Nessuna disponibilità !");
        }else {
            //Avvio questa parte del programma se il prodotto è disponibile

            //Riduco di uno la disponibilità del prodotto nel punto vendita
            prodotto.setDiponivilita(prodotto.getDiponivilita()-1);
            System.out.println(prodotto.getDiponivilita()); // debug

            //Salvo la mia lista di prodotti aggiornata
            prodotti.set(Integer.parseInt(numSingoloProdotto),prodotto);
            puntoVendita.setProdotti(prodotti);
            puntiVendita.set(Integer.parseInt(S),puntoVendita);

            //Recupero l'utente che sta andando ad acquistare
            List<Utente> utenti =  DB.getListaUtenti();
            Utente utente = utenti.get(DB.getSessione().getNumUtente());

            //Creo un nuovo prodotto e ci copio tutte le informazioni di quello che abbiamo scelto in precedenza
            Prodotti prodotto1 = new Prodotti(prodotto.getnome(),prodotto.getprezzo());
            prodotto1.setDiponivilita(1);
            prodotto1.setProduttore(prodotto.getProduttore());
            prodotto1.setNumScaffale(prodotto.getNumScaffale());
            prodotto1.setDescrizione(prodotto.getDescrizione());
            prodotto1.setNumCorsia(prodotto.getNumCorsia());
            prodotto1.setPathIMG(prodotto.getPathIMG());
            prodotto1.setNumCategoria(prodotto.getNumCategoria());
            prodotto1.setNumSottocategoria(prodotto.getNumSottocategoria());
            prodotto1.setNumPuntoVendita(Integer.parseInt(S));
            //Aggiungo il prodotto appena creato nel carrello
            utente.addProdottoalCarrello(prodotto1);

            //Salvo tutte le modifiche
            utenti.set(DB.getSessione().getNumUtente(),utente);

            DB.setListaUtenti(utenti);
            DB.setPuntiVendita(puntiVendita);
            M.SaveFD(DB);
            JOptionPane.showMessageDialog(M, "Aggiunto al carrello correttamente");
        }

        CarrelloDAO CDAO = new CarrelloDAO();
        CDAO.UpdateCarrello(M.getFD(),M.getConnection());

    }

    public void AggiungiServizioAlCarrello(MenuPrincipale M, String S, String numSingoloServizio) throws SQLException {

        //Svolgo la stessa procedura fatta in AggiungiProdottoAlCarrello

        FakeDatabase DB = M.getFD();
        List<PuntiVendita> puntiVendita = DB.getPuntivendita();
        PuntiVendita puntoVendita = puntiVendita.get(Integer.parseInt(S));
        List<Servizi> servizi = puntoVendita.getServizi();
        Servizi servizio = servizi.get(Integer.parseInt(numSingoloServizio));
        if (servizio.getDiponivilita()==0){
            JOptionPane.showMessageDialog(M, "Nessuna disponibilità !");
        }else {
            servizio.setDiponivilita(servizio.getDiponivilita()-1);
            System.out.println(servizio.getDiponivilita());
            servizi.set(Integer.parseInt(numSingoloServizio),servizio);
            puntoVendita.setServizi(servizi);
            puntiVendita.set(Integer.parseInt(S),puntoVendita);

            List<Utente> utenti =  DB.getListaUtenti();
            Utente utente = utenti.get(DB.getSessione().getNumUtente());
            Servizi servizio1 = new Servizi(servizio.getNome(),servizio.getPrezzo());
            servizio1.setDiponivilita(1);
            servizio1.setProduttore(servizio.getProduttore());
            servizio1.setNumScaffale(servizio.getNumScaffale());
            servizio1.setDescrizione(servizio.getDescrizione());
            servizio1.setNumCorsia(servizio.getNumCorsia());
            servizio1.setPathIMG(servizio.getPathIMG());
            servizio1.setNumCategoria(servizio.getNumCategoria());
            servizio1.setNumSottocategoria(servizio.getNumSottocategoria());
            servizio1.setNumPuntoVendita(Integer.parseInt(S));
            utente.addServiziolCarrello(servizio1);

            utenti.set(DB.getSessione().getNumUtente(),utente);

            DB.setListaUtenti(utenti);
            DB.setPuntiVendita(puntiVendita);
            M.SaveFD(DB);
            JOptionPane.showMessageDialog(M, "Aggiunto al carrello correttamente");
        }

        CarrelloDAO CDAO = new CarrelloDAO();
        CDAO.UpdateCarrello(M.getFD(),M.getConnection());

    }

    public void AggiungiProdottoCompositoAlCarrello(MenuPrincipale M, String S, String numSingoloProdotto) throws SQLException {

        //Recupero il prodotto composito ed i suoi sottoprodotti
        FakeDatabase DB = M.getFD();
        List<PuntiVendita> puntiVendita = DB.getPuntivendita();
        PuntiVendita puntoVendita = puntiVendita.get(Integer.parseInt(S));
        List<IProdotti> prodotti = puntoVendita.getProdotti();
        ProdottoComposito prodottoComposito = (ProdottoComposito) prodotti.get(Integer.parseInt(numSingoloProdotto));
        List<IProdotti> sottoprodotti = prodottoComposito.getSottoprodotti();

        //Controllo se uno dei sottoprodotti abbia finito la disponibilità
        int tmp = 0;
        for (int i = 0; i<sottoprodotti.size(); i++){
            Prodotti prod = (Prodotti) sottoprodotti.get(i);
            if (prod.getDiponivilita() == 0){
                tmp++;
            }
        }
        if (tmp != 0){
            JOptionPane.showMessageDialog(M, "Disponibilità di 1 o più prodotti non sufficiente");
            return;
        }else {
            //Se è presente disponibilità provvedo ad abbassare di uno la disponibilità a tutti i prodotti
            for (int i = 0; i<sottoprodotti.size(); i++){
                Prodotti prod = (Prodotti) sottoprodotti.get(i);
                prod.setNumPuntoVendita(Integer.parseInt(S));
                prod.setDiponivilita(prod.getDiponivilita()-1);
                sottoprodotti.set(i,prod);
            }
        }

        //Salvo ciò che ho modificato per il mio punto vendita
        prodottoComposito.setNumPuntoVendita(Integer.parseInt(S));
        prodottoComposito.setSottoprodotti(sottoprodotti);
        prodotti.set(Integer.parseInt(numSingoloProdotto),(IProdotti) prodottoComposito);
        puntoVendita.setProdotti(prodotti);
        puntiVendita.set(Integer.parseInt(S),puntoVendita);
        DB.setPuntiVendita(puntiVendita);
        M.SaveFD(DB);

        //Creo un nuovo prodotto composito con le caratteristiche di quello che abbiamo scelto nel negozio
        ProdottoComposito prodComposito = new ProdottoComposito(prodottoComposito.getnome());
        prodComposito.setNumPuntoVendita(Integer.parseInt(S));
        List<IProdotti> prodotti1 = new ArrayList<>();

        //Aggiungi i prodotti al nostro nuovo prodotto composito, clonando tutte le caratteristiche
        for (int i=0; i<prodottoComposito.getSottoprodotti().size();i++){
            Prodotti prod = new Prodotti(sottoprodotti.get(i).getnome(),sottoprodotti.get(i).getprezzo());
            prod.setNumPuntoVendita(Integer.parseInt(S));
            prod.clonaProdotto((Prodotti) sottoprodotti.get(i));
            prod.setDiponivilita(1);
            prodotti1.add(prod);
        }

        //Salvo tutte le modifiche
        prodComposito.setSottoprodotti(prodotti1);

        List<Utente> utenti = DB.getListaUtenti();
        Utente utente = utenti.get(DB.getSessione().getNumUtente());
        utente.addProdottoalCarrello(prodComposito);
        utenti.set(DB.getSessione().getNumUtente(),utente);
        DB.setListaUtenti(utenti);
        M.SaveFD(DB);
        JOptionPane.showMessageDialog(M, "Prodotto composito aggiunto correttamente al carrello");

        CarrelloDAO CDAO = new CarrelloDAO();
        CDAO.UpdateCarrello(M.getFD(),M.getConnection());

    }

}
