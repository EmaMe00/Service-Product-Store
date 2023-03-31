package View.Sottomenu_MenuUtente;

import Controller.ControllerSottomenu_MenuUtente.ProdottiAcquistatiController;
import Model.Merce.*;
import Model.Utenti.Utente;
import View.ControlloFinestre.IFinestre;
import View.MenuAmministratore;
import View.MenuPrincipale;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

public class ProdottiAcquistati extends JFrame implements IFinestre {

    public ProdottiAcquistati(){super("MyShop");}

    public void StartProdottiAcquistati(MenuPrincipale M,VisualizzaCarrelloUtente V){

        this.setSize(1000,1000);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        VisualizzaPuntiProdottiList(M);
        VisualizzaServiziList(M);

        JPanel p1 = new JPanel(new GridLayout(1,1));
        JButton b1 = new JButton("INDIETRO");
        p1.add(b1);
        this.getContentPane().add(p1,BorderLayout.SOUTH);
        this.setVisible(true);

        ActionListener A = new ProdottiAcquistatiController(M,V,this);

        b1.addActionListener(A);
        b1.setActionCommand("Indietro");

    }

    public void VisualizzaPuntiProdottiList(MenuPrincipale M){

        Utente utente = M.getFD().getListaUtenti().get(M.getFD().getSessione().getNumUtente());
        List<IProdotti> prodottiAcquistati = utente.getProdottiAcquistati();

        JPanel panel = new JPanel(new GridLayout(1,1));
        DefaultListModel model = new DefaultListModel();
        model.addElement("ELENCO PRODOTTI");


        for (int i=0;i<prodottiAcquistati.size();i++){

            if (prodottiAcquistati.get(i).getPC() == false){
                String tmp = i+" - " + prodottiAcquistati.get(i).getnome() + "  " + prodottiAcquistati.get(i).getprezzo() + "€\n";
                model.addElement(tmp);

            }else {
                String tmp3 = "Prodotto Composito: ";
                String tmp = i+" - " + prodottiAcquistati.get(i).getnome() + "  " + prodottiAcquistati.get(i).getprezzo() + "€";
                ProdottoComposito tmp1 = (ProdottoComposito) prodottiAcquistati.get(i);

                model.addElement(tmp3);
                model.addElement(tmp);


                for (int j=0; j<tmp1.getSottoprodotti().size();j++){

                    String tmpA = "   " + j +" - " + tmp1.getSottoprodotti().get(j).getnome() + "  " + tmp1.getSottoprodotti().get(j).getprezzo() + "€";
                    model.addElement(tmpA);

                }

            }
        }

        JList lista = new JList();
        lista.setModel(model);

        JScrollPane scroll = new JScrollPane(lista);

        panel.add(scroll);

        this.getContentPane().add(panel,BorderLayout.WEST);

    }

    public void VisualizzaServiziList(MenuPrincipale M){

        Utente utente = M.getFD().getListaUtenti().get(M.getFD().getSessione().getNumUtente());
        List<Servizi> serviziAcquistati = utente.getServiziAcquistati();


        JPanel panel = new JPanel(new GridLayout(1,1));
        DefaultListModel model = new DefaultListModel();
        model.addElement("ELENCO SERVIZI");
        for (int i=0;i<serviziAcquistati.size();i++){
            String tmp = i+") " + serviziAcquistati.get(i).getNome() + " | Prezzo: " +serviziAcquistati.get(i).getPrezzo();
            model.addElement(tmp);
        }

        JList lista = new JList();
        lista.setModel(model);

        JScrollPane scroll = new JScrollPane(lista);

        panel.add(scroll);

        this.getContentPane().add(panel,BorderLayout.EAST);

    }

    @Override
    public JFrame getFinestra() {
        return this;
    }
}
