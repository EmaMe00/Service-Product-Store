package View.Sottomenu_MenuUtente.Sottomenu_MenuUtente_VisualizzaPuntiVenditaUtente.Sottomenu_MenuUtente_VisualizzaPuntiVenditaUtente_SingoloPuntoVendita;

import Controller.ControllerSottomenu_MenuUtente.ControllerSottomenu_MenuUtente_VisualizzaPuntiVenditaController.ControllerSottomenu_MenuUtente_VisualizzaPuntiVenditaController_VisualizzazioneSingoloProdottoController.VisualizzaRecensioniController;
import Model.Merce.IProdotti;
import Model.Merce.Prodotti;
import Model.Merce.Servizi;
import View.ControlloFinestre.IFinestre;
import View.MenuPrincipale;
import View.Sottomenu_MenuUtente.Sottomenu_MenuUtente_VisualizzaPuntiVenditaUtente.VisualizzazioneProdottiUtente;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

//Frame per la visualizzazione delle recensioni dei prodotti o dei servizi
public class VisualizzaRecensioni extends JFrame implements IFinestre {

    //Valori che mi servono per capire se sto lavorando con un servizio o con un prodotto, se il valore è differente
    //da -1 allora stiamo lavorando con quella tipologia di dato
    private int numeroProdotto = -1;
    private int numeroServizio = -1;
    //voto riguardante il prodotto o servizio
    private double voto = 0;

    public VisualizzaRecensioni(){super("MyShop");}

    public void StartVisualizzaRecensioni(MenuPrincipale M, VisualizzazioneProdottiUtente V, String S, VisualizzazioneSingoloProdotto V1){

        this.setSize(1000,1000);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        JPanel p1 = new JPanel(new GridLayout(1,5));
        JButton b1 = new JButton("INDIETRO");
        JButton b2 = new JButton("AGGIUNGI RECENSIONE");
        JButton b3 = new JButton("VOTA PRODOTTO");
        JButton b4 = new JButton("ELIMINA VOTO");
        JButton b5 = new JButton("ELIMINA RECENSIONE");
        p1.add(b1); p1.add(b2); p1.add(b3); p1.add(b4); p1.add(b5);

        VisualizzazioneRecensioni(V,V1,M,S);

        JPanel p2 = new JPanel(new GridLayout(1,1));
        JLabel Jvoto = new JLabel(" VOTO: " + voto);
        Jvoto.setHorizontalAlignment(JLabel.CENTER);
        p2.add(Jvoto);

        this.getContentPane().add(p2,BorderLayout.NORTH);

        this.getContentPane().add(p1,BorderLayout.SOUTH);

        this.setVisible(true);

        ActionListener A = new VisualizzaRecensioniController(M,V,S,V1,this);

        b1.addActionListener(A);
        b1.setActionCommand("Indietro");

        b2.addActionListener(A);
        b2.setActionCommand("Aggiungi recensione");

        b3.addActionListener(A);
        b3.setActionCommand("Aggiungi voto");

        b4.addActionListener(A);
        b4.setActionCommand("Elimina voto");

        b5.addActionListener(A);
        b5.setActionCommand("Elimina recensioni");

    }

    //Visualizzazione della lista di recensioni riguardante il prodotto o servizio
    public void VisualizzazioneRecensioni(VisualizzazioneProdottiUtente V,VisualizzazioneSingoloProdotto V1, MenuPrincipale M1, String S){

        if (V1.getisProdotto()){//se è un prodotto

            java.util.List<IProdotti> prodotti = M1.getFD().getPuntivendita().get(Integer.parseInt(S)).getProdotti();
            Prodotti prodotto = (Prodotti) prodotti.get(Integer.parseInt(V.getNumProdotto()));
            numeroProdotto = Integer.parseInt(V.getNumProdotto());
            voto = prodotto.getMedia();

            JPanel panel = new JPanel(new GridLayout(prodotto.getRecensioni().size(),1));
            DefaultListModel model = new DefaultListModel();

            model.addElement("RECENSIONI: \n\n");

            for (int i=0;i<prodotto.getRecensioni().size();i++){
                String tmp = i+" - " + prodotto.getRecensioni().get(i).getTestoRecensione();
                String tmp1 = "\n" + prodotto.getRecensioni().get(i).getNome() + " " + prodotto.getRecensioni().get(i).getCognome();
                String tmp2 = "\n" + prodotto.getRecensioni().get(i).getData();
                model.addElement(tmp);
                model.addElement(tmp1);
                model.addElement(tmp2);
            }

            JList lista = new JList();
            lista.setModel(model);

            JScrollPane scroll = new JScrollPane(lista);

            panel.add(scroll);

            this.getContentPane().add(panel,BorderLayout.CENTER);


        }else {//Se è un servizio
            List<Servizi> servizi = M1.getFD().getPuntivendita().get(Integer.parseInt(S)).getServizi();
            Servizi servizio = servizi.get(Integer.parseInt(V.getNumServizio()));
            numeroServizio = Integer.parseInt(V.getNumServizio());

            voto = servizio.getMedia();

            JPanel panel = new JPanel(new GridLayout(servizio.getRecensioni().size(),1));
            DefaultListModel model = new DefaultListModel();

            model.addElement("RECENSIONI: ");

            for (int i=0;i<servizio.getRecensioni().size();i++){
                String tmp = i+" - " + servizio.getRecensioni().get(i).getTestoRecensione();
                String tmp1 = "\n" + servizio.getRecensioni().get(i).getNome() + " " + servizio.getRecensioni().get(i).getCognome();
                String tmp2 = "\n" + servizio.getRecensioni().get(i).getData() + "\n";
                model.addElement(tmp);
                model.addElement(tmp1);
                model.addElement(tmp2);
            }

            JList lista = new JList();
            lista.setModel(model);

            JScrollPane scroll = new JScrollPane(lista);

            panel.add(scroll);

            this.getContentPane().add(panel,BorderLayout.CENTER);
        }


    }

    public int getNumeroProdotto() {
        return numeroProdotto;
    }

    public void setNumeroProdotto(int numeroProdotto) {
        this.numeroProdotto = numeroProdotto;
    }

    public int getNumeroServizio() {
        return numeroServizio;
    }

    public void setNumeroServizio(int numeroServizio) {
        this.numeroServizio = numeroServizio;
    }

    @Override
    public JFrame getFinestra() {
        return this;
    }

}
