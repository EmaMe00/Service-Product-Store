package View.Sottomenu_MenuUtente;

import Controller.ControllerSottomenu_MenuUtente.VisualizzaCarrelloUtenteController;
import Model.FakeDatabase;
import Model.Merce.*;
import Model.Utenti.Utente;
import View.ControlloFinestre.IFinestre;
import View.MenuPrincipale;
import View.MenuUtente;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//Frame di visualizzazione del carrello dell'utente
public class VisualizzaCarrelloUtente extends JFrame implements IFinestre {

    //Liste di soli nomi e prezzi dei vari prodotti, prodotti compositi e servizi
    //Le utilizzo successivamente per stampare delle email
    //I dati sono salvati come Prodotti[i] = "NomeProdotto" e Prodotti[i+1] = "PrezzoProdotto_i"
    List<String> Prodotti = new ArrayList<>();
    List<String> ProdottiCompositi = new ArrayList<>();
    List<String> Servizi = new ArrayList<>();

    JTextField t1 = new JTextField();
    JTextField t2 = new JTextField();
    JTextField t3 = new JTextField();
    JTextField t4 = new JTextField();

    public VisualizzaCarrelloUtente() { super("MyShop"); }

    public void StartVisualizzaCarrelloUtente(MenuUtente M, MenuPrincipale M1) throws IOException {

        this.setSize(1000,1000);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        JPanel p1 = new JPanel(new GridLayout(4,3));

        JLabel l1 = new JLabel("(Numero dei prodotti separati da spazio) Acquista: ");
        JButton acquista = new JButton("ACQUISTA");

        JLabel l2 = new JLabel("(Numero del prodotto) Rimuovi: ");
        JButton rimuovi = new JButton("RIMUOVI");

        JLabel l3 = new JLabel("(Numero dei servizi separati da spazio) Acquista: ");
        JButton acquista1 = new JButton("ACQUISTA");

        JLabel l4 = new JLabel("(Numero del servizio) Rimuovi: ");
        JButton rimuovi1 = new JButton("RIMUOVI");

        p1.add(l1);p1.add(t1);p1.add(acquista);
        p1.add(l2);p1.add(t2);p1.add(rimuovi);
        p1.add(l3); p1.add(t3);p1.add(acquista1);
        p1.add(l4);p1.add(t4);p1.add(rimuovi1);

        JPanel p2 = new JPanel(new GridLayout(1,5));

        JButton indietro = new JButton("INDIETRO");
        JButton acquistaTutto = new JButton("ACQUISTA TUTTO");
        JButton cancellaTutto = new JButton("CANCELLA TUTTO");
        JButton generaPDF = new JButton("GENERA PDF");
        JButton acquistati = new JButton("GIA ACQUISTATI");

        p2.add(indietro);p2.add(acquistaTutto);p2.add(cancellaTutto);p2.add(generaPDF);p2.add(acquistati);

        VisualizzaProdottiPV(M1);

        this.getContentPane().add(p1,BorderLayout.NORTH);
        this.getContentPane().add(p2,BorderLayout.SOUTH);

        this.setVisible(true);

        ActionListener A = new VisualizzaCarrelloUtenteController(M,M1,this);

        indietro.addActionListener(A);
        indietro.setActionCommand("Indietro");

        acquista.addActionListener(A);
        acquista.setActionCommand("Acquista Prodotto");

        rimuovi.addActionListener(A);
        rimuovi.setActionCommand("Elimina Prodotto");

        acquista1.addActionListener(A);
        acquista1.setActionCommand("Acquista Servizio");

        rimuovi1.addActionListener(A);
        rimuovi1.setActionCommand("Elimina Servizio");

        cancellaTutto.addActionListener(A);
        cancellaTutto.setActionCommand("Elimina Tutto");

        generaPDF.addActionListener(A);
        generaPDF.setActionCommand("Genera PDF");

        acquistaTutto.addActionListener(A);
        acquistaTutto.setActionCommand("Acquista tutto");

        acquistati.addActionListener(A);
        acquistati.setActionCommand("Acquistati");
    }

    @Override
    public JFrame getFinestra() {
        return this;
    }

    public String getT1() {
        return t1.getText();
    }

    public String getT2() {
        return t2.getText();
    }

    public String getT3(){return t3.getText();}

    public String getT4(){return  t4.getText();}

