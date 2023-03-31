package Controller.ControllerSottomenu_MenuUtente;


import View.ControlloFinestre.ControlloFinestre;
import View.MenuPrincipale;
import View.MenuUtente;
import View.Sottomenu_MenuUtente.Sottomenu_MenuUtente_VisualizzaPuntiVenditaUtente.VisualizzazioneProdottiUtente;
import View.Sottomenu_MenuUtente.VisualizzaPuntiVendita;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class VisualizzaPuntiVenditaController implements ActionListener {

    private VisualizzaPuntiVendita V;
    private MenuPrincipale M1;
    private MenuUtente M;
    private boolean administratorIdentificator;
    private boolean managerIdentificator;

    public VisualizzaPuntiVenditaController(VisualizzaPuntiVendita v, MenuUtente m, MenuPrincipale m1, boolean a, boolean b){
        V=v;
        M1=m1;
        M=m;
        administratorIdentificator = a;
        managerIdentificator = b;
    }

    @Override
    public void actionPerformed(ActionEvent e) {


        if ("Indietro".equals(e.getActionCommand())){
            if (administratorIdentificator || managerIdentificator){
                ControlloFinestre.CloseFrame(V);
            }else {
                if (V.getGuestIdentificator()==1){
                    ControlloFinestre.CloseFrame(V);
                }
                else {
                    ControlloFinestre.SwitchFrameOn(M);
                    ControlloFinestre.CloseFrame(V);
                }

            }

        }

        if ("Avanti".equals(e.getActionCommand())){
            try {

                VisualizzazioneProdottiUtente V1 = new VisualizzazioneProdottiUtente();
                try {
                    V1.StartVisualizzazionePuntiVenditaUtente(M1,V,V.getPuntoVendita());
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                ControlloFinestre.SwitchFrameOff(V);


            }catch (Exception E){

                JOptionPane.showMessageDialog(V, "Ciò che hai selezionato non esiste");

            }


        }

    }
}
