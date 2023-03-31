package Model;

import java.util.Date;

public class Recensioni {

    private String nome;
    private String cognome;
    private String testoRecensione;
    private String data;
    private String email; //email dell'utente o del manager che inserisce la recensione

    public Recensioni(String nome, String cognome, String testoRecensione, String data,String email){
        this.nome = nome;
        this.cognome = cognome;
        this.testoRecensione = testoRecensione;
        this.data = data;
        this.email = email;
    }

    public Recensioni(){}

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getTestoRecensione() {
        return testoRecensione;
    }

    public void setTestoRecensione(String testoRecensione) {
        this.testoRecensione = testoRecensione;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
