package Controller;

import Model.FakeDatabase;
import View.ControlloFinestre.ControlloFinestre;
import View.MenuAmministratore;
import View.MenuPrincipale;
import View.MenuUtente;
import View.Sottomenu_MenuAmministratore.*;
import View.Sottomenu_MenuUtente.VisualizzaPuntiVendita;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuAmministratoreController implements ActionListener {

    private MenuAmministratore M;
    private MenuPrincipale M1;
    private MenuUtente M2 = new MenuUtente();

    public MenuAmministratoreController(MenuAmministratore m, MenuPrincipale m1){
        M = m;
        M1 = m1;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if("Indietro".equals(e.getActionCommand())){
            //Una volta che io clicco indietro (e quindi ho già apportato tutte le modifiche)
            //devo salvare il mio puntivendita (database presente solo in MenuAmministratore) nel
            //vero database che è FakeDatabase

            //Creo un oggetto db temporaneo
            FakeDatabase tmp = FakeDatabase.getInstance();
            //Copio nel temporaneo tutti i miei punti vendita
            tmp.setPuntiVendita(M.getPuntivendita());
            //Salvo tutto nel DataBase principale
            M1.SaveFD(tmp);

            ControlloFinestre.SwitchFrameOn(M1);
            ControlloFinestre.CloseFrame(M);
        }

        if("Aggiungi punto vendita".equals(e.getActionCommand())){
            AggiungiPuntoVendita A = new AggiungiPuntoVendita();
            A.StartAggiungiPuntoVendita(M);
            ControlloFinestre.SwitchFrameOff(M);
        }

        if("Visualizza punti vendita esistenti".equals(e.getActionCommand())){
            VisualizzaPuntiVendita V = new VisualizzaPuntiVendita();
            V.setadministratoIdentificator(true);
            V.StartVisualizzaPuntiVendita(M2,M1);
        }

        if ("Aggiungi o Modifica punto vendita".equals(e.getActionCommand())){
            ModificaPuntoVendita P = new ModificaPuntoVendita();
            P.StartModificaPuntoVendita(M);
            ControlloFinestre.SwitchFrameOff(M);

        }

        if ("Elimina punto vendita".equals(e.getActionCommand())){
            EliminaPuntoVendita E = new EliminaPuntoVendita();
            E.StartEliminaPuntoVendita(M,M1);
            ControlloFinestre.SwitchFrameOff(M);
        }

        if ("Manager".equals(e.getActionCommand())){
            GestioneManager G = new GestioneManager();
            G.StartGestioneManager(M,M1);
            ControlloFinestre.SwitchFrameOff(M);
        }

    }

}
