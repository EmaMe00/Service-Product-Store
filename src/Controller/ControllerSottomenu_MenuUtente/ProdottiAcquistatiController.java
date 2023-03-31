package Controller.ControllerSottomenu_MenuUtente;

import View.ControlloFinestre.ControlloFinestre;
import View.MenuPrincipale;
import View.Sottomenu_MenuUtente.ProdottiAcquistati;
import View.Sottomenu_MenuUtente.VisualizzaCarrelloUtente;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProdottiAcquistatiController implements ActionListener {

    private MenuPrincipale M;
    private VisualizzaCarrelloUtente V;
    private ProdottiAcquistati P;

    public ProdottiAcquistatiController(MenuPrincipale m, VisualizzaCarrelloUtente v, ProdottiAcquistati p){
        M=m;
        V=v;
        P=p;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if ("Indietro".equals(e.getActionCommand())){
            ControlloFinestre.SwitchFrameOn(V);
            ControlloFinestre.CloseFrame(P);
        }

    }

}
