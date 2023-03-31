package View;

import Controller.InvioEmailController;
import View.ControlloFinestre.IFinestre;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

//Frame per inviare le email da parte del manager
public class InvioEmail extends JFrame implements IFinestre {

    JTextField numeroUtente = new JTextField();
    JTextField testo = new JTextField();

    public InvioEmail(){super("MyShop");}

    public void StartInvioEmail(MenuPrincipale M, int numPuntoVendita, MenuManager M1){

        this.setSize(1000,1000);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        JPanel p1 = new JPanel(new GridLayout(2,2));
        JLabel l1 = new JLabel("Numero Utente: ");
        l1.setHorizontalAlignment(JLabel.CENTER);
        JLabel l2 = new JLabel("Testo del messaggio: ");
        l2.setHorizontalAlignment(JLabel.CENTER);

        p1.add(l1); p1.add(numeroUtente); p1.add(l2); p1.add(testo);

        JPanel p2 = new JPanel(new GridLayout(1,3));
        JButton b1 = new JButton("INDIETRO");
        JButton b2 = new JButton("INVIA");
        JButton b3 = new JButton("ELIMINA ISCRIZIONE");
        p2.add(b1); p2.add(b2); p2.add(b3);

        JScrollPane scroll =  VisualizzaUtenti(M,numPuntoVendita);
        JPanel p3 = new JPanel(new GridLayout(1,1));
        p3.add(scroll);

        this.getContentPane().add(p1,BorderLayout.NORTH);
        this.getContentPane().add(p2,BorderLayout.SOUTH);
        this.getContentPane().add(p3,BorderLayout.CENTER);

        this.setVisible(true);

        ActionListener A = new InvioEmailController(M,numPuntoVendita,M1,this);

        b1.addActionListener(A);
        b1.setActionCommand("Indietro");

        b2.addActionListener(A);
        b2.setActionCommand("Invia");

        numeroUtente.addActionListener(A);
        numeroUtente.setActionCommand("Invia");

        testo.addActionListener(A);
        testo.setActionCommand("Invia");

        b3.addActionListener(A);
        b3.setActionCommand("Elimina");

    }


    //Metodo per visualizzare gli utenti in una lista numerata
    public JScrollPane VisualizzaUtenti(MenuPrincipale M1, int numPuntoVendita){

        JPanel panel = new JPanel(new GridLayout(1,1));
        DefaultListModel model = new DefaultListModel();

        for (int i=0;i<M1.getFD().getListaUtenti().size();i++){
            if (M1.getFD().getListaUtenti().get(i).getNumPuntoVendita() == numPuntoVendita){
                String tmp = i+") " + M1.getFD().getListaUtenti().get(i).getNome() + " " + M1.getFD().getListaUtenti().get(i).getCognome() + " " + M1.getFD().getListaUtenti().get(i).getEmail();
                model.addElement(tmp);
            }
        }

        JList lista = new JList();
        lista.setModel(model);

        JScrollPane scroll = new JScrollPane(lista);

        panel.add(scroll);

        return scroll;

    }

    public String getNumeroUtente(){
        return numeroUtente.getText();
    }

    public String getTesto(){
        return testo.getText();
    }

    @Override
    public JFrame getFinestra() {
        return this;
    }

}
