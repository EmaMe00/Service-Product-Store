package Model.Merce;

import java.util.ArrayList;
import java.util.List;

//è un prodotto che contine più prodotti (Utilizziamo il design pattern composite)
public class ProdottoComposito extends Articoli implements IProdotti {

    //creo una lista generica di IProdotti (in modo da poter inserire sia prodotti che prodotti compositi)
    private List<IProdotti> sottoprodotti = new ArrayList<IProdotti>();

    //nome del prodotto composito
    public ProdottoComposito(String nome) {
        super(nome);
    }

    //aggiungo alla mia lista il geneiro IProdotto (che può essere un prodotto o un prodotto composito)
    public void add(IProdotti sottoprodotto){
        sottoprodotti.add(sottoprodotto);
    }

    //stampa il nome del prodotto composito e i prodotti che lo compongono
    public void testPrint(){
        System.out.println("Prodotto composto: " + getNome());
        System.out.println("Sottoprodotti: ");
        for (IProdotti sotprod : sottoprodotti){
            Prodotti prod = (Prodotti) sotprod;
            System.out.println(prod.getNome() + " Prezzo: " + prod.getprezzo());
        }
        System.out.println("Prezzo composito: " + getprezzo());
    }

    @Override
    public Double getprezzo() {
        double prezzo = 0;
        for (IProdotti sotprod : sottoprodotti){
            prezzo = prezzo + sotprod.getprezzo();
        }
        double tmp= (prezzo*30)/100;
        prezzo = prezzo-tmp;
        return prezzo;
    }

    @Override
    public String getnome() {
        return getNome();
    }

    @Override
    public boolean getPC() {
        return true;
    }

    public List<IProdotti> getSottoprodotti() {
        return sottoprodotti;
    }

    public void setSottoprodotti(List<IProdotti> sottoprod) { sottoprodotti = sottoprod; }

}
