package Controller.ControllerSottomenu_MenuAmministratore.ControllerSottomenu_MenuAmministratore_GestioneManagerController;

import Business.AggiungiManagerNuovi;
import View.ControlloFinestre.ControlloFinestre;
import View.MenuAmministratore;
import View.MenuPrincipale;
import View.Sottomenu_MenuAmministratore.GestioneManager;
import View.Sottomenu_MenuAmministratore.Sottomenu_MenuAmministratore_GestioneManager.AggiungiManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public class AggiungiManagerController implements ActionListener {

    private AggiungiManager A;
    private MenuPrincipale M1;
    private MenuAmministratore M;
    private GestioneManager G;

    public AggiungiManagerController(AggiungiManager a, MenuPrincipale m1, MenuAmministratore m, GestioneManager g){
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
            AggiungiManagerNuovi Agg = new AggiungiManagerNuovi();

            try {
                if (!A.getNumeroUtente().equals("") && M1.getFD().getPuntivendita().size()>Integer.parseInt(A.getNumeroPuntoVendita())){
                    try {
                        Agg.aggiungiManager(M1,Integer.parseInt(A.getNumeroPuntoVendita()),Integer.parseInt(A.getNumeroUtente()));
                    } catch (SQLException | NoSuchAlgorithmException ex) {

                    }
                    JOptionPane.showMessageDialog(A, "Hai creato un nuovo manager!");
                }else{
                    JOptionPane.showMessageDialog(A, "Inserisci valori validi!");
                }
            }catch (Exception E){
                JOptionPane.showMessageDialog(A, "Inserisci valori validi!");
            }

            ControlloFinestre.SwitchFrameOn(M);
            ControlloFinestre.CloseFrame(A);
            ControlloFinestre.CloseFrame(G);
        }

    }

}
