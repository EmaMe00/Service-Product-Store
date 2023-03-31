package View.Sottomenu_MenuAmministratore.Sottomenu_MenuAmministratore_ModificaPuntoVendita.Sottomenu_MenuAmministratore_ModificaPuntoVendita_Prodotti.Sottomenu_MenuAmministratore_ModificaPuntoVendita_Prodotti_NUOVO;

import Controller.ControllerSottomenu_MenuAmministratore.ControllerSottomenu_MenuAmministratore_ModificaPuntoVenditaController.ControllerSottomenu_MenuAmministratore_ModificaPuntoVenditaController_ModificheProdottiController.ControllerSottomenu_MenuAmministratore_ModificaPuntoVenditaController_ModificheProdottiController_NuovoProdottoController.NuovoProdottoController;
import Model.Merce.Categoria;
import Model.Merce.ICategoria;
import Model.Merce.PuntiVendita;
import View.ControlloFinestre.IFinestre;
import View.MenuAmministratore;
import View.MenuPrincipale;
import View.Sottomenu_MenuAmministratore.Sottomenu_MenuAmministratore_ModificaPuntoVendita.MenuModifichePuntoVendita;
import View.Sottomenu_MenuAmministratore.Sottomenu_MenuAmministratore_ModificaPuntoVendita.Sottomenu_MenuAmministratore_ModificaPuntoVendita_Prodotti.ModificheProdotti;

import javax.swing.*;
import javax.xml.transform.Source;
import java.awt.*;
import java.awt.event.ActionListener;

//Frame per l'inserimento di un nuovo prodotto
public class NuovoProdotto extends JFrame implements IFinestre {

    private JTextField nome = new JTextField();
    private JTextField descrizione = new JTextField();
    private JTextField costo = new JTextField();
    private JTextField produttore = new JTextField();
    private JTextField disponibita = new JTextField();

    private JTextField categoria = new JTextField();
    private JTextField sottocategoria = new JTextField();

    private JTextField corsia = new JTextField();
    private JTextField scaffale = new JTextField();

    public NuovoProdotto(){ super("MyShop"); }

    public void StartNuovoProdotto(MenuAmministratore M, ModificheProdotti M1, String S, MenuModifichePuntoVendita M2,boolean a){

        this.setSize(1000,1000);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        JPanel p1 = new JPanel(new GridLayout(12,2));
        JLabel l1 = new JLabel("INSERISCI NUOVO PRODOTTO",SwingConstants.CENTER);
        JLabel space = new JLabel(" ");
        p1.add(l1); p1.add(space);

        JLabel lnome = new JLabel("Nome",SwingConstants.CENTER);
        JLabel ldescrizione = new JLabel("Descrizione",SwingConstants.CENTER);
        JLabel lcosto = new JLabel("Costo",SwingConstants.CENTER);
        JLabel lproduttore = new JLabel("Produttore",SwingConstants.CENTER);
        p1.add(lnome); p1.add(nome); p1.add(ldescrizione); p1.add(descrizione); p1.add(lcosto); p1.add(costo);
        p1.add(lproduttore); p1.add(produttore);

        JLabel ldisponibilita = new JLabel("Disponibilità",SwingConstants.CENTER);
        p1.add(ldisponibilita); p1.add(disponibita);

        JLabel l2 = new JLabel("CATEGORIE",SwingConstants.CENTER);
        JLabel space2 = new JLabel(" ");
        p1.add(l2); p1.add(space2);

        JLabel lcategoria = new JLabel("NUMERO CATEGORIA",SwingConstants.CENTER);
        JLabel lsottocategoria = new JLabel("NUMERO SOTTOCATEGORIA",SwingConstants.CENTER);
        p1.add(lcategoria); p1.add(categoria); p1.add(lsottocategoria); p1.add(sottocategoria);

        JLabel l3 = new JLabel("CORSIE",SwingConstants.CENTER);
        JLabel space3 = new JLabel(" ");
        p1.add(l3);p1.add(space3);

        JLabel lcorsia = new JLabel("NUMERO CORSIA",SwingConstants.CENTER);
        JLabel lscaffale = new JLabel("INSERISCI UN NUOVO CODICE SCAFFALE (NUMERICO)",SwingConstants.CENTER);
        p1.add(lcorsia); p1.add(corsia); p1.add(lscaffale); p1.add(scaffale);

        this.getContentPane().add(p1,BorderLayout.NORTH);

        JScrollPane tmp = VisualizzaCategorieList(M,S);
        JScrollPane tmp1 = VisualizzaCorsieList(M,S);

        JPanel p2 = new JPanel(new GridLayout(2,1));
        p2.add(tmp); p2.add(tmp1);
        this.getContentPane().add(p2,BorderLayout.CENTER);

        JPanel p7 = new JPanel(new GridLayout(2,2));
        JLabel limmagine = new JLabel("CARICA IMMAGINE",SwingConstants.CENTER);
        JButton immagine = new JButton("CARICA");
        JButton indietro = new JButton("INDIETRO");
        JButton salva = new JButton("SALVA");
        p7.add(limmagine); p7.add(immagine); p7.add(indietro); p7.add(salva);

        this.getContentPane().add(p7,BorderLayout.SOUTH);

        this.setVisible(true);

        ActionListener A = new NuovoProdottoController(this,M,M1,S,M2,a);

        indietro.addActionListener(A);
        indietro.setActionCommand("Indietro");

        immagine.addActionListener(A);
        immagine.setActionCommand("Carica");

        salva.addActionListener(A);
        salva.setActionCommand("Salva");

    }

