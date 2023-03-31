package Controller.ControllerSottomenu_MenuAmministratore;

import View.ControlloFinestre.ControlloFinestre;
import View.MenuAmministratore;
import View.MenuPrincipale;
import View.Sottomenu_MenuAmministratore.GestioneManager;
import View.Sottomenu_MenuAmministratore.Sottomenu_MenuAmministratore_GestioneManager.AggiungiManager;
import View.Sottomenu_MenuAmministratore.Sottomenu_MenuAmministratore_GestioneManager.RimuoviManager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ConcurrentModificationException;

public class GestioneManagerController implements ActionListener {

    private MenuAmministratore M;
    private GestioneManager G;
    private MenuPrincipale M1;

    public GestioneManagerController(MenuAmministratore m, GestioneManager g, MenuPrincipale m1){
        M = m;
        G = g;
        M1 = m1;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if("Indietro".equals(e.getActionCommand())){
            ControlloFinestre.SwitchFrameOn(M);
            ControlloFinestre.CloseFrame(G);
        }

        if("Aggiungi".equals(e.getActionCommand())){
            AggiungiManager A = new AggiungiManager();
            A.StartAggiungiManager(G,M,M1);
            ControlloFinestre.SwitchFrameOff(G);
        }

        if("Rimuovi".equals(e.getActionCommand())){
            RimuoviManager A = new RimuoviManager();
            A.StartRimuoviManager(G,M,M1);
            ControlloFinestre.SwitchFrameOff(G);
        }

    }

}
