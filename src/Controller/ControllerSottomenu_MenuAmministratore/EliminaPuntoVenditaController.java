package Controller.ControllerSottomenu_MenuAmministratore;

import Business.GestisciPuntiVenditaB;
import DAO.PuntiVenditaDAO;
import DAO.UtenteDAO;
import View.ControlloFinestre.ControlloFinestre;
import View.MenuAmministratore;
import View.MenuPrincipale;
import View.Sottomenu_MenuAmministratore.EliminaPuntoVendita;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class EliminaPuntoVenditaController implements ActionListener {

    private EliminaPuntoVendita E;
    private MenuAmministratore M;
    private MenuPrincipale M1;

    public EliminaPuntoVenditaController(EliminaPuntoVendita e, MenuAmministratore m, MenuPrincipale m1){
        E = e;
        M = m;
        M1 = m1;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if("Indietro".equals(e.getActionCommand())){
            ControlloFinestre.SwitchFrameOn(M);
            ControlloFinestre.CloseFrame(E);
        }

        if("Conferma".equals(e.getActionCommand())){
            GestisciPuntiVenditaB gpv = new GestisciPuntiVenditaB();
            try {
                gpv.EliminaPV(M,E.getNumeroPuntoVendita(),M1);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

            PuntiVenditaDAO PVDAO = new PuntiVenditaDAO();
            try {
                PVDAO.UpdatePuntiVendita(M.getMenuPrincipale().getFD(),M.getMenuPrincipale().getConnection());
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

            UtenteDAO UDAO = new UtenteDAO();
            try {
                UDAO.UpdateUtenti(M.getMenuPrincipale().getFD(),M.getMenuPrincipale().getConnection());
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

            ControlloFinestre.SwitchFrameOn(M);
            ControlloFinestre.CloseFrame(E);
        }

    }

}
