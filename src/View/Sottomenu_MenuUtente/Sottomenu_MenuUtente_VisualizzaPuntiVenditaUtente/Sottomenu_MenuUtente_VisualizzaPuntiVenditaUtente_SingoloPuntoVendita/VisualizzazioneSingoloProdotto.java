package View.Sottomenu_MenuUtente.Sottomenu_MenuUtente_VisualizzaPuntiVenditaUtente.Sottomenu_MenuUtente_VisualizzaPuntiVenditaUtente_SingoloPuntoVendita;

import Controller.ControllerSottomenu_MenuUtente.ControllerSottomenu_MenuUtente_VisualizzaPuntiVenditaController.ControllerSottomenu_MenuUtente_VisualizzaPuntiVenditaController_VisualizzazioneSingoloProdottoController.VisualizzazioneSingoloProdottoController;
import Model.FakeDatabase;
import Model.Merce.*;
import View.ControlloFinestre.IFinestre;
import View.MenuPrincipale;
import View.Sottomenu_MenuUtente.Sottomenu_MenuUtente_VisualizzaPuntiVenditaUtente.VisualizzaProdottoComposito;
import View.Sottomenu_MenuUtente.Sottomenu_MenuUtente_VisualizzaPuntiVenditaUtente.VisualizzazioneProdottiUtente;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

//Frame per la visualizzazione del singolo prodotto o del singolo servizio
public class VisualizzazioneSingoloProdotto extends JFrame implements IFinestre {

    private JPanel panel1;
    private JLabel nomePuntoVendita;
    private JLabel img;
    private JLabel nomeProdotto;
    private JLabel descrizione;
    private JLabel categoria;
    private JLabel sottocategoria;
    private JButton acquista;
    private JButton indietro;
    private JLabel prezzo;
    private JLabel corsia;
    private JLabel scaffale;
    private JLabel produttore;
    private JLabel disponibilita;
    private JLabel img1;
    private JLabel img2;
    private JLabel img3;
    private JButton recensioni;
    private boolean isProdotto;

    public VisualizzazioneSingoloProdotto(){ super("MyShop"); }

    //metodo per attivare la visualizzazione del prodotto
    public void StartVisualizzazioneSingoloProdotto(MenuPrincipale M, VisualizzazioneProdottiUtente V, String S, String numSingoloProdotto, boolean a, int tmp) throws IOException {

        this.setSize(1000,1000);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        // metodo che modifica i contenuti degli elementi del frame con ciò che abbiamo scelto
        selezioneProdotto(M,V,S,numSingoloProdotto,a,tmp);

        this.setContentPane(panel1);
        this.setVisible(true);

        ActionListener A = new VisualizzazioneSingoloProdottoController(M,this,V,S,numSingoloProdotto,0);

        indietro.addActionListener(A);
        indietro.setActionCommand("Indietro");

        acquista.addActionListener(A);
        acquista.setActionCommand("Acquista");

        recensioni.addActionListener(A);
        recensioni.setActionCommand("Recensioni");

    }

    public void StartVisualizzazioneSingoloProdottoCP(MenuPrincipale M, VisualizzaProdottoComposito V, String S, String numSingoloProdotto, VisualizzazioneProdottiUtente V1) throws IOException {

        this.setSize(1000,1000);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        // metodo che modifica i contenuti degli elementi del frame con ciò che abbiamo scelto
        selezioneProdottoC(M,V,S,numSingoloProdotto);

        this.setContentPane(panel1);
        this.setVisible(true);


        ActionListener A = new VisualizzazioneSingoloProdottoController(M,this,V,S,numSingoloProdotto,0,V1);

        indietro.addActionListener(A);
        indietro.setActionCommand("Indietro");

        acquista.addActionListener(A);
        acquista.setActionCommand("Acquista");

        recensioni.addActionListener(A);
        recensioni.setActionCommand("Recensioni");



    }

