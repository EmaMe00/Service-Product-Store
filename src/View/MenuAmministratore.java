package View;

import Controller.MenuAmministratoreController;
import Model.Merce.PuntiVendita;
import View.ControlloFinestre.IFinestre;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MenuAmministratore extends JFrame implements IFinestre {

    private MenuPrincipale M1;

    //questa finestra deve contenere temporaneamente i dati che si inseriscono all'interno di essa e delle sue
    //sottofinestre, quindi devo avere una lista di punti vendita che viene sempre aggiornata qui ogni modifica che
    //che si fa in ogni sottofinestra


    // Quindi ora il funzionamento è questo:
    // I dati del FakeDatabase vengono copiati in questo ArrayList di punti vendita (questo viene fatto quando la finestra
    // viene creata) e successivamente, quando si dovrà distruggere questa finestra, ovvero MenuAmministratore, prima
    // di distruggerla i dati verranno rimessi a posto in FakeDatabase
    private List<PuntiVendita> puntivendita = new ArrayList<PuntiVendita>();

    public MenuAmministratore(MenuPrincipale M) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        super("MyShop");
        //Ogni volta che viene creata questa finestra accade questo:
        //Prelevo i dati da FakeDatabase in modo di averne una copia esatta in puntivendita (da ora in poi questa finestra
        // e le figle lavoreranno con puntivendita e non con FakeDatabase per i motivi detti sopra)
        setPuntivendita(M.getFD().getPuntivendita());
        M1 = M;
    }

    public void StartMenuAmministratore(MenuPrincipale M){


        this.setSize(1000,1000);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        JPanel p = new JPanel(new GridLayout(3,3));

        JButton b1 = new JButton("Aggiungi punto vendita");
        JButton b2 = new JButton("Visualizza punti vendita esistenti");
        JButton b3 = new JButton("Elimina punto vendita");
        JButton b4 = new JButton("Aggiungi prodotto - Modifica punto vendita");
        JButton b6 = new JButton("Gestisci Manager");

        p.add(b1); p.add(b2); p.add(b3); p.add(b4); p.add(b6);


        JPanel p1 = new JPanel(new GridLayout(1,1));

        JButton b5 = new JButton("Indietro");

        p1.add(b5);

        this.getContentPane().add(p,BorderLayout.CENTER);
        this.getContentPane().add(p1,BorderLayout.SOUTH);

        this.setVisible(true);

        ActionListener A = new MenuAmministratoreController(this,M);

        b1.addActionListener(A);
        b1.setActionCommand("Aggiungi punto vendita");

        b2.addActionListener(A);
        b2.setActionCommand("Visualizza punti vendita esistenti");

        b3.addActionListener(A);
        b3.setActionCommand("Elimina punto vendita");

        b4.addActionListener(A);
        b4.setActionCommand("Aggiungi o Modifica punto vendita");

        b5.addActionListener(A);
        b5.setActionCommand("Indietro");

        b6.addActionListener(A);
        b6.setActionCommand("Manager");

    }

    public void setPuntivendita(List<PuntiVendita> puntivendita) {
        this.puntivendita = puntivendita;
    }

    public List<PuntiVendita> getPuntivendita() {
        return puntivendita;
    }

    public void addPuntiVendita(List<PuntiVendita> puntivendita) { this.puntivendita.addAll(puntivendita); }

    public MenuPrincipale getMenuPrincipale(){
        return M1;
    }

    @Override
    public JFrame getFinestra() {
        return this;
    }
}
