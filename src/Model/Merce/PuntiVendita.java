package Model.Merce;

import Model.Utenti.Manager;

import java.util.ArrayList;
import java.util.List;

//Nel punto vendita deve essere possibile gestire tutto
public class PuntiVendita{

    private String nome;
    private Manager manager;

    private List<IProdotti> prodotti = new ArrayList<IProdotti>();
    private List<Servizi> servizi = new ArrayList<Servizi>();
    private List<Corsia> corsie = new ArrayList<Corsia>();
    private List<ICategoria> categorie = new ArrayList<ICategoria>();

    public PuntiVendita(String nome){
        this.nome = nome;

        Categoria cat11 = new Categoria("CategoriaNulla");
        ICategoria sottocat11 = new Sottocategoria("SottocategoriaNulla");
        cat11.add(sottocat11);
        categorie.add(cat11);

        Corsia cors = new Corsia("CorsiaNulla");
        Scaffale scaf = new Scaffale(9999);
        cors.add(scaf);
        corsie.add(cors);
    }

    public void addProdotto(IProdotti prodotto){
        prodotti.add(prodotto);
    }

    public void addServizi(Servizi servizio){
        servizi.add(servizio);
    }

    public void addCorsia(Corsia corsia){
        corsie.add(corsia);
    }

    public void addCategoria(ICategoria categoria){ this.categorie.add(categoria); }

    public String getNome(){
        return nome;
    }

    public Manager getManager() {
        return manager;
    }

    public List<IProdotti> getProdotti() {
        return prodotti;
    }

    public List<Servizi> getServizi() {
        return servizi;
    }

    public List<Corsia> getCorsie() {
        return corsie;
    }

    public List<ICategoria> getCategorie() {
        return categorie;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    public void setProdotti(List<IProdotti> prodotti) {
        this.prodotti = prodotti;
    }

    public void setServizi(List<Servizi> servizi) {
        this.servizi = servizi;
    }

    public void setCorsie(List<Corsia> corsie) {
        this.corsie = corsie;
    }

    public void setCategorie(List<ICategoria> categorie) {
        this.categorie = categorie;
    }

}





