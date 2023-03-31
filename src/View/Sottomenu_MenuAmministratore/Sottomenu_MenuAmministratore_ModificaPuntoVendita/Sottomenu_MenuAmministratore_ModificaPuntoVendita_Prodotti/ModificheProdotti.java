package View.Sottomenu_MenuAmministratore.Sottomenu_MenuAmministratore_ModificaPuntoVendita.Sottomenu_MenuAmministratore_ModificaPuntoVendita_Prodotti;

import Controller.ControllerSottomenu_MenuAmministratore.ControllerSottomenu_MenuAmministratore_ModificaPuntoVenditaController.ControllerSottomenu_MenuAmministratore_ModificaPuntoVenditaController_ModificheProdottiController.ModificheProdottiController;
import Model.Merce.*;
import View.ControlloFinestre.IFinestre;
import View.MenuAmministratore;
import View.MenuPrincipale;
import View.Sottomenu_MenuAmministratore.Sottomenu_MenuAmministratore_ModificaPuntoVendita.MenuModifichePuntoVendita;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

//Frame per la modifica dei prodotti, si sceglie il tipo di modifica da apportare
public class ModificheProdotti extends JFrame implements IFinestre {

    boolean managerIdentificator = false;
    private JTextField textField = new JTextField();

    public ModificheProdotti(){super("MyShop");}

    public void StartModificheProdotti(MenuAmministratore M, MenuModifichePuntoVendita M1, String S){

        this.setSize(800,600);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        JPanel p1 = new JPanel(new GridLayout(3,2));
        JLabel label = new JLabel("Inserisci il numero del prodotto da modificare: ");
        JButton modifica = new JButton("MODIFICA");
        JButton composito = new JButton("PRODOTTO COMPOSITO");
        JButton nuovo = new JButton("NUOVO");
        JButton elimina = new JButton("ELIMINA");
        JButton eliminaImmagini = new JButton("ELIMINA IMMAGINI");

        p1.add(label); p1.add(textField); p1.add(modifica); p1.add(nuovo); p1.add(elimina);p1.add(eliminaImmagini);

        this.getContentPane().add(p1,BorderLayout.NORTH);

        VisualizzaPuntiProdottiList(M,S);

        JPanel p2 = new JPanel(new GridLayout(1,1));
        JButton indietro = new JButton("INDIETRO");
        p2.add(indietro); p2.add(composito);
        this.getContentPane().add(p2,BorderLayout.SOUTH);
        this.setVisible(true);

        ActionListener A = new ModificheProdottiController(this,M,M1,S);

        indietro.addActionListener(A);
        indietro.setActionCommand("Indietro");

        nuovo.addActionListener(A);
        nuovo.setActionCommand("Nuovo");

        composito.addActionListener(A);
        composito.setActionCommand("Composito");

        modifica.addActionListener(A);
        modifica.setActionCommand("Modifica");

        elimina.addActionListener(A);
        elimina.setActionCommand("Elimina");

        eliminaImmagini.addActionListener(A);
        eliminaImmagini.setActionCommand("Elimina immagini");

    }


    //Metodo per la generazione di una lista di prodotti numerata
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
            }else {
                String tmp3 = "Prodotto Composito: ";
                String tmp = i+" - " + M.getPuntivendita().get(Integer.parseInt(S)).getProdotti().get(i).getnome() + "  " + M.getPuntivendita().get(Integer.parseInt(S)).getProdotti().get(i).getprezzo() + "€";
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

                model.addElement(tmp3);
                model.addElement(tmp);


                for (int j=0; j<tmp1.getSottoprodotti().size();j++){

                    String tmpA = "   " + j +" - " + tmp1.getSottoprodotti().get(j).getnome() + "  " + tmp1.getSottoprodotti().get(j).getprezzo() + "€";
                    Prodotti tmpB = (Prodotti) tmp1.getSottoprodotti().get(j);
                    int numCate = tmpB.getNumCategoria();
                    int numSottoCate = tmpB.getNumSottocategoria();
                    int numCorsi = tmpB.getNumCorsia();
                    int disponivilita = tmpB.getDiponivilita();

                    Categoria categoriaA = (Categoria) M.getPuntivendita().get(Integer.parseInt(S)).getCategorie().get(numCate);
                    String nomeCategoriaA = categoriaA.getnome();
                    System.out.println(nomeCategoriaA);
                    String nomeSottocategoriaA = categoriaA.getNomeSottocategoria(numSottoCate);

                    Corsia corsiaA = M.getPuntivendita().get(Integer.parseInt(S)).getCorsie().get(numCorsi);
                    String nomeCorsiaA = corsiaA.getCodCorsia();

                    String tmpC = "   Categoria: " + nomeCategoriaA + " - Sottocategoria: " + nomeSottocategoriaA + " - Corsia: " + nomeCorsiaA + " - Disponibilità: " + disponivilita;
                    model.addElement(tmpA);
                    model.addElement(tmpC);

                }

            }
        }

        JList lista = new JList();
        lista.setModel(model);

        JScrollPane scroll = new JScrollPane(lista);

        panel.add(scroll);

        this.getContentPane().add(panel,BorderLayout.CENTER);

    }

    public boolean getManagerIdentificator(){
        return managerIdentificator;
    }

    public void setManagerIdentificator(boolean a){managerIdentificator = a;}

    @Override
    public JFrame getFinestra() {
        return this;
    }

    public String getTextField() {
        return textField.getText();
    }

}
