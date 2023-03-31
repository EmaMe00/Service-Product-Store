package View.Sottomenu_MenuAmministratore.Sottomenu_MenuAmministratore_ModificaPuntoVendita.Sottomenu_MenuAmministratore_ModificaPuntoVendita_Servizi;

import Controller.ControllerSottomenu_MenuAmministratore.ControllerSottomenu_MenuAmministratore_ModificaPuntoVenditaController.ControllerSottomenu_MenuAmministratore_ModificaPuntoVenditaController_MenuServiziController.MenuServiziController;
import View.ControlloFinestre.IFinestre;
import View.MenuAmministratore;
import View.Sottomenu_MenuAmministratore.Sottomenu_MenuAmministratore_ModificaPuntoVendita.MenuModifichePuntoVendita;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

//Frame che permette di gestire tutti i servizi
public class MenuServizi extends JFrame implements IFinestre {

    private JTextField nServizio = new JTextField();
    //variabile che mi indica se è un manager a manovare su questa finestra
    private boolean managerIdentificator = false;

    public MenuServizi(){ super(); }

    public void StartMenuServizi(MenuAmministratore M, MenuModifichePuntoVendita M2, String S){

        this.setSize(1000,1000);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        JPanel p1 = new JPanel(new GridLayout(3,2));
        JLabel l1 = new JLabel("Numero servizio da modificare: ");
        JButton b1 = new JButton("MODIFICA");
        JButton b2 = new JButton("NUOVO");
        JButton b3 = new JButton("ELIMINA");
        JButton b4 = new JButton("ELIMINA IMMAGINI");

        p1.add(l1); p1.add(nServizio); p1.add(b1); p1.add(b2);p1.add(b3);p1.add(b4);

        VisualizzaServiziList(M,S);

        JPanel p2 = new JPanel(new GridLayout(1,1));
        JButton indietro = new JButton("INDIETRO");
        p2.add(indietro);


        this.getContentPane().add(p1,BorderLayout.NORTH);
        this.getContentPane().add(p2,BorderLayout.SOUTH);
        this.setVisible(true);

        ActionListener A = new MenuServiziController(M,this,M2,S);

        indietro.addActionListener(A);
        indietro.setActionCommand("Indietro");

        b2.addActionListener(A);
        b2.setActionCommand("Nuovo");

        b1.addActionListener(A);
        b1.setActionCommand("Modifica");

        b3.addActionListener(A);
        b3.setActionCommand("Elimina");

        b4.addActionListener(A);
        b4.setActionCommand("Elimina immagini");

    }

    public void VisualizzaServiziList(MenuAmministratore M,String S){

        JPanel panel = new JPanel(new GridLayout(1,1));
        DefaultListModel model = new DefaultListModel();
        model.addElement("ELENCO SERVIZI");
        for (int i=0;i<M.getPuntivendita().get(Integer.parseInt(S)).getServizi().size();i++){
            String tmp = i+") " + M.getPuntivendita().get(Integer.parseInt(S)).getServizi().get(i).getNome() + " | Prezzo: " + M.getPuntivendita().get(Integer.parseInt(S)).getServizi().get(i).getPrezzo();
            model.addElement(tmp);
        }

        JList lista = new JList();
        lista.setModel(model);

        JScrollPane scroll = new JScrollPane(lista);

        panel.add(scroll);

        this.getContentPane().add(panel,BorderLayout.CENTER);

    }

    public String getnServizo() {return nServizio.getText();}

    public void setManagerIdentificator(boolean a){
        managerIdentificator = a;
    }

    public boolean getManagerIdentificator(){
        return managerIdentificator;
    }

    @Override
    public JFrame getFinestra() {
        return this;
    }
}
