package Controller.ControllerSottomenu_MenuAmministratore.ControllerSottomenu_MenuAmministratore_ModificaPuntoVenditaController.ControllerSottomenu_MenuAmministratore_ModificaPuntoVenditaController_NuovaCorsiaController;

import Business.GestioneCorsieScaffali;
import View.ControlloFinestre.ControlloFinestre;
import View.MenuAmministratore;
import View.Sottomenu_MenuAmministratore.Sottomenu_MenuAmministratore_ModificaPuntoVendita.MenuModifichePuntoVendita;
import View.Sottomenu_MenuAmministratore.Sottomenu_MenuAmministratore_ModificaPuntoVendita.Sottomenu_MenuAmministratore_ModificaPuntoVendita_Corsie.EliminaCorsia;
import View.Sottomenu_MenuAmministratore.Sottomenu_MenuAmministratore_ModificaPuntoVendita.Sottomenu_MenuAmministratore_ModificaPuntoVendita_Corsie.NuovaCorsia;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class EliminaCorsiaController implements ActionListener {

    private MenuAmministratore M;
    private NuovaCorsia N;
    private String S;
    private EliminaCorsia E;

    public EliminaCorsiaController(MenuAmministratore m, NuovaCorsia n, EliminaCorsia e, String s){
        M=m;
        N=n;
        S=s;
        E=e;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if ("Indietro".equals(e.getActionCommand())){
            ControlloFinestre.SwitchFrameOn(N);
            ControlloFinestre.CloseFrame(E);
        }

        if ("Elimina intera corsia".equals(e.getActionCommand())){
            GestioneCorsieScaffali G = new GestioneCorsieScaffali();
            try {
                G.EliminaCorsia(S,Integer.parseInt(E.getNumeroCorsia()),M);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(N, "Inserisci correttamente i dati");
            }
            ControlloFinestre.SwitchFrameOn(N);
            ControlloFinestre.CloseFrame(E);
        }

        if ("Elimina scaffale".equals(e.getActionCommand())){
            GestioneCorsieScaffali G = new GestioneCorsieScaffali();
            try {
                G.EliminaScaffale(S,Integer.parseInt(E.getNumeroScaffale()),Integer.parseInt(E.getNumeroCorsia()),M);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(N, "Inserisci correttamente i dati");
            }
            ControlloFinestre.SwitchFrameOn(N);
            ControlloFinestre.CloseFrame(E);
        }

    }

}
