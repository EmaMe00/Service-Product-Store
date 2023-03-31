package View.Sottomenu_MenuUtente.Sottomenu_MenuUtente_VisualizzaPuntiVenditaUtente;

import Controller.ControllerSottomenu_MenuUtente.ControllerSottomenu_MenuUtente_VisualizzaPuntiVenditaController.VisualizzazioneProdottiController;
import Model.FakeDatabase;
import Model.Merce.*;
import View.ControlloFinestre.IFinestre;
import View.MenuPrincipale;
import View.Sottomenu_MenuUtente.VisualizzaPuntiVendita;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

//Frame per la visualizzazione dei prodotti e dei servizi per l'utente una volta scelto il punto vendita desiderato
public class VisualizzazioneProdottiUtente extends JFrame implements IFinestre {

    private JTextField numProdotto = new JTextField();
    private JTextField numServizio = new JTextField();
    private int x=1;


    public VisualizzazioneProdottiUtente(){ super("MyShop"); }

    public void StartVisualizzazionePuntiVenditaUtente(MenuPrincipale M, VisualizzaPuntiVendita V, String S) throws IOException {

        this.setSize(1000,1000);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        JPanel p1 = new JPanel(new GridLayout(1,2));
        JButton indietro = new JButton("INDIETRO");
        JButton iscriviti = new JButton("ISCRIVITI / DISISCRIVITI AL PUNTO VENDITA");
        p1.add(indietro); p1.add(iscriviti);

        JPanel p2 = new JPanel(new GridLayout(2,3));
        JLabel l1 = new JLabel("INSERISCI IL NUMERO DEL PRODOTTO:");
        JButton conferma = new JButton("CONFERMA");


        JLabel l2 = new JLabel("INSERISCI IL NUMERO DEL SERVIZIO");
        JButton conferma1 = new JButton("CONFERMA");

        p2.add(l1); p2.add(numProdotto); p2.add(conferma);
        p2.add(l2); p2.add(numServizio); p2.add(conferma1);


        this.getContentPane().add(p1,BorderLayout.SOUTH);
        this.getContentPane().add(p2,BorderLayout.NORTH);

        ActionListener A = new VisualizzazioneProdottiController(M,this,V,S);

        VisualizzaProdottiPV(M,S,A);

        this.setVisible(true);


        indietro.addActionListener(A);
        indietro.setActionCommand("Indietro");

        conferma.addActionListener(A);
        conferma.setActionCommand("Conferma");

        conferma1.addActionListener(A);
        conferma1.setActionCommand("Conferma1");

        iscriviti.addActionListener(A);
        iscriviti.setActionCommand("Iscriviti");

    }