    //Metodo di visualizzazione del prodotti e dei servizi all'interno del Frame
    public void VisualizzaProdottiPV(MenuPrincipale M) throws IOException {

        FakeDatabase DB = M.getFD();
        List<Utente> utenti = DB.getListaUtenti();
        Utente utente = utenti.get(DB.getSessione().getNumUtente());
        java.util.List<IProdotti> prodotti = utente.getCarrelloProdotti();

        JPanel p3 = new JPanel(new GridLayout(utente.getCarrelloServizi().size() + prodotti.size(),6));
        Dimension d = new Dimension(100,80);

        for (int i=0; i<prodotti.size(); i++){

            //Controlla se il prodotto è un prodotto Composito
            if (prodotti.get(i).getPC()==true){

                ProdottoComposito prodottoComposito = (ProdottoComposito) prodotti.get(i);

                JLabel label = new JLabel("[PRODOTTO COMPOSITO] ");
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

                double prez = 0;
                for(int j=0; j<prodottoComposito.getSottoprodotti().size(); j++){
                    prez = prez +  prodottoComposito.getSottoprodotti().get(j).getprezzo();
                }

                JLabel prezzo = new JLabel(String.valueOf(prez),SwingConstants.CENTER);
                prezzo.setPreferredSize(d);
                prezzo.setFont(new Font("Serif", Font.PLAIN, 18));

                JLabel sottocategoria = new JLabel(prodottoComposito.getSottoprodotti().size() + " Sottocategorie");
                sottocategoria.setPreferredSize(d);
                sottocategoria.setFont(new Font("Serif", Font.PLAIN, 18));

                JLabel l = new JLabel(" ");
                l.setPreferredSize(d);
                p3.add(img);p3.add(label);p3.add(nome);p3.add(prezzo);p3.add(sottocategoria); p3.add(l);

                ProdottiCompositi.add(prodottoComposito.getNome());
                ProdottiCompositi.add(String.valueOf(prodottoComposito.getprezzo()));
                //Se non è un prodotto composito lo lavora come prodotto
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

                PuntiVendita puntoVendita = M.getFD().getPuntivendita().get(prodotto.getNumPuntoVendita());
                Categoria categoria = (Categoria) puntoVendita.getCategorie().get(prodotto.getNumCategoria());
                JLabel sottocategoria = new JLabel(categoria.getNomeSottocategoria(prodotto.getNumSottocategoria()),SwingConstants.CENTER);
                sottocategoria.setPreferredSize(d);
                sottocategoria.setFont(new Font("Serif", Font.PLAIN, 18));

                Corsia cors = puntoVendita.getCorsie().get(prodotto.getNumCorsia());
                JLabel corsia = new JLabel(cors.getCodCorsia(),SwingConstants.CENTER);
                corsia.setPreferredSize(d);
                corsia.setFont(new Font("Serif", Font.PLAIN, 18));

                p3.add(img); p3.add(label); p3.add(nome); p3.add(prezzo); p3.add(sottocategoria); p3.add(corsia);

                Prodotti.add(prodotto.getnome());
                Prodotti.add(String.valueOf(prodotto.getPrezzo()));

            }
        }

        //Da qui in poi si stampano solo servizi
        List<Servizi> servizi = utente.getCarrelloServizi();

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

            PuntiVendita puntoVendita = M.getFD().getPuntivendita().get(servizio.getNumPuntoVendita());
            Categoria categoria = (Categoria) puntoVendita.getCategorie().get(servizio.getNumCategoria());
            JLabel sottocategoria = new JLabel(categoria.getNomeSottocategoria(servizio.getNumSottocategoria()),SwingConstants.CENTER);
            sottocategoria.setPreferredSize(d);
            sottocategoria.setFont(new Font("Serif", Font.PLAIN, 18));

            JLabel l = new JLabel(" ");


            p3.add(img); p3.add(label); p3.add(nome); p3.add(prezzo); p3.add(sottocategoria); p3.add(l);

            Servizi.add(servizio.getNome());
            Servizi.add(String.valueOf(servizio.getPrezzo()));

        }

        JScrollPane scroll = new JScrollPane(p3,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setViewportView(p3);
        this.getContentPane().add(scroll,BorderLayout.CENTER);

    }


    public List<String> getProdotti() {
        return Prodotti;
    }

    public void setProdotti(List<String> prodotti) {
        Prodotti = prodotti;
    }

    public List<String> getProdottiCompositi() {
        return ProdottiCompositi;
    }

    public void setProdottiCompositi(List<String> prodottiCompositi) {
        ProdottiCompositi = prodottiCompositi;
    }

    public List<String> getServizi() {
        return Servizi;
    }

    public void setServizi(List<String> servizi) {
        Servizi = servizi;
    }
}
