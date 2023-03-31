package View.Sottomenu_MenuAmministratore;

import Controller.ControllerSottomenu_MenuAmministratore.AggiungiPuntoVenditaController;
import View.ControlloFinestre.IFinestre;
import View.MenuAmministratore;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

//Menu per la creazione del punto vendita, si inserisce il nome del nuovo punto
public class AggiungiPuntoVendita extends JFrame implements IFinestre {

    JTextField nome_punto_vendita = new JTextField();

    public AggiungiPuntoVendita(){super("MyShop");}

    //questa finestra essendo sottofinestra di MenuAmministratore, deve prendere i dati che vuole modificare da MenuAmministratore
    public void StartAggiungiPuntoVendita(MenuAmministratore M){

        this.setSize(1000,1000);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        JPanel p = new JPanel(new GridLayout(2,1));

        JLabel l1 = new JLabel("Inserisci il nome del punto vendita: ");
        l1.setHorizontalAlignment( JLabel.CENTER );
        p.add(l1); p.add(nome_punto_vendita);

        JPanel p1 = new JPanel(new GridLayout(1,2));
        JButton b1 = new JButton("Conferma");
        JButton b2 = new JButton("Indietro");
        p1.add(b1); p1.add(b2);

        this.getContentPane().add(p,BorderLayout.CENTER);
        this.getContentPane().add(p1,BorderLayout.SOUTH);

        this.setVisible(true);

        //GLi passo al listener sia questa finestra che la finestra di partenza, la finestra padre che contiene i
        //dati da modificare, che è MenuAmministratore
        ActionListener A = new AggiungiPuntoVenditaController(this,M);

        b2.addActionListener(A);
        b2.setActionCommand("Indietro");

        b1.addActionListener(A);
        b1.setActionCommand("Conferma");

        nome_punto_vendita.addActionListener(A);
        nome_punto_vendita.setActionCommand("Conferma");

    }

    public String getNomePuntoVendita(){
        return nome_punto_vendita.getText();
    }

    @Override
    public JFrame getFinestra() {
        return this;
    }
}
