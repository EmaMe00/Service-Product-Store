package Controller.ControllerSottomenu_MenuUtente;

import Business.CreazionePDF;
import Business.GestioneCarrello;
import Business.GestioneEmail;
import Business.GestisciServizi;
import Model.FakeDatabase;
import Model.Merce.IProdotti;
import Model.Merce.Prodotti;
import Model.Merce.Servizi;
import Model.Utenti.Utente;
import View.ControlloFinestre.ControlloFinestre;
import View.MenuPrincipale;
import View.MenuUtente;
import View.Sottomenu_MenuUtente.ProdottiAcquistati;
import View.Sottomenu_MenuUtente.VisualizzaCarrelloUtente;

import javax.mail.MessagingException;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class VisualizzaCarrelloUtenteController implements ActionListener {

    private MenuUtente M;
    private MenuPrincipale M1;
    private VisualizzaCarrelloUtente V;

    public VisualizzaCarrelloUtenteController(MenuUtente m, MenuPrincipale m1, VisualizzaCarrelloUtente v){
        M = m;
        M1 = m1;
        V = v;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if("Indietro".equals(e.getActionCommand())){
            ControlloFinestre.SwitchFrameOn(M);
            ControlloFinestre.CloseFrame(V);
        }

        if("Elimina Prodotto".equals(e.getActionCommand())){
            GestioneCarrello G = new GestioneCarrello();
            try {
                G.RimuoviProdottoDalCarrello(M1,V);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            ControlloFinestre.SwitchFrameOn(M);
            ControlloFinestre.CloseFrame(V);
        }

        if("Elimina Servizio".equals(e.getActionCommand())){
            GestioneCarrello G = new GestioneCarrello();
            try {
                G.RimuoviServizioDalCarrello(M1,V);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            ControlloFinestre.SwitchFrameOn(M);
            ControlloFinestre.CloseFrame(V);
        }

        if ("Elimina Tutto".equals(e.getActionCommand())){
            GestioneCarrello G = new GestioneCarrello();
            try {
                G.EliminaTutto(M1,V);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            JOptionPane.showMessageDialog(M, "Eliminato tutto dal carrello");
            ControlloFinestre.SwitchFrameOn(M);
            ControlloFinestre.CloseFrame(V);
        }

        if ("Genera PDF".equals(e.getActionCommand())){
            CreazionePDF C = new CreazionePDF(M1);
            try {
                C.CreaPDF();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(M, "Non siamo riusciti a generare il pdf");
            }
        }

        if("Acquista tutto".equals(e.getActionCommand())){

            GestioneCarrello G = new GestioneCarrello();
            try {
                G.AcquistaTutto(M1,V);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

            ControlloFinestre.SwitchFrameOn(M);
            ControlloFinestre.CloseFrame(V);
        }

        if ("Acquista Prodotto".equals(e.getActionCommand())){

            GestioneCarrello G = new GestioneCarrello();
            try {
                G.AcquistaProdotto(M1,V);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }


            ControlloFinestre.SwitchFrameOn(M);
            ControlloFinestre.CloseFrame(V);
        }

        if ("Acquista Servizio".equals(e.getActionCommand())){

            GestioneCarrello G = new GestioneCarrello();
            try {
                G.AcquistaServizio(M1,V);

            } catch (SQLException ex) {
                ex.printStackTrace();
            }

            ControlloFinestre.SwitchFrameOn(M);
            ControlloFinestre.CloseFrame(V);
        }

        if ("Acquistati".equals(e.getActionCommand())){
            ProdottiAcquistati A = new ProdottiAcquistati();
            A.StartProdottiAcquistati(M1,V);
            ControlloFinestre.SwitchFrameOff(V);
        }

    }


}
