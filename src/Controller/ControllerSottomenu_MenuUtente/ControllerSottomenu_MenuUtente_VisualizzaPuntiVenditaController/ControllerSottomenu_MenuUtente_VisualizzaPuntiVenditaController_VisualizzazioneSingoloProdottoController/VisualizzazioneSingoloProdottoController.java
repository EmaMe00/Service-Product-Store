package Controller.ControllerSottomenu_MenuUtente.ControllerSottomenu_MenuUtente_VisualizzaPuntiVenditaController.ControllerSottomenu_MenuUtente_VisualizzaPuntiVenditaController_VisualizzazioneSingoloProdottoController;

import Business.AggiuntaAlCarrello;
import View.ControlloFinestre.ControlloFinestre;
import View.MenuPrincipale;
import View.Sottomenu_MenuUtente.Sottomenu_MenuUtente_VisualizzaPuntiVenditaUtente.Sottomenu_MenuUtente_VisualizzaPuntiVenditaUtente_SingoloPuntoVendita.VisualizzaRecensioni;
import View.Sottomenu_MenuUtente.Sottomenu_MenuUtente_VisualizzaPuntiVenditaUtente.Sottomenu_MenuUtente_VisualizzaPuntiVenditaUtente_SingoloPuntoVendita.VisualizzazioneSingoloProdotto;
import View.Sottomenu_MenuUtente.Sottomenu_MenuUtente_VisualizzaPuntiVenditaUtente.VisualizzaProdottoComposito;
import View.Sottomenu_MenuUtente.Sottomenu_MenuUtente_VisualizzaPuntiVenditaUtente.VisualizzazioneProdottiUtente;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class VisualizzazioneSingoloProdottoController implements ActionListener {

    private MenuPrincipale M;
    private VisualizzazioneSingoloProdotto V;
    private VisualizzazioneProdottiUtente V1;
    private String S;
    private String numProdotto;
    private int Serv = 0 ;
    private VisualizzaProdottoComposito V2;
    private int where;

    public VisualizzazioneSingoloProdottoController(MenuPrincipale m, VisualizzazioneSingoloProdotto v, VisualizzazioneProdottiUtente v1, String s, String numSingoloProdotto, int serv){
        M=m;
        V=v;
        V1=v1;
        S=s;
        numProdotto = numSingoloProdotto;
        Serv = serv;
        V2 = new VisualizzaProdottoComposito();
        where = 0;
    }

    public VisualizzazioneSingoloProdottoController(MenuPrincipale m, VisualizzazioneSingoloProdotto v, VisualizzaProdottoComposito v1, String s, String numSingoloProdotto, int serv,VisualizzazioneProdottiUtente v2){
        M=m;
        V=v;
        V2=v1;
        S=s;
        numProdotto = numSingoloProdotto;
        Serv = serv;
        V1 = v2;
        where = 1;
        V.setisProdotto(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if("Indietro".equals(e.getActionCommand())){

            if (where==0){
                ControlloFinestre.SwitchFrameOn(V1);
                ControlloFinestre.CloseFrame(V);
            }else {
                ControlloFinestre.CloseFrame(V);
            }

        }

        //M.getFD().getPuntivendita().get(Integer.parseInt(S)).getProdotti().get(Integer.parseInt(numProdotto)).getPC()
        //JOptionPane.showMessageDialog(V, "Devi acquistare tutto il prodotto composito !!");
        if("Acquista".equals(e.getActionCommand())){

            if (M.getFD().getSessione().getNumUtente() == -1){
                JOptionPane.showMessageDialog(V, "Solo gli utenti possono acquistare");
            } else {
                if (Serv == -1){
                    AggiuntaAlCarrello AGG = new AggiuntaAlCarrello();
                    try {
                        AGG.AggiungiServizioAlCarrello(M,S,numProdotto);
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }else {
                    if (M.getFD().getPuntivendita().get(Integer.parseInt(S)).getProdotti().get(Integer.parseInt(numProdotto)).getPC()){
                        JOptionPane.showMessageDialog(V, "Devi acquistare tutto il prodotto composito !!");
                    } else {
                        AggiuntaAlCarrello AGG = new AggiuntaAlCarrello();
                        try {
                            AGG.AggiungiProdottoAlCarrello(M,S,numProdotto);
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                    }

                }


                ControlloFinestre.CloseFrame(V2);
                ControlloFinestre.SwitchFrameOn(V1);
                ControlloFinestre.CloseFrame(V);
            }

        }

        if ("Recensioni".equals(e.getActionCommand())){
            if (M.getFD().getPuntivendita().get(Integer.parseInt(S)).getProdotti().get(Integer.parseInt(numProdotto)).getPC()){
                JOptionPane.showMessageDialog(V, "Recensioni disponibili nei singli prodotti");
            }else {
                VisualizzaRecensioni R = new VisualizzaRecensioni();
                R.StartVisualizzaRecensioni(M,V1,S,V);
                ControlloFinestre.SwitchFrameOff(V);
            }
        }

    }
}
