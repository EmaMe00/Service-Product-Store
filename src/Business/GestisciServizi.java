package Business;

import DAO.ArticoliDAO;
import DAO.CarrelloDAO;
import DAO.PathIMGDAO;
import DAO.ScaffaleDAO;
import Model.Merce.IProdotti;
import Model.Merce.Prodotti;
import Model.Merce.PuntiVendita;
import Model.Merce.Servizi;
import Model.Utenti.Utente;
import View.MenuAmministratore;
import View.Sottomenu_MenuAmministratore.Sottomenu_MenuAmministratore_ModificaPuntoVendita.Sottomenu_MenuAmministratore_ModificaPuntoVendita_Servizi.Sottomenu_MenuAmministratore_ModificaPuntoVendita_Servizi_NuovoServizio.NuovoServizio;

import javax.swing.*;
import java.awt.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.List;

public class GestisciServizi {

    private MenuAmministratore M;
    private NuovoServizio N;
    private String S;

    public GestisciServizi(MenuAmministratore m, NuovoServizio n, String s){
        M = m;
        N = n;
        S = s;
    }

    public GestisciServizi(MenuAmministratore m,String s){
        M=m;
        S=s;
    }

    public void AggiungiNuovoServizio(Path PathImg) throws SQLException {

        try{Servizi servizio = new Servizi(N.getNome(),Double.parseDouble(N.getCosto()));
            servizio.setProduttore(N.getProduttore());
            servizio.setDescrizione(N.getDescrizione());
            servizio.setNumCategoria(Integer.parseInt(N.getCategoria()));
            servizio.setDiponivilita(Integer.parseInt(N.getDisponibilita()));
            servizio.setNumSottocategoria(Integer.parseInt(N.getSottocategoria()));
            servizio.setNumPuntoVendita(Integer.parseInt(S));
            servizio.addPathIMG(PathImg);

            List<PuntiVendita> puntiVendita = M.getPuntivendita();
            PuntiVendita puntoVendita = puntiVendita.get(Integer.parseInt(S));
            puntoVendita.addServizi(servizio);
            puntiVendita.set(Integer.parseInt(S),puntoVendita);
            M.getMenuPrincipale().getFD().setPuntiVendita(puntiVendita);

            ArticoliDAO ADAO = new ArticoliDAO();
            ADAO.UpdateArticoli(M.getMenuPrincipale().getFD(),M.getMenuPrincipale().getConnection());

            PathIMGDAO PDAO = new PathIMGDAO();
            PDAO.UpdatePathIMG(M.getMenuPrincipale().getFD(),M.getMenuPrincipale().getConnection());

            ScaffaleDAO SDAO = new ScaffaleDAO();
            SDAO.UpdateScaffale(M.getMenuPrincipale().getFD(),M.getMenuPrincipale().getConnection());
        }
        catch (Exception e){
            JOptionPane.showMessageDialog(M, "Inserisci i dati correttamente !");
        }



    }

    public void EliminaServizio(int numServizio) throws SQLException {
        List<PuntiVendita> puntiVendita = M.getPuntivendita();
        PuntiVendita puntoVendita = puntiVendita.get(Integer.parseInt(S));
        List<Servizi> servizi = puntoVendita.getServizi();


        List<Utente> utenti = M.getMenuPrincipale().getFD().getListaUtenti();

        for (int i=0;i<utenti.size();i++){

            List<Servizi> carrelloServizi = utenti.get(i).getCarrelloServizi();
            for (int j=0; j<carrelloServizi.size();j++){
                Servizi servizioCarrello = carrelloServizi.get(j);
                if (servizioCarrello.getNome().equals(servizi.get(numServizio).getNome()) && servizioCarrello.getPrezzo() == servizi.get(numServizio).getPrezzo()){
                    carrelloServizi.remove(j);
                }
            }
            utenti.get(i).setCarrelloServizi(carrelloServizi);
        }

        servizi.remove(numServizio);
        puntoVendita.setServizi(servizi);
        puntiVendita.set(Integer.parseInt(S),puntoVendita);
        M.getMenuPrincipale().getFD().setPuntiVendita(puntiVendita);
        M.getMenuPrincipale().getFD().setListaUtenti(utenti);


        ArticoliDAO ADAO = new ArticoliDAO();
        ADAO.UpdateArticoli(M.getMenuPrincipale().getFD(),M.getMenuPrincipale().getConnection());


    }

}
