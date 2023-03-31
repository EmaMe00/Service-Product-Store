package Controller.ControllerSottomenu_MenuAmministratore.ControllerSottomenu_MenuAmministratore_ModificaPuntoVenditaController.ControllerSottomenu_MenuAmministratore_ModificaPuntoVenditaController_NuovaCorsiaController;

import DAO.CorsieDAO;
import Model.Merce.Corsia;
import Model.Merce.PuntiVendita;
import Model.Merce.Scaffale;
import View.ControlloFinestre.ControlloFinestre;
import View.MenuAmministratore;
import View.Sottomenu_MenuAmministratore.Sottomenu_MenuAmministratore_ModificaPuntoVendita.MenuModifichePuntoVendita;
import View.Sottomenu_MenuAmministratore.Sottomenu_MenuAmministratore_ModificaPuntoVendita.Sottomenu_MenuAmministratore_ModificaPuntoVendita_Corsie.EliminaCorsia;
import View.Sottomenu_MenuAmministratore.Sottomenu_MenuAmministratore_ModificaPuntoVendita.Sottomenu_MenuAmministratore_ModificaPuntoVendita_Corsie.NuovaCorsia;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

public class NuovaCorsiaController implements ActionListener {

    private MenuAmministratore M;
    private NuovaCorsia N;
    private String S;
    private MenuModifichePuntoVendita M1;
    private boolean managerIdentificator;

    public NuovaCorsiaController(MenuAmministratore m, NuovaCorsia n, String s, MenuModifichePuntoVendita m1,boolean a){
        M = m;
        N = n;
        S = s;
        M1 = m1;
        managerIdentificator = a;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if("Indietro".equals(e.getActionCommand())){
            if (managerIdentificator) {
                ControlloFinestre.CloseFrame(N);
            }else{
                ControlloFinestre.SwitchFrameOn(M1);
                ControlloFinestre.CloseFrame(N);
            }
        }

        if("Nuova".equals(e.getActionCommand())){

            List<PuntiVendita> ttmp = M.getPuntivendita();
            PuntiVendita tmp = ttmp.get(Integer.parseInt(S));
            String Ncorsia = N.getCorsia();
            if (N.getCorsia().equals("")){
                Ncorsia = "CorsiaStandard"  + M.getPuntivendita().get(Integer.parseInt(S)).getCorsie().size();
            }
            Corsia corsia = new Corsia(Ncorsia);
            tmp.addCorsia(corsia);

            ttmp.set(Integer.parseInt(S),tmp);
            M.setPuntivendita(ttmp);

            CorsieDAO CDAO = new CorsieDAO();
            try {
                CDAO.UpdateCorsia(M.getMenuPrincipale().getFD(),M.getMenuPrincipale().getConnection());
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

            JOptionPane.showMessageDialog(N, "Corsia aggiunta correttamente");
            if (managerIdentificator) {
                ControlloFinestre.CloseFrame(N);
            }else{
                ControlloFinestre.SwitchFrameOn(M1);
                ControlloFinestre.CloseFrame(N);
            }


        }

        if("Elimina".equals(e.getActionCommand())){
            EliminaCorsia E = new EliminaCorsia();
            E.StartEliminaCorsia(N,S,M);
            ControlloFinestre.SwitchFrameOff(N);
        }

    }


}
