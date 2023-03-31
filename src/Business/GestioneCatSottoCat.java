package Business;

import DAO.ArticoliDAO;
import DAO.CategoriaDAO;
import Model.Merce.*;
import View.MenuAmministratore;

import javax.swing.*;
import java.sql.SQLException;
import java.util.List;

public class GestioneCatSottoCat {

    public void EliminaCategoria(MenuAmministratore M,String S,int numCat) throws SQLException {

        //Prelevo la categoria dal punto vendita
        List<PuntiVendita> puntiVendita = M.getPuntivendita();
        PuntiVendita puntoVendita = puntiVendita.get(Integer.parseInt(S));
        List<ICategoria> categorie = puntoVendita.getCategorie();
        //Se c'è una sola categoria non ti fa eliminare l'ultima
        if (categorie.size()<2 || numCat==0){
            JOptionPane.showMessageDialog(M, "Non puoi eliminare la categoria");
            return;
        }
        //Elimino la categoria
        categorie.remove(numCat);

        List<IProdotti> prodotti = puntoVendita.getProdotti();
        for (int i=0; i<prodotti.size();i++){

            if (prodotti.get(i).getPC()){
                ProdottoComposito prodottoComposito = (ProdottoComposito) prodotti.get(i);
                for (int j=0;j<prodottoComposito.getSottoprodotti().size();j++){
                    Prodotti tmp = (Prodotti) prodottoComposito.getSottoprodotti().get(j);
                    System.out.println("SONO QUI");
                    System.out.println(tmp.getNumCategoria());
                    if (tmp.getNumCategoria() == numCat){
                        tmp.setNumCategoria(0);
                        tmp.setNumSottocategoria(0);
                        prodottoComposito.getSottoprodotti().set(j,tmp);
                    }else {
                        if (tmp.getNumCategoria()>numCat){
                            tmp.setNumCategoria(tmp.getNumCategoria()-1);
                            prodottoComposito.getSottoprodotti().set(j,tmp);
                        }
                    }
                }

            }else {
                Prodotti tmp = (Prodotti) prodotti.get(i);
                if (tmp.getNumCategoria() == numCat){
                    tmp.setNumCategoria(0);
                    tmp.setNumSottocategoria(0);
                    prodotti.set(i,tmp);
                }else {
                    if (tmp.getNumCategoria() > numCat){
                        tmp.setNumCategoria(tmp.getNumCategoria()-1);
                        prodotti.set(i,tmp);
                    }
                }
            }

        }

        List<Servizi> servizi = puntoVendita.getServizi();
        for (int i=0; i<servizi.size();i++){
            Servizi tmp = servizi.get(i);
            if (tmp.getNumCategoria() == numCat){
                tmp.setNumCategoria(0);
                tmp.setNumSottocategoria(0);
                servizi.set(i,tmp);
            }else{
                if (tmp.getNumCategoria() > numCat) {
                    tmp.setNumCategoria(tmp.getNumCategoria()-1);
                    servizi.set(i,tmp);
                }
            }
        }

        //Salvo tutte le modifiche
        puntoVendita.setCategorie(categorie);
        puntoVendita.setServizi(servizi);
        puntoVendita.setProdotti(prodotti);
        puntiVendita.set(Integer.parseInt(S),puntoVendita);
        // M.setPuntivendita(puntiVendita);
        M.getMenuPrincipale().getFD().setPuntiVendita(puntiVendita);

        CategoriaDAO CDAO = new CategoriaDAO();
        CDAO.UpdateCategoria(M.getMenuPrincipale().getFD(),M.getMenuPrincipale().getConnection());

        ArticoliDAO ADAO = new ArticoliDAO();
        ADAO.UpdateArticoli(M.getMenuPrincipale().getFD(),M.getMenuPrincipale().getConnection());

        JOptionPane.showMessageDialog(M, "Categoria eliminata correttamente");

    }

}
