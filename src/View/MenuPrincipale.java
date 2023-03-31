package View;

import Controller.MenuPrincipaleController;
import DAO.*;
import DBInterface.ConnectionDB;
import Model.FakeDatabase;
import Model.Merce.Scaffale;
import Model.Merce.Sottocategoria;
import Model.Recensioni;
import View.ControlloFinestre.IFinestre;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.io.*;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.SQLException;

//Frame menu principale
public class MenuPrincipale extends JFrame implements IFinestre {

    //Durante il progetto utilizzo un DB temporaneo nel cui salvare tutte le informazioni prima di implementare quello vero
    //tutto quello che faranno le altre pagine e tutte le modifiche verranno salvate qui dentro e a sua volta questo
    //oggetto di Database verrà poi salvato nel reale database. Quindi tutte le pagine comunicheranno direttamente con questo DB
    private final FakeDatabase FD = FakeDatabase.getInstance();

    private JPanel panel1;
    private JTextField fieldpassword;
    private JButton login;
    private JButton register;
    private JButton guest;
    private JLabel username;
    private JLabel password;
    private JLabel crediti;
    private JTextField fieldusername;
    private JPasswordField passwordField1;
    private ConnectionDB C = ConnectionDB.getInstance();
    private Connection c = C.StartConnection();

    public MenuPrincipale() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException { super("MyShop"); }

    public void StartMenuPrincipale() throws IOException, ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException, NoSuchAlgorithmException {

        //Salvataggio delle informazioni del programma nel database (usato solo per resettare tutto il DB)
        //FD.DefaultDatabase(); C.SalvaDatabase(FD,c);

        //Caricamento delle informazioni dal database nel programma
        C.LoadDatabase(FD,c);


        this.setSize(1000,1000);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        this.setContentPane(panel1);
        this.setVisible(true);

        ActionListener A = new MenuPrincipaleController(this);

        login.addActionListener(A);
        login.setActionCommand("login");

        fieldusername.addActionListener(A);
        fieldusername.setActionCommand("login");

        passwordField1.addActionListener(A);
        passwordField1.setActionCommand("login");

        register.addActionListener(A);
        register.setActionCommand("Registrati");

        guest.addActionListener(A);
        guest.setActionCommand("Guest");

    }

    public String getUsername(){
        return fieldusername.getText();
    }

    public void SaveFD(FakeDatabase tmp){
        FD.setPuntiVendita(tmp.getPuntivendita());
    } //Salva il DB temporaneo

    public FakeDatabase getFD(){
        return FD;
    }

    @Override
    public JFrame getFinestra() {
        return this;
    }

    public Connection getConnection() {
        return c;
    }

    public JTextField getUsernameJ(){return fieldusername;};

    public JTextField getPasswordJ(){return fieldpassword;};

    public String getPassword(){
        char[] input = passwordField1.getPassword();
        return String.valueOf(input);
    }

}