    //metodo per attivare la visualizzazione del servizio
    public void StartVisualizzazioneSingoloServizio(MenuPrincipale M, VisualizzazioneProdottiUtente V, String S, String numSingoloProdotto) throws IOException {

        this.setSize(1000,1000);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        selezioneServizio(M,V,S,numSingoloProdotto);

        this.setContentPane(panel1);
        this.setVisible(true);

        ActionListener A = new VisualizzazioneSingoloProdottoController(M,this,V,S,numSingoloProdotto,-1);

        indietro.addActionListener(A);
        indietro.setActionCommand("Indietro");

        acquista.addActionListener(A);
        acquista.setActionCommand("Acquista");

        recensioni.addActionListener(A);
        recensioni.setActionCommand("Recensioni");

    }

    //questo metodo permette dinamicamente di prelevare le infomazioni dal mio database locale e di inserirle all'interlo della mia
    //finestra in modo da avere la visualizzazione di ciò che ho scelto
    //quindi modifica i contenuti degli elemnti del frame
    public void selezioneProdotto(MenuPrincipale M, VisualizzazioneProdottiUtente V, String S, String numSingoloProdotto, boolean a, int tmp) throws IOException {

        FakeDatabase DB = M.getFD();
        List<PuntiVendita> puntiVendita = DB.getPuntivendita();
        PuntiVendita puntoVendita = puntiVendita.get(Integer.parseInt(S));
        List<IProdotti> prodotti = puntoVendita.getProdotti();
        Prodotti prodotto;


        if (a==true){
            ProdottoComposito pdc = (ProdottoComposito) prodotti.get(Integer.parseInt(numSingoloProdotto));
            prodotto = (Prodotti) pdc.getSottoprodotti().get(tmp);
        }else {
            prodotto = (Prodotti) prodotti.get(Integer.parseInt(numSingoloProdotto));
        }


        nomePuntoVendita.setText("Nome punto vendita: " + puntoVendita.getNome());
        nomePuntoVendita.setFont(new Font("Serif", Font.PLAIN, 22));
        nomeProdotto.setText("Nome prodotto: " + prodotto.getNome());
        nomeProdotto.setFont(new Font("Serif", Font.PLAIN, 22));
        descrizione.setText("Descrizione: " + prodotto.getDescrizione());
        descrizione.setFont(new Font("Serif", Font.PLAIN, 22));
        produttore.setText("Produttore: " + prodotto.getProduttore());
        produttore.setFont(new Font("Serif", Font.PLAIN, 22));
        prezzo.setText("Prezzo: " + prodotto.getPrezzo());
        prezzo.setFont(new Font("Serif", Font.PLAIN, 22));
        disponibilita.setText("Disponibilità: " + prodotto.getDiponivilita());
        disponibilita.setFont(new Font("Serif", Font.PLAIN, 22));

        List<ICategoria> categorie = puntoVendita.getCategorie();
        Categoria categoriaa = (Categoria) categorie.get(prodotto.getNumCategoria());

        categoria.setText("Categoria: " + categoriaa.getnome());
        categoria.setFont(new Font("Serif", Font.PLAIN, 22));

        List<ICategoria> sottocategorie = categoriaa.getSottocategorie();
        Sottocategoria sottocategoriaa = (Sottocategoria) sottocategorie.get(prodotto.getNumSottocategoria());

        sottocategoria.setText("Sottocategoria: " + sottocategoriaa.getnome());
        sottocategoria.setFont(new Font("Serif", Font.PLAIN, 22));

        List<Corsia> corsie = puntoVendita.getCorsie();
        Corsia corsiaa = corsie.get(prodotto.getNumCorsia());

        corsia.setText("Corsia: " + corsiaa.getCodCorsia());
        corsia.setFont(new Font("Serif", Font.PLAIN, 22));

        List<Scaffale> scaffali = corsiaa.getScaffali();
        Scaffale scaffalee = scaffali.get(prodotto.getNumScaffale());

        scaffale.setText("Scaffale: " + scaffalee.getNumero());
        scaffale.setFont(new Font("Serif", Font.PLAIN, 22));

        /* IMG */

        for (int i=0; i<prodotto.getPathIMG().size();i++){

            BufferedImage image = ImageIO.read(new File("src/img/prodotti/StandardIMG.png"));
            try {
                image = ImageIO.read(new File(prodotto.getSiglePath(i).toString()));
                System.out.println(prodotto.getSiglePath(i).toString());
            } catch (IOException e) {
                e.printStackTrace();
            }

            Image dimg = image.getScaledInstance(300, 300, Image.SCALE_SMOOTH);
            ImageIcon imageIcon = new ImageIcon(dimg);

            if (i==0){
                img.setIcon(imageIcon);
                img.setHorizontalAlignment(0);
            }

            if (i==1){
                img1.setIcon(imageIcon);
                img1.setHorizontalAlignment(0);
            }

            if (i==2){
                img2.setIcon(imageIcon);
                img2.setHorizontalAlignment(0);
            }
            if (i==3){
                img3.setIcon(imageIcon);
                img3.setHorizontalAlignment(0);
            }

        }

    }

