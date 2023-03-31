package Model.Merce;

//Prodotti singoli
public class Prodotti extends Articoli implements IProdotti {

    public Prodotti(String nome, double prezzo) {
        super(nome, prezzo);
    }

    //Stampa le informazioni riguardanti il prodotto
    public void testPrint(){
        System.out.println("Nome: " + getNome());
        System.out.println("Prezzo: " + getprezzo());
    }

    @Override
    public Double getprezzo() {
        return prezzo;
    }

    public void setPrezzo(double prezzo){ this.prezzo = prezzo; }

    @Override
    public String getnome() {
        return getNome();
    }

    @Override
    public boolean getPC() {
        return false;
    }

    public void clonaProdotto(Prodotti prodotto){
        this.setNome(prodotto.getNome());
        this.setPrezzo(prodotto.getPrezzo());
        this.setDiponivilita(prodotto.getDiponivilita());
        this.setNumSottocategoria(prodotto.getNumSottocategoria());
        this.setNumCategoria(prodotto.getNumCategoria());
        this.setDescrizione(prodotto.getDescrizione());
        this.setProduttore(prodotto.getProduttore());
        this.setNumCorsia(prodotto.getNumCorsia());
        this.setNumScaffale(prodotto.getNumScaffale());
        this.setPathIMG(prodotto.getPathIMG());
    }


}

