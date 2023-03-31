package Model;

import Model.Merce.*;
import Model.Utenti.Manager;
import Model.Utenti.Utente;
import com.sun.tools.internal.xjc.model.CPropertyVisitor;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

//Questo è il database temporaneo su cui lavora il programma, tutte le modifiche che vengono fatte verranno
//salvate qui dentro

public class FakeDatabase implements Container{

    private static FakeDatabase instance;

    private List<PuntiVendita> puntivendita = new ArrayList<PuntiVendita>();
    private List<Utente> listautenti = new ArrayList<>();
    private List<Manager> listamanager = new ArrayList<>();
    private Sessione sessione = new Sessione();

    private FakeDatabase() {}

    // Singleton
    public static FakeDatabase getInstance(){
        if (instance == null){
            instance = new FakeDatabase();
        }
        return instance;
    }

    //Sostituisce il database in ingresso come parametro con il database presente qui
    public void setPuntiVendita(List<PuntiVendita> puntivendita) {
        this.puntivendita = puntivendita;
    }

    //Recupera il database
    public List<PuntiVendita> getPuntivendita() {
        return puntivendita;
    }

    //Aggiunge concatena a questo database il database in ingresso come parametro
    public void addPuntiVendita(List<PuntiVendita> puntivendita) {
        this.puntivendita.addAll(puntivendita);
    }

    public List<Utente> getListaUtenti() {
        return listautenti;
    }

    public void setListaUtenti(List<Utente> lista) {
        listautenti = lista;
    }

    public List<Manager> getListamanager() {
        return listamanager;
    }

    public void setListamanager(List<Manager> listamanager) {
        this.listamanager = listamanager;
    }

    public Sessione getSessione() {
        return sessione;
    }

    public void setSessione(Sessione sessione) {
        this.sessione = sessione;
    }

    public void resetSessione(){
        this.sessione = new Sessione();
    }

