package Controller.ControllerSottomenu_MenuAmministratore;

import View.ControlloFinestre.ControlloFinestre;
import View.MenuAmministratore;
import View.Sottomenu_MenuAmministratore.ModificaPuntoVendita;
import View.Sottomenu_MenuAmministratore.Sottomenu_MenuAmministratore_ModificaPuntoVendita.MenuModifichePuntoVendita;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ModificaPuntoVenditaContoller implements ActionListener {

    private ModificaPuntoVendita V;
    private MenuAmministratore M;
    private MenuModifichePuntoVendita M1;


    public ModificaPuntoVenditaContoller(ModificaPuntoVendita v,MenuAmministratore m){
        V = v;
        M = m;
        M1 = new MenuModifichePuntoVendita();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if ("Indietro".equals(e.getActionCommand())){
            ControlloFinestre.SwitchFrameOn(M);
            ControlloFinestre.CloseFrame(V);
        }

        if ("Conferma".equals(e.getActionCommand())){
            if (!V.getNumeroPuntoVendita().equals("") && Integer.parseInt(V.getNumeroPuntoVendita()) < M.getPuntivendita().size()){
                M1.StartModifichePuntoVendita(V,M,V.getNumeroPuntoVendita());
                ControlloFinestre.SwitchFrameOff(V);
            }else{
                JOptionPane.showMessageDialog(M, "Inserisci un valore valido !");
            }

        }
    }


}