    public void selezioneServizio(MenuPrincipale M, VisualizzazioneProdottiUtente V, String S, String numSingoloProdotto) throws IOException {

        FakeDatabase DB = M.getFD();
        List<PuntiVendita> puntiVendita = DB.getPuntivendita();
        PuntiVendita puntoVendita = puntiVendita.get(Integer.parseInt(S));
        List<Servizi> servizi = puntoVendita.getServizi();
        Servizi servizio = servizi.get(Integer.parseInt(numSingoloProdotto));

        nomePuntoVendita.setText("Nome punto vendita: " + puntoVendita.getNome());
        nomePuntoVendita.setFont(new Font("Serif", Font.PLAIN, 22));
        nomeProdotto.setText("Nome prodotto: " + servizio.getNome());
        nomeProdotto.setFont(new Font("Serif", Font.PLAIN, 22));
        descrizione.setText("Descrizione: " + servizio.getDescrizione());
        descrizione.setFont(new Font("Serif", Font.PLAIN, 22));
        produttore.setText("Produttore: " + servizio.getProduttore());
        produttore.setFont(new Font("Serif", Font.PLAIN, 22));
        prezzo.setText("Prezzo: " + servizio.getPrezzo());
        prezzo.setFont(new Font("Serif", Font.PLAIN, 22));
        disponibilita.setText("Disponibilità: " + servizio.getDiponivilita());
        disponibilita.setFont(new Font("Serif", Font.PLAIN, 22));

        List<ICategoria> categorie = puntoVendita.getCategorie();
        Categoria categoriaa = (Categoria) categorie.get(servizio.getNumCategoria());

        categoria.setText("Categoria: " + categoriaa.getnome());
        categoria.setFont(new Font("Serif", Font.PLAIN, 22));

        List<ICategoria> sottocategorie = categoriaa.getSottocategorie();
        Sottocategoria sottocategoriaa = (Sottocategoria) sottocategorie.get(servizio.getNumSottocategoria());

        sottocategoria.setText("Sottocategoria: " + sottocategoriaa.getnome());
        sottocategoria.setFont(new Font("Serif", Font.PLAIN, 22));

        corsia.setText(" ");
        scaffale.setText(" ");

        /* IMG */

        for (int i=0; i<servizio.getPathIMG().size();i++){

            BufferedImage image = ImageIO.read(new File("src/img/prodotti/StandardIMG.png"));
            try {
                image = ImageIO.read(new File(servizio.getSiglePath(i).toString()));
                System.out.println(servizio.getPathIMG().toString());
            } catch (IOException e) {
                e.printStackTrace();
            }

            Image dimg = image.getScaledInstance(300, 300, Image.SCALE_SMOOTH);
            ImageIcon imageIcon = new ImageIcon(dimg);

            if (i==0){
                img.setIcon(imageIcon);
                img.setHorizontalAlignment(0);
            }

            if (i==1){
                img1.setIcon(imageIcon);
                img1.setHorizontalAlignment(0);
            }

            if (i==2){
                img2.setIcon(imageIcon);
                img2.setHorizontalAlignment(0);
            }
            if (i==3){
                img3.setIcon(imageIcon);
                img3.setHorizontalAlignment(0);
            }

        }


    }