    public void DefaultDatabase() throws NoSuchAlgorithmException {
        PuntiVendita pt = new PuntiVendita("IKEA");

        Categoria cat = new Categoria("Mobili");
        ICategoria sottocat = new Sottocategoria("Camera da letto");
        cat.add(sottocat);
        sottocat = new Sottocategoria("Soggiorno");
        cat.add(sottocat);
        sottocat = new Sottocategoria("Bagno");
        cat.add(sottocat);
        ICategoria categoria = (ICategoria) cat;
        pt.addCategoria(categoria);

        Categoria cat1 = new Categoria("Spedizioni");
        ICategoria sottocat1 = new Sottocategoria("Pacchi");
        cat1.add(sottocat1);
        sottocat1 = new Sottocategoria("Mobili");
        cat1.add(sottocat1);
        sottocat1 = new Sottocategoria("Animali");
        cat1.add(sottocat1);
        ICategoria categoria1 = (ICategoria) cat1;
        pt.addCategoria(categoria1);

        Corsia corsia = new Corsia("CORS1");
        pt.addCorsia(corsia);

        Scaffale scaffale = new Scaffale(1111);
        corsia.add(scaffale);
        Scaffale scaffale1 = new Scaffale(2222);
        corsia.add(scaffale1);

        corsia = new Corsia("CORS2");
        Scaffale sc = new Scaffale(3213);
        corsia.add(sc);
        pt.addCorsia(corsia);

        Prodotti prodotto = new Prodotti("Letto",580);
        Path mypath = Paths.get("src/img/prodotti/letto1.jpeg");
        prodotto.addPathIMG(mypath);
        mypath = Paths.get("src/img/prodotti/letto2.jpeg");
        prodotto.addPathIMG(mypath);
        mypath = Paths.get("src/img/prodotti/letto3.jpeg");
        prodotto.addPathIMG(mypath);
        mypath = Paths.get("src/img/prodotti/letto4.png");
        prodotto.addPathIMG(mypath);
        prodotto.setNumCorsia(1);
        prodotto.setNumPuntoVendita(0);
        prodotto.setNumSottocategoria(0);
        prodotto.setDiponivilita(3);
        prodotto.setNumCategoria(1);
        prodotto.setProduttore("Lupilli");
        prodotto.setDescrizione("Letto in ferro battuto");
        prodotto.setNumScaffale(0);
        LocalDateTime d = LocalDateTime.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        String data = dtf.format(d);
        Recensioni recensione = new Recensioni("Emanuele","Mele","Questa è una recensione di test",data,"emanuele.mele@studenti.unisalento.it");
        prodotto.addRecensioni(recensione);
        prodotto.addVoto(5);
        prodotto.addEmailVoto("myshop2021emfg@gmail.com");
        prodotto.addVoto(3.5);
        prodotto.addEmailVoto("emanuele.mele@studenti.unisalento.it");
        pt.addProdotto(prodotto);

        Prodotti prodotto1 = new Prodotti("Divano",580);
        Path mypath1 = Paths.get("src/img/prodotti/divano1.jpeg");
        prodotto1.addPathIMG(mypath1);
        mypath1 = Paths.get("src/img/prodotti/divano2.jpeg");
        prodotto1.addPathIMG(mypath1);
        prodotto1.setNumCorsia(1);
        prodotto1.setNumPuntoVendita(0);
        prodotto1.setNumSottocategoria(1);
        prodotto1.setDiponivilita(5);
        prodotto1.setNumCategoria(1);
        prodotto1.setProduttore("Gianni");
        prodotto1.setDescrizione("Divano in pelle vera");
        prodotto1.setNumScaffale(1);
        pt.addProdotto(prodotto1);

        Prodotti prodotto2 = new Prodotti("Poltrona",580);
        Path mypath2 = Paths.get("src/img/prodotti/poltrona1.jpeg");
        prodotto2.addPathIMG(mypath2);
        mypath2 = Paths.get("src/img/prodotti/poltrona2.jpeg");
        prodotto2.addPathIMG(mypath2);
        prodotto2.setNumCorsia(2);
        prodotto2.setNumSottocategoria(1);
        prodotto2.setDiponivilita(5);
        prodotto2.setNumCategoria(1);
        prodotto2.setNumPuntoVendita(0);
        prodotto2.setProduttore("Luca");
        prodotto2.setDescrizione("Poltrona in pelle vera");
        prodotto2.setNumScaffale(0);
        pt.addProdotto(prodotto2);

        Prodotti prodotto4 = new Prodotti("Tavolo",580);
        Path mypath4 = Paths.get("src/img/prodotti/tavolino1.jpeg");
        prodotto4.addPathIMG(mypath4);
        mypath4 = Paths.get("src/img/prodotti/tavolino2.jpeg");
        prodotto4.addPathIMG(mypath4);
        prodotto4.setNumCorsia(2);
        prodotto4.setNumSottocategoria(1);
        prodotto4.setDiponivilita(5);
        prodotto4.setNumCategoria(1);
        prodotto4.setNumPuntoVendita(0);
        prodotto4.setProduttore("Magri");
        prodotto4.setDescrizione("Tavolino in legno");
        prodotto4.setNumScaffale(0);
        pt.addProdotto(prodotto4);

        Prodotti prodotto44 = new Prodotti("Tavolino",580);
        Path mypath44 = Paths.get("src/img/prodotti/tavolino1.jpeg");
        prodotto44.addPathIMG(mypath44);
        mypath44 = Paths.get("src/img/prodotti/tavolino2.jpeg");
        prodotto44.addPathIMG(mypath44);
        prodotto44.setNumCorsia(2);
        prodotto44.setNumSottocategoria(1);
        prodotto44.setNumPuntoVendita(0);
        prodotto44.setDiponivilita(5);
        prodotto44.setNumCategoria(1);
        prodotto44.setProduttore("Magri");
        prodotto44.setDescrizione("Tavolino in legno");
        prodotto44.setNumScaffale(0);

        Prodotti prodotto22 = new Prodotti("Poltroncina",580);
        Path mypath22 = Paths.get("src/img/prodotti/poltrona1.jpeg");
        prodotto22.addPathIMG(mypath22);
        mypath22 = Paths.get("src/img/prodotti/poltrona2.jpeg");
        prodotto22.addPathIMG(mypath22);
        prodotto22.setNumCorsia(2);
        prodotto22.setNumSottocategoria(1);
        prodotto22.setDiponivilita(5);
        prodotto22.setNumPuntoVendita(0);
        prodotto22.setNumCategoria(1);
        prodotto22.setProduttore("Luca");
        prodotto22.setDescrizione("Poltrona in pelle vera");
        prodotto22.setNumScaffale(0);

        ProdottoComposito prodottoComposito = new ProdottoComposito("TAVOLINO+POLTRONA (KIT)");
        prodottoComposito.add(prodotto44);
        prodottoComposito.add(prodotto22);
        pt.addProdotto(prodottoComposito);



        Manager manager1 = new Manager(0);
        manager1.setNome("Manager");
        manager1.setEmail("manager");
        manager1.setPassword("manager");
        manager1.setNickname("manager");
        manager1.setCognome("manager");
        manager1.setNumPuntoVendita(0);
        listamanager.add(manager1);

        Utente utente = new Utente.Builder("UserName")
                .setEmail("user")
                .setPassword("user")
                .setCognome("UserSurname")
                .setNickname("User")
                .setNumPuntoVendita(1)
                .build();
        listautenti.add(utente);

        Utente utente1 = new Utente.Builder("Emanuele")
                .setEmail("myshop2021emfg@gmail.com")
                .setPassword("Myshop2021")
                .setCognome("Federico")
                .setNickname("EmaFede")
                .setNumPuntoVendita(1)
                .build();
        listautenti.add(utente1);

        Utente utente2 = new Utente.Builder("Emanuele")
                .setEmail("emanuele.mele@studenti.unisalento.it")
                .setPassword("emanuele")
                .setCognome("Mele")
                .setNickname("Kyascian")
                .setNumPuntoVendita(0)
                .build();
        listautenti.add(utente2);

        Utente utente3 = new Utente.Builder("Federico")
                .setEmail("federico.grasso@studenti.unisalento.it")
                .setPassword("federico")
                .setCognome("Grasso")
                .setNickname("federico00")
                .setNumPuntoVendita(0)
                .build();
        listautenti.add(utente3);

        Servizi servizio = new Servizi("Trasloco",350);
        servizio.setDescrizione("Servizio di trasloco direttaemnte a casa tua");
        servizio.setDiponivilita(100);
        servizio.setNumCategoria(1);
        servizio.setNumPuntoVendita(0);
        servizio.setNumSottocategoria(0);
        servizio.addPathIMG(Paths.get("src/img/prodotti/trasloco1.jpeg"));
        servizio.addPathIMG(Paths.get("src/img/prodotti/trasloco2.jpeg"));
        servizio.addPathIMG(Paths.get("src/img/prodotti/trasloco3.jpeg"));
        servizio.addPathIMG(Paths.get("src/img/prodotti/trasloco4.jpeg"));
        pt.addServizi(servizio);

        Servizi servizio1 = new Servizi("Consegna",25);
        servizio1.setDescrizione("Consegna pacchi di medie e piccole dimensioni");
        servizio1.setDiponivilita(50);
        servizio1.setNumCategoria(2);
        servizio1.setNumPuntoVendita(0);
        servizio1.setNumSottocategoria(0);
        servizio1.addPathIMG(Paths.get("src/img/prodotti/consegna1.jpeg"));
        pt.addServizi(servizio1);

        puntivendita.add(pt);

        PuntiVendita pv1 = new PuntiVendita("Mediaworld");
        Categoria cat5 = new Categoria("Elettronica");
        ICategoria sottocat5 = new Sottocategoria("Cellulari");
        cat5.add(sottocat5);
        sottocat5 = new Sottocategoria("Pc");
        cat5.add(sottocat5);
        sottocat5 = new Sottocategoria("Televisori");
        cat5.add(sottocat5);
        ICategoria categoria2 = (ICategoria) cat5;
        pv1.addCategoria(cat5);

        Corsia corsia1 = new Corsia("CORS1");
        Corsia corsia11 = new Corsia("CORS12");
        pv1.addCorsia(corsia1);
        pv1.addCorsia(corsia11);

        Scaffale scaffale11 = new Scaffale(1111);
        corsia1.add(scaffale11);
        Scaffale scaffale12 = new Scaffale(2222);
        corsia1.add(scaffale12);

        Prodotti prodotto5 = new Prodotti("Iphone 12",580);
        Path mypath3 = Paths.get("src/img/prodotti/iphone12.png");
        prodotto5.addPathIMG(mypath3);
        prodotto5.setNumCorsia(0);
        prodotto5.setNumSottocategoria(0);
        prodotto5.setDiponivilita(50);
        prodotto5.setNumCategoria(1);
        prodotto5.setNumPuntoVendita(1);
        prodotto5.setProduttore("Apple");
        prodotto5.setDescrizione("Iphone 12 pro max");
        prodotto5.setNumScaffale(0);
        pv1.addProdotto(prodotto5);

        Servizi servizio11 = new Servizi("Consegna",25);
        servizio11.setDescrizione("Consegna pacchi di medie e piccole dimensioni");
        servizio11.setDiponivilita(50);
        servizio11.setNumCategoria(1);
        servizio11.setNumPuntoVendita(1);
        servizio11.setNumSottocategoria(0);
        servizio11.addPathIMG(Paths.get("src/img/prodotti/consegna1.jpeg"));
        LocalDateTime d1 = LocalDateTime.now();
        DateTimeFormatter dtf1 = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        String data1 = dtf.format(d1);
        Recensioni recensione1 = new Recensioni("Emanuele","Mele","Questa è una recensione di test",data1,"emanuele.mele@studenti.unisalento.it");
        servizio11.addRecensioni(recensione1);
        pv1.addServizi(servizio11);

        puntivendita.add(pv1);

        System.out.println("DATABASE DI DEFAULT SETTATO CORRETAMENTE!");
    }

    @Override
    public Iterator getIterator() {
        return new PuntiVenditaIterator();
    }

    private class PuntiVenditaIterator implements Iterator {

        int index;

        @Override
        public boolean hasNext() {
            if (index < puntivendita.size()){
                return true;
            }
            return false;
        }

        @Override
        public Object next() {
            if (this.hasNext()){
                return puntivendita.get(index++);
            }
            return null;
        }
    }
}
