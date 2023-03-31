package DAO;

import Model.FakeDatabase;
import Model.Merce.*;
import Model.Utenti.Utente;
import sun.lwawt.macosx.CSystemTray;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CarrelloDAO {

    public int SalvaCarrello(FakeDatabase FD, Connection connection) throws SQLException {

        Statement statement = connection.createStatement();
        List<Utente> utenti = FD.getListaUtenti();

        for (int i=0; i<utenti.size(); i++){

            Utente utente = utenti.get(i);
            List<IProdotti> carrelloProdotti = utente.getCarrelloProdotti();
            List<IProdotti> prodottiAcquistati = utente.getProdottiAcquistati();
            List<Servizi> carrelloServizi = utente.getCarrelloServizi();
            List<Servizi> serviziAcquistati = utente.getServiziAcquistati();

            for (int j=0; j<carrelloProdotti.size() ;j++){

                if (carrelloProdotti.get(j).getPC()){

                    ProdottoComposito prodottoComposito = (ProdottoComposito) carrelloProdotti.get(j);
                    List<IProdotti> sottoprodotti = prodottoComposito.getSottoprodotti();

                    for (int k=0; k<sottoprodotti.size(); k++){

                        Prodotti sottoprodotto = (Prodotti) sottoprodotti.get(k);
                        int codArt=-1;
                        ResultSet rs = statement.executeQuery("SELECT * FROM Articoli " +
                                "WHERE (Nome = '" + sottoprodotto.getNome() + "') " +
                                "AND (Prezzo = " + sottoprodotto.getPrezzo() + ") " +
                                "AND (Produttore = '" + sottoprodotto.getProduttore() + "')" +
                                "AND (Descrizione = '" + sottoprodotto.getDescrizione() + "')" +
                                ";");
                        while (rs.next()){
                            codArt = Integer.parseInt(rs.getString("CodArt"));
                        }
                        statement.executeUpdate("INSERT INTO Carrello (Email,IsAcq,CodArt) VALUES " +
                                "('" + utente.getEmail() + "',"
                                + 0 + ","
                                + codArt + ");");

                    }

                }
                else{

                    Prodotti prodotto = (Prodotti) carrelloProdotti.get(j);

                    System.out.println(prodotto.getNome() + " " + prodotto.getPrezzo());

                    int codArt=-1;
                    ResultSet rs = statement.executeQuery("SELECT * FROM Articoli " +
                            "WHERE (Nome = '" + prodotto.getNome() + "') " +
                            "AND (Prezzo = " + prodotto.getPrezzo() + ") " +
                            "AND (Produttore = '" + prodotto.getProduttore() + "')" +
                            "AND (Descrizione = '" + prodotto.getDescrizione() + "')" +
                            ";");

                    while (rs.next()){
                        codArt = Integer.parseInt(rs.getString("CodArt"));
                    }

                      System.out.println(codArt);
                      statement.executeUpdate("INSERT INTO Carrello (Email,IsAcq,CodArt) VALUES " +
                            "('" + utente.getEmail() + "',"
                             + 0 + ","
                             + codArt + ");");


                }

            }

            for (int j=0; j<prodottiAcquistati.size(); j++){

                if (prodottiAcquistati.get(j).getPC()){

                    ProdottoComposito prodottoComposito = (ProdottoComposito) prodottiAcquistati.get(j);
                    List<IProdotti> sottoprodotti = prodottoComposito.getSottoprodotti();

                    for (int k=0; k<sottoprodotti.size(); k++){

                        Prodotti sottoprodotto = (Prodotti) sottoprodotti.get(k);
                        int codArt=-1;
                        ResultSet rs = statement.executeQuery("SELECT * FROM Articoli " +
                                "WHERE (Nome = '" + sottoprodotto.getNome() + "') " +
                                "AND (Prezzo = " + sottoprodotto.getPrezzo() + ") " +
                                "AND (Produttore = '" + sottoprodotto.getProduttore() + "')" +
                                "AND (Descrizione = '" + sottoprodotto.getDescrizione() + "')" +
                                ";");
                        while (rs.next()){
                            codArt = Integer.parseInt(rs.getString("CodArt"));
                        }
                        statement.executeUpdate("INSERT INTO Carrello (Email,IsAcq,CodArt) VALUES " +
                                "('" + utente.getEmail() + "',"
                                + 1 + ","
                                + codArt + ");");

                    }
                }
                else{

                    Prodotti prodotto = (Prodotti) prodottiAcquistati.get(j);

                    int codArt=-1;
                    ResultSet rs = statement.executeQuery("SELECT * FROM Articoli " +
                            "WHERE (Nome = '" + prodotto.getNome() + "') " +
                            "AND (Prezzo = " + prodotto.getPrezzo() + ") " +
                            "AND (Produttore = '" + prodotto.getProduttore() + "')" +
                            "AND (Descrizione = '" + prodotto.getDescrizione() + "')" +
                            ";");

                    while (rs.next()){
                        codArt = Integer.parseInt(rs.getString("CodArt"));
                    }

                     statement.executeUpdate("INSERT INTO Carrello (Email,IsAcq,CodArt) VALUES " +
                            "('" + utente.getEmail() + "',"
                            + 1 + ","
                            + codArt + ");");

                }

            }

            for (int j=0; j<carrelloServizi.size(); j++){

                Servizi servizio = carrelloServizi.get(j);
                int codArt=-1;
                ResultSet rs = statement.executeQuery("SELECT * FROM Articoli " +
                        "WHERE (Nome = '" + servizio.getNome() + "') " +
                        "AND (Prezzo = " + servizio.getPrezzo() + ") " +
                        "AND (Produttore = '" + servizio.getProduttore() + "')" +
                        "AND (Descrizione = '" + servizio.getDescrizione() + "')" +
                        ";");
                while (rs.next()){
                    codArt = Integer.parseInt(rs.getString("CodArt"));
                }

                statement.executeUpdate("INSERT INTO Carrello (Email,IsAcq,CodArt) VALUES " +
                        "('" + utente.getEmail() + "',"
                        + 0 + ","
                        + codArt + ");");

            }

            for (int j=0; j<serviziAcquistati.size(); j++){

                Servizi servizio = serviziAcquistati.get(j);
                int codArt=-1;
                ResultSet rs = statement.executeQuery("SELECT * FROM Articoli " +
                        "WHERE (Nome = '" + servizio.getNome() + "') " +
                        "AND (Prezzo = " + servizio.getPrezzo() + ") " +
                        "AND (Produttore = '" + servizio.getProduttore() + "')" +
                        "AND (Descrizione = '" + servizio.getDescrizione() + "')" +
                        ";");
                while (rs.next()){
                    codArt = Integer.parseInt(rs.getString("CodArt"));
                }

                statement.executeUpdate("INSERT INTO Carrello (Email,IsAcq,CodArt) VALUES " +
                        "('" + utente.getEmail() + "',"
                        + 1 + ","
                        + codArt + ");");


            }

        }

        return 1;
    }

    public int UpdateCarrello(FakeDatabase FD, Connection connection) throws SQLException {

        Statement statement = connection.createStatement();
        statement.executeUpdate("DELETE FROM Carrello");

        SalvaCarrello(FD,connection);
        System.out.println("Carrelli Aggiornati");

        return 1;
    }

    public int LoadCarrello(FakeDatabase FD, Connection connection) throws SQLException {

        Statement statement = connection.createStatement();
        Statement statement1 = connection.createStatement();

        List<Utente> utenti = FD.getListaUtenti();

        for (int i=0; i<utenti.size(); i++){

            Utente utente = utenti.get(i);

            ArrayList<Integer> codArt = new ArrayList<>();
            ResultSet rs = statement.executeQuery("SELECT * FROM Carrello " +
                    "WHERE (Email = '" + utente.getEmail() + "') " +
                    "AND (isAcq = 1)" +
                    ";");
            while (rs.next()){
                codArt.add(Integer.parseInt(rs.getString("CodArt")));
            }

            for (int j=0; j<codArt.size();j++){

                rs = statement.executeQuery("SELECT * FROM Articoli WHERE (" +
                        "CodArt = " + codArt.get(j)+ ");");

                while (rs.next()){
                    if (Integer.parseInt(rs.getString("isServ")) == 1){
                        Servizi servizio = new Servizi(rs.getString("Nome"),Double.parseDouble(rs.getString("Prezzo")));
                        servizio.setDiponivilita(1);
                        servizio.setProduttore(rs.getString("Produttore"));
                        servizio.setDescrizione(rs.getString("Descrizione"));


                        utente.addServiziAcquistati(servizio);
                    }else{
                        Prodotti prodotto = new Prodotti(rs.getString("Nome"),Double.parseDouble(rs.getString("Prezzo")));
                        prodotto.setDiponivilita(1);
                        prodotto.setProduttore(rs.getString("Produttore"));
                        prodotto.setDescrizione(rs.getString("Descrizione"));

                        utente.addProdottiAcquistati(prodotto);
                    }

                }
            }

           utenti.set(i,utente);

        }

        for (int i=0; i<utenti.size(); i++){

            Utente utente = utenti.get(i);

            ArrayList<Integer> codArt = new ArrayList<>();
            ResultSet rs = statement.executeQuery("SELECT * FROM Carrello " +
                    "WHERE (Email = '" + utente.getEmail() + "') " +
                    "AND (isAcq = 0)" +
                    ";");
            while (rs.next()){
                codArt.add(Integer.parseInt(rs.getString("CodArt")));
            }

            for (int j=0; j<codArt.size();j++){

                rs = statement.executeQuery("SELECT * FROM Articoli WHERE (" +
                        "CodArt = " + codArt.get(j)+ ");");

                while (rs.next()){
                    if (Integer.parseInt(rs.getString("isServ")) == 1){
                        Servizi servizio = new Servizi(rs.getString("Nome"),Double.parseDouble(rs.getString("Prezzo")));
                        servizio.setDiponivilita(1);
                        servizio.setProduttore(rs.getString("Produttore"));
                        servizio.setDescrizione(rs.getString("Descrizione"));
                        servizio.setNumCategoria(Integer.parseInt(rs.getString("NumCat")));
                        servizio.setNumSottocategoria(Integer.parseInt(rs.getString("NumSottoCat")));
                        servizio.setNumCorsia(Integer.parseInt(rs.getString("NumCorsia")));
                        servizio.setNumScaffale(Integer.parseInt(rs.getString("NumScaffale")));
                        servizio.setNumPuntoVendita(Integer.parseInt(rs.getString("NumPuntoV")));

                        int codProdotto = Integer.parseInt(rs.getString("CodArt"));
                        ResultSet r1 = statement1.executeQuery("SELECT * FROM PathIMG WHERE (CodArt = " + codProdotto + ")");
                        ArrayList<Path> paths = new ArrayList<>();
                        while(r1.next()){
                            Path path = Paths.get(r1.getString("Path"));
                            paths.add(path);
                        }
                        servizio.setPathIMG(paths);

                        utente.addServiziolCarrello(servizio);
                    }else{
                        Prodotti prodotto = new Prodotti(rs.getString("Nome"),Double.parseDouble(rs.getString("Prezzo")));
                        prodotto.setDiponivilita(1);
                        prodotto.setProduttore(rs.getString("Produttore"));
                        prodotto.setDescrizione(rs.getString("Descrizione"));
                        prodotto.setNumCategoria(Integer.parseInt(rs.getString("NumCat")));
                        prodotto.setNumSottocategoria(Integer.parseInt(rs.getString("NumSottoCat")));
                        prodotto.setNumCorsia(Integer.parseInt(rs.getString("NumCorsia")));
                        prodotto.setNumScaffale(Integer.parseInt(rs.getString("NumScaffale")));
                        prodotto.setNumPuntoVendita(Integer.parseInt(rs.getString("NumPuntoV")));

                        int codProdotto = Integer.parseInt(rs.getString("CodArt"));
                        ResultSet r1 = statement1.executeQuery("SELECT * FROM PathIMG WHERE (CodArt = " + codProdotto + ")");
                        ArrayList<Path> paths = new ArrayList<>();
                        while(r1.next()){
                            Path path = Paths.get(r1.getString("Path"));
                            paths.add(path);
                        }
                        prodotto.setPathIMG(paths);

                        utente.addProdottoalCarrello(prodotto);
                    }
                }
            }

            utenti.set(i,utente);

        }


        FD.setListaUtenti(utenti);

        return 1;

    }

    public int removeElementoUtente(Connection connection, FakeDatabase FD, String email) throws SQLException {
        Statement statement = connection.createStatement();
        statement.executeUpdate("DELETE FROM Carrello WHERE (Email = '" +  email + " ');");
        return 1;
    }

}
