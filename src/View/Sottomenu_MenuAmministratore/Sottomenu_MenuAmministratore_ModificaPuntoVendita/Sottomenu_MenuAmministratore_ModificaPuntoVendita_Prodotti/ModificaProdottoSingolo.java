package View.Sottomenu_MenuAmministratore.Sottomenu_MenuAmministratore_ModificaPuntoVendita.Sottomenu_MenuAmministratore_ModificaPuntoVendita_Prodotti;

import Controller.ControllerSottomenu_MenuAmministratore.ControllerSottomenu_MenuAmministratore_ModificaPuntoVenditaController.ControllerSottomenu_MenuAmministratore_ModificaPuntoVenditaController_ModificheProdottiController.ModificheProdottoSingoloController;
import Model.Merce.Categoria;
import Model.Merce.ICategoria;
import Model.Merce.Prodotti;
import Model.Merce.PuntiVendita;
import View.ControlloFinestre.IFinestre;
import View.MenuAmministratore;
import View.Sottomenu_MenuAmministratore.Sottomenu_MenuAmministratore_ModificaPuntoVendita.MenuModifichePuntoVendita;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

//Frame per la singola modifica dei prodotti
public class ModificaProdottoSingolo extends JFrame implements IFinestre {

    private JTextField nome = new JTextField();
    private JTextField descrizione = new JTextField();
    private JTextField costo = new JTextField();
    private JTextField produttore = new JTextField();
    private JTextField disponibita = new JTextField();

    private JTextField categoria = new JTextField();
    private JTextField sottocategoria = new JTextField();

    private JTextField corsia = new JTextField();
    private JTextField scaffale = new JTextField();

    public ModificaProdottoSingolo(){super("MyShop");}

    public void StartModificaProdottoSingolo(MenuAmministratore M, MenuModifichePuntoVendita M1, String S,ModificheProdotti M2){

        this.setSize(800,600);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        JPanel p1 = new JPanel(new GridLayout(12,2));
        JLabel l1 = new JLabel("MODIFICA PRODOTTO",SwingConstants.CENTER);
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

        SetDatiLabel(M,S,M2);

        this.getContentPane().add(p7,BorderLayout.SOUTH);

        this.setVisible(true);

        ActionListener A = new ModificheProdottoSingoloController(this,M2,M,M1,S);

        immagine.addActionListener(A);
        immagine.setActionCommand("Carica");

        indietro.addActionListener(A);
        indietro.setActionCommand("Indietro");

        salva.addActionListener(A);
        salva.setActionCommand("Salva");

    }

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

    public void SetDatiLabel(MenuAmministratore M, String S, ModificheProdotti M2){

        if (!M.getPuntivendita().get(Integer.parseInt(S)).getProdotti().get(Integer.parseInt(M2.getTextField())).getPC()){

            Prodotti prodotto = (Prodotti) M.getPuntivendita().get(Integer.parseInt(S)).getProdotti().get(Integer.parseInt(M2.getTextField()));
            nome.setText(prodotto.getnome());
            descrizione.setText(prodotto.getDescrizione());
            costo.setText(String.valueOf(prodotto.getprezzo()));
            produttore.setText(prodotto.getProduttore());
            disponibita.setText(String.valueOf(prodotto.getDiponivilita()));
            categoria.setText(String.valueOf(prodotto.getNumCategoria()));
            sottocategoria.setText(String.valueOf(prodotto.getNumSottocategoria()));
            corsia.setText(String.valueOf(prodotto.getNumCorsia()));
            scaffale.setText(String.valueOf(prodotto.getNumScaffale()));

        }


    }

    @Override
    public JFrame getFinestra() {
        return this;
    }

    public String getNome() {
        return nome.getText();
    }

    public void setNome(String nome) {
        this.nome.setText(nome);
    }

    public String getDescrizione() {
        return descrizione.getText();
    }

    public void setDescrizione(String descrizione) {
        this.descrizione.setText(descrizione);
    }

    public String getCosto() {
        return costo.getText();
    }

    public void setCosto(String costo) {
        this.costo.setText(costo);
    }

    public String getProduttore() {
        return produttore.getText();
    }

    public void setProduttore(String produttore) {
        this.produttore.setText(produttore);
    }

    public String getDisponibita() {
        return disponibita.getText();
    }

    public void setDisponibita(String disponibita) {
        this.disponibita.setText(disponibita);
    }

    public String getCategoria() {
        return categoria.getText();
    }

    public void setCategoria(String categoria) {
        this.categoria.setText(categoria);
    }

    public String getSottocategoria() {
        return sottocategoria.getText();
    }

    public void setSottocategoria(String sottocategoria) {
        this.sottocategoria.setText(sottocategoria);
    }

    public String getCorsia() {
        return corsia.getText();
    }

    public void setCorsia(String corsia) {
        this.corsia.setText(corsia);
    }

    public String getScaffale() {
        return scaffale.getText();
    }

    public void setScaffale(String scaffale) {
        this.scaffale.setText(scaffale);
    }
}
