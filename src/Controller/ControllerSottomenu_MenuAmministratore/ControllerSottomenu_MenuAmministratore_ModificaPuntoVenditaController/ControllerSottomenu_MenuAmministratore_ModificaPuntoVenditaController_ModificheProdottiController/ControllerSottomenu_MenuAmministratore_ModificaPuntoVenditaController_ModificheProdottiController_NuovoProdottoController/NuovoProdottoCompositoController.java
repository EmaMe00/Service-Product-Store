package Controller.ControllerSottomenu_MenuAmministratore.ControllerSottomenu_MenuAmministratore_ModificaPuntoVenditaController.ControllerSottomenu_MenuAmministratore_ModificaPuntoVenditaController_ModificheProdottiController.ControllerSottomenu_MenuAmministratore_ModificaPuntoVenditaController_ModificheProdottiController_NuovoProdottoController;

import Business.GestisciProdotti;
import View.ControlloFinestre.ControlloFinestre;
import View.MenuAmministratore;
import View.Sottomenu_MenuAmministratore.Sottomenu_MenuAmministratore_ModificaPuntoVendita.Sottomenu_MenuAmministratore_ModificaPuntoVendita_Prodotti.ModificheProdotti;
import View.Sottomenu_MenuAmministratore.Sottomenu_MenuAmministratore_ModificaPuntoVendita.Sottomenu_MenuAmministratore_ModificaPuntoVendita_Prodotti.Sottomenu_MenuAmministratore_ModificaPuntoVendita_Prodotti_NUOVO.NuovoProdottoComposito;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class NuovoProdottoCompositoController implements ActionListener {

    private MenuAmministratore M;
    private ModificheProdotti M2;
    private NuovoProdottoComposito N;
    private String S;

    public NuovoProdottoCompositoController(MenuAmministratore M, ModificheProdotti M2, NuovoProdottoComposito N, String S){
        this.M = M;
        this.M2 = M2;
        this.N = N;
        this.S = S;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if ("Indietro".equals(e.getActionCommand())){
            ControlloFinestre.SwitchFrameOn(M2);
            ControlloFinestre.CloseFrame(N);
        }

        if ("Conferma".equals(e.getActionCommand())){
            GestisciProdotti Agg = new GestisciProdotti(M,N,S);
            try {
                Agg.AggiungiNuovoProdottoComposito();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

            ControlloFinestre.SwitchFrameOn(M2);
            ControlloFinestre.CloseFrame(N);
        }

    }

}
