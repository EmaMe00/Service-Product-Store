package Business;

import DAO.ArticoliDAO;
import DAO.CorsieDAO;
import Model.Merce.*;
import View.MenuAmministratore;

import javax.swing.*;
import java.sql.SQLException;
import java.util.List;

//L'eliminazione delle corsie e degli scaffali funziona esattamente come quella delle categorie
public class GestioneCorsieScaffali {

    public void EliminaCorsia(String S, int numCorsia, MenuAmministratore M) throws SQLException {

        List<PuntiVendita> puntiVendita = M.getPuntivendita();
        PuntiVendita puntoVendita = puntiVendita.get(Integer.parseInt(S));
        List<Corsia> corsie = puntoVendita.getCorsie();
        if (corsie.size()<2 || numCorsia == 0){
            JOptionPane.showMessageDialog(M, "Non puoi eliminare questa corsia");
            return;
        }
        corsie.remove(numCorsia);
        List<IProdotti> prodotti = puntoVendita.getProdotti();
        for (int i=0;i<prodotti.size();i++){

            if (prodotti.get(i).getPC()){

                ProdottoComposito prodottoComposito = (ProdottoComposito) prodotti.get(i);
                List<IProdotti> sottoprodotti = prodottoComposito.getSottoprodotti();

                for (int j=0;j<sottoprodotti.size();j++){

                    Prodotti sottoprodotto = (Prodotti) sottoprodotti.get(j);
                    if (sottoprodotto.getNumCorsia() == numCorsia){
                        sottoprodotto.setNumCorsia(0);
                        sottoprodotto.setNumScaffale(0);
                        sottoprodotti.set(j,sottoprodotto);
                    }else {
                        if (sottoprodotto.getNumCorsia() > numCorsia){
                            sottoprodotto.setNumCorsia(sottoprodotto.getNumCorsia()-1);
                            sottoprodotti.set(j,sottoprodotto);
                        }
                    }
                }
                prodottoComposito.setSottoprodotti(sottoprodotti);
                prodotti.set(i,prodottoComposito);
            }
            else {
                Prodotti tmp = (Prodotti) prodotti.get(i);
                if (tmp.getNumCorsia() == numCorsia){
                    tmp.setNumCorsia(0);
                    tmp.setNumScaffale(0);
                    prodotti.set(i,tmp);
                }else {
                    if(tmp.getNumCorsia() > numCorsia){
                        tmp.setNumCorsia(tmp.getNumCorsia()-1);
                        prodotti.set(i,tmp);
                        System.out.println(((Articoli) prodotti.get(i)).getNumCorsia());
                    }
                }
            }


        }
        puntoVendita.setProdotti(prodotti);
        puntoVendita.setCorsie(corsie);
        puntiVendita.set(Integer.parseInt(S),puntoVendita);
        M.getMenuPrincipale().getFD().setPuntiVendita(puntiVendita);
        JOptionPane.showMessageDialog(M, "Corsia eliminata correttamente");

        CorsieDAO CDAO = new CorsieDAO();
        CDAO.UpdateCorsia(M.getMenuPrincipale().getFD(),M.getMenuPrincipale().getConnection());

        ArticoliDAO ADAO = new ArticoliDAO();
        ADAO.UpdateArticoli(M.getMenuPrincipale().getFD(),M.getMenuPrincipale().getConnection());

    }

    public void EliminaScaffale(String S,int numScaffale, int numCorsia, MenuAmministratore M) throws SQLException {

        List<PuntiVendita> puntiVendita = M.getPuntivendita();
        PuntiVendita puntoVendita = puntiVendita.get(Integer.parseInt(S));
        List<Corsia> corsie = puntoVendita.getCorsie();
        Corsia corsia = corsie.get(numCorsia);
        List<Scaffale> scaffali = corsia.getScaffali();
        if (scaffali.size()<2){
            JOptionPane.showMessageDialog(M, "Non puoi eliminare l'ultimo scaffale rimanente");
            return;
        }
        scaffali.remove(numScaffale);

        List<IProdotti> prodotti = puntoVendita.getProdotti();
        for (int i=0;i<prodotti.size();i++){

            if (prodotti.get(i).getPC()){

                ProdottoComposito prodottoComposito = (ProdottoComposito) prodotti.get(i);
                List<IProdotti> sottoprodotti = prodottoComposito.getSottoprodotti();

                for (int j=0; j<sottoprodotti.size(); j++){
                    Prodotti sottoprodotto = (Prodotti) sottoprodotti.get(j);
                    if (sottoprodotto.getNumScaffale() == numScaffale && sottoprodotto.getNumCorsia() == numCorsia ){
                        sottoprodotto.setNumScaffale(0);
                        sottoprodotti.set(j,sottoprodotto);
                    } else {
                        if (sottoprodotto.getNumScaffale() > numScaffale && sottoprodotto.getNumCorsia() == numCorsia){
                            sottoprodotto.setNumScaffale(sottoprodotto.getNumScaffale()-1);
                            sottoprodotti.set(j,sottoprodotto);
                        }
                    }
                }
                prodottoComposito.setSottoprodotti(sottoprodotti);
                prodotti.set(i,prodottoComposito);

            }else {
                Prodotti tmp = (Prodotti) prodotti.get(i);
                if (tmp.getNumScaffale() == numScaffale && tmp.getNumCorsia() == numCorsia){
                    tmp.setNumScaffale(0);
                    prodotti.set(i,tmp);
                } else {
                    if (tmp.getNumScaffale() > numScaffale && tmp.getNumCorsia() == numCorsia){
                        tmp.setNumScaffale(tmp.getNumScaffale()-1);
                        prodotti.set(i,tmp);
                    }
                }
            }


        }
        corsia.setScaffali(scaffali);
        corsie.set(numCorsia,corsia);
        puntoVendita.setCorsie(corsie);
        puntiVendita.set(Integer.parseInt(S),puntoVendita);
        M.getMenuPrincipale().getFD().setPuntiVendita(puntiVendita);
        JOptionPane.showMessageDialog(M, "Scaffale eliminato correttamente");

        CorsieDAO CDAO = new CorsieDAO();
        CDAO.UpdateCorsia(M.getMenuPrincipale().getFD(),M.getMenuPrincipale().getConnection());

    }

}
