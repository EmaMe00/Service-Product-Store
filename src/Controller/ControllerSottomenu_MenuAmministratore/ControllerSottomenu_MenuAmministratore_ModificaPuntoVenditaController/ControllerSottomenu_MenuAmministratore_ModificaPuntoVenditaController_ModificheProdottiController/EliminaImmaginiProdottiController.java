package Controller.ControllerSottomenu_MenuAmministratore.ControllerSottomenu_MenuAmministratore_ModificaPuntoVenditaController.ControllerSottomenu_MenuAmministratore_ModificaPuntoVenditaController_ModificheProdottiController;

import Controller.ControllerSottomenu_MenuAmministratore.ControllerSottomenu_MenuAmministratore_ModificaPuntoVenditaController.ControllerSottomenu_MenuAmministratore_ModificaPuntoVenditaController_MenuServiziController.EliminaImmaginiServizioController;
import DAO.ArticoliDAO;
import Model.Merce.IProdotti;
import Model.Merce.Prodotti;
import Model.Merce.PuntiVendita;
import Model.Merce.Servizi;
import View.ControlloFinestre.ControlloFinestre;
import View.MenuAmministratore;
import View.Sottomenu_MenuAmministratore.Sottomenu_MenuAmministratore_ModificaPuntoVendita.Sottomenu_MenuAmministratore_ModificaPuntoVendita_Prodotti.EliminaImmaginiProdotti;
import View.Sottomenu_MenuAmministratore.Sottomenu_MenuAmministratore_ModificaPuntoVendita.Sottomenu_MenuAmministratore_ModificaPuntoVendita_Prodotti.ModificheProdotti;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class EliminaImmaginiProdottiController implements ActionListener {

    private MenuAmministratore M;
    private ModificheProdotti M1;
    private String S;
    private EliminaImmaginiProdotti E;

    public EliminaImmaginiProdottiController(MenuAmministratore m, ModificheProdotti m1, String s, EliminaImmaginiProdotti e){
        M=m;
        M1=m1;
        S=s;
        E=e;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if("Indietro".equals(e.getActionCommand())){
            ControlloFinestre.SwitchFrameOn(M1);
            ControlloFinestre.CloseFrame(E);
        }

        if("Elimina".equals(e.getActionCommand())){
            try{

                java.util.List<PuntiVendita> puntiVendita = M.getPuntivendita();
                PuntiVendita puntoVendita = puntiVendita.get(Integer.parseInt(S));
                List<IProdotti> prodotti = puntoVendita.getProdotti();
                Prodotti prodotto = (Prodotti) prodotti.get(Integer.parseInt(M1.getTextField()));
                ArrayList<Path> paths = prodotto.getPathIMG();
                if (paths.size()<2){
                    JOptionPane.showMessageDialog(M, "Non puoi eliminare l'ultima immagine rimanente");
                }else {
                    paths.remove(Integer.parseInt(E.getTextField()));
                    prodotto.setPathIMG(paths);
                    prodotti.set(Integer.parseInt(M1.getTextField()),prodotto);
                    puntoVendita.setProdotti(prodotti);
                    puntiVendita.set(Integer.parseInt(S),puntoVendita);
                    M.setPuntivendita(puntiVendita);
                    JOptionPane.showMessageDialog(M, "Immagini eliminata correttamente");
                }
                ControlloFinestre.SwitchFrameOn(M1);
                ControlloFinestre.CloseFrame(E);

                ArticoliDAO ADAO = new ArticoliDAO();
                ADAO.UpdateArticoli(M.getMenuPrincipale().getFD(),M.getMenuPrincipale().getConnection());

            } catch(Exception E){
                JOptionPane.showMessageDialog(M, "Inserisci un valore valido!");
            }

        }

    }

}
