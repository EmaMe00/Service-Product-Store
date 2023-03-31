package View.Sottomenu_MenuAmministratore.Sottomenu_MenuAmministratore_ModificaPuntoVendita;

import Controller.ControllerSottomenu_MenuAmministratore.ControllerSottomenu_MenuAmministratore_ModificaPuntoVenditaController.MenuModifichePuntoVenditaController;
import View.ControlloFinestre.IFinestre;
import View.MenuAmministratore;
import View.Sottomenu_MenuAmministratore.ModificaPuntoVendita;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

//Menu per le gestire tutte le modifiche che si possono apportare al punto vendita
public class MenuModifichePuntoVendita extends JFrame implements IFinestre {

    public MenuModifichePuntoVendita(){ super("MyShop"); }

    public void StartModifichePuntoVendita(ModificaPuntoVendita a, MenuAmministratore a1, String s){

        this.setSize(1000,1000);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        JPanel p = new JPanel(new GridLayout(2,3));

        JButton b1 = new JButton("Nome");
        JButton b3 = new JButton("Prodotti");
        JButton b4 = new JButton("Categorie e Sottocategorie");
        JButton b5 = new JButton("Corsie e Scaffali");
        JButton b6 = new JButton("Servizi");


        p.add(b1); p.add(b3);p.add(b4); p.add(b5); p.add(b6);


        JPanel p1 = new JPanel(new GridLayout(1,1));

        JButton b10 = new JButton("Indietro");

        p1.add(b10);

        this.getContentPane().add(p1,BorderLayout.SOUTH);
        this.getContentPane().add(p,BorderLayout.CENTER);

        this.setVisible(true);

        ActionListener A = new MenuModifichePuntoVenditaController(this,a,a1,s);

        b10.addActionListener(A);
        b10.setActionCommand("Indietro");

        b1.addActionListener(A);
        b1.setActionCommand("Nome");

        b3.addActionListener(A);
        b3.setActionCommand("Prodotti");

        b4.addActionListener(A);
        b4.setActionCommand("Categorie");

        b5.addActionListener(A);
        b5.setActionCommand("Corsie");

        b6.addActionListener(A);
        b6.setActionCommand("Servizi");

    }

    @Override
    public JFrame getFinestra() {
        return this;
    }
}
