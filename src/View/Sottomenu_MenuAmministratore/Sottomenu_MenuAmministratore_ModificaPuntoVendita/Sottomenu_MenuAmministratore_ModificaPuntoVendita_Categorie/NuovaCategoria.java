package View.Sottomenu_MenuAmministratore.Sottomenu_MenuAmministratore_ModificaPuntoVendita.Sottomenu_MenuAmministratore_ModificaPuntoVendita_Categorie;

import Controller.ControllerSottomenu_MenuAmministratore.ControllerSottomenu_MenuAmministratore_ModificaPuntoVenditaController.ControllerSottomenu_MenuAmministratore_ModificaPuntoVenditaController_ModificheProdottiController.ControllerSottomenu_MenuAmministratore_ModificaPuntoVenditaController_ModificheProdottiController_NuovoProdottoController.NuovoProdottoController;
import Controller.ControllerSottomenu_MenuAmministratore.ControllerSottomenu_MenuAmministratore_ModificaPuntoVenditaController.ControllerSottomenu_MenuAmministratore_ModificaPuntoVenditaController_NuovaCategoriaController.NuovaCategoriaController;
import Model.Merce.Categoria;
import Model.Merce.ICategoria;
import Model.Merce.PuntiVendita;
import View.ControlloFinestre.IFinestre;
import View.MenuAmministratore;
import View.Sottomenu_MenuAmministratore.Sottomenu_MenuAmministratore_ModificaPuntoVendita.MenuModifichePuntoVendita;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

//Frame per l'inserimento di una nuova categoria
public class NuovaCategoria extends JFrame implements IFinestre {

    JTextField categoria = new JTextField();
    JTextField sottocategoria1 = new JTextField();
    JTextField sottocategoria2 = new JTextField();
    JTextField sottocategoria3 = new JTextField();
    boolean managerIdentificator = false;

    public NuovaCategoria(){ super("MyShop"); }

    public void StartNuovaCategoria(MenuAmministratore M, MenuModifichePuntoVendita M1,String S){

        this.setSize(1000,1000);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);


        JPanel p1 = new JPanel(new GridLayout(5,2));
        JLabel l1 = new JLabel("Nome Categoria");
        l1.setHorizontalAlignment(JLabel.CENTER);
        JLabel l2 = new JLabel("Sottocategoria 1");
        l2.setHorizontalAlignment(JLabel.CENTER);
        JLabel l3 = new JLabel("Sottocategoria 2");
        l3.setHorizontalAlignment(JLabel.CENTER);
        JLabel l4 = new JLabel("Sottocategoria 3");
        l4.setHorizontalAlignment(JLabel.CENTER);
        JButton b1 = new JButton("Nuova");
        JButton b4 = new JButton("Elimina");


        p1.add(l1); p1.add(categoria); p1.add(l2); p1.add(sottocategoria1);
        p1.add(l3); p1.add(sottocategoria2); p1.add(l4); p1.add(sottocategoria3);
        p1.add(b1);p1.add(b4);


        this.getContentPane().add(p1,BorderLayout.NORTH);

        VisualizzaCategorieList(M,S);

        JPanel p = new JPanel(new GridLayout(1,1));

        JButton b10 = new JButton("Indietro");

        p.add(b10);

        this.getContentPane().add(p,BorderLayout.SOUTH);

        this.setVisible(true);

        ActionListener A = new NuovaCategoriaController(this,M,M1,S,managerIdentificator);

        b10.addActionListener(A);
        b10.setActionCommand("Indietro");

        b1.addActionListener(A);
        b1.setActionCommand("Nuova");

        categoria.addActionListener(A);
        categoria.setActionCommand("Nuova");

        sottocategoria1.addActionListener(A);
        sottocategoria1.setActionCommand("Nuova");

        sottocategoria2.addActionListener(A);
        sottocategoria2.setActionCommand("Nuova");

        sottocategoria3.addActionListener(A);
        sottocategoria3.setActionCommand("Nuova");

        b4.addActionListener(A);
        b4.setActionCommand("Elimina");



    }

    //Generazione di una lista di categorie numerata
    public void VisualizzaCategorieList(MenuAmministratore M,String S){

        JPanel panel = new JPanel(new GridLayout(M.getPuntivendita().size(),1));
        DefaultListModel model = new DefaultListModel();
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

        panel.add(scroll);

        this.getContentPane().add(panel,BorderLayout.CENTER);

    }

    public String getCategoria() {
        return categoria.getText();
    }

    public String getSottocategoria1() {
        return sottocategoria1.getText();
    }

    public String getSottocategoria2() {
        return sottocategoria2.getText();
    }

    public String getSottocategoria3() {
        return sottocategoria3.getText();
    }

    public boolean getManagerIdentificator(){
        return managerIdentificator;
    }

    public void setManagerIdentificator(boolean a){managerIdentificator = a;}


    @Override
    public JFrame getFinestra() {
        return this;
    }
}
