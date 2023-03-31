package View.Sottomenu_MenuAmministratore.Sottomenu_MenuAmministratore_ModificaPuntoVendita.Sottomenu_MenuAmministratore_ModificaPuntoVendita_Corsie;

import Controller.ControllerSottomenu_MenuAmministratore.ControllerSottomenu_MenuAmministratore_ModificaPuntoVenditaController.ControllerSottomenu_MenuAmministratore_ModificaPuntoVenditaController_NuovaCategoriaController.NuovaCategoriaController;
import Controller.ControllerSottomenu_MenuAmministratore.ControllerSottomenu_MenuAmministratore_ModificaPuntoVenditaController.ControllerSottomenu_MenuAmministratore_ModificaPuntoVenditaController_NuovaCorsiaController.NuovaCorsiaController;
import Model.Merce.Categoria;
import Model.Merce.ICategoria;
import Model.Merce.PuntiVendita;
import View.ControlloFinestre.IFinestre;
import View.MenuAmministratore;
import View.Sottomenu_MenuAmministratore.Sottomenu_MenuAmministratore_ModificaPuntoVendita.MenuModifichePuntoVendita;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

//Frame per la gestione delle corsie
public class NuovaCorsia extends JFrame implements IFinestre {

    //Variabile che mi controlla se ci sia un manager a fare questa manovra o meno
    private boolean managerIdentificator = false;
    private JTextField corsia = new JTextField();


    public NuovaCorsia(){ super("MyShop"); }

    public void StartNuovaCorsia(MenuAmministratore M, MenuModifichePuntoVendita M1, String S){

        this.setSize(1000,1000);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        JPanel p1 = new JPanel(new GridLayout(2,2));
        JLabel l1 = new JLabel("Numero Corsia:");
        l1.setHorizontalAlignment(JLabel.CENTER);
        JButton b1 = new JButton("Nuova");
        JButton b3 = new JButton("Elimina");

        p1.add(l1); p1.add(corsia);
        p1.add(b1);p1.add(b3);

        this.getContentPane().add(p1,BorderLayout.NORTH);

        VisualizzaCorsieList(M,S);

        JPanel p = new JPanel(new GridLayout(1,1));

        JButton b10 = new JButton("Indietro");

        p.add(b10);

        this.getContentPane().add(p,BorderLayout.SOUTH);

        this.setVisible(true);

        ActionListener A = new NuovaCorsiaController(M,this,S,M1,managerIdentificator);

        b10.addActionListener(A);
        b10.setActionCommand("Indietro");

        b1.addActionListener(A);
        b1.setActionCommand("Nuova");

        corsia.addActionListener(A);
        corsia.setActionCommand("Nuova");

        b3.addActionListener(A);
        b3.setActionCommand("Elimina");

    }

    //metodo che genera una lista di corsie numerata
    public void VisualizzaCorsieList(MenuAmministratore M,String S){

        JPanel panel = new JPanel(new GridLayout(M.getPuntivendita().size(),1));
        DefaultListModel model = new DefaultListModel();
        PuntiVendita tmp = M.getPuntivendita().get(Integer.parseInt(S));

        for (int i=0;i<tmp.getCorsie().size();i++){
            String stmp = i + ") "+ tmp.getCorsie().get(i).getCodCorsia();
            model.addElement(stmp);
            for (int j=0;j<tmp.getCorsie().get(i).getScaffali().size();j++){
                int scaftmp = tmp.getCorsie().get(i).getScaffali().get(j).getNumero();
                String scaftmps = "  " + j + " - " + scaftmp;
                model.addElement(scaftmps);
            }
        }

        JList lista = new JList();
        lista.setModel(model);

        JScrollPane scroll = new JScrollPane(lista);

        panel.add(scroll);

        this.getContentPane().add(panel,BorderLayout.CENTER);

    }

    public String getCorsia() {
        return corsia.getText();
    }


    public void setManagerIdentificator(boolean a){managerIdentificator = a;}

    @Override
    public JFrame getFinestra() {
        return this;
    }
}
