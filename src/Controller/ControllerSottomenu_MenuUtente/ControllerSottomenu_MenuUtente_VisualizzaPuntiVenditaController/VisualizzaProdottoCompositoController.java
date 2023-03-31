package Controller.ControllerSottomenu_MenuUtente.ControllerSottomenu_MenuUtente_VisualizzaPuntiVenditaController;

import View.ControlloFinestre.ControlloFinestre;
import View.MenuPrincipale;
import View.Sottomenu_MenuUtente.Sottomenu_MenuUtente_VisualizzaPuntiVenditaUtente.Sottomenu_MenuUtente_VisualizzaPuntiVenditaUtente_SingoloPuntoVendita.VisualizzazioneSingoloProdotto;
import View.Sottomenu_MenuUtente.Sottomenu_MenuUtente_VisualizzaPuntiVenditaUtente.VisualizzaProdottoComposito;
import View.Sottomenu_MenuUtente.Sottomenu_MenuUtente_VisualizzaPuntiVenditaUtente.VisualizzazioneProdottiUtente;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class VisualizzaProdottoCompositoController implements ActionListener {

    private MenuPrincipale M;
    private VisualizzazioneProdottiUtente V;
    private String S;
    private VisualizzaProdottoComposito V1;

    public VisualizzaProdottoCompositoController(MenuPrincipale m, VisualizzazioneProdottiUtente v, String s, VisualizzaProdottoComposito v1){

        M=m;
        V=v;
        S=s;
        V1=v1;

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if ("Indietro".equals(e.getActionCommand())){
            ControlloFinestre.SwitchFrameOn(V);
            ControlloFinestre.CloseFrame(V1);
        }

        if ("Visualizza".equals(e.getActionCommand())){
            VisualizzazioneSingoloProdotto VS = new VisualizzazioneSingoloProdotto();
            try {
                VS.StartVisualizzazioneSingoloProdottoCP(M,V1,S,(V.getNumProdotto()),V);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

    }
}
