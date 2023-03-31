package View;

import Controller.ControllerSottomenu_MenuAmministratore.ControllerSottomenu_MenuAmministratore_ModificaPuntoVenditaController.MenuModifichePuntoVenditaController;
import Controller.MenuManagerController;
import View.ControlloFinestre.IFinestre;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.sql.SQLException;

//Frame principale per il Manager
public class MenuManager extends JFrame implements IFinestre {

    public MenuManager(){ super("MyShop"); }

    public void StartMenuManager(MenuPrincipale M, int numPuntoVendita) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {

        this.setSize(1000,1000);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        JPanel p = new JPanel(new GridLayout(2,3));

        JButton b1 = new JButton("Nome");
        JButton b3 = new JButton("Prodotti");
        JButton b4 = new JButton("Categorie e Sottocategorie");
        JButton b5 = new JButton("Corsie e Scaffali");
        JButton b6 = new JButton("Servizi");
        JButton b7 = new JButton("Visualizza prodotti e punti vendita");


        p.add(b1); p.add(b3);p.add(b4); p.add(b5);p.add(b6);p.add(b7);


        JPanel p1 = new JPanel(new GridLayout(1,2));

        JButton b10 = new JButton("Indietro");
        JButton b11 = new JButton("Manda email ad un utente o elimina iscrizione");

        p1.add(b10);p1.add(b11);

        this.getContentPane().add(p1,BorderLayout.SOUTH);
        this.getContentPane().add(p,BorderLayout.CENTER);

        this.setVisible(true);

        ActionListener A = new MenuManagerController(this,M,numPuntoVendita);

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

        b7.addActionListener(A);
        b7.setActionCommand("Visualizza");

        b11.addActionListener(A);
        b11.setActionCommand("Email");

    }

    @Override
    public JFrame getFinestra() {
        return this;
    }

}
