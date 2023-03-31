package View.Sottomenu_MenuAmministratore.Sottomenu_MenuAmministratore_ModificaPuntoVendita.Sottomenu_MenuAmministratore_ModificaPuntoVendita_Prodotti;

import Controller.ControllerSottomenu_MenuAmministratore.ControllerSottomenu_MenuAmministratore_ModificaPuntoVenditaController.ControllerSottomenu_MenuAmministratore_ModificaPuntoVenditaController_ModificheProdottiController.EliminaImmaginiProdottiController;
import Model.Merce.IProdotti;
import Model.Merce.Prodotti;
import Model.Merce.PuntiVendita;
import Model.Merce.Servizi;
import View.ControlloFinestre.IFinestre;
import View.MenuAmministratore;
import View.Sottomenu_MenuAmministratore.Sottomenu_MenuAmministratore_ModificaPuntoVendita.MenuModifichePuntoVendita;
import View.Sottomenu_MenuAmministratore.Sottomenu_MenuAmministratore_ModificaPuntoVendita.Sottomenu_MenuAmministratore_ModificaPuntoVendita_Servizi.MenuServizi;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class EliminaImmaginiProdotti extends JFrame implements IFinestre {

    private JTextField textField1;
    private JButton elimina;
    private JButton indietro;
    private JLabel img;
    private JLabel img1;
    private JLabel img2;
    private JLabel img3;
    private JPanel panel1;

    public EliminaImmaginiProdotti(){super("MyShop");}

    public void StartEliminaImmaginiProdotti(MenuAmministratore M, ModificheProdotti M1, String S) throws IOException {

        this.setSize(800,600);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        StampaImmagini(M,S,M1);
        this.getContentPane().add(panel1, BorderLayout.CENTER);
        this.setVisible(true);

        ActionListener A = new EliminaImmaginiProdottiController(M,M1,S,this);

        indietro.addActionListener(A);
        indietro.setActionCommand("Indietro");

        elimina.addActionListener(A);
        elimina.setActionCommand("Elimina");

        textField1.addActionListener(A);
        textField1.setActionCommand("Elimina");

    }

    public String getTextField(){return textField1.getText();}

    public void StampaImmagini(MenuAmministratore M,String S, ModificheProdotti M2) throws IOException {

        java.util.List<PuntiVendita> puntiVendita = M.getPuntivendita();
        PuntiVendita puntoVendita = puntiVendita.get(Integer.parseInt(S));
        List<IProdotti> prodotti = puntoVendita.getProdotti();
        Prodotti prodotto = (Prodotti) prodotti.get(Integer.parseInt(M2.getTextField()));


        /* IMG */

        for (int i = 0; i < prodotto.getPathIMG().size(); i++) {

            BufferedImage image = ImageIO.read(new File("src/img/prodotti/StandardIMG.png"));
            try {
                image = ImageIO.read(new File(prodotto.getSiglePath(i).toString()));
                System.out.println(prodotto.getPathIMG().toString());
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
