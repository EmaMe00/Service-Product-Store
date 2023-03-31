package View.Sottomenu_MenuAmministratore.Sottomenu_MenuAmministratore_ModificaPuntoVendita.Sottomenu_MenuAmministratore_ModificaPuntoVendita_Servizi;

import Controller.ControllerSottomenu_MenuAmministratore.ControllerSottomenu_MenuAmministratore_ModificaPuntoVenditaController.ControllerSottomenu_MenuAmministratore_ModificaPuntoVenditaController_MenuServiziController.EliminaImmaginiServizioController;
import Model.FakeDatabase;
import Model.Merce.PuntiVendita;
import Model.Merce.Servizi;
import View.ControlloFinestre.IFinestre;
import View.MenuAmministratore;
import View.Sottomenu_MenuAmministratore.Sottomenu_MenuAmministratore_ModificaPuntoVendita.MenuModifichePuntoVendita;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

//Frame per l'eliminazione delle immagini
public class EliminaImmaginiServizio extends JFrame implements IFinestre {

    private JTextField textField1;
    private JButton indietro;
    private JLabel label1;
    private JLabel img;
    private JLabel img1;
    private JLabel img2;
    private JLabel img3;
    private JButton elimina;
    private JPanel panel1;

    public EliminaImmaginiServizio(){super("MyShop");}

    public void StartEliminaImmaginiServizio(MenuAmministratore M, MenuServizi M2, String S) throws IOException {

        this.setSize(800,600);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        this.getContentPane().add(panel1, BorderLayout.CENTER);
        StampaImmagini(M,S,M2);
        this.setVisible(true);

        ActionListener A = new EliminaImmaginiServizioController(M,M2,S,this);

        indietro.addActionListener(A);
        indietro.setActionCommand("Indietro");

        elimina.addActionListener(A);
        elimina.setActionCommand("Elimina");

        textField1.addActionListener(A);
        textField1.setActionCommand("Elimina");

    }

    public String getTextField(){return textField1.getText();}

    //metodo per stampare le immagini presenti per il prodotto
    public void StampaImmagini(MenuAmministratore M,String S, MenuServizi M2) throws IOException {

        java.util.List<PuntiVendita> puntiVendita = M.getPuntivendita();
        PuntiVendita puntoVendita = puntiVendita.get(Integer.parseInt(S));
        List<Servizi> servizi = puntoVendita.getServizi();
        Servizi servizio = servizi.get(Integer.parseInt(M2.getnServizo()));

        /* IMG */

        for (int i = 0; i < servizio.getPathIMG().size(); i++) {

            BufferedImage image = ImageIO.read(new File("src/img/prodotti/StandardIMG.png"));
            try {
                image = ImageIO.read(new File(servizio.getSiglePath(i).toString()));
                System.out.println(servizio.getPathIMG().toString());
            } catch (IOException e) {
                e.printStackTrace();
            }

            Image dimg = image.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
            ImageIcon imageIcon = new ImageIcon(dimg);

            if (i == 0) {
                img.setIcon(imageIcon);
                img.setHorizontalAlignment(0);
            }

            if (i == 1) {
                img1.setIcon(imageIcon);
                img1.setHorizontalAlignment(0);
            }

            if (i == 2) {
                img2.setIcon(imageIcon);
                img2.setHorizontalAlignment(0);
            }
            if (i == 3) {
                img3.setIcon(imageIcon);
                img3.setHorizontalAlignment(0);
            }

        }

    }

    @Override
    public JFrame getFinestra() {
        return this;
    }
}
