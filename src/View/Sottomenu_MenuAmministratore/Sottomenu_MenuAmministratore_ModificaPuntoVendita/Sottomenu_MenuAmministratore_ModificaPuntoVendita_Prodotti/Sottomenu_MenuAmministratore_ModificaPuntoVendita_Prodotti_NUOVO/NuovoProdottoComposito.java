package View.Sottomenu_MenuAmministratore.Sottomenu_MenuAmministratore_ModificaPuntoVendita.Sottomenu_MenuAmministratore_ModificaPuntoVendita_Prodotti.Sottomenu_MenuAmministratore_ModificaPuntoVendita_Prodotti_NUOVO;

import Controller.ControllerSottomenu_MenuAmministratore.ControllerSottomenu_MenuAmministratore_ModificaPuntoVenditaController.ControllerSottomenu_MenuAmministratore_ModificaPuntoVenditaController_ModificheProdottiController.ControllerSottomenu_MenuAmministratore_ModificaPuntoVenditaController_ModificheProdottiController_NuovoProdottoController.NuovoProdottoCompositoController;
import Model.Merce.Categoria;
import Model.Merce.Corsia;
import Model.Merce.Prodotti;
import Model.Merce.ProdottoComposito;
import View.ControlloFinestre.IFinestre;
import View.MenuAmministratore;
import View.Sottomenu_MenuAmministratore.Sottomenu_MenuAmministratore_ModificaPuntoVendita.Sottomenu_MenuAmministratore_ModificaPuntoVendita_Prodotti.ModificheProdotti;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

//Creazione di un nuovo prodotto composito, questo frame permette di unire più prodotti sotto il nome di uno solo
public class NuovoProdottoComposito extends JFrame implements IFinestre {

    private JTextField prodottoPrincipale = new JTextField();
    private JTextField prodottoMinore = new JTextField();

    public NuovoProdottoComposito(){ super("MyShop"); }

    public void StartNuovoProdottoComposito(MenuAmministratore M, ModificheProdotti M1,String S){

        this.setSize(1000,1000);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        JPanel p1 = new JPanel(new GridLayout(2,2));
        JLabel l1 = new JLabel("NOME DEL NUOVO PRODOTTO COMPOSITO");
        JLabel l2 = new JLabel("INSERISCI I PRODOTTI CHE VUOI AGGREGARE AL PRINCIPALE: (Es: 1 2 23 43)");

        p1.add(l1); p1.add(prodottoPrincipale); p1.add(l2); p1.add(prodottoMinore);

        JPanel p2 = new JPanel(new GridLayout(1,2));
        JButton indietro = new JButton("INDIETRO ");
        JButton conferma = new JButton("CONFERMA");

        p2.add(indietro); p2.add(conferma);

        VisualizzaPuntiProdottiList(M,S);

        this.getContentPane().add(p2,BorderLayout.SOUTH);
        this.getContentPane().add(p1,BorderLayout.NORTH);
        this.setVisible(true);

        ActionListener A = new NuovoProdottoCompositoController(M,M1,this,S);

        indietro.addActionListener(A);
        indietro.setActionCommand("Indietro");

        conferma.addActionListener(A);
        conferma.setActionCommand("Conferma");


    }

    public String getProdottoPrincipale() {
        return prodottoPrincipale.getText();
    }

    public void setProdottoPrincipale(JTextField prodottoPrincipale) {
        this.prodottoPrincipale = prodottoPrincipale;
    }

    public String getProdottoMinore() {
        return prodottoMinore.getText();
    }

    public void setProdottoMinore(JTextField prodottoMinore) {
        this.prodottoMinore = prodottoMinore;
    }

