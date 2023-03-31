package Controller.ControllerSottomenu_MenuAmministratore.ControllerSottomenu_MenuAmministratore_ModificaPuntoVenditaController.ControllerSottomenu_MenuAmministratore_ModificaPuntoVenditaController_MenuServiziController;

import DAO.ArticoliDAO;
import DAO.PathIMGDAO;
import Model.Merce.IProdotti;
import Model.Merce.Prodotti;
import Model.Merce.PuntiVendita;
import Model.Merce.Servizi;
import Model.Utenti.Utente;
import View.ControlloFinestre.ControlloFinestre;
import View.MenuAmministratore;
import View.Sottomenu_MenuAmministratore.Sottomenu_MenuAmministratore_ModificaPuntoVendita.Sottomenu_MenuAmministratore_ModificaPuntoVendita_Servizi.MenuServizi;
import View.Sottomenu_MenuAmministratore.Sottomenu_MenuAmministratore_ModificaPuntoVendita.Sottomenu_MenuAmministratore_ModificaPuntoVendita_Servizi.ModificaServizioSingolo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.util.List;

public class ModificaServizioSingoloController implements ActionListener {

    private Path PathImg = Paths.get("src/img/prodotti/StandardIMG.png");
    private ModificaServizioSingolo M1;
    private MenuAmministratore M;
    private MenuServizi MS;
    private String S;
    private int imgchange=0;


    public ModificaServizioSingoloController(ModificaServizioSingolo m1, MenuAmministratore m, MenuServizi ms, String s){
        M1 = m1;
        M = m;
        MS = ms;
        S = s;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if ("Indietro".equals(e.getActionCommand())){
            ControlloFinestre.SwitchFrameOn(MS);
            ControlloFinestre.CloseFrame(M1);
        }

        if ("Carica".equals(e.getActionCommand())){

            List<PuntiVendita> puntiVendita = M.getPuntivendita();
            PuntiVendita puntoVendita = puntiVendita.get(Integer.parseInt(S));
            List<Servizi> servizi = puntoVendita.getServizi();
            Servizi servizio =  servizi.get(Integer.parseInt(MS.getnServizo()));

            if (servizio.getPathIMG().size()<4){
                imgchange++;
                String pathFile;

                JFileChooser f = new JFileChooser();
                f.setDialogTitle("CARICA IMMAGINE");
                int r = f.showOpenDialog((Component)null);

                File file = f.getSelectedFile();
                pathFile = file.getAbsolutePath();
                String localPath = "src/img/prodotti";
                String localPath1 = "src/img/prodotti/";
                Path pathF = Paths.get(pathFile);
                Path pathS = Paths.get(localPath);

                if (r==0){

                    //Salvataggio nei dati nella directory del programma
                    try {
                        Files.copy(pathF, pathS.resolve(pathF.getFileName()) , StandardCopyOption.REPLACE_EXISTING);
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }

                    // Controlli sullo stream dei dati standard
                    try {
                        FileOutputStream f2 = new FileOutputStream(file);
                        f2.flush();
                        f2.close();
                    } catch (FileNotFoundException var8) {
                        var8.printStackTrace();
                    } catch (IOException var9) {
                        var9.printStackTrace();
                    }

                }

                PathImg = Paths.get(localPath1 + file.getName());
            }
            else {
                JOptionPane.showMessageDialog(M, "Raggiunto il massimo di 4 immagini");
            }


        }

        if ("Salva".equals(e.getActionCommand())){
            try {
                SalvaModificheServizi();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            ControlloFinestre.CloseFrame(M1);
            ControlloFinestre.CloseFrame(MS);
            ControlloFinestre.SwitchFrameOn(M);

        }

    }

    public void SalvaModificheServizi() throws SQLException {

        try {
            java.util.List<PuntiVendita> puntiVendita = M.getPuntivendita();
            PuntiVendita puntoVendita = puntiVendita.get(Integer.parseInt(S));
            List<Servizi> servizi = puntoVendita.getServizi();
            Servizi servizio = servizi.get(Integer.parseInt(MS.getnServizo()));

            List<Utente> utenti = M.getMenuPrincipale().getFD().getListaUtenti();

            for (int i=0;i<utenti.size();i++){

                List<Servizi> carrelloServizi = utenti.get(i).getCarrelloServizi();
                for (int j=0; j<carrelloServizi.size();j++){
                    Servizi servizioCarrello = carrelloServizi.get(j);
                    if (servizioCarrello.getNome().equals(servizi.get(Integer.parseInt(MS.getnServizo())).getNome()) && servizioCarrello.getPrezzo() == servizi.get(Integer.parseInt(MS.getnServizo())).getPrezzo()){
                        servizioCarrello.setNome(M1.getNome());
                        servizioCarrello.setPrezzo(Double.parseDouble(M1.getCosto()));
                        servizioCarrello.setDiponivilita(Integer.parseInt(M1.getDisponibilita()));
                        servizioCarrello.setDescrizione(M1.getDescrizione());
                        servizioCarrello.setProduttore(M1.getProduttore());
                        servizioCarrello.setNumCategoria(Integer.parseInt(M1.getCategoria()));
                        servizioCarrello.setNumSottocategoria(Integer.parseInt(M1.getSottocategoria()));
                        carrelloServizi.set(j,servizioCarrello);
                    }
                }
                utenti.get(i).setCarrelloServizi(carrelloServizi);
            }

            M.getMenuPrincipale().getFD().setListaUtenti(utenti);

            servizio.setNome(M1.getNome());
            servizio.setPrezzo(Double.parseDouble(M1.getCosto()));
            servizio.setDiponivilita(Integer.parseInt(M1.getDisponibilita()));
            servizio.setDescrizione(M1.getDescrizione());
            servizio.setProduttore(M1.getProduttore());
            servizio.setNumCategoria(Integer.parseInt(M1.getCategoria()));
            servizio.setNumSottocategoria(Integer.parseInt(M1.getSottocategoria()));
            if (imgchange!=0){
                if (servizio.getPathIMG().size()<4){
                    servizio.addPathIMG(PathImg);
                }else{
                    JOptionPane.showMessageDialog(M, "Raggiunto il massimo di 4 immagini");
                }

            }

            servizi.set(Integer.parseInt(MS.getnServizo()),servizio);
            puntoVendita.setServizi(servizi);
            puntiVendita.set(Integer.parseInt(S),puntoVendita);
            M.getMenuPrincipale().getFD().setPuntiVendita(puntiVendita);


            ArticoliDAO ADAO = new ArticoliDAO();
            ADAO.UpdateArticoli(M.getMenuPrincipale().getFD(),M.getMenuPrincipale().getConnection());
            JOptionPane.showMessageDialog(M, "Servizio modificato correttamente");
        }catch (Exception E){
            JOptionPane.showMessageDialog(M, "Inserisci i dati correttamente !");
            ControlloFinestre.CloseFrame(M1);
        }



    }

}
