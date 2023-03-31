package View.Sottomenu_MenuAmministratore.Sottomenu_MenuAmministratore_GestioneManager;

import Controller.ControllerSottomenu_MenuAmministratore.ControllerSottomenu_MenuAmministratore_GestioneManagerController.AggiungiManagerController;
import Model.Merce.Iterator;
import Model.Merce.PuntiVendita;
import View.ControlloFinestre.IFinestre;
import View.MenuAmministratore;
import View.MenuPrincipale;
import View.Sottomenu_MenuAmministratore.GestioneManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class AggiungiManager extends JFrame implements IFinestre {

    private final JTextField numeroPuntoVendita = new JTextField();
    private final JTextField numeroUtente = new JTextField();

    public AggiungiManager(){ super("MyShop"); }

    public void StartAggiungiManager(GestioneManager G, MenuAmministratore M, MenuPrincipale M1){

        this.setSize(1000,1000);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        JPanel p1 = new JPanel(new GridLayout(3,2));
        JLabel l1 = new JLabel("NUMERO PUNTO VENDITA DA ASSEGNARE: ");
        l1.setHorizontalAlignment(JLabel.CENTER);
        JLabel l2 = new JLabel("NUMERO DELL'UTENTE A CUI ASSEGNARLO: ");
        l2.setHorizontalAlignment(JLabel.CENTER);
        JButton b1 = new JButton("CONFERMA");
        JButton b2 = new JButton("INDIETRO");

        p1.add(l1); p1.add(numeroPuntoVendita); p1.add(l2); p1.add(numeroUtente); p1.add(b1); p1.add(b2);

        JScrollPane scroll = VisualizzaPuntiVList(M);
        JScrollPane scroll1 = VisualizzaUtenti(M1);

        JPanel p2 = new JPanel(new GridLayout(2,1));
        p2.add(scroll); p2.add(scroll1);

        this.getContentPane().add(p2,BorderLayout.CENTER);
        this.getContentPane().add(p1,BorderLayout.NORTH);
        this.setVisible(true);

        ActionListener A = new AggiungiManagerController(this, M1, M, G);

        b2.addActionListener(A);
        b2.setActionCommand("Indietro");

        b1.addActionListener(A);
        b1.setActionCommand("Conferma");

        numeroPuntoVendita.addActionListener(A);
        numeroPuntoVendita.setActionCommand("Conferma");

        numeroUtente.addActionListener(A);
        numeroUtente.setActionCommand("Conferma");

    }

    public JScrollPane VisualizzaPuntiVList(MenuAmministratore M){

        JPanel panel = new JPanel(new GridLayout(1,1));
        DefaultListModel model = new DefaultListModel();

        int i=0;
        for (Iterator iter = M.getMenuPrincipale().getFD().getIterator(); iter.hasNext();){
            PuntiVendita pv = (PuntiVendita) iter.next();
            String tmp = i+") " + pv.getNome();
            model.addElement(tmp);
            i++;
        }

        /*
        for (int i=0;i<M.getPuntivendita().size();i++){
            String tmp = i+") " + M.getPuntivendita().get(i).getNome();
            model.addElement(tmp);
        }*/

        JList lista = new JList();
        lista.setModel(model);

        JScrollPane scroll = new JScrollPane(lista);

        panel.add(scroll);

        return scroll;

    }

    public JScrollPane VisualizzaUtenti(MenuPrincipale M1){

        JPanel panel = new JPanel(new GridLayout(1,1));
        DefaultListModel model = new DefaultListModel();

        for (int i=0;i<M1.getFD().getListaUtenti().size();i++){
            String tmp = i+") " + M1.getFD().getListaUtenti().get(i).getNome() + " " + M1.getFD().getListaUtenti().get(i).getCognome() + " " + M1.getFD().getListaUtenti().get(i).getEmail();
            model.addElement(tmp);
        }

        JList lista = new JList();
        lista.setModel(model);

        JScrollPane scroll = new JScrollPane(lista);

        panel.add(scroll);

        return scroll;

    }

    public String getNumeroPuntoVendita() {
        return numeroPuntoVendita.getText();
    }

    public String getNumeroUtente() {
        return numeroUtente.getText();
    }


    @Override
    public JFrame getFinestra() {
        return this;
    }
}
