package View;

import Controller.RegistrazioneController;
import View.ControlloFinestre.IFinestre;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

//Frame per la registrazione degli utenti
public class Registrazione extends JFrame implements IFinestre {

    private JPanel panel1;
    private JTextField nome;
    private JTextField nickname;
    private JTextField cognome;
    private JTextField email;
    private JTextField password;
    private JTextField telefono;
    private JTextField eta;
    private JTextField citta;
    private JLabel label1;
    private JButton b1;
    private JButton b2;

    public Registrazione(){super("MyShop");}

    public void StartRegistrazione(MenuPrincipale M){

        this.setSize(800,600);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);


        this.getContentPane().add(panel1,BorderLayout.CENTER);
        this.setVisible(true);

        ActionListener A = new RegistrazioneController(this,M);

        b1.addActionListener(A);
        b1.setActionCommand("indietro");

        b2.addActionListener(A);
        b2.setActionCommand("Registrati");

        nome.addActionListener(A);
        nome.setActionCommand("Registrati");

        nickname.addActionListener(A);
        nickname.setActionCommand("Registrati");

        cognome.addActionListener(A);
        cognome.setActionCommand("Registrati");

        email.addActionListener(A);
        email.setActionCommand("Registrati");

        password.addActionListener(A);
        password.setActionCommand("Registrati");

        telefono.addActionListener(A);
        telefono.setActionCommand("Registrati");

        eta.addActionListener(A);
        eta.setActionCommand("Registrati");

        citta.addActionListener(A);
        citta.setActionCommand("Registrati");

    }

    public String getNome() {
        return nome.getText();
    }

    public String getNickname() {
        return nickname.getText();
    }

    public String getCognome() {
        return cognome.getText();
    }

    public String getEmail() {
        return email.getText();
    }

    public String getPassword() {
        return password.getText();
    }

    public String getTelefono() {
        return telefono.getText();
    }

    public String getEta() {
        return eta.getText();
    }

    public String getCitta() {
        return citta.getText();
    }

    @Override
    public JFrame getFinestra() {
        return this;
    }

}
