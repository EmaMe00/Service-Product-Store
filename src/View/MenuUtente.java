package View;

import Controller.MenuUtenteController;
import Model.FakeDatabase;
import View.ControlloFinestre.IFinestre;

import javax.management.ImmutableDescriptor;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

//Frame del menu principale per gli utenti
public class MenuUtente extends JFrame implements IFinestre {

    public MenuUtente(){super("MyShop");}

    public void StartMenuUtente(MenuPrincipale M){

        //Debug ------
        //FakeDatabase f = new FakeDatabase();
        //f.setPuntiVendita(M.getFD().getPuntivendita());
        //---------

        this.setSize(1000,1000);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        JPanel p1 = new JPanel(new GridLayout(1,2));

        JButton b1 = new JButton("Visualizza Punti Vendita");
        JButton b2 = new JButton("Visualizza Carrello");

        p1.add(b1);p1.add(b2);

        JPanel p2 = new JPanel(new GridLayout(1,1));
        JButton b3 = new JButton("Indietro");
        p2.add(b3);

        this.getContentPane().add(p1,BorderLayout.CENTER);
        this.getContentPane().add(p2,BorderLayout.SOUTH);


        this.setVisible(true);

        ActionListener A = new MenuUtenteController(this,M);

        b1.addActionListener(A);
        b1.setActionCommand("Visualizza Punti Vendita");

        b2.addActionListener(A);
        b2.setActionCommand("Visualizza Carrello");

        b3.addActionListener(A);
        b3.setActionCommand("Indietro");

    }

    @Override
    public JFrame getFinestra() {
        return this;
    }

}
