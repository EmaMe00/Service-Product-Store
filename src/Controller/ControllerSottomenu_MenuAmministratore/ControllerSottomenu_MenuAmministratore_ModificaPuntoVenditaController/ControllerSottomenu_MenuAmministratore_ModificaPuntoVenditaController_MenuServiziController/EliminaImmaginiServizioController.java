package Controller.ControllerSottomenu_MenuAmministratore.ControllerSottomenu_MenuAmministratore_ModificaPuntoVenditaController.ControllerSottomenu_MenuAmministratore_ModificaPuntoVenditaController_MenuServiziController;

import Model.Merce.PuntiVendita;
import Model.Merce.Servizi;
import View.ControlloFinestre.ControlloFinestre;
import View.MenuAmministratore;
import View.Sottomenu_MenuAmministratore.Sottomenu_MenuAmministratore_ModificaPuntoVendita.Sottomenu_MenuAmministratore_ModificaPuntoVendita_Servizi.EliminaImmaginiServizio;
import View.Sottomenu_MenuAmministratore.Sottomenu_MenuAmministratore_ModificaPuntoVendita.Sottomenu_MenuAmministratore_ModificaPuntoVendita_Servizi.MenuServizi;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class EliminaImmaginiServizioController implements ActionListener {

    private MenuAmministratore M;
    private MenuServizi M2;
    private String S;
    private EliminaImmaginiServizio E;

    public EliminaImmaginiServizioController(MenuAmministratore m, MenuServizi m2, String s, EliminaImmaginiServizio e){
        M=m;
        M2=m2;
        S=s;
        E=e;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if("Indietro".equals(e.getActionCommand())){
            ControlloFinestre.SwitchFrameOn(M2);
            ControlloFinestre.CloseFrame(E);
        }

        if("Elimina".equals(e.getActionCommand())){
            try{
                List<PuntiVendita> puntiVendita = M.getPuntivendita();
                PuntiVendita puntoVendita = puntiVendita.get(Integer.parseInt(S));
                List<Servizi> servizi = puntoVendita.getServizi();
                Servizi servizio = servizi.get(Integer.parseInt(M2.getnServizo()));
                ArrayList<Path> paths = servizio.getPathIMG();
                if (paths.size()<2){
                    JOptionPane.showMessageDialog(M, "Non puoi eliminare l'unica immagine presente");
                }else {
                    paths.remove(Integer.parseInt(E.getTextField()));
                    servizio.setPathIMG(paths);
                    servizi.set(Integer.parseInt(M2.getnServizo()),servizio);
                    puntoVendita.setServizi(servizi);
                    puntiVendita.set(Integer.parseInt(S),puntoVendita);
                    M.setPuntivendita(puntiVendita);
                    JOptionPane.showMessageDialog(M, "Immagini eliminata correttamente");
                }

                ControlloFinestre.SwitchFrameOn(M2);
                ControlloFinestre.CloseFrame(E);
            }catch (Exception E){
                JOptionPane.showMessageDialog(M, "Inserisci i dati correttamente !");
            }

        }
    }
}
