package View.Sottomenu_MenuAmministratore;

import Controller.ControllerSottomenu_MenuAmministratore.EliminaPuntoVenditaController;
import Model.Merce.Iterator;
import Model.Merce.PuntiVendita;
import View.ControlloFinestre.IFinestre;
import View.MenuAmministratore;
import View.MenuPrincipale;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

//Menu per l'eliminazione dei punti vendita
public class EliminaPuntoVendita extends JFrame implements IFinestre {

    private final JTextField t1 = new JTextField();

    public EliminaPuntoVendita() { super("MyShop"); }

    public void StartEliminaPuntoVendita(MenuAmministratore M, MenuPrincipale M1){

        this.setSize(1000,1000);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        JPanel p1 = new JPanel(new GridLayout(2,2));
        JLabel l1 = new JLabel("Inserisci il numero del punto vendita che vuoi eliminare: ");
        JButton b1 = new JButton("Indietro");
        JButton b2= new JButton("Conferma");
        l1.setHorizontalAlignment( JLabel.CENTER );
        p1.add(l1); p1.add(t1); p1.add(b2); p1.add(b1);

        this.getContentPane().add(p1,BorderLayout.NORTH);

        // lista di punti vendita numerata
        VisualizzaPuntiVList(M);

        this.setVisible(true);

        ActionListener A = new EliminaPuntoVenditaController(this,M,M1);

        b1.addActionListener(A);
        b1.setActionCommand("Indietro");

        b2.addActionListener(A);
        b2.setActionCommand("Conferma");

        t1.addActionListener(A);
        t1.setActionCommand("Conferma");

    }

    // generazione della lista di punti vendita numerata
    public void VisualizzaPuntiVList(MenuAmministratore M){

        JPanel panel = new JPanel(new GridLayout(M.getPuntivendita().size(),1));
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

        this.getContentPane().add(panel,BorderLayout.CENTER);

    }

    //prendo dalla casella ciò che ho scritto
    public String getNumeroPuntoVendita(){ return t1.getText(); }

    @Override
    public JFrame getFinestra() {
        return this;
    }
}
