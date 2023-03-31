package Controller;

import Business.GestioneEmail;
import DAO.UtenteDAO;
import Model.Utenti.Utente;
import View.ControlloFinestre.ControlloFinestre;
import View.InvioEmail;
import View.MenuManager;
import View.MenuPrincipale;

import javax.mail.MessagingException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

public class InvioEmailController implements ActionListener {

    private MenuPrincipale M;
    private int numPV;
    private MenuManager M1;
    private InvioEmail E;


    public InvioEmailController(MenuPrincipale m, int numPuntoVendita, MenuManager m1, InvioEmail e){
        M = m;
        numPV = numPuntoVendita;
        M1 = m1;
        E = e;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if ("Indietro".equals(e.getActionCommand())){
            ControlloFinestre.SwitchFrameOn(M1);
            ControlloFinestre.CloseFrame(E);
        }

        if ("Invia".equals(e.getActionCommand())){
            try {
                String tmp = "\nManager: " + M.getFD().getListamanager().get(M.getFD().getSessione().getNumManager()).getNome() + " " + M.getFD().getListamanager().get(M.getFD().getSessione().getNumManager()).getEmail();
                if(M.getFD().getListaUtenti().size()>Integer.parseInt(E.getNumeroUtente()) && !E.getTesto().equals("") && M.getFD().getListaUtenti().get(Integer.parseInt(E.getNumeroUtente())).getNumPuntoVendita() == M.getFD().getListamanager().get(M.getFD().getSessione().getNumManager()).getnumPuntoVendita()){
                    GestioneEmail.sendMail(M.getFD().getListaUtenti().get(Integer.parseInt(E.getNumeroUtente())).getEmail(),E.getTesto() + tmp);
                    JOptionPane.showMessageDialog(M, "Email inviata correttamente");
                }else {
                    JOptionPane.showMessageDialog(M, "Non siamo riusciti ad inviare l'email controlla il numero dell'utente o il testo");
                }
                ControlloFinestre.SwitchFrameOn(M1);
                ControlloFinestre.CloseFrame(E);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(M, "Non siamo riusciti ad inviare l'email");
            }
        }

        if("Elimina".equals(e.getActionCommand())){
            List<Utente> utenti  = M.getFD().getListaUtenti();
            int numeroUtente = Integer.parseInt(E.getNumeroUtente());
            utenti.get(numeroUtente).setNumPuntoVendita(-1);
            M.getFD().setListaUtenti(utenti);
            JOptionPane.showMessageDialog(M, "Iscrizione dell'utente eliminata");
            UtenteDAO UDAO = new UtenteDAO();
            try {
                UDAO.ChangeNumPV(utenti.get(numeroUtente).getEmail(),M.getConnection(),M.getFD());
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

    }

}
