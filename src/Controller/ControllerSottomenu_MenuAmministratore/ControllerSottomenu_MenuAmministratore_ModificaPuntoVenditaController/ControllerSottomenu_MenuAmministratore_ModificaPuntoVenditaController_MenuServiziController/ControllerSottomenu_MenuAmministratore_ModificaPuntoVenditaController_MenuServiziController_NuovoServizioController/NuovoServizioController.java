package Controller.ControllerSottomenu_MenuAmministratore.ControllerSottomenu_MenuAmministratore_ModificaPuntoVenditaController.ControllerSottomenu_MenuAmministratore_ModificaPuntoVenditaController_MenuServiziController.ControllerSottomenu_MenuAmministratore_ModificaPuntoVenditaController_MenuServiziController_NuovoServizioController;

import Business.GestisciServizi;
import View.ControlloFinestre.ControlloFinestre;
import View.MenuAmministratore;
import View.Sottomenu_MenuAmministratore.Sottomenu_MenuAmministratore_ModificaPuntoVendita.Sottomenu_MenuAmministratore_ModificaPuntoVendita_Servizi.MenuServizi;
import View.Sottomenu_MenuAmministratore.Sottomenu_MenuAmministratore_ModificaPuntoVendita.Sottomenu_MenuAmministratore_ModificaPuntoVendita_Servizi.Sottomenu_MenuAmministratore_ModificaPuntoVendita_Servizi_NuovoServizio.NuovoServizio;

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

public class NuovoServizioController implements ActionListener {

    private Path PathImg = Paths.get("src/img/prodotti/StandardIMG.png");
    private MenuAmministratore M;
    private NuovoServizio N;
    private MenuServizi M1;
    private String S;

    public NuovoServizioController(MenuAmministratore m, NuovoServizio n, MenuServizi m1, String s){
        M = m;
        N = n;
        M1 = m1;
        S = s;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if("Indietro".equals(e.getActionCommand())){
            ControlloFinestre.SwitchFrameOn(M1);
            ControlloFinestre.CloseFrame(N);
        }

        if("Carica".equals(e.getActionCommand())){

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

        if("Salva".equals(e.getActionCommand())){

            GestisciServizi ANS = new GestisciServizi(M,N,S);
            try {
                ANS.AggiungiNuovoServizio(PathImg);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            ControlloFinestre.SwitchFrameOn(M1);
            ControlloFinestre.CloseFrame(N);


        }

    }

}
