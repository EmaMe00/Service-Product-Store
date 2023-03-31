package Controller.ControllerSottomenu_MenuUtente.ControllerSottomenu_MenuUtente_VisualizzaPuntiVenditaController;

import Business.AggiuntaAlCarrello;
import Business.GestisciPuntiVenditaB;
import Model.FakeDatabase;
import Model.Merce.ProdottoComposito;
import Model.Utenti.Utente;
import View.ControlloFinestre.ControlloFinestre;
import View.MenuPrincipale;
import View.Sottomenu_MenuUtente.Sottomenu_MenuUtente_VisualizzaPuntiVenditaUtente.Sottomenu_MenuUtente_VisualizzaPuntiVenditaUtente_SingoloPuntoVendita.VisualizzazioneSingoloProdotto;
import View.Sottomenu_MenuUtente.Sottomenu_MenuUtente_VisualizzaPuntiVenditaUtente.VisualizzaProdottoComposito;
import View.Sottomenu_MenuUtente.Sottomenu_MenuUtente_VisualizzaPuntiVenditaUtente.VisualizzazioneProdottiUtente;
import View.Sottomenu_MenuUtente.VisualizzaPuntiVendita;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VisualizzazioneProdottiController implements ActionListener {

    private MenuPrincipale M;
    private VisualizzaPuntiVendita V1;
    private VisualizzazioneProdottiUtente V;
    private String S;
    private int tmp = -1;


    public VisualizzazioneProdottiController(MenuPrincipale m, VisualizzazioneProdottiUtente v, VisualizzaPuntiVendita v1, String s){
        M = m;
        V = v;
        V1 = v1;
        S = s;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if ("Indietro".equals(e.getActionCommand())){
            ControlloFinestre.SwitchFrameOn(V1);
            ControlloFinestre.CloseFrame(V);
        }

        if ("Iscriviti".equals(e.getActionCommand())){
            GestisciPuntiVenditaB G = new GestisciPuntiVenditaB();
            try {
                G.IscrivitiAlPV(M,S);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

        if ("Conferma".equals(e.getActionCommand())){

            try{

                ArrayList<VisualizzazioneSingoloProdotto> VS = new ArrayList<>();
                VisualizzazioneSingoloProdotto visProd = new VisualizzazioneSingoloProdotto();
                visProd.setisProdotto(true);
                try {
                    if (M.getFD().getPuntivendita().get(Integer.parseInt(S)).getProdotti().get(Integer.parseInt(V.getNumProdotto())).getPC()){

                        // ProdottoComposito pdc = (ProdottoComposito) M.getFD().getPuntivendita().get(Integer.parseInt(S)).getProdotti().get(Integer.parseInt(V.getNumProdotto()));
                    /*
                    for (int i=0; i<pdc.getSottoprodotti().size();i++){
                        visProd = new VisualizzazioneSingoloProdotto();
                        VS.add(visProd);
                        System.out.println("Stampo " + i);
                        VS.get(i).StartVisualizzazioneSingoloProdotto(M,V,S,V.getNumProdotto(),true,i);
                        VS.get(i).setisProdotto(true);
                    }
                    */
                        VisualizzaProdottoComposito V3 = new VisualizzaProdottoComposito();
                        V3.StartVisualizzaProdottoComposito(M,V,S);

                    }else{
                        visProd.StartVisualizzazioneSingoloProdotto(M,V,S,V.getNumProdotto(),false,tmp);
                    }

                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                ControlloFinestre.SwitchFrameOff(V);

            }catch(Exception E){

                JOptionPane.showMessageDialog(V, "Il prodotto selezionato non esiste !");

            }



        }

        if ("Conferma1".equals(e.getActionCommand())){

            try{
                VisualizzazioneSingoloProdotto VS = new VisualizzazioneSingoloProdotto();
                try {
                    VS.StartVisualizzazioneSingoloServizio(M,V,S,V.getNumServizio());
                    VS.setisProdotto(false);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }catch(Exception E){
                JOptionPane.showMessageDialog(V, "Il prodotto selezionato non esiste !");
            }


        }

        for (int i=0; i<M.getFD().getPuntivendita().get(Integer.parseInt(S)).getProdotti().size(); i++){

            if (("Acquista " + i).equals(e.getActionCommand())){

                if(M.getFD().getSessione().getNumUtente()!=-1){
                    AggiuntaAlCarrello AGG = new AggiuntaAlCarrello();
                    try {
                        AGG.AggiungiProdottoCompositoAlCarrello(M,S,String.valueOf(i));
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }else{
                    JOptionPane.showMessageDialog(M, "Solo gli utenti possono acquistare !");
                }

            }

        }


    }


}
