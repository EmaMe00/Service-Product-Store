package Controller.ControllerSottomenu_MenuAmministratore.ControllerSottomenu_MenuAmministratore_ModificaPuntoVenditaController.ControllerSottomenu_MenuAmministratore_ModificaPuntoVenditaController_ModificheProdottiController.ControllerSottomenu_MenuAmministratore_ModificaPuntoVenditaController_ModificheProdottiController_NuovoProdottoController;

import Business.GestisciProdotti;
import View.ControlloFinestre.ControlloFinestre;
import View.MenuAmministratore;
import View.Sottomenu_MenuAmministratore.Sottomenu_MenuAmministratore_ModificaPuntoVendita.MenuModifichePuntoVendita;
import View.Sottomenu_MenuAmministratore.Sottomenu_MenuAmministratore_ModificaPuntoVendita.Sottomenu_MenuAmministratore_ModificaPuntoVendita_Prodotti.ModificheProdotti;
import View.Sottomenu_MenuAmministratore.Sottomenu_MenuAmministratore_ModificaPuntoVendita.Sottomenu_MenuAmministratore_ModificaPuntoVendita_Prodotti.Sottomenu_MenuAmministratore_ModificaPuntoVendita_Prodotti_NUOVO.NuovoProdotto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;

public class NuovoProdottoController implements ActionListener {

    private Path PathImg = Paths.get("src/img/prodotti/StandardIMG.png");
    private NuovoProdotto N;
    private MenuAmministratore M;
    private ModificheProdotti M1;
    private String S;
    private MenuModifichePuntoVendita M2;
    boolean bl;

    public NuovoProdottoController(NuovoProdotto n, MenuAmministratore m, ModificheProdotti m1, String s, MenuModifichePuntoVendita m2,boolean a){
        N = n;
        M = m;
        M1 = m1;
        S = s;
        M2 = m2;
        bl = a;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if ("Indietro".equals(e.getActionCommand())){
            ControlloFinestre.SwitchFrameOn(M1);
            ControlloFinestre.CloseFrame(N);
        }

        if ("Carica".equals(e.getActionCommand())){

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

        if ("Salva".equals(e.getActionCommand())){

            GestisciProdotti A = new GestisciProdotti(M,N,S);
            try {
                A.AggiungiProdotto(PathImg);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            if (bl){
                ControlloFinestre.CloseFrame(N);
            }else{
                ControlloFinestre.CloseFrame(M1);
                ControlloFinestre.SwitchFrameOn(M2);
                ControlloFinestre.CloseFrame(N);
            }

        }

    }

}