    public void selezioneProdottoC(MenuPrincipale M, VisualizzaProdottoComposito V, String S, String numSingoloProdotto) throws IOException {

        try{


            FakeDatabase DB = M.getFD();
            List<PuntiVendita> puntiVendita = DB.getPuntivendita();
            PuntiVendita puntoVendita = puntiVendita.get(Integer.parseInt(S));
            List<IProdotti> prodotti = puntoVendita.getProdotti();
            ProdottoComposito prodottoComposito = (ProdottoComposito) prodotti.get(Integer.parseInt(numSingoloProdotto));
            Prodotti prodotto = (Prodotti) prodottoComposito.getSottoprodotti().get(Integer.parseInt(V.getNumProd()));



            nomePuntoVendita.setText("Nome punto vendita: " + puntoVendita.getNome());
            nomePuntoVendita.setFont(new Font("Serif", Font.PLAIN, 22));
            nomeProdotto.setText("Nome prodotto: " + prodotto.getNome());
            nomeProdotto.setFont(new Font("Serif", Font.PLAIN, 22));
            descrizione.setText("Descrizione: " + prodotto.getDescrizione());
            descrizione.setFont(new Font("Serif", Font.PLAIN, 22));
            produttore.setText("Produttore: " + prodotto.getProduttore());
            produttore.setFont(new Font("Serif", Font.PLAIN, 22));
            prezzo.setText("Prezzo: " + prodotto.getPrezzo());
            prezzo.setFont(new Font("Serif", Font.PLAIN, 22));
            disponibilita.setText("Disponibilità: " + prodotto.getDiponivilita());
            disponibilita.setFont(new Font("Serif", Font.PLAIN, 22));

            List<ICategoria> categorie = puntoVendita.getCategorie();
            Categoria categoriaa = (Categoria) categorie.get(prodotto.getNumCategoria());

            categoria.setText("Categoria: " + categoriaa.getnome());
            categoria.setFont(new Font("Serif", Font.PLAIN, 22));

            List<ICategoria> sottocategorie = categoriaa.getSottocategorie();
            Sottocategoria sottocategoriaa = (Sottocategoria) sottocategorie.get(prodotto.getNumSottocategoria());

            sottocategoria.setText("Sottocategoria: " + sottocategoriaa.getnome());
            sottocategoria.setFont(new Font("Serif", Font.PLAIN, 22));

            List<Corsia> corsie = puntoVendita.getCorsie();
            Corsia corsiaa = corsie.get(prodotto.getNumCorsia());

            corsia.setText("Corsia: " + corsiaa.getCodCorsia());
            corsia.setFont(new Font("Serif", Font.PLAIN, 22));

            List<Scaffale> scaffali = corsiaa.getScaffali();
            Scaffale scaffalee = scaffali.get(prodotto.getNumScaffale());

            scaffale.setText("Scaffale: " + scaffalee.getNumero());
            scaffale.setFont(new Font("Serif", Font.PLAIN, 22));

            /* IMG */

            for (int i=0; i<prodotto.getPathIMG().size();i++){

                BufferedImage image = ImageIO.read(new File("src/img/prodotti/StandardIMG.png"));
                try {
                    image = ImageIO.read(new File(prodotto.getSiglePath(i).toString()));
                    System.out.println(prodotto.getSiglePath(i).toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Image dimg = image.getScaledInstance(300, 300, Image.SCALE_SMOOTH);
                ImageIcon imageIcon = new ImageIcon(dimg);

                if (i==0){
                    img.setIcon(imageIcon);
                    img.setHorizontalAlignment(0);
                }

                if (i==1){
                    img1.setIcon(imageIcon);
                    img1.setHorizontalAlignment(0);
                }

                if (i==2){
                    img2.setIcon(imageIcon);
                    img2.setHorizontalAlignment(0);
                }
                if (i==3){
                    img3.setIcon(imageIcon);
                    img3.setHorizontalAlignment(0);
                }

            }

        }
        catch (Exception e){

        }


    }

    public boolean getisProdotto() {
        return isProdotto;
    }

    public void setisProdotto(boolean prodotto) {
        isProdotto = prodotto;
    }

    @Override
    public JFrame getFinestra() {
        return this;
    }
}
