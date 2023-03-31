package View.Sottomenu_MenuAmministratore.Sottomenu_MenuAmministratore_GestioneManager;

import Controller.ControllerSottomenu_MenuAmministratore.ControllerSottomenu_MenuAmministratore_GestioneManagerController.RimuoviManagerController;
import View.ControlloFinestre.IFinestre;
import View.MenuAmministratore;
import View.MenuPrincipale;
import View.Sottomenu_MenuAmministratore.GestioneManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class RimuoviManager extends JFrame implements IFinestre {

    private JTextField numeroManager = new JTextField();

    public RimuoviManager(){ super("MyShop"); }

    public void StartRimuoviManager(GestioneManager G, MenuAmministratore M, MenuPrincipale M1){

        this.setSize(1000,1000);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        JPanel p1 = new JPanel(new GridLayout(2,2));
        JLabel l2 = new JLabel("NUMERO DEL MANAGER A CUI ASSEGNARLO: ");
        l2.setHorizontalAlignment(JLabel.CENTER);
        JButton b1 = new JButton("CONFERMA");
        JButton b2 = new JButton("INDIETRO");

        p1.add(l2); p1.add(numeroManager); p1.add(b1); p1.add(b2);

        JScrollPane scroll1 = VisualizzaManager(M1);

        JPanel p2 = new JPanel(new GridLayout(1,1));
        p2.add(scroll1);

        this.getContentPane().add(p2,BorderLayout.CENTER);
        this.getContentPane().add(p1,BorderLayout.NORTH);
        this.setVisible(true);

        ActionListener A = new RimuoviManagerController(this, M1, M, G);

        b2.addActionListener(A);
        b2.setActionCommand("Indietro");

        b1.addActionListener(A);
        b1.setActionCommand("Conferma");

        numeroManager.addActionListener(A);
        numeroManager.setActionCommand("Conferma");

    }

    public void SwitchFrameOff(){
        this.setVisible(false);
    }

    public void SwitchFrameOn(){
        this.setVisible(true);
    }

    public void CloseFrame(){
        this.setVisible(false);
        this.dispose();
    }

    public JScrollPane VisualizzaManager(MenuPrincipale M1){

        JPanel panel = new JPanel(new GridLayout(1,1));
        DefaultListModel model = new DefaultListModel();

        for (int i=0;i<M1.getFD().getListamanager().size();i++){
            String tmp = i+") " + M1.getFD().getListamanager().get(i).getNome() + " " + M1.getFD().getListamanager().get(i).getEmail() + " " + M1.getFD().getPuntivendita().get(M1.getFD().getListamanager().get(i).getNumPuntoVendita()).getNome();
            model.addElement(tmp);
        }

        JList lista = new JList();
        lista.setModel(model);

        JScrollPane scroll = new JScrollPane(lista);

        panel.add(scroll);

        return scroll;

    }

    public String getNumeroManager(){
        return numeroManager.getText();
    }

    @Override
    public JFrame getFinestra() {
        return this;
    }
}