    //Metodo per la visualizzazione di prodotti e servizi
    public void VisualizzaProdottiPV(MenuPrincipale M,String S,ActionListener A) throws IOException {

        FakeDatabase DB = M.getFD();
        List<PuntiVendita> PV = DB.getPuntivendita();
        PuntiVendita puntoVendita = PV.get(Integer.parseInt(S));
        List<IProdotti> prodotti = puntoVendita.getProdotti();

        JPanel p3 = new JPanel(new GridLayout(puntoVendita.getServizi().size() + prodotti.size(),6));
        Dimension d = new Dimension(100,80);

        for (int i=0; i<prodotti.size(); i++){

            //Controllo se è un prodotto composito
            if (prodotti.get(i).getPC()==true){

                ProdottoComposito prodottoComposito = (ProdottoComposito) prodotti.get(i);

                JLabel label = new JLabel("[PRODOTTO COMPOSITO]");
                label.setFont(new Font("Serif", Font.PLAIN, 18));

                BufferedImage image = ImageIO.read(new File("src/img/prodotti/StandardIMG.png"));

                Image dimg = image.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
                ImageIcon imageIcon = new ImageIcon(dimg);

                JLabel img = new JLabel();
                img.setIcon(imageIcon);
                img.setHorizontalAlignment(0);

                JLabel nome = new JLabel(  i + " - " + prodottoComposito.getNome(),SwingConstants.CENTER);
                nome.setPreferredSize(d);
                nome.setFont(new Font("Serif", Font.PLAIN, 18));


                JLabel prezzo = new JLabel(String.valueOf(prodottoComposito.getprezzo()));
                prezzo.setPreferredSize(d);
                prezzo.setFont(new Font("Serif", Font.PLAIN, 18));

                JLabel sottocategoria = new JLabel(prodottoComposito.getSottoprodotti().size() + " Sottocategorie",SwingConstants.CENTER);
                sottocategoria.setPreferredSize(d);
                sottocategoria.setFont(new Font("Serif", Font.PLAIN, 18));

                JButton acquista = new JButton("Acquista");
                acquista.setPreferredSize(d);
                p3.add(img);p3.add(label);p3.add(nome);p3.add(prezzo);p3.add(acquista);p3.add(sottocategoria);
                acquista.addActionListener(A);
                acquista.setActionCommand("Acquista " + i);

                //se non è un prodotto composito lavoro come se fosse un prodotto
             }else{

                Prodotti prodotto = (Prodotti) prodotti.get(i);
                JLabel label = new JLabel("[PRODOTTO]");
                label.setFont(new Font("Serif", Font.PLAIN, 22));

                BufferedImage image = ImageIO.read(new File("src/img/prodotti/StandardIMG.png"));
                try {
                    image = ImageIO.read(new File(prodotto.getSiglePath(0).toString()));
                    System.out.println(prodotto.getPathIMG().toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Image dimg = image.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
                ImageIcon imageIcon = new ImageIcon(dimg);

                JLabel img = new JLabel();
                img.setIcon(imageIcon);
                img.setHorizontalAlignment(0);

                JLabel nome = new JLabel(i + " - " + prodotto.getNome(),SwingConstants.CENTER);
                nome.setPreferredSize(d);
                nome.setFont(new Font("Serif", Font.PLAIN, 18));

                JLabel prezzo = new JLabel(String.valueOf(prodotto.getPrezzo()),SwingConstants.CENTER);
                prezzo.setPreferredSize(d);
                prezzo.setFont(new Font("Serif", Font.PLAIN, 18));

                Categoria categoria = (Categoria) puntoVendita.getCategorie().get(prodotto.getNumCategoria());
                JLabel sottocategoria = new JLabel(categoria.getNomeSottocategoria(prodotto.getNumSottocategoria()),SwingConstants.CENTER);
                sottocategoria.setPreferredSize(d);
                sottocategoria.setFont(new Font("Serif", Font.PLAIN, 18));

                Corsia cors = puntoVendita.getCorsie().get(prodotto.getNumCorsia());
                JLabel corsia = new JLabel(cors.getCodCorsia(),SwingConstants.CENTER);
                corsia.setPreferredSize(d);
                corsia.setFont(new Font("Serif", Font.PLAIN, 18));

                p3.add(img); p3.add(label); p3.add(nome); p3.add(prezzo); p3.add(sottocategoria); p3.add(corsia);

            }
        }

        //Lavoro con i servizi
        List<Servizi> servizi = puntoVendita.getServizi();

        for (int j=0; j<servizi.size(); j++){

            Servizi servizio = servizi.get(j);

            JLabel label = new JLabel("[SERVIZIO]");
            label.setFont(new Font("Serif", Font.PLAIN, 22));

            BufferedImage image = ImageIO.read(new File("src/img/prodotti/StandardIMG.png"));
            try {
                image = ImageIO.read(new File(servizio.getSiglePath(0).toString()));
                System.out.println(servizio.getPathIMG().toString());
            } catch (IOException e) {
                e.printStackTrace();
            }

            Image dimg = image.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            ImageIcon imageIcon = new ImageIcon(dimg);

            JLabel img = new JLabel();
            img.setIcon(imageIcon);
            img.setHorizontalAlignment(0);


            JLabel nome = new JLabel( + j + " - " + servizio.getNome(),SwingConstants.CENTER);
            nome.setPreferredSize(d);
            nome.setFont(new Font("Serif", Font.PLAIN, 18));

            JLabel prezzo = new JLabel(String.valueOf(servizio.getPrezzo()),SwingConstants.CENTER);
            prezzo.setPreferredSize(d);
            prezzo.setFont(new Font("Serif", Font.PLAIN, 18));

            Categoria categoria = (Categoria) puntoVendita.getCategorie().get(servizio.getNumCategoria());
            JLabel sottocategoria = new JLabel(categoria.getNomeSottocategoria(servizio.getNumSottocategoria()),SwingConstants.CENTER);
            sottocategoria.setPreferredSize(d);
            sottocategoria.setFont(new Font("Serif", Font.PLAIN, 18));

            JLabel l = new JLabel(" ");


            p3.add(img); p3.add(label); p3.add(nome); p3.add(prezzo); p3.add(sottocategoria); p3.add(l);

        }

        JScrollPane scroll = new JScrollPane(p3,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setViewportView(p3);
        this.getContentPane().add(scroll,BorderLayout.CENTER);

    }

    public String getNumProdotto() {
        return numProdotto.getText();
    }

    public String getNumServizio(){
        return  numServizio.getText();
    }

    @Override
    public JFrame getFinestra() {
        return this;
    }
}
