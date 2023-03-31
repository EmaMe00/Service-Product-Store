package Controller.ControllerSottomenu_MenuAmministratore.ControllerSottomenu_MenuAmministratore_GestioneManagerController;

import Business.GestisciManagerNuovi;
import View.ControlloFinestre.ControlloFinestre;
import View.MenuAmministratore;
import View.MenuPrincipale;
import View.Sottomenu_MenuAmministratore.GestioneManager;
import View.Sottomenu_MenuAmministratore.Sottomenu_MenuAmministratore_GestioneManager.RimuoviManager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class RimuoviManagerController implements ActionListener {

    private RimuoviManager A;
    private MenuPrincipale M1;
    private MenuAmministratore M;
    private GestioneManager G;

    public RimuoviManagerController(RimuoviManager a, MenuPrincipale m1, MenuAmministratore m, GestioneManager g) {
        A = a;
        M1 = m1;
        M = m;
        G = g;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if("Indietro".equals(e.getActionCommand())){
            ControlloFinestre.SwitchFrameOn(G);
            ControlloFinestre.CloseFrame(A);
        }

        if("Conferma".equals(e.getActionCommand())){
            GestisciManagerNuovi Gmn = new GestisciManagerNuovi();
            try {
                Gmn.RimuoviManager(M1,A.getNumeroManager());
            } catch (SQLException ex) {
                ex.printStackTrace();
            }


            ControlloFinestre.SwitchFrameOn(M);
            ControlloFinestre.CloseFrame(A);
            ControlloFinestre.CloseFrame(G);
        }

    }
}
