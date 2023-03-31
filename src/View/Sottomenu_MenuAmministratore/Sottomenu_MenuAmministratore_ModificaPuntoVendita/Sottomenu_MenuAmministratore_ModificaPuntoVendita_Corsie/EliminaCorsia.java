package View.Sottomenu_MenuAmministratore.Sottomenu_MenuAmministratore_ModificaPuntoVendita.Sottomenu_MenuAmministratore_ModificaPuntoVendita_Corsie;

import Controller.ControllerSottomenu_MenuAmministratore.ControllerSottomenu_MenuAmministratore_ModificaPuntoVenditaController.ControllerSottomenu_MenuAmministratore_ModificaPuntoVenditaController_NuovaCorsiaController.EliminaCorsiaController;
import Model.Merce.Categoria;
import Model.Merce.ICategoria;
import Model.Merce.PuntiVendita;
import View.ControlloFinestre.IFinestre;
import View.MenuAmministratore;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

//Frame per l'eliminazione delle corsie
public class EliminaCorsia extends JFrame implements IFinestre {

    private JTextField numeroCorsia = new JTextField();
    private JTextField numeroScaffale = new JTextField();

    public EliminaCorsia(){super("MyShop");}

    public void StartEliminaCorsia(NuovaCorsia N, String S, MenuAmministratore M){

        this.setSize(1000,1000);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        JPanel p1 = new JPanel(new GridLayout(4,2));
        JLabel l0 = new JLabel("Inserire numero corsia e di scaffale se si vuole eliminare uno scaffale da una corsia, se si vuole eliminare");
        JLabel riempitivo = new JLabel("solo una corsia contente tutti i suoi scaffali inserire solo il numero di corsia.");
        JLabel l1 = new JLabel("Numero corsia");
        JLabel l2 = new JLabel("Numero scaffale");
        JButton b1 = new JButton("Elimina Scaffale");
        JButton b2 = new JButton("Elimina intera corsia");

        p1.add(l0); p1.add(riempitivo); p1.add(l1); p1.add(numeroCorsia); p1.add(l2); p1.add(numeroScaffale);
        p1.add(b1); p1.add(b2);

        this.getContentPane().add(p1,BorderLayout.NORTH);

        VisualizzaCorsieList(M,S);

        JPanel p = new JPanel(new GridLayout(1,1));

        JButton b10 = new JButton("Indietro");

        p.add(b10);

        this.getContentPane().add(p,BorderLayout.SOUTH);

        this.setVisible(true);

        ActionListener A = new EliminaCorsiaController(M,N,this,S);

        b10.addActionListener(A);
        b10.setActionCommand("Indietro");

        b2.addActionListener(A);
        b2.setActionCommand("Elimina intera corsia");

        b1.addActionListener(A);
        b1.setActionCommand("Elimina scaffale");

    }

    // metodo che genera una lista di corsie numerata
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

    public String getNumeroCorsia(){
        return numeroCorsia.getText();
    }

    public String getNumeroScaffale(){
        return numeroScaffale.getText();
    }



    @Override
    public JFrame getFinestra() {
        return this;
    }
}
