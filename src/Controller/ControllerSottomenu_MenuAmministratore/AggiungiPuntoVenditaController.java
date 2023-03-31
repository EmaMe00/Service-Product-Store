package Controller.ControllerSottomenu_MenuAmministratore;

import DAO.*;
import Model.Merce.Corsia;
import Model.Merce.PuntiVendita;
import View.ControlloFinestre.ControlloFinestre;
import View.MenuAmministratore;
import View.Sottomenu_MenuAmministratore.AggiungiPuntoVendita;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AggiungiPuntoVenditaController implements ActionListener {

    private AggiungiPuntoVendita A;
    private MenuAmministratore M;

    public AggiungiPuntoVenditaController(AggiungiPuntoVendita a,MenuAmministratore m){
        A = a;
        M = m;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if("Indietro".equals(e.getActionCommand())){
            ControlloFinestre.SwitchFrameOn(M);
            ControlloFinestre.CloseFrame(A);
        }

        if("Conferma".equals(e.getActionCommand())){

            if(A.getNomePuntoVendita().equals("")){
                JOptionPane.showMessageDialog(A, "Scrivi un nome adatto al tuo punto vendita !");
            }
            else{

                List<PuntiVendita> puntivendita = new ArrayList<PuntiVendita>();
                PuntiVendita tmp = new PuntiVendita(A.getNomePuntoVendita());
                puntivendita.add(tmp);
                M.getMenuPrincipale().getFD().addPuntiVendita(puntivendita);
                ControlloFinestre.SwitchFrameOn(M);
                JOptionPane.showMessageDialog(A, "Punto vendita aggiunto correttamente!");
                ControlloFinestre.CloseFrame(A);

                PuntiVenditaDAO PVDAO = new PuntiVenditaDAO();
                try {
                    PVDAO.addPuntoVendita(M.getMenuPrincipale().getFD(),M.getMenuPrincipale().getConnection(),tmp,M.getMenuPrincipale().getFD().getPuntivendita().size()-1);
                    PVDAO.UpdatePuntiVendita(M.getMenuPrincipale().getFD(),M.getMenuPrincipale().getConnection());

                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

                for (int i=0; i<M.getPuntivendita().size();i++){
                    System.out.println(i+") " + M.getPuntivendita().get(i).getNome());
                }

            }


        }
    }
}
