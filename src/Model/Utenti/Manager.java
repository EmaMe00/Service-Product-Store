package Model.Utenti;
//Attributi riguardanti i nostri manager
public class Manager extends Utente {


    //numero del punto vendita che controlla il manager
    private int numPuntoVendita;

    public Manager(int numPuntoVendita){
        this.numPuntoVendita = numPuntoVendita;
    }

    public Manager(){}

    public int getnumPuntoVendita() { return  numPuntoVendita; }



}
