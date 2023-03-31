package Model.Merce;

import Model.Recensioni;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

//classe che si dividerà in Prodotti (con relativi prodotti congiunti) e Servizi
public class Articoli{

    private List<Double> voto = new ArrayList<>();
    private List<String> emailVoto = new ArrayList<>(); //email di chi ha votato
    private List<Recensioni> recensioni = new ArrayList<>();
    private String nome;
    protected double prezzo;
    private String descrizione;
    private String produttore;
    private ArrayList<Path> pathIMG = new ArrayList<>();
    //valori che tengono traccia (nel punto vendita) a quale categoria,sottocategoria e corsia appartiene l'articolo
    //servono quando si salvano i dati
    //vai a vedere in Business - AggiuntaNuovoProdotto
    private int numCategoria;
    private int numSottocategoria;
    private int numCorsia;
    private int numScaffale;
    private int numPuntoVendita;
    private int diponivilita;

    public int getDiponivilita() {
        return diponivilita;
    }

    public void setDiponivilita(int diponivilita) {
        this.diponivilita = diponivilita;
    }

    //costruttore da utilizzare per prodotti e servizi
    public Articoli(String nome,double prezzo){
        this.nome = nome;
        this.prezzo = prezzo;
    }

    //costruttore da utilizzare per prodotti compositi
    public Articoli(String nome){
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {this.nome = nome;}

    public void setDescrizione(String descr){
        descrizione = descr;
    }

    public void setProduttore(String prod){
        produttore = prod;
    }

    public void setPathIMG(ArrayList<Path> path){
        pathIMG = path;
    }

    public void addPathIMG(Path path){pathIMG.add(path);}

    public void setNumCategoria(int num){
        numCategoria = num;
    }

    public void setNumSottocategoria(int num){
        numSottocategoria = num;
    }

    public void setNumCorsia(int num){
        numCorsia = num;
    }

    public void setPrezzo(Double prez){ prezzo = prez; }

    public double getPrezzo() {
        return prezzo;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public String getProduttore() {
        return produttore;
    }

    public ArrayList<Path> getPathIMG() {
        return pathIMG;
    }

    public Path getSiglePath(int i){return pathIMG.get(i);}

    public int getNumCategoria() {
        return numCategoria;
    }

    public int getNumSottocategoria() {
        return numSottocategoria;
    }

    public int getNumCorsia() {
        return numCorsia;
    }

    public int getNumScaffale() {
        return numScaffale;
    }

    public void setNumScaffale(int numScaffale) {
        this.numScaffale = numScaffale;
    }

    public int getNumPuntoVendita() {
        return numPuntoVendita;
    }

    public void setNumPuntoVendita(int numPuntoVendita) {
        this.numPuntoVendita = numPuntoVendita;
    }

    public List<Recensioni> getRecensioni() {
        return recensioni;
    }

    public void setRecensioni(List<Recensioni> recensioni) {
        recensioni = recensioni;
    }

    public void addRecensioni(Recensioni recensione){
        recensioni.add(recensione);
    }

    public List<Double> getVoto() {
        return voto;
    }

    public void setVoto(List<Double> voto) {
        this.voto = voto;
    }

    public void addVoto(double myVoto){
        voto.add(myVoto);
    }

    public double getMedia(){
        double media=0;
        for (int i=0; i<voto.size(); i++) {
            media = media + voto.get(i);
        }
        media = media/voto.size();
        return media;
    }

    public List<String> getEmailVoto() {
        return emailVoto;
    }

    public void setEmailVoto(List<String> emailVoto) {
        this.emailVoto = emailVoto;
    }

    public void addEmailVoto(String email){
        emailVoto.add(email);
    }
}
