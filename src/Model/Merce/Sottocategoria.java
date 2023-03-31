package Model.Merce;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

//La sottocategoria contiene i prodotti (IProdotti generici, quindi sia prodotti normali che compositi),
//e contiene anche i servizi
public class Sottocategoria implements ICategoria {

    private String nome;

    public Sottocategoria(String nome){
        this.nome = nome;
    }

    @Override
    public String getnome() {
        return nome;
    }
}
