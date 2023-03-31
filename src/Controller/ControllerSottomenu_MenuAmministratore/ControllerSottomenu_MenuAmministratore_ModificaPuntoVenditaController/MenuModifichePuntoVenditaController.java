package Controller.ControllerSottomenu_MenuAmministratore.ControllerSottomenu_MenuAmministratore_ModificaPuntoVenditaController;

import Model.Merce.PuntiVendita;
import View.ControlloFinestre.ControlloFinestre;
import View.MenuAmministratore;
import View.Sottomenu_MenuAmministratore.ModificaPuntoVendita;
import View.Sottomenu_MenuAmministratore.Sottomenu_MenuAmministratore_ModificaPuntoVendita.MenuModifichePuntoVendita;
import View.Sottomenu_MenuAmministratore.Sottomenu_MenuAmministratore_ModificaPuntoVendita.Sottomenu_MenuAmministratore_ModificaPuntoVendita_Categorie.NuovaCategoria;
import View.Sottomenu_MenuAmministratore.Sottomenu_MenuAmministratore_ModificaPuntoVendita.Sottomenu_MenuAmministratore_ModificaPuntoVendita_Corsie.NuovaCorsia;
import View.Sottomenu_MenuAmministratore.Sottomenu_MenuAmministratore_ModificaPuntoVendita.Sottomenu_MenuAmministratore_ModificaPuntoVendita_Prodotti.ModificheProdotti;
import View.Sottomenu_MenuAmministratore.Sottomenu_MenuAmministratore_ModificaPuntoVendita.Sottomenu_MenuAmministratore_ModificaPuntoVendita_Servizi.MenuServizi;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class MenuModifichePuntoVenditaController implements ActionListener {

    private MenuModifichePuntoVendita M;
    private ModificaPuntoVendita A;
    private MenuAmministratore A1;
    private String S;

    public MenuModifichePuntoVenditaController(MenuModifichePuntoVendita m, ModificaPuntoVendita a, MenuAmministratore a1, String s){
        M = m;
        A = a;
        A1 = a1;
        S = s;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        List<PuntiVendita> puntivendita = new ArrayList<>(A1.getPuntivendita());
        PuntiVendita puntovendita = puntivendita.get(Integer.parseInt(S));

        if("Indietro".equals(e.getActionCommand())){
            ControlloFinestre.SwitchFrameOn(A1);
            ControlloFinestre.CloseFrame(A);
            ControlloFinestre.CloseFrame(M);
        }

        if("Nome".equals(e.getActionCommand())) {
            String s = (String) JOptionPane.showInputDialog(M,"Inserisci il nuovo nome","Rinomina",JOptionPane.PLAIN_MESSAGE, null,null,"NuovoNome");
            puntovendita.setNome(s);
            puntivendita.set(Integer.parseInt(S),puntovendita);
            A1.setPuntivendita(puntivendita);
        }

        if("Prodotti".equals(e.getActionCommand())){
            ModificheProdotti D = new ModificheProdotti();
            D.StartModificheProdotti(A1,M,S);
            ControlloFinestre.SwitchFrameOff(M);
        }

        if("Categorie".equals(e.getActionCommand())){
            NuovaCategoria A = new NuovaCategoria();
            A.StartNuovaCategoria(A1,M,S);
            ControlloFinestre.SwitchFrameOff(M);
        }

        if("Corsie".equals(e.getActionCommand())){
            NuovaCorsia N = new NuovaCorsia();
            N.StartNuovaCorsia(A1,M,S);
            ControlloFinestre.SwitchFrameOff(M);
        }

        if ("Servizi".equals(e.getActionCommand())){
            MenuServizi Serv = new MenuServizi();
            Serv.StartMenuServizi(A1,M,S);
            ControlloFinestre.SwitchFrameOff(M);
        }



    }
}
