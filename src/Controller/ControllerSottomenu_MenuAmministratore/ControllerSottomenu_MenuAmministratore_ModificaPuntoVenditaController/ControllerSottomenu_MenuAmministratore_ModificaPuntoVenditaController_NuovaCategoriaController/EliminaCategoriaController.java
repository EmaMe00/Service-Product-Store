package Controller.ControllerSottomenu_MenuAmministratore.ControllerSottomenu_MenuAmministratore_ModificaPuntoVenditaController.ControllerSottomenu_MenuAmministratore_ModificaPuntoVenditaController_NuovaCategoriaController;

import Business.GestioneCatSottoCat;
import Controller.ControllerSottomenu_MenuAmministratore.ControllerSottomenu_MenuAmministratore_ModificaPuntoVenditaController.ControllerSottomenu_MenuAmministratore_ModificaPuntoVenditaController_NuovaCorsiaController.EliminaCorsiaController;
import View.ControlloFinestre.ControlloFinestre;
import View.MenuAmministratore;
import View.Sottomenu_MenuAmministratore.Sottomenu_MenuAmministratore_ModificaPuntoVendita.Sottomenu_MenuAmministratore_ModificaPuntoVendita_Categorie.EliminaCategoria;
import View.Sottomenu_MenuAmministratore.Sottomenu_MenuAmministratore_ModificaPuntoVendita.Sottomenu_MenuAmministratore_ModificaPuntoVendita_Categorie.NuovaCategoria;
import View.Sottomenu_MenuAmministratore.Sottomenu_MenuAmministratore_ModificaPuntoVendita.Sottomenu_MenuAmministratore_ModificaPuntoVendita_Corsie.EliminaCorsia;
import View.Sottomenu_MenuAmministratore.Sottomenu_MenuAmministratore_ModificaPuntoVendita.Sottomenu_MenuAmministratore_ModificaPuntoVendita_Corsie.NuovaCorsia;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class EliminaCategoriaController implements ActionListener {

    private MenuAmministratore M;
    private NuovaCategoria N;
    private String S;
    private EliminaCategoria E;

    public EliminaCategoriaController(MenuAmministratore m, NuovaCategoria n, String s, EliminaCategoria e){
        M=m;
        N=n;
        S=s;
        E=e;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if ("Indietro".equals(e.getActionCommand())){
            ControlloFinestre.SwitchFrameOn(N);
            ControlloFinestre.CloseFrame(E);
        }

        if ("Elimina categoria".equals(e.getActionCommand())){
            GestioneCatSottoCat G = new GestioneCatSottoCat();

                try {
                    int numCat = Integer.parseInt(E.getNumeroCat());
                    G.EliminaCategoria(M,S,numCat);
                }catch (Exception A){
                    JOptionPane.showMessageDialog(M, "Inserisci tutti i dati correttamente");
                }


            ControlloFinestre.SwitchFrameOn(N);
            ControlloFinestre.CloseFrame(E);
        }

    }


}
