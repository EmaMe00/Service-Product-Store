package Model;

public class Sessione {

    private int numManager;
    private String emailManager;
    private String passwordManager;
    private int numUtente;
    private String email; //utente
    private String password; //utente

    public Sessione(String email, String password, int numUtente){
        this.email = email;
        this.password = password;
        this.numUtente = numUtente;
        numManager = -1;
    }

    public Sessione(){
        System.out.println("Nuova sessione creata!");
        numUtente = -1;
        numManager = -1;
    }

    public int getNumManager() {
        return numManager;
    }

    public void setNumManager(int numManager) {
        this.numManager = numManager;
    }

    public int getNumUtente() {
        return numUtente;
    }

    public void setNumUtente(int numUtente) {
        this.numUtente = numUtente;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmailManager() {
        return emailManager;
    }

    public void setEmailManager(String emailManager) {
        this.emailManager = emailManager;
    }

    public String getPasswordManager() {
        return passwordManager;
    }

    public void setPasswordManager(String passwordManager) {
        this.passwordManager = passwordManager;
    }
}