    //Generazione di una lista di corsie
    public JScrollPane VisualizzaCorsieList(MenuAmministratore M,String S){

        JPanel panel = new JPanel(new GridLayout(1,1));
        DefaultListModel model = new DefaultListModel();
        model.addElement("ELENCO CORSIE");
        PuntiVendita tmp = M.getPuntivendita().get(Integer.parseInt(S));

        for (int i=0;i<tmp.getCorsie().size();i++){
            String stmp = i + ") "+ tmp.getCorsie().get(i).getCodCorsia();
            model.addElement(stmp);
            for (int j=0;j<tmp.getCorsie().get(i).getScaffali().size();j++){
                int scaftmp = tmp.getCorsie().get(i).getScaffali().get(j).getNumero();
                String scaftmps = "  " + j + " - " + scaftmp;
                model.addElement(scaftmps);
            }
        }

        JList lista = new JList();
        lista.setModel(model);

        JScrollPane scroll = new JScrollPane(lista);

       // panel.add(scroll);

      //  this.getContentPane().add(panel,BorderLayout.CENTER);

        return scroll;

    }

    //Generazione di una lista di categorie
    public JScrollPane VisualizzaCategorieList(MenuAmministratore M,String S){

        JPanel panel = new JPanel(new GridLayout(1,1));
        DefaultListModel model = new DefaultListModel();
        model.addElement("ELENCO CATEGORIE");
        PuntiVendita tmp = M.getPuntivendita().get(Integer.parseInt(S));

        for (int i=0;i<tmp.getCategorie().size();i++){
            ICategoria ICtmp = tmp.getCategorie().get(i);
            Categoria Ctmp = (Categoria) ICtmp;
            String stmp = i + ") " +  Ctmp.getnome();
            model.addElement(stmp);
            for (int j=0; j<Ctmp.getSottocategorie().size(); j++){
                String sstmp = "  " + j + " - " + Ctmp.getSottocategorie().get(j).getnome();
                model.addElement(sstmp);
            }
        }

        JList lista = new JList();
        lista.setModel(model);

        JScrollPane scroll = new JScrollPane(lista);

        //panel.add(scroll);

        //this.getContentPane().add(panel,BorderLayout.CENTER);
        return scroll;

    }

    public String getNome() {
        return nome.getText();
    }

    public String getDescrizione() {
        return descrizione.getText();
    }

    public String getCosto() {
        return costo.getText();
    }

    public String getProduttore() {
        return produttore.getText();
    }

    public String getCategoria() {
        return categoria.getText();
    }

    public String getSottocategoria() {
        return sottocategoria.getText();
    }

    public String getCorsia() {
        return corsia.getText();
    }

    public String getScaffale() {
        return scaffale.getText();
    }

    public String getDisponibilita() { return  disponibita.getText(); }

    @Override
    public JFrame getFinestra() {
        return this;
    }
}
