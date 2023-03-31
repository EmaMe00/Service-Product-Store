package Controller;

import Business.AggiungiUtenti;
import Model.FakeDatabase;
import Model.Merce.PuntiVendita;
import Model.Utenti.Utente;
import View.ControlloFinestre.ControlloFinestre;
import View.MenuPrincipale;
import View.Registrazione;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RegistrazioneController implements ActionListener {

    private MenuPrincipale M;
    private Registrazione R;

    public RegistrazioneController(Registrazione r,MenuPrincipale m){
        M=m;
        R=r;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if ("indietro".equals(e.getActionCommand())) {
            ControlloFinestre.SwitchFrameOn(M);
            ControlloFinestre.CloseFrame(R);
        }

        if("Registrati".equals(e.getActionCommand())) {

            AggiungiUtenti U = new AggiungiUtenti();
            try {
                U.aggiungiUtente(M,R);
            } catch (SQLException | NoSuchAlgorithmException ex) {
                ex.printStackTrace();
            }
            ControlloFinestre.SwitchFrameOn(M);
            ControlloFinestre.CloseFrame(R);

        }
    }
}
