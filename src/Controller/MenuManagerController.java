package Controller;

import Model.FakeDatabase;
import Model.Merce.PuntiVendita;
import View.*;
import View.ControlloFinestre.ControlloFinestre;
import View.ControlloFinestre.IFinestre;
import View.Sottomenu_MenuAmministratore.Sottomenu_MenuAmministratore_ModificaPuntoVendita.MenuModifichePuntoVendita;
import View.Sottomenu_MenuAmministratore.Sottomenu_MenuAmministratore_ModificaPuntoVendita.Sottomenu_MenuAmministratore_ModificaPuntoVendita_Categorie.NuovaCategoria;
import View.Sottomenu_MenuAmministratore.Sottomenu_MenuAmministratore_ModificaPuntoVendita.Sottomenu_MenuAmministratore_ModificaPuntoVendita_Corsie.NuovaCorsia;
import View.Sottomenu_MenuAmministratore.Sottomenu_MenuAmministratore_ModificaPuntoVendita.Sottomenu_MenuAmministratore_ModificaPuntoVendita_Prodotti.ModificheProdotti;
import View.Sottomenu_MenuAmministratore.Sottomenu_MenuAmministratore_ModificaPuntoVendita.Sottomenu_MenuAmministratore_ModificaPuntoVendita_Servizi.MenuServizi;
import View.Sottomenu_MenuAmministratore.Sottomenu_MenuAmministratore_ModificaPuntoVendita.Sottomenu_MenuAmministratore_ModificaPuntoVendita_Servizi.Sottomenu_MenuAmministratore_ModificaPuntoVendita_Servizi_NuovoServizio.NuovoServizio;
import View.Sottomenu_MenuUtente.VisualizzaPuntiVendita;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

public class MenuManagerController implements ActionListener {

    private MenuManager M1;
    private MenuPrincipale M;
    private int numPuntoVendita;
    private MenuModifichePuntoVendita M2;
    private MenuAmministratore M3;
    private MenuUtente M4 = new MenuUtente();


    public MenuManagerController(MenuManager m1, MenuPrincipale m, int numPuntoVendita) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        M1 = m1;
        M = m;
        this.numPuntoVendita = numPuntoVendita;
        M2 = new MenuModifichePuntoVendita();
        M3 = new MenuAmministratore(M);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if ("Indietro".equals(e.getActionCommand())){

            //Resetto la sessione del manager
            FakeDatabase DB = M.getFD();
            DB.resetSessione();
            M.SaveFD(DB);

            ControlloFinestre.SwitchFrameOn(M);
            ControlloFinestre.CloseFrame(M1);
        }

        if ("Email".equals(e.getActionCommand())){
            InvioEmail I = new InvioEmail();
            I.StartInvioEmail(M,numPuntoVendita,M1);
            ControlloFinestre.SwitchFrameOff(M1);
        }

        if ("Nome".equals(e.getActionCommand())){
            String s = (String) JOptionPane.showInputDialog(M1,"Inserisci il nuovo nome","Rinomina",JOptionPane.PLAIN_MESSAGE, null,null,"NuovoNome");
            List<PuntiVendita> puntivendita = M.getFD().getPuntivendita();
            PuntiVendita puntovendita = puntivendita.get(numPuntoVendita);
            puntovendita.setNome(s);
            puntivendita.set(numPuntoVendita,puntovendita);
            FakeDatabase DB = M.getFD();
            DB.setPuntiVendita(puntivendita);
            M.SaveFD(DB);
            JOptionPane.showMessageDialog(M1, "Punto vendita rinominato correttamente!");
        }

        if ("Prodotti".equals(e.getActionCommand())){
            ModificheProdotti modProd = new ModificheProdotti();
            modProd.setManagerIdentificator(true);
            modProd.StartModificheProdotti(M3,M2,String.valueOf(numPuntoVendita));
        }

        if("Categorie".equals(e.getActionCommand())){
            NuovaCategoria nuovaCat = new NuovaCategoria();
            nuovaCat.setManagerIdentificator(true);
            nuovaCat.StartNuovaCategoria(M3,M2,String.valueOf(numPuntoVendita));
        }

        if("Corsie".equals(e.getActionCommand())){
            NuovaCorsia nuovaCors = new NuovaCorsia();
            nuovaCors.setManagerIdentificator(true);
            nuovaCors.StartNuovaCorsia(M3,M2,String.valueOf(numPuntoVendita));
        }

        if("Servizi".equals(e.getActionCommand())){
            MenuServizi MS = new MenuServizi();
            MS.setManagerIdentificator(true);
            MS.StartMenuServizi(M3,M2,String.valueOf(numPuntoVendita));
        }

        if("Visualizza".equals(e.getActionCommand())){
            VisualizzaPuntiVendita V = new VisualizzaPuntiVendita();
            V.setManagerIdentificator(true);
            V.StartVisualizzaPuntiVendita(M4,M);
        }


    }

}
