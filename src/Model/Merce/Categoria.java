package Model.Merce;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

//La categoria contiene le generiche (ICategorie) che in questo caso possono essere categorie o sottocategorie
//Utilizza il design pattern composite
public class Categoria implements ICategoria{

    private String nome; //("mobili","illuminazione",tessili")
    private List<ICategoria> sottocategorie = new ArrayList<ICategoria>();


    public Categoria(String nome){
        this.nome = nome;
    }

    public void add(ICategoria sottocategoria) {
        sottocategorie.add(sottocategoria);
    }

    @Override
    public String getnome() {
        return nome;
    }

    public String getNomeSottocategoria(int i){ return sottocategorie.get(i).getnome(); }

    public List<ICategoria> getSottocategorie(){ return  sottocategorie; }

    //Stampa il nome della categoria e le sottocategorie che contiene
    public void testprint(){
        System.out.println("Categoria principale: " + nome);
        System.out.println("Sottocategorie: ");
        for (ICategoria sottocategoria : sottocategorie){
            System.out.println(sottocategoria.getnome());
        }
    }

}
