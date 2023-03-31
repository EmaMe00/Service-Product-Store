package Controller;

import Model.FakeDatabase;

import View.ControlloFinestre.ControlloFinestre;
import View.MenuPrincipale;
import View.MenuUtente;
import View.Sottomenu_MenuUtente.VisualizzaPuntiVendita;
import View.Sottomenu_MenuUtente.VisualizzaCarrelloUtente;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class MenuUtenteController implements ActionListener {
    private MenuUtente M;
    private MenuPrincipale M1;

    public MenuUtenteController(MenuUtente m, MenuPrincipale m1){
        M = m;
        M1 = m1;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if ("Indietro".equals(e.getActionCommand())) {
            //Una volta che io clicco indietro (e quindi ho già apportato tutte le modifiche)
            //devo salvare il mio puntivendita (database presente solo in MenuAmministratore) nel
            //vero database che è FakeDatabase

            //Creo un oggetto db temporaneo
            FakeDatabase tmp = FakeDatabase.getInstance();
            tmp.resetSessione();
            //Copio nel temporaneo tutti i miei punti vendita
            tmp.setPuntiVendita(M1.getFD().getPuntivendita());
            //Salvo tutto nel DataBase principale
            M1.SaveFD(tmp);

            ControlloFinestre.SwitchFrameOn(M1);
            ControlloFinestre.CloseFrame(M);
        }

        if("Visualizza Punti Vendita".equals(e.getActionCommand())){
            VisualizzaPuntiVendita V = new VisualizzaPuntiVendita();
            V.StartVisualizzaPuntiVendita(M,M1);
            ControlloFinestre.SwitchFrameOff(M);
        }

        if("Visualizza Carrello".equals(e.getActionCommand())){
            VisualizzaCarrelloUtente S = new VisualizzaCarrelloUtente();
            try {
                S.StartVisualizzaCarrelloUtente(M,M1);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            ControlloFinestre.SwitchFrameOff(M);
        }
    }
}
