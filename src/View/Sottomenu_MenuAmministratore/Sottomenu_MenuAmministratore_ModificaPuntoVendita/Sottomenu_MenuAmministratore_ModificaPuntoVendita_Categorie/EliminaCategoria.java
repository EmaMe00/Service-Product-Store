package View.Sottomenu_MenuAmministratore.Sottomenu_MenuAmministratore_ModificaPuntoVendita.Sottomenu_MenuAmministratore_ModificaPuntoVendita_Categorie;

import Controller.ControllerSottomenu_MenuAmministratore.ControllerSottomenu_MenuAmministratore_ModificaPuntoVenditaController.ControllerSottomenu_MenuAmministratore_ModificaPuntoVenditaController_NuovaCategoriaController.EliminaCategoriaController;
import Model.Merce.Categoria;
import Model.Merce.ICategoria;
import Model.Merce.PuntiVendita;
import View.ControlloFinestre.IFinestre;
import View.MenuAmministratore;
import View.Sottomenu_MenuAmministratore.Sottomenu_MenuAmministratore_ModificaPuntoVendita.MenuModifichePuntoVendita;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

//Frame per l'eliminazione di una categoria
public class EliminaCategoria extends JFrame implements IFinestre {

    private JTextField numeroCategoria = new JTextField();

    public EliminaCategoria(){super("MyShop");}

    public void StartEliminaCategoria(MenuAmministratore M, NuovaCategoria N1, String S){

        this.setSize(1000,1000);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        JPanel p1 = new JPanel(new GridLayout(4,2));
        JLabel l0 = new JLabel("Inserire numero categoria");

        JButton b1 = new JButton("Elimina categoria");

        p1.add(l0); p1.add(numeroCategoria);
        p1.add(b1);

        this.getContentPane().add(p1,BorderLayout.NORTH);

        VisualizzaCategorieList(M,S);

        JPanel p = new JPanel(new GridLayout(1,1));

        JButton b10 = new JButton("Indietro");

        p.add(b10);

        this.getContentPane().add(p,BorderLayout.SOUTH);

        this.setVisible(true);

        ActionListener A = new EliminaCategoriaController(M,N1,S,this);

        b10.addActionListener(A);
        b10.setActionCommand("Indietro");

        b1.addActionListener(A);
        b1.setActionCommand("Elimina categoria");


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

    public String getNumeroCat(){return numeroCategoria.getText();}


    @Override
    public JFrame getFinestra() {
        return this;
    }
}
