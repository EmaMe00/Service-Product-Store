package Model.Merce;

//interfaccia utilizzata per utilizzare un design pattern composite
//interfaccia per creare un generico IProdotto (può essere un prodotto o un prodotto composito),
//in modo da rendere più versatile e generico il codice
public interface IProdotti {
    public Double getprezzo();
    public String getnome();
    public boolean getPC();
    public int getNumPuntoVendita();
}
