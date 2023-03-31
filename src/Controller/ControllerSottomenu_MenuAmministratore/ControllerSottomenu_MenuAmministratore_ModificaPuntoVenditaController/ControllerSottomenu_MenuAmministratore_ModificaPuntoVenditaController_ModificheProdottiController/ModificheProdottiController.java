package Controller.ControllerSottomenu_MenuAmministratore.ControllerSottomenu_MenuAmministratore_ModificaPuntoVenditaController.ControllerSottomenu_MenuAmministratore_ModificaPuntoVenditaController_ModificheProdottiController;

import Business.GestisciProdotti;
import DAO.ArticoliDAO;
import DAO.PathIMGDAO;
import Model.FakeDatabase;
import Model.Merce.IProdotti;
import Model.Merce.Prodotti;
import Model.Merce.ProdottoComposito;
import Model.Merce.PuntiVendita;
import Model.Utenti.Utente;
import View.ControlloFinestre.ControlloFinestre;
import View.MenuAmministratore;
import View.Sottomenu_MenuAmministratore.Sottomenu_MenuAmministratore_ModificaPuntoVendita.MenuModifichePuntoVendita;
import View.Sottomenu_MenuAmministratore.Sottomenu_MenuAmministratore_ModificaPuntoVendita.Sottomenu_MenuAmministratore_ModificaPuntoVendita_Prodotti.EliminaImmaginiProdotti;
import View.Sottomenu_MenuAmministratore.Sottomenu_MenuAmministratore_ModificaPuntoVendita.Sottomenu_MenuAmministratore_ModificaPuntoVendita_Prodotti.ModificaProdottoSingolo;
import View.Sottomenu_MenuAmministratore.Sottomenu_MenuAmministratore_ModificaPuntoVendita.Sottomenu_MenuAmministratore_ModificaPuntoVendita_Prodotti.ModificheProdotti;
import View.Sottomenu_MenuAmministratore.Sottomenu_MenuAmministratore_ModificaPuntoVendita.Sottomenu_MenuAmministratore_ModificaPuntoVendita_Prodotti.Sottomenu_MenuAmministratore_ModificaPuntoVendita_Prodotti_NUOVO.NuovoProdotto;
import View.Sottomenu_MenuAmministratore.Sottomenu_MenuAmministratore_ModificaPuntoVendita.Sottomenu_MenuAmministratore_ModificaPuntoVendita_Prodotti.Sottomenu_MenuAmministratore_ModificaPuntoVendita_Prodotti_NUOVO.NuovoProdottoComposito;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ModificheProdottiController implements ActionListener {

    private ModificheProdotti M;
    private MenuAmministratore M1;
    private MenuModifichePuntoVendita M2;
    private String S;

    public ModificheProdottiController(ModificheProdotti m, MenuAmministratore m1, MenuModifichePuntoVendita m2, String s){
        M = m;
        M1 = m1;
        M2 = m2;
        S = s;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if("Indietro".equals(e.getActionCommand())){

            if (M.getManagerIdentificator()==true){
                ControlloFinestre.CloseFrame(M);
            }else {
                ControlloFinestre.CloseFrame(M);
                ControlloFinestre.SwitchFrameOn(M2);
            }
        }

        if ("Nuovo".equals(e.getActionCommand())){
            NuovoProdotto N = new NuovoProdotto();
            N.StartNuovoProdotto(M1,M,S,M2,M.getManagerIdentificator());
            ControlloFinestre.SwitchFrameOff(M);
        }

        if ("Composito".equals(e.getActionCommand())){
            NuovoProdottoComposito N1 = new NuovoProdottoComposito();
            N1.StartNuovoProdottoComposito(M1,M,S);
        }

        if("Modifica".equals(e.getActionCommand())){

            try{

                FakeDatabase DB = M1.getMenuPrincipale().getFD();
                List<PuntiVendita> puntiVendita = DB.getPuntivendita();
                PuntiVendita puntoVendita = puntiVendita.get(Integer.parseInt(S));
                List<IProdotti> prodotti = puntoVendita.getProdotti();
                IProdotti prodotto = prodotti.get(Integer.parseInt(M.getTextField()));
                if (prodotto.getPC()){

                    String s = (String) JOptionPane.showInputDialog(M,"Inserisci 1 per separare i prodotti e poi modificarli, 0 per annullare","1 Separa - 0 Annulla",JOptionPane.PLAIN_MESSAGE, null,null,"1");
                    if (Integer.parseInt(s)==1){

                        ProdottoComposito prodottoComposito = (ProdottoComposito) prodotto;

                        List<Utente> utenti = M1.getMenuPrincipale().getFD().getListaUtenti();
                        for (int i=0; i<utenti.size();i++){

                            Utente utente = utenti.get(i);
                            List<IProdotti> prodottiCarrello = utente.getCarrelloProdotti();
                            for (int k=0; k<prodottiCarrello.size(); k++){
                                if (prodottiCarrello.get(k).getPC()){
                                    if (prodottiCarrello.get(k).getnome().equals(prodottoComposito.getnome())){
                                        prodottiCarrello.remove(k);
                                    }
                                }
                            }
                            utente.setCarrelloProdotti(prodottiCarrello);
                            utenti.set(i,utente);
                        }
                        M1.getMenuPrincipale().getFD().setListaUtenti(utenti);

                        List<IProdotti> sottoprodotti = prodottoComposito.getSottoprodotti();

                        for (int i=0; i<sottoprodotti.size(); i++){
                            Prodotti prod  = (Prodotti) sottoprodotti.get(i);
                            prod.setNome(prod.getNome() +"-"+ prodotti.size());
                            prodotti.add(prod);
                        }

                        prodotti.remove(Integer.parseInt(M.getTextField()));
                        puntoVendita.setProdotti(prodotti);
                        puntiVendita.set(Integer.parseInt(S),puntoVendita);
                        DB.setPuntiVendita(puntiVendita);
                        M1.getMenuPrincipale().SaveFD(DB);

                        ArticoliDAO ADAO = new ArticoliDAO();
                        try {
                            ADAO.UpdateArticoli(M1.getMenuPrincipale().getFD(),M1.getMenuPrincipale().getConnection());
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }

                        PathIMGDAO PDAO = new PathIMGDAO();
                        try {
                            PDAO.UpdatePathIMG(M1.getMenuPrincipale().getFD(),M1.getMenuPrincipale().getConnection());
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }

                        ControlloFinestre.SwitchFrameOff(M);
                        ControlloFinestre.SwitchFrameOn(M2);
                    }

                }
                else{
                    ModificaProdottoSingolo MO = new ModificaProdottoSingolo();
                    MO.StartModificaProdottoSingolo(M1,M2,S,M);
                    ControlloFinestre.SwitchFrameOff(M);
                }

            }
            catch (Exception E){
                JOptionPane.showMessageDialog(M, "Inserisci un valore valido !");
            }



        }

        if("Elimina".equals(e.getActionCommand())){

            try{

                GestisciProdotti A = new GestisciProdotti(M1,S);
                try {
                    A.EliminaProdotto(Integer.parseInt(M.getTextField()));
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                JOptionPane.showMessageDialog(M, "Prodotto eliminato correttamente");
                if (M.getManagerIdentificator()==true){
                    ControlloFinestre.CloseFrame(M);

                }else {
                    ControlloFinestre.CloseFrame(M);
                    ControlloFinestre.SwitchFrameOn(M2);
                }

            }
            catch (Exception E){
                JOptionPane.showMessageDialog(M, "Inserisci una valore valido ");
            }

        }

        if("Elimina immagini".equals(e.getActionCommand())){

            try{

                EliminaImmaginiProdotti E = new EliminaImmaginiProdotti();
                try {
                    E.StartEliminaImmaginiProdotti(M1,M,S);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                ControlloFinestre.SwitchFrameOff(M);

            }
            catch (Exception E){
                JOptionPane.showMessageDialog(M, "Inserisci una valore valido ");
            }


        }

    }
}