    public void VisualizzaPuntiProdottiList(MenuAmministratore M,String S){

        JPanel panel = new JPanel(new GridLayout(1,1));
        DefaultListModel model = new DefaultListModel();
        model.addElement("ELENCO PRODOTTI");

        for (int i=0;i<M.getPuntivendita().get(Integer.parseInt(S)).getProdotti().size();i++){
            if (M.getPuntivendita().get(Integer.parseInt(S)).getProdotti().get(i).getPC() == false){
                String tmp = i+" - " + M.getPuntivendita().get(Integer.parseInt(S)).getProdotti().get(i).getnome() + "  " + M.getPuntivendita().get(Integer.parseInt(S)).getProdotti().get(i).getprezzo() + "€";
                Prodotti tmp1 = (Prodotti) M.getPuntivendita().get(Integer.parseInt(S)).getProdotti().get(i);
                int numCat = tmp1.getNumCategoria();
                int numSottoCat = tmp1.getNumSottocategoria();
                int numCors = tmp1.getNumCorsia();
                int disponivilità = tmp1.getDiponivilita();

                Categoria categoria = (Categoria) M.getPuntivendita().get(Integer.parseInt(S)).getCategorie().get(numCat);
                String nomeCategoria = categoria.getnome();
                String nomeSottocategoria = categoria.getNomeSottocategoria(numSottoCat);

                Corsia corsia = M.getPuntivendita().get(Integer.parseInt(S)).getCorsie().get(numCors);
                String nomeCorsia = corsia.getCodCorsia();

                String tmp2 = "Categoria: " + nomeCategoria + " - Sottocategoria: " + nomeSottocategoria + " - Corsia: " + nomeCorsia + " - Disponibilità: " + disponivilità;
                model.addElement(tmp);
                model.addElement(tmp2);
                model.addElement("\n");
                model.addElement("\n");
            }else {/*
                model.addElement("PRODOTTO COMPOSITO: ");
                String tmp = i +" - " + M.getPuntivendita().get(Integer.parseInt(S)).getProdotti().get(i).getnome() + "  " + M.getPuntivendita().get(Integer.parseInt(S)).getProdotti().get(i).getprezzo() + "€";
                ProdottoComposito tmp1 = (ProdottoComposito) M.getPuntivendita().get(Integer.parseInt(S)).getProdotti().get(i);

                int numCat = tmp1.getNumCategoria();
                int numSottoCat = tmp1.getNumSottocategoria();
                int numCors = tmp1.getNumCorsia();
                int disponivilità = tmp1.getDiponivilita();

                Categoria categoria = (Categoria) M.getPuntivendita().get(Integer.parseInt(S)).getCategorie().get(numCat);
                String nomeCategoria = categoria.getnome();
                String nomeSottocategoria = categoria.getNomeSottocategoria(numSottoCat);

                Corsia corsia = M.getPuntivendita().get(Integer.parseInt(S)).getCorsie().get(numCors);
                String nomeCorsia = corsia.getCodCorsia();

                model.addElement(tmp);
                model.addElement("\n");

                for (int j=0; j<tmp1.getSottoprodotti().size();j++){

                    model.addElement("SOTTOPRODOTTO: ");
                    String tmpA = j+" - " + tmp1.getSottoprodotti().get(j).getnome() + "  " + tmp1.getSottoprodotti().get(j).getprezzo() + "€";
                    Prodotti tmpB = (Prodotti) tmp1.getSottoprodotti().get(j);
                    int numCate = tmpB.getNumCategoria();
                    int numSottoCate = tmpB.getNumSottocategoria();
                    int numCorsi = tmpB.getNumCorsia();
                    int disponivilita = tmpB.getDiponivilita();

                    System.out.println(numCate + " " + numSottoCate);

                    Categoria categoriaA = (Categoria) M.getPuntivendita().get(Integer.parseInt(S)).getCategorie().get(numCate);
                    String nomeCategoriaA = categoriaA.getnome();
                    String nomeSottocategoriaA = categoriaA.getNomeSottocategoria(numSottoCate);

                    Corsia corsiaA = M.getPuntivendita().get(Integer.parseInt(S)).getCorsie().get(numCorsi);
                    String nomeCorsiaA = corsiaA.getCodCorsia();

                    String tmpC = "Categoria: " + nomeCategoriaA + " - Sottocategoria: " + nomeSottocategoriaA + " - Corsia: " + nomeCorsiaA + " - Disponibilità: " + disponivilita;
                    model.addElement(tmpA);
                    model.addElement(tmpC);
                    model.addElement("\n");

                }
                */
            }
        }

        JList lista = new JList();
        lista.setModel(model);

        JScrollPane scroll = new JScrollPane(lista);

        panel.add(scroll);

        this.getContentPane().add(panel,BorderLayout.CENTER);

    }

    @Override
    public JFrame getFinestra() {
        return this;
    }
}
