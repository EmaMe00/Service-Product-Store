package View.Sottomenu_MenuAmministratore;

import Controller.ControllerSottomenu_MenuAmministratore.GestioneManagerController;
import View.ControlloFinestre.IFinestre;
import View.MenuAmministratore;
import View.MenuPrincipale;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

//Menu per la gestione dei Manager, è solo un menù di selezione

public class GestioneManager extends JFrame implements IFinestre {

    public GestioneManager(){ super("MyShop"); }

    public void StartGestioneManager(MenuAmministratore M, MenuPrincipale M1){

        this.setSize(1000,1000);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        JPanel p1 = new JPanel(new GridLayout(1,2));
        JButton b1 = new JButton("Aggiungi Manager");
        JButton b2 = new JButton("Rimuovi Manager");

        p1.add(b1); p1.add(b2);

        JPanel p2 = new JPanel(new GridLayout(1,1));
        JButton indietro = new JButton("Indietro");
        p2.add(indietro);

        this.getContentPane().add(p2,BorderLayout.SOUTH);
        this.getContentPane().add(p1,BorderLayout.CENTER);
        this.setVisible(true);

        ActionListener A = new GestioneManagerController(M,this,M1);

        b1.addActionListener(A);
        b1.setActionCommand("Aggiungi");

        b2.addActionListener(A);
        b2.setActionCommand("Rimuovi");

        indietro.addActionListener(A);
        indietro.setActionCommand("Indietro");

    }

    @Override
    public JFrame getFinestra() {
        return this;
    }
}
