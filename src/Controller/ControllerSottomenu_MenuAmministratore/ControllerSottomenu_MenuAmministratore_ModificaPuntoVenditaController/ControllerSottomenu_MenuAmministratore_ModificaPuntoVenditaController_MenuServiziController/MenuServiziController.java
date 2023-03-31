package Controller.ControllerSottomenu_MenuAmministratore.ControllerSottomenu_MenuAmministratore_ModificaPuntoVenditaController.ControllerSottomenu_MenuAmministratore_ModificaPuntoVenditaController_MenuServiziController;

import Business.GestisciServizi;
import View.ControlloFinestre.ControlloFinestre;
import View.MenuAmministratore;
import View.Sottomenu_MenuAmministratore.Sottomenu_MenuAmministratore_ModificaPuntoVendita.MenuModifichePuntoVendita;
import View.Sottomenu_MenuAmministratore.Sottomenu_MenuAmministratore_ModificaPuntoVendita.Sottomenu_MenuAmministratore_ModificaPuntoVendita_Servizi.EliminaImmaginiServizio;
import View.Sottomenu_MenuAmministratore.Sottomenu_MenuAmministratore_ModificaPuntoVendita.Sottomenu_MenuAmministratore_ModificaPuntoVendita_Servizi.MenuServizi;
import View.Sottomenu_MenuAmministratore.Sottomenu_MenuAmministratore_ModificaPuntoVendita.Sottomenu_MenuAmministratore_ModificaPuntoVendita_Servizi.ModificaServizioSingolo;
import View.Sottomenu_MenuAmministratore.Sottomenu_MenuAmministratore_ModificaPuntoVendita.Sottomenu_MenuAmministratore_ModificaPuntoVendita_Servizi.Sottomenu_MenuAmministratore_ModificaPuntoVendita_Servizi_NuovoServizio.NuovoServizio;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;

public class MenuServiziController implements ActionListener {

    private MenuAmministratore M;
    private MenuServizi M1;
    private MenuModifichePuntoVendita M2;
    private String S;

    public MenuServiziController(MenuAmministratore m, MenuServizi m1, MenuModifichePuntoVendita m2, String s){
        M = m;
        M1 = m1;
        M2 = m2;
        S = s;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if ("Indietro".equals(e.getActionCommand())){
            if(M1.getManagerIdentificator()){
                ControlloFinestre.CloseFrame(M1);
            }else{
                ControlloFinestre.SwitchFrameOn(M2);
                ControlloFinestre.CloseFrame(M1);
            }
        }

        if("Nuovo".equals(e.getActionCommand())){
            NuovoServizio NS = new NuovoServizio();
            NS.StartNuovoServizio(M,M1,S);
            ControlloFinestre.SwitchFrameOff(M1);
        }

        if ("Modifica".equals(e.getActionCommand())){
            try{
                if (M1.getnServizo().equals("") || Integer.parseInt(M1.getnServizo())>M.getPuntivendita().get(Integer.parseInt(S)).getServizi().size()){
                    JOptionPane.showMessageDialog(M, "Inserisci i dati correttamente !");
                }else {
                    ModificaServizioSingolo MSS = new ModificaServizioSingolo();
                    MSS.StartModificaSingoloServizio(M,M1,S);
                    ControlloFinestre.SwitchFrameOff(M1);
                }
            }catch (Exception E){}

        }

        if("Elimina".equals(e.getActionCommand())){
            GestisciServizi G = new GestisciServizi(M,S);
            try {
                G.EliminaServizio(Integer.parseInt(M1.getnServizo()));
                JOptionPane.showMessageDialog(M, "Servizio eliminato correttamente");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(M, "Inserisci tutti i dati correttamente");
            }

            if(M1.getManagerIdentificator()){
                ControlloFinestre.CloseFrame(M1);
            }else{
                ControlloFinestre.SwitchFrameOn(M2);
                ControlloFinestre.CloseFrame(M1);
            }
        }

        if ("Elimina immagini".equals(e.getActionCommand())){
            EliminaImmaginiServizio E = new EliminaImmaginiServizio();
            try {
                E.StartEliminaImmaginiServizio(M,M1,S);
            } catch (Exception ex) {
                ControlloFinestre.SwitchFrameOn(M2);
                JOptionPane.showMessageDialog(M, "Inserisci tutti i dati correttamente");
            }
            ControlloFinestre.SwitchFrameOff(M1);
        }


    }


}
