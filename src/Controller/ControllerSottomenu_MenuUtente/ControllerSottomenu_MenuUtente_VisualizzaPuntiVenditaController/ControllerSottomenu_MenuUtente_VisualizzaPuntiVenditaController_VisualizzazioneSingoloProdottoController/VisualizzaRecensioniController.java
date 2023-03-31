package Controller.ControllerSottomenu_MenuUtente.ControllerSottomenu_MenuUtente_VisualizzaPuntiVenditaController.ControllerSottomenu_MenuUtente_VisualizzaPuntiVenditaController_VisualizzazioneSingoloProdottoController;

import Business.GestioneRecensioni;
import Model.Recensioni;
import Model.FakeDatabase;
import Model.Merce.IProdotti;
import Model.Merce.Prodotti;
import Model.Merce.PuntiVendita;
import Model.Merce.Servizi;
import Model.Utenti.Manager;
import Model.Utenti.Utente;
import View.ControlloFinestre.ControlloFinestre;
import View.MenuPrincipale;
import View.Sottomenu_MenuUtente.Sottomenu_MenuUtente_VisualizzaPuntiVenditaUtente.Sottomenu_MenuUtente_VisualizzaPuntiVenditaUtente_SingoloPuntoVendita.VisualizzaRecensioni;
import View.Sottomenu_MenuUtente.Sottomenu_MenuUtente_VisualizzaPuntiVenditaUtente.Sottomenu_MenuUtente_VisualizzaPuntiVenditaUtente_SingoloPuntoVendita.VisualizzazioneSingoloProdotto;
import View.Sottomenu_MenuUtente.Sottomenu_MenuUtente_VisualizzaPuntiVenditaUtente.VisualizzazioneProdottiUtente;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class VisualizzaRecensioniController implements ActionListener {

    private MenuPrincipale M;
    private VisualizzazioneProdottiUtente V;
    private String S;
    private VisualizzazioneSingoloProdotto V1;
    private VisualizzaRecensioni V2;

    public VisualizzaRecensioniController(MenuPrincipale m, VisualizzazioneProdottiUtente v, String s, VisualizzazioneSingoloProdotto v1, VisualizzaRecensioni v2){
        M = m;
        V = v;
        S = s;
        V1 = v1;
        V2 = v2;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if ("Indietro".equals(e.getActionCommand())){
            ControlloFinestre.CloseFrame(V2);
            ControlloFinestre.SwitchFrameOn(V1);
        }

        if ("Aggiungi recensione".equals(e.getActionCommand())) {

            GestioneRecensioni G = new GestioneRecensioni();
            try {
                G.AggiungiRecensione(M,V2,S,V1);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

            ControlloFinestre.CloseFrame(V2);
            ControlloFinestre.SwitchFrameOn(V1);

        }

        if("Aggiungi voto".equals(e.getActionCommand())){

            GestioneRecensioni G = new GestioneRecensioni();
            try {
                G.AggiungiVoto(M,V2,S,V1);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

            ControlloFinestre.CloseFrame(V2);
            ControlloFinestre.SwitchFrameOn(V1);
        }

        if ("Elimina voto".equals(e.getActionCommand())){

            GestioneRecensioni G = new GestioneRecensioni();
            try {
                G.EliminaVoto(M,V2,S,V1);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

            ControlloFinestre.CloseFrame(V2);
            ControlloFinestre.SwitchFrameOn(V1);

        }

        if ("Elimina recensioni".equals(e.getActionCommand())){

            GestioneRecensioni G = new GestioneRecensioni();
            try {
                G.EliminaRecensione(M,V2,S,V1);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

            ControlloFinestre.CloseFrame(V2);
            ControlloFinestre.SwitchFrameOn(V1);

        }

    }

}



