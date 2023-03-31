package View.Sottomenu_MenuAmministratore.Sottomenu_MenuAmministratore_ModificaPuntoVendita.Sottomenu_MenuAmministratore_ModificaPuntoVendita_Servizi;

import Controller.ControllerSottomenu_MenuAmministratore.ControllerSottomenu_MenuAmministratore_ModificaPuntoVenditaController.ControllerSottomenu_MenuAmministratore_ModificaPuntoVenditaController_MenuServiziController.ModificaServizioSingoloController;
import Model.Merce.*;
import View.ControlloFinestre.ControlloFinestre;
import View.ControlloFinestre.IFinestre;
import View.MenuAmministratore;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

//Frame per la modifica del servizio
public class ModificaServizioSingolo extends JFrame implements IFinestre {

    JTextField nome = new JTextField();
    JTextField descrizione = new JTextField();
    JTextField costo = new JTextField();
    JTextField produttore = new JTextField();
    JTextField disponibita = new JTextField();

    JTextField categoria = new JTextField();
    JTextField sottocategoria = new JTextField();

    public void StartModificaSingoloServizio(MenuAmministratore M, MenuServizi MS,String S){

        this.setSize(1000,1000);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        JPanel p1 = new JPanel(new GridLayout(12,2));
        JLabel l1 = new JLabel("INSERISCI NUOVO SERVIZIO",SwingConstants.CENTER);
        JLabel space = new JLabel(" ");
        p1.add(l1); p1.add(space);

        JLabel lnome = new JLabel("Nome",SwingConstants.CENTER);
        JLabel ldescrizione = new JLabel("Descrizione",SwingConstants.CENTER);
        JLabel lcosto = new JLabel("Costo",SwingConstants.CENTER);
        JLabel lproduttore = new JLabel("Produttore",SwingConstants.CENTER);
        p1.add(lnome); p1.add(nome); p1.add(ldescrizione); p1.add(descrizione); p1.add(lcosto); p1.add(costo);
        p1.add(lproduttore); p1.add(produttore);

        JLabel ldisponibilita = new JLabel("Disponibilità",SwingConstants.CENTER);
        p1.add(ldisponibilita); p1.add(disponibita);

        JLabel l2 = new JLabel("CATEGORIE",SwingConstants.CENTER);
        JLabel space2 = new JLabel(" ");
        p1.add(l2); p1.add(space2);

        JLabel lcategoria = new JLabel("NUMERO CATEGORIA",SwingConstants.CENTER);
        JLabel lsottocategoria = new JLabel("NUMERO SOTTOCATEGORIA",SwingConstants.CENTER);
        p1.add(lcategoria); p1.add(categoria); p1.add(lsottocategoria); p1.add(sottocategoria);


        this.getContentPane().add(p1,BorderLayout.NORTH);

        JScrollPane tmp = VisualizzaCategorieList(M,S);

        JPanel p2 = new JPanel(new GridLayout(1,1));
        p2.add(tmp);
        this.getContentPane().add(p2,BorderLayout.CENTER);

        JPanel p7 = new JPanel(new GridLayout(2,2));
        JLabel limmagine = new JLabel("CARICA IMMAGINE",SwingConstants.CENTER);
        JButton immagine = new JButton("CARICA");
        JButton indietro = new JButton("INDIETRO");
        JButton salva = new JButton("SALVA");
        p7.add(limmagine); p7.add(immagine); p7.add(indietro); p7.add(salva);

        SetDatiLabel(M,MS,S);

        this.getContentPane().add(p7,BorderLayout.SOUTH);

        this.setVisible(true);

        ActionListener A = new ModificaServizioSingoloController(this,M,MS,S);

        indietro.addActionListener(A);
        indietro.setActionCommand("Indietro");

        immagine.addActionListener(A);
        immagine.setActionCommand("Carica");

        salva.addActionListener(A);
        salva.setActionCommand("Salva");

    }

    public JScrollPane VisualizzaCategorieList(MenuAmministratore M,String S){

        JPanel panel = new JPanel(new GridLayout(1,1));
        DefaultListModel model = new DefaultListModel();
        model.addElement("ELENCO CATEGORIE");
        PuntiVendita tmp = M.getPuntivendita().get(Integer.parseInt(S));

        for (int i=0;i<tmp.getCategorie().size();i++){
            ICategoria ICtmp = tmp.getCategorie().get(i);
            Categoria Ctmp = (Categoria) ICtmp;
            String stmp = i + ") " +  Ctmp.getnome();
            model.addElement(stmp);
            for (int j=0; j<Ctmp.getSottocategorie().size(); j++){
                String sstmp = "  " + j + " - " + Ctmp.getSottocategorie().get(j).getnome();
                model.addElement(sstmp);
            }
        }

        JList lista = new JList();
        lista.setModel(model);

        JScrollPane scroll = new JScrollPane(lista);

        //panel.add(scroll);

        //this.getContentPane().add(panel,BorderLayout.CENTER);
        return scroll;

    }

    //questo metodo prende i dati del vecchio servizio e setta tutto nelle label in modo da poi poter modificare ciò che si vuole
    public void SetDatiLabel(MenuAmministratore M, MenuServizi MS,String S){
        try{
            Servizi servizio = M.getPuntivendita().get(Integer.parseInt(S)).getServizi().get(Integer.parseInt(MS.getnServizo()));
            nome.setText(servizio.getNome());
            descrizione.setText(servizio.getDescrizione());
            costo.setText(String.valueOf(servizio.getPrezzo()));
            produttore.setText(servizio.getProduttore());
            categoria.setText(String.valueOf(servizio.getNumCategoria()));
            sottocategoria.setText(String.valueOf(servizio.getNumSottocategoria()));
            disponibita.setText(String.valueOf(servizio.getDiponivilita()));
        } catch (Exception E){
            JOptionPane.showMessageDialog(M, "Inserisci i dati correttamente!");
            ControlloFinestre.CloseFrame(this);
            ControlloFinestre.SwitchFrameOn(MS);
        }


    }

    public String getNome() {
        return nome.getText();
    }
    public void setNome(String n) {nome.setText(n);}

    public String getDescrizione() {
        return descrizione.getText();
    }
    public void setDescrizione(String d) {descrizione.setText(d);}

    public String getCosto() {
        return costo.getText();
    }
    public void setCosto(String d) {costo.setText(d);}

    public String getProduttore() {
        return produttore.getText();
    }
    public void setProduttore(String p) {produttore.setText(p);}

    public String getCategoria() {
        return categoria.getText();
    }
    public void setCategoria(String c) {categoria.setText(c);}

    public String getSottocategoria() {
        return sottocategoria.getText();
    }
    public void setSottocategoria(String s) {sottocategoria.setText(s);}

    public String getDisponibilita() { return  disponibita.getText(); }
    public void setDisponibita(String d) {disponibita.setText(d);}

    @Override
    public JFrame getFinestra() {
        return this;
    }
}
