package Model.Utenti;

import Model.Merce.IProdotti;
import Model.Merce.Prodotti;
import Model.Merce.Servizi;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

//Utente generico presente all'interno della nostra piattaforma
public class Utente {

    //Carrello dei prodotti
    private List<IProdotti> carrelloProdotti = new ArrayList<>();
    //Carrello dei servizi
    private List<Servizi> carrelloServizi = new ArrayList<>();

    //Lista dei prodotti già acquistata
    private List<IProdotti> prodottiAcquistati = new ArrayList<>();
    //Lista dei servizi già acquistata
    private List<Servizi> serviziAcquistati = new ArrayList<>();

    private String nome;
    private String cognome;
    private String nickname;
    private String email;
    private String password;
    private String telefono;
    private String citta;
    private int eta;
    private int numPuntoVendita = -1; //Iscrizione al punto vendita


    public Utente(){
        this.nome = "";
        this.nickname = "";
        this.email = "";
        this.eta = 0;
    }


    public Utente(String nome, String cognome ,String nickname, String email,String password,String telefono,String citta, int eta){
        this.nome = nome;
        this.cognome = cognome;
        this.nickname = nickname;
        this.email = email;
        this.eta = eta;
        this.password = password;
        this.telefono = telefono;
        this.citta = citta;
    }

    // Builder
    public static class Builder {

        private String nome;
        private String cognome;
        private String nickname;
        private String email;
        private String password;
        private String telefono;
        private String citta;
        private int eta;
        private int numPuntoVendita = -1; //Iscrizione al punto vendita

        public Builder(String nome){
            this.nome = nome;
            this.cognome="";
            this.nickname="";
            this.email="";
            this.password="";
            this.telefono="";
            this.citta="";
            this.eta=0;
            this.numPuntoVendita = -1;
        }

        public Utente build() throws NoSuchAlgorithmException {
            Utente utente = new Utente();
            utente.nome = this.nome;
            utente.cognome = this.cognome;
            utente.nickname = this.nickname;
            utente.email = this.email;
            utente.setPassword(this.password);
            utente.telefono = this.telefono;
            utente.citta = this.citta;
            utente.eta = this.eta;
            utente.numPuntoVendita = this.numPuntoVendita;
            return utente;
        }

        public Builder setNumPuntoVendita(int numPuntoVendita) {
            this.numPuntoVendita = numPuntoVendita;
            return this;
        }

        public Builder setCognome(String cognome) {
            this.cognome = cognome;
            return this;
        }

        public Builder setNickname(String nickname) {
            this.nickname = nickname;
            return this;
        }

        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder setPassword(String password) {
            this.password = password;
            return this;
        }

        public Builder setTelefono(String telefono) {
            this.telefono = telefono;
            return this;
        }

        public Builder setCitta(String citta) {
            this.citta = citta;
            return this;
        }

        public Builder setEta(int eta) {
            this.eta = eta;
            return this;
        }
    }

    public String getNome() {
        return nome;
    }

    public String getNickname() {
        return nickname;
    }

    public String getEmail() {
        return email;
    }

    public int getEta() {
        return eta;
    }

    public String getCognome() { return cognome; }

    public String getPassword() { return password; }

    public String getCitta(){ return citta; }

    public String getTelefono(){ return  telefono; }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setEta(int eta) {
        this.eta = eta;
    }

    public void setCognome(String cognome){ this.cognome = cognome; }

    public void setTelefono(String telefono){ this.telefono = telefono; }

    public void setPassword(String password) throws NoSuchAlgorithmException {

        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes());
        byte[] digest = md.digest();
        BigInteger bigInt = new BigInteger(1,digest);
        password = bigInt.toString();

        this.password = password;
    }

    public void setPasswordNOMD5(String password){
        this.password = password;
    }

    public void setCitta(String citta){
        this.citta = citta;
    }

    public void addProdottoalCarrello(IProdotti prodotto){
        carrelloProdotti.add(prodotto);
    }

    public void removeProdottodalCarrello(int i){
        carrelloProdotti.remove(i);
    }

    public List<IProdotti> getCarrelloProdotti(){
        return carrelloProdotti;
    }

    public void addServiziolCarrello(Servizi servizio){
        carrelloServizi.add(servizio);
    }

    public void removeServiziodalCarrello(int i){
        carrelloServizi.remove(i);
    }

    public List<Servizi> getCarrelloServizi(){ return carrelloServizi;}

    public int getNumPuntoVendita() {
        return numPuntoVendita;
    }

    public void setNumPuntoVendita(int numPuntoVendita) {
        this.numPuntoVendita = numPuntoVendita;
    }

    public void setCarrelloProdotti(List<IProdotti> carrelloProdotti) {
        this.carrelloProdotti = carrelloProdotti;
    }

    public void setCarrelloServizi(List<Servizi> carrelloServizi) {
        this.carrelloServizi = carrelloServizi;
    }

    public List<IProdotti> getProdottiAcquistati() {
        return prodottiAcquistati;
    }

    public void addProdottiAcquistati(IProdotti i){
        prodottiAcquistati.add(i);
    }

    public void setProdottiAcquistati(List<IProdotti> prodottiAcquistati) {
        this.prodottiAcquistati = prodottiAcquistati;
    }

    public List<Servizi> getServiziAcquistati() {
        return serviziAcquistati;
    }

    public void addServiziAcquistati(Servizi s){
        serviziAcquistati.add(s);
    }

    public void setServiziAcquistati(List<Servizi> serviziAcquistati) {
        this.serviziAcquistati = serviziAcquistati;
    }
}


