package Controller;

import Business.ControlloAutenticazione;
import Model.Sessione;
import View.*;
import View.ControlloFinestre.ControlloFinestre;
import View.Sottomenu_MenuUtente.VisualizzaPuntiVendita;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public class MenuPrincipaleController implements ActionListener {

    private MenuPrincipale M;
    private MenuUtente M1 = new MenuUtente();

    public MenuPrincipaleController(MenuPrincipale m){
        M = m;
    }

    @Override
    public void actionPerformed(ActionEvent e) {


        if ("login".equals(e.getActionCommand())){

            ControlloAutenticazione C = ControlloAutenticazione.getInstance();
            int a,b,c;

            a = C.ControlloAmministratore(M.getUsername(),M.getPassword());
            try {
                b = C.ControlloCredenzialiUtente(M.getUsername(),M.getPassword(),M);
            } catch (NoSuchAlgorithmException ex) {
                ex.printStackTrace();
                b=0;
            }
            try {
                c = C.ControlloCredenzialiManager(M.getUsername(),M.getPassword(),M);
            } catch (NoSuchAlgorithmException ex) {
                ex.printStackTrace();
                c=0;
            }

            if (a == 0){
                MenuAmministratore M1 = null;
                try {
                    M1 = new MenuAmministratore(M);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                } catch (InstantiationException ex) {
                    ex.printStackTrace();
                } catch (IllegalAccessException ex) {
                    ex.printStackTrace();
                }
                M1.StartMenuAmministratore(M);
                ControlloFinestre.SwitchFrameOff(M);
            }
            else if (b == 0){
                MenuUtente M2 = new MenuUtente();
                M2.StartMenuUtente(M);
                ControlloFinestre.SwitchFrameOff(M);
            }
            else
            {
                int numPuntoVendita = c;
                if (numPuntoVendita >= 0){
                    MenuManager M1 = new MenuManager();
                    try {
                        M1.StartMenuManager(M,numPuntoVendita);
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    } catch (ClassNotFoundException ex) {
                        ex.printStackTrace();
                    } catch (InstantiationException ex) {
                        ex.printStackTrace();
                    } catch (IllegalAccessException ex) {
                        ex.printStackTrace();
                    }
                    ControlloFinestre.SwitchFrameOff(M);
                }
            }

            if(a==1&b==1&c==-1){
                JOptionPane.showMessageDialog(M, "Credenziali errate !");
            }

        }

        if("Registrati".equals(e.getActionCommand())){
            Registrazione R = new Registrazione();
            R.StartRegistrazione(M);
            ControlloFinestre.SwitchFrameOff(M);
        }

        if("Guest".equals(e.getActionCommand())){
            VisualizzaPuntiVendita V = new VisualizzaPuntiVendita();
            V.StartVisualizzaPuntiVendita(M1,M);
            V.setGuestIdentificator(1);
            M.getFD().resetSessione();
        }

        /*
        if("Carica".equals(e.getActionCommand())){
            try {
                M.CaricaDaFile();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            } catch (ClassNotFoundException classNotFoundException) {
                classNotFoundException.printStackTrace();
            }
        }

        if("Salva".equals(e.getActionCommand())){
            try {
                M.SalvaSuFile();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
        */


    }

}
