package Business;

import DAO.*;
import Model.Merce.*;
import Model.Utenti.Utente;
import View.MenuAmministratore;
import View.Sottomenu_MenuAmministratore.Sottomenu_MenuAmministratore_ModificaPuntoVendita.Sottomenu_MenuAmministratore_ModificaPuntoVendita_Prodotti.Sottomenu_MenuAmministratore_ModificaPuntoVendita_Prodotti_NUOVO.NuovoProdotto;
import View.Sottomenu_MenuAmministratore.Sottomenu_MenuAmministratore_ModificaPuntoVendita.Sottomenu_MenuAmministratore_ModificaPuntoVendita_Prodotti.Sottomenu_MenuAmministratore_ModificaPuntoVendita_Prodotti_NUOVO.NuovoProdottoComposito;

import javax.swing.*;
import java.nio.file.Path;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GestisciProdotti {

    private MenuAmministratore M;
    private NuovoProdotto N;
    private String S;
    private NuovoProdottoComposito N1;


    public GestisciProdotti(MenuAmministratore m, NuovoProdotto n, String s){
        M = m;
        N = n;
        S = s;
    }

    public GestisciProdotti(MenuAmministratore m, NuovoProdottoComposito n1, String s){
        M = m;
        N1 = n1;
        S = s;
    }

    public GestisciProdotti(MenuAmministratore m, String s){
        M=m;
        S=s;
    }

    public void AggiungiProdotto(Path pathIMG) throws SQLException {

        try{
            Prodotti prodotto = new Prodotti(N.getNome(),Double.parseDouble(N.getCosto()));
            prodotto.setProduttore(N.getProduttore());
            prodotto.setDescrizione(N.getDescrizione());
            prodotto.setNumCategoria(Integer.parseInt(N.getCategoria()));
            prodotto.setDiponivilita(Integer.parseInt(N.getDisponibilita()));
            prodotto.setNumSottocategoria(Integer.parseInt(N.getSottocategoria()));
            prodotto.setNumCorsia(Integer.parseInt(N.getCorsia()));
            prodotto.setNumPuntoVendita(Integer.parseInt(S));
            prodotto.addPathIMG(pathIMG);

            IProdotti iprodotto = (IProdotti) prodotto;

            List<PuntiVendita> puntivendita = M.getPuntivendita();
            PuntiVendita tmp = puntivendita.get(Integer.parseInt(S));
            List<Corsia> lctmp = tmp.getCorsie();
            Corsia ctmp = lctmp.get(Integer.parseInt(N.getCorsia()));
            Scaffale stmp = new Scaffale(Integer.parseInt(N.getScaffale()));
            ctmp.add(stmp);
            prodotto.setNumScaffale(ctmp.getScaffali().size()-1);

            lctmp.set(Integer.parseInt(N.getCorsia()),ctmp);
            tmp.setCorsie(lctmp);
            tmp.addProdotto(iprodotto);
            puntivendita.set(Integer.parseInt(S),tmp);
            M.getMenuPrincipale().getFD().setPuntiVendita(puntivendita);

            ArticoliDAO ADAO = new ArticoliDAO();
            ADAO.UpdateArticoli(M.getMenuPrincipale().getFD(),M.getMenuPrincipale().getConnection());

            PathIMGDAO PDAO = new PathIMGDAO();
            PDAO.UpdatePathIMG(M.getMenuPrincipale().getFD(),M.getMenuPrincipale().getConnection());

            ScaffaleDAO SDAO = new ScaffaleDAO();
            SDAO.UpdateScaffale(M.getMenuPrincipale().getFD(),M.getMenuPrincipale().getConnection());
        } catch(Exception e){
            JOptionPane.showMessageDialog(M, "Inserisci correttamente tutti i dati !");
        }



    }

    public void AggiungiNuovoProdottoComposito() throws SQLException {

        try{
            List<PuntiVendita> puntiVendita = M.getPuntivendita();
            PuntiVendita puntoVendita = puntiVendita.get(Integer.parseInt(S));
            List<IProdotti> prodotti = puntoVendita.getProdotti();

            ProdottoComposito prodottoComposito = new ProdottoComposito(N1.getProdottoPrincipale());

            String[] prodottiSingoli = N1.getProdottoMinore().split(" ");


            for (int i=0; i<prodottiSingoli.length;i++){

                Prodotti prodotto = new Prodotti(prodotti.get(Integer.parseInt(prodottiSingoli[i])).getnome(),prodotti.get(Integer.parseInt(prodottiSingoli[i])).getprezzo());
                prodotto.clonaProdotto((Prodotti) prodotti.get(Integer.parseInt(prodottiSingoli[i])));
                prodotto.setNome(prodotto.getNome());
                /*
                prodotto.setRecensioni(new ArrayList<>());
                prodotto.setVoto(new ArrayList<>());
                prodotto.setEmailVoto(new ArrayList<>());

                 */
                prodottoComposito.add(prodotto);
            }

        /*
        int l=0;
        for (int i=0;i<prodottiSingoli.length;i++){
            prodotti.remove(Integer.parseInt(prodottiSingoli[i-l]));
            l++;
        }

        */



            IProdotti prodComp = (IProdotti) prodottoComposito;
            prodotti.add(prodComp);
            puntoVendita.setProdotti(prodotti);
            puntiVendita.set(Integer.parseInt(S),puntoVendita);
            M.getMenuPrincipale().getFD().setPuntiVendita(puntiVendita);

            ArticoliDAO ADAO = new ArticoliDAO();
            ADAO.UpdateArticoli(M.getMenuPrincipale().getFD(),M.getMenuPrincipale().getConnection());

            PathIMGDAO PDAO = new PathIMGDAO();
            PDAO.UpdatePathIMG(M.getMenuPrincipale().getFD(),M.getMenuPrincipale().getConnection());

            RecensioniDAO RDAO = new RecensioniDAO();
            RDAO.UpdateRecensione(M.getMenuPrincipale().getFD(),M.getMenuPrincipale().getConnection());


            JOptionPane.showMessageDialog(N, "Nuovo prodotto composito aggiunto correttamente");
        }catch (Exception E){
            JOptionPane.showMessageDialog(M, "Inserisci correttamente tutti i dati!");
        }




    }

    public void EliminaProdotto(int numProdotto) throws SQLException {

        List<PuntiVendita> puntiVendita = M.getMenuPrincipale().getFD().getPuntivendita();
        PuntiVendita puntoVendita = puntiVendita.get(Integer.parseInt(S));
        List<IProdotti> prodotti = puntoVendita.getProdotti();


        List<Utente> utenti = M.getMenuPrincipale().getFD().getListaUtenti();

        for (int i=0;i<utenti.size();i++){

            List<IProdotti> carrelloProdotti = utenti.get(i).getCarrelloProdotti();
            for (int j=0; j<carrelloProdotti.size();j++){
                IProdotti prodottoCarrello = carrelloProdotti.get(j);
                System.out.println("ECCOMI : " + prodottoCarrello.getnome() + " " + prodotti.get(numProdotto).getnome() + " | " + prodottoCarrello.getprezzo() + " " + prodotti.get(numProdotto).getprezzo());
                if (prodottoCarrello.getnome().equals(prodotti.get(numProdotto).getnome()) && Objects.equals(prodottoCarrello.getprezzo(), prodotti.get(numProdotto).getprezzo())){
                    carrelloProdotti.remove(j);
                    System.out.println("SONO QUI");
                }
            }
            utenti.get(i).setCarrelloProdotti(carrelloProdotti);

        }
        prodotti.remove(numProdotto);
        puntoVendita.setProdotti(prodotti);
        puntiVendita.set(Integer.parseInt(S),puntoVendita);
        M.getMenuPrincipale().getFD().setPuntiVendita(puntiVendita);
        M.getMenuPrincipale().getFD().setListaUtenti(utenti);

        ArticoliDAO ADAO = new ArticoliDAO();
        ADAO.UpdateArticoli(M.getMenuPrincipale().getFD(),M.getMenuPrincipale().getConnection());

    }


}
