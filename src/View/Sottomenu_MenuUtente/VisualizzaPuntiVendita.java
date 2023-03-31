package View.Sottomenu_MenuUtente;

import Controller.ControllerSottomenu_MenuUtente.VisualizzaPuntiVenditaController;
import Model.Merce.Iterator;
import Model.Merce.PuntiVendita;
import View.ControlloFinestre.IFinestre;
import View.MenuPrincipale;
import View.MenuUtente;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

//Frame per la visualizzazione dei punti vendita
public class VisualizzaPuntiVendita extends JFrame implements IFinestre {

    private JTextField puntovendita = new JTextField();
    private boolean administratorIdentificator = false;
    private boolean managerIdentificator = false;
    //Valore per identificare se ci sia loggato un utente Guest o meno
    private int guestIdentificator;

    public VisualizzaPuntiVendita(){super("MyShop");}

    public void StartVisualizzaPuntiVendita(MenuUtente M, MenuPrincipale M1){

        this.setSize(1000,1000);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        JPanel p1 = new JPanel(new GridLayout(1,3));
        JLabel l1 = new JLabel("SCEGLI PUNTO VENDITA: ");
        JButton b2 = new JButton("AVANTI");
        l1.setHorizontalAlignment( JLabel.CENTER );
        p1.add(l1); p1.add(puntovendita); p1.add(b2);

        JPanel p2 = new JPanel(new GridLayout(1,1));
        JButton b1 = new JButton("Indietro");
        p2.add(b1);

        this.getContentPane().add(p1,BorderLayout.NORTH);
        this.getContentPane().add(p2,BorderLayout.SOUTH);

        VisualizzaPuntiVList(M1);

        this.setVisible(true);

        ActionListener A = new VisualizzaPuntiVenditaController(this,M,M1,administratorIdentificator,managerIdentificator);
        b1.addActionListener(A);
        b1.setActionCommand("Indietro");

        b2.addActionListener(A);
        b2.setActionCommand("Avanti");

        puntovendita.addActionListener(A);
        puntovendita.setActionCommand("Avanti");

    }

    //Metodo per l'inserimento dei punti vendita in una lista numerata
    public void VisualizzaPuntiVList(MenuPrincipale M1){

        JPanel panel = new JPanel(new GridLayout(M1.getFD().getPuntivendita().size(),1));
        DefaultListModel model = new DefaultListModel();

        model.addElement("PUNTI VENDITA ESISTENTI");

        int i=0;
        for (Iterator iter = M1.getFD().getIterator(); iter.hasNext();){
            PuntiVendita pv = (PuntiVendita) iter.next();
            String tmp = i+") " + pv.getNome();
            model.addElement(tmp);
            i++;
        }

        /*
        for (int i=0;i<M1.getFD().getPuntivendita().size();i++){
            String tmp = i+") " + M1.getFD().getPuntivendita().get(i).getNome();
            model.addElement(tmp);
        }*/

        JList lista = new JList();
        lista.setModel(model);

        JScrollPane scroll = new JScrollPane(lista);

        panel.add(scroll);

        this.getContentPane().add(panel,BorderLayout.CENTER);
    }

    public String getPuntoVendita() {
        return puntovendita.getText();
    }

    @Override
    public JFrame getFinestra() {
        return this;
    }

    public void setadministratoIdentificator(boolean a){
        administratorIdentificator = a;
    }

    public boolean getadministratorIdentificator(){
        return administratorIdentificator;
    }

    public void setManagerIdentificator(boolean a) { managerIdentificator = a; }

    public boolean getManagerIdentificator() {return managerIdentificator; }

    public void setGuestIdentificator (int guest){
        guestIdentificator = guest;
    }

    public int getGuestIdentificator(){
        return  guestIdentificator;
        //se è loggato un gust il valore è 1
    }
}
