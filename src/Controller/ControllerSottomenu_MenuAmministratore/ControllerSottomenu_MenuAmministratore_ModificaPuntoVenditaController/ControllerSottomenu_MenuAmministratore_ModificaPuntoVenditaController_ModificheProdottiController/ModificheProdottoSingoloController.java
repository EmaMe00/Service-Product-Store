package Controller.ControllerSottomenu_MenuAmministratore.ControllerSottomenu_MenuAmministratore_ModificaPuntoVenditaController.ControllerSottomenu_MenuAmministratore_ModificaPuntoVenditaController_ModificheProdottiController;

import DAO.ArticoliDAO;
import DAO.PathIMGDAO;
import Model.Merce.*;
import Model.Utenti.Utente;
import View.ControlloFinestre.ControlloFinestre;
import View.MenuAmministratore;
import View.Sottomenu_MenuAmministratore.Sottomenu_MenuAmministratore_ModificaPuntoVendita.MenuModifichePuntoVendita;
import View.Sottomenu_MenuAmministratore.Sottomenu_MenuAmministratore_ModificaPuntoVendita.Sottomenu_MenuAmministratore_ModificaPuntoVendita_Prodotti.ModificaProdottoSingolo;
import View.Sottomenu_MenuAmministratore.Sottomenu_MenuAmministratore_ModificaPuntoVendita.Sottomenu_MenuAmministratore_ModificaPuntoVendita_Prodotti.ModificheProdotti;

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
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ModificheProdottoSingoloController implements ActionListener {

    private Path PathImg = Paths.get("src/img/prodotti/StandardIMG.png");
    private ModificheProdotti M;
    private MenuAmministratore M1;
    private MenuModifichePuntoVendita M2;
    private String S;
    private ModificaProdottoSingolo M3;
    private int imgchange=0;

    public ModificheProdottoSingoloController(ModificaProdottoSingolo m3,ModificheProdotti m,MenuAmministratore m1, MenuModifichePuntoVendita m2,String s){

        M3 = m3;
        M = m;
        M1 = m1;
        M2 = m2;
        S = s;

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if ("Indietro".equals(e.getActionCommand())){
            ControlloFinestre.SwitchFrameOn(M);
            ControlloFinestre.CloseFrame(M3);
        }

        if ("Carica".equals(e.getActionCommand())){

            List<PuntiVendita> puntiVendita = M1.getPuntivendita();
            PuntiVendita puntoVendita = puntiVendita.get(Integer.parseInt(S));
            List<IProdotti> prodotti = puntoVendita.getProdotti();
            Prodotti prodotto = (Prodotti) prodotti.get(Integer.parseInt(M.getTextField()));

            if (prodotto.getPathIMG().size()<4){

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

            }else{
                JOptionPane.showMessageDialog(M2, "Raggiunto il massimo di 4 immagini");
            }

        }

        if ("Salva".equals(e.getActionCommand())){
            try {
                SalvaModificheProdotti();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            ControlloFinestre.CloseFrame(M);
            ControlloFinestre.CloseFrame(M3);
            ControlloFinestre.SwitchFrameOn(M2);
        }

    }

    public void SalvaModificheProdotti() throws SQLException {

        try{

            List<PuntiVendita> puntiVendita = M1.getPuntivendita();
            PuntiVendita puntoVendita = puntiVendita.get(Integer.parseInt(S));
            List<Corsia> corsie = puntoVendita.getCorsie();
            Corsia corsia = corsie.get(Integer.parseInt(M3.getCorsia()));
            List<Scaffale> scaffali = corsia.getScaffali();
            Scaffale scaf = new Scaffale(Integer.parseInt(M3.getScaffale()));
            scaffali.add(scaf);
            corsia.setScaffali(scaffali);
            corsie.set(Integer.parseInt(M3.getCorsia()),corsia);
            puntoVendita.setCorsie(corsie);
            List<IProdotti> prodotti = puntoVendita.getProdotti();
            Prodotti prodotto = (Prodotti) prodotti.get(Integer.parseInt(M.getTextField()));

            List<Utente> utenti = M1.getMenuPrincipale().getFD().getListaUtenti();

            for (int i=0;i<utenti.size();i++){

                List<IProdotti> carrelloProdotti = utenti.get(i).getCarrelloProdotti();
                for (int j=0; j<carrelloProdotti.size();j++){
                    Prodotti prodottoCarrello = (Prodotti) carrelloProdotti.get(j);
                    if (prodottoCarrello.getnome().equals(prodotti.get(Integer.parseInt(M.getTextField())).getnome()) && Objects.equals(prodottoCarrello.getprezzo(), prodotti.get(Integer.parseInt(M.getTextField())).getprezzo())){
                        prodottoCarrello.setNome(M3.getNome());
                        prodottoCarrello.setPrezzo(Double.parseDouble(M3.getCosto()));
                        prodottoCarrello.setDiponivilita(Integer.parseInt(M3.getDisponibita()));
                        prodottoCarrello.setDescrizione(M3.getDescrizione());
                        prodottoCarrello.setProduttore(M3.getProduttore());
                        prodottoCarrello.setNumCategoria(Integer.parseInt(M3.getCategoria()));
                        prodottoCarrello.setNumCorsia(Integer.parseInt(M3.getCorsia()));
                        prodottoCarrello.setNumSottocategoria(Integer.parseInt(M3.getSottocategoria()));
                        prodottoCarrello.setNumScaffale(scaffali.size()-1);


                    }
                }
                utenti.get(i).setCarrelloProdotti(carrelloProdotti);

            }

            M1.getMenuPrincipale().getFD().setListaUtenti(utenti);

            prodotto.setNome(M3.getNome());
            prodotto.setPrezzo(Double.parseDouble(M3.getCosto()));
            prodotto.setDiponivilita(Integer.parseInt(M3.getDisponibita()));
            prodotto.setDescrizione(M3.getDescrizione());
            prodotto.setProduttore(M3.getProduttore());
            prodotto.setNumCategoria(Integer.parseInt(M3.getCategoria()));
            prodotto.setNumCorsia(Integer.parseInt(M3.getCorsia()));
            prodotto.setNumSottocategoria(Integer.parseInt(M3.getSottocategoria()));
            prodotto.setNumScaffale(scaffali.size()-1);
            if (imgchange!=0){
                if(prodotto.getPathIMG().size()<4){
                    prodotto.addPathIMG(PathImg);
                }else{
                    JOptionPane.showMessageDialog(M2, "Raggiunto il massimo di 4 immagini");
                }

            }

            prodotti.set(Integer.parseInt(M.getTextField()),prodotto);
            puntoVendita.setProdotti(prodotti);
            puntiVendita.set(Integer.parseInt(S),puntoVendita);
            M1.getMenuPrincipale().getFD().setPuntiVendita(puntiVendita);

            ArticoliDAO ADAO = new ArticoliDAO();
            ADAO.UpdateArticoli(M1.getMenuPrincipale().getFD(),M1.getMenuPrincipale().getConnection());
            JOptionPane.showMessageDialog(M2, "Prodotto modificato correttamente !");

        }catch(Exception E){
            JOptionPane.showMessageDialog(M2, "Inserisci tutti i dati correttamente !");
        }



    }

}
