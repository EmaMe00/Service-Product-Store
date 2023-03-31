package View.Sottomenu_MenuUtente.Sottomenu_MenuUtente_VisualizzaPuntiVenditaUtente;

import Controller.ControllerSottomenu_MenuUtente.ControllerSottomenu_MenuUtente_VisualizzaPuntiVenditaController.VisualizzaProdottoCompositoController;
import Model.Merce.IProdotti;
import Model.Merce.Prodotti;
import Model.Merce.ProdottoComposito;
import View.ControlloFinestre.IFinestre;
import View.MenuPrincipale;
import View.Sottomenu_MenuUtente.VisualizzaPuntiVendita;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

public class VisualizzaProdottoComposito extends JFrame implements IFinestre {

    private JTextField numProd = new JTextField();

    public VisualizzaProdottoComposito(){super("MyShop");}

    public void StartVisualizzaProdottoComposito(MenuPrincipale M, VisualizzazioneProdottiUtente V, String S){

        this.setSize(1000,1000);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        JPanel p1 = new JPanel(new GridLayout(1,3));
        JLabel l1 = new JLabel("Numero del prodotto da visualizzare: ");
        JButton b1 = new JButton("Visualizza");
        p1.add(l1); p1.add(numProd); p1.add(b1);

        VisualizzaSottoprodotti(M,V,S);

        JPanel p2 = new JPanel(new GridLayout(1,1));
        JButton indietro = new JButton("Indietro");
        p2.add(indietro);

        this.getContentPane().add(p2,BorderLayout.SOUTH);
        this.getContentPane().add(p1,BorderLayout.NORTH);

        this.setVisible(true);
        ActionListener A = new VisualizzaProdottoCompositoController(M,V,S,this);

        indietro.addActionListener(A);
        indietro.setActionCommand("Indietro");

        b1.addActionListener(A);
        b1.setActionCommand("Visualizza");

    }

    public void VisualizzaSottoprodotti(MenuPrincipale M1, VisualizzazioneProdottiUtente V, String S){

        ProdottoComposito prodottoComposito = (ProdottoComposito) M1.getFD().getPuntivendita().get(Integer.parseInt(S)).getProdotti().get(Integer.parseInt(V.getNumProdotto()));
        List<IProdotti> sottoprodotti = prodottoComposito.getSottoprodotti();

        JPanel panel = new JPanel(new GridLayout(1,1));
        DefaultListModel model = new DefaultListModel();

        model.addElement("SOTTOPRODOTTI");

        for (int i=0;i<sottoprodotti.size();i++){
            String tmp = i+") " + sottoprodotti.get(i).getnome();
            model.addElement(tmp);
        }

        JList lista = new JList();
        lista.setModel(model);

        JScrollPane scroll = new JScrollPane(lista);

        panel.add(scroll);

        this.getContentPane().add(panel,BorderLayout.CENTER);
    }

    public String getNumProd(){
        return numProd.getText();
    }


    @Override
    public JFrame getFinestra() {
        return this;
    }
}
