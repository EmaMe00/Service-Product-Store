package Model.Merce;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

//La corsia contiene più scaffali
public class Corsia {

    private String codCorsia;
    private List<Scaffale> scaffali = new ArrayList<Scaffale>();

    public Corsia(String codCorsia){
        this.codCorsia = codCorsia;
    }

    public void add(Scaffale scaffale){
        scaffali.add(scaffale);
    }

    public String getCodCorsia(){
        return codCorsia;
    }

    public List<Scaffale> getScaffali() { return scaffali; }

    public void setScaffali(List<Scaffale> scaffali) { this.scaffali = scaffali; }



}
