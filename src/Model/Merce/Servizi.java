package Model.Merce;

import java.io.Serializable;

public class Servizi extends Articoli {

    public Servizi(String nome, double prezzo) {
        super(nome, prezzo);
    }

    public boolean getServ() {
        return true;
    }

}
