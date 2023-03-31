package View.Sottomenu_MenuAmministratore.Sottomenu_MenuAmministratore_ModificaPuntoVendita.Sottomenu_MenuAmministratore_ModificaPuntoVendita_Servizi.Sottomenu_MenuAmministratore_ModificaPuntoVendita_Servizi_NuovoServizio;

import Controller.ControllerSottomenu_MenuAmministratore.ControllerSottomenu_MenuAmministratore_ModificaPuntoVenditaController.ControllerSottomenu_MenuAmministratore_ModificaPuntoVenditaController_MenuServiziController.ControllerSottomenu_MenuAmministratore_ModificaPuntoVenditaController_MenuServiziController_NuovoServizioController.NuovoServizioController;
import Controller.ControllerSottomenu_MenuAmministratore.ControllerSottomenu_MenuAmministratore_ModificaPuntoVenditaController.ControllerSottomenu_MenuAmministratore_ModificaPuntoVenditaController_ModificheProdottiController.ControllerSottomenu_MenuAmministratore_ModificaPuntoVenditaController_ModificheProdottiController_NuovoProdottoController.NuovoProdottoController;
import Model.Merce.Categoria;
import Model.Merce.ICategoria;
import Model.Merce.PuntiVendita;
import View.ControlloFinestre.IFinestre;
import View.MenuAmministratore;
import View.Sottomenu_MenuAmministratore.Sottomenu_MenuAmministratore_ModificaPuntoVendita.Sottomenu_MenuAmministratore_ModificaPuntoVendita_Servizi.MenuServizi;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

//Frame per la crezione di un nuovo servizio
public class NuovoServizio extends JFrame implements IFinestre {

    JTextField nome = new JTextField();
    JTextField descrizione = new JTextField();
    JTextField costo = new JTextField();
    JTextField produttore = new JTextField();
    JTextField disponibita = new JTextField();

    JTextField categoria = new JTextField();
    JTextField sottocategoria = new JTextField();


    public NuovoServizio(){ super("MyShop"); }

    public void StartNuovoServizio(MenuAmministratore M, MenuServizi MS,String S){

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


        this.getContentPane().add(p1,BorderLayout.NORTH);

        JScrollPane tmp = VisualizzaCategorieList(M,S);

        JPanel p2 = new JPanel(new GridLayout(1,1));
        p2.add(tmp);
        this.getContentPane().add(p2,BorderLayout.CENTER);

        JPanel p7 = new JPanel(new GridLayout(2,2));
        JLabel limmagine = new JLabel("CARICA IMMAGINE",SwingConstants.CENTER);
        JButton immagine = new JButton("CARICA");
        JButton indietro = new JButton("INDIETRO");
        JButton salva = new JButton("SALVA");
        p7.add(limmagine); p7.add(immagine); p7.add(indietro); p7.add(salva);

        this.getContentPane().add(p7,BorderLayout.SOUTH);

        this.setVisible(true);

        ActionListener A = new NuovoServizioController(M,this,MS,S);

        indietro.addActionListener(A);
        indietro.setActionCommand("Indietro");

        immagine.addActionListener(A);
        immagine.setActionCommand("Carica");

        salva.addActionListener(A);
        salva.setActionCommand("Salva");

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

    public String getDisponibilita() { return  disponibita.getText(); }

    @Override
    public JFrame getFinestra() {
        return this;
    }

}
