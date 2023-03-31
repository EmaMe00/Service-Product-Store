package DAO;

import Model.FakeDatabase;
import Model.Merce.*;
import Model.Recensioni;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class RecensioniDAO {

    public int SalvaRecensioni(FakeDatabase FD, Connection connection) throws SQLException {

        Statement statement = connection.createStatement();
        List<PuntiVendita> puntiVendita = FD.getPuntivendita();

        for (int i=0; i<puntiVendita.size();i++){

            PuntiVendita puntoVendita = puntiVendita.get(i);
            List<IProdotti> prodotti = puntoVendita.getProdotti();

            for (int j=0; j<prodotti.size(); j++){

                if (prodotti.get(j).getPC()){/*

                    ProdottoComposito prodottoComposito = (ProdottoComposito) prodotti.get(j);
                    List<IProdotti> sottoprodotti = prodottoComposito.getSottoprodotti();

                    for (int k=0; k<sottoprodotti.size(); k++){
                        Prodotti sottoprodotto = (Prodotti) sottoprodotti.get(k);
                        List<Recensioni> recensioni = sottoprodotto.getRecensioni();

                        ResultSet rs = statement.executeQuery("SELECT * FROM Articoli WHERE (Nome = '" +  sottoprodotto.getnome() + "') AND (Prezzo = " + sottoprodotto.getPrezzo() + ") AND (NomePV = '" + puntoVendita.getNome() + "') AND (Produttore = '" + sottoprodotto.getProduttore() + "')" + ";");
                        int codArt = 0;
                        while (rs.next()){
                            codArt = Integer.parseInt(rs.getString("CodArt"));
                        }

                        for (int l=0; l<recensioni.size(); l++){

                            Recensioni recensione = recensioni.get(l);
                            statement.executeUpdate("INSERT INTO Recensioni (Nome,Cognome,Testo,Data,Email,CodArt,Indice)" + "VALUES " +
                                    "("
                                    + "'" + recensione.getNome() + "'" + ","
                                    + "'" + recensione.getCognome() + "'" + ","
                                    + "'" + recensione.getTestoRecensione() + "'" + ","
                                    + "'" + recensione.getData() + "'" + ","
                                    + "'" + recensione.getEmail() + "'" + ","
                                    + codArt + ","
                                    + l
                                    + ");");

                        }

                    }
                    */

                }else{

                    Prodotti prodotto = (Prodotti) prodotti.get(j);
                    List<Recensioni> recensioni = prodotto.getRecensioni();

                    ResultSet rs = statement.executeQuery("SELECT * FROM Articoli WHERE (Nome = '" +  prodotto.getnome() + "') AND (Prezzo = " + prodotto.getPrezzo() + ") AND (NomePV = '" + puntoVendita.getNome() + "') AND (Produttore = '" + prodotto.getProduttore() + "')" + ";");
                    int codArt = 0;
                    while (rs.next()){
                        codArt = Integer.parseInt(rs.getString("CodArt"));
                    }

                    for (int k=0; k<recensioni.size(); k++){

                        Recensioni recensione = recensioni.get(k);

                        statement.executeUpdate("INSERT INTO Recensioni (Nome,Cognome,Testo,Data,Email,CodArt,Indice) " + "VALUES " +
                                "("
                                + "'" + recensione.getNome() + "'" + ","
                                + "'" + recensione.getCognome() + "'" + ","
                                + "'" + recensione.getTestoRecensione() + "'" + ","
                                + "'" + recensione.getData() + "'" + ","
                                + "'" + recensione.getEmail() + "'" + ","
                                + codArt + ","
                                + k
                                + ");");


                    }

                }

            }

        }

        for (int i=0; i<puntiVendita.size(); i++){

            PuntiVendita puntoVendita = puntiVendita.get(i);
            List<Servizi> servizi = puntoVendita.getServizi();

            for (int j=0; j<servizi.size(); j++){

                Servizi servizio = servizi.get(j);
                List<Recensioni> recensioni = servizio.getRecensioni();

                ResultSet rs = statement.executeQuery("SELECT * FROM Articoli WHERE (Nome = '" +  servizio.getNome() + "') AND (Prezzo = " + servizio.getPrezzo() + ") AND (NomePV = '" + puntoVendita.getNome() + "') AND (Produttore = '" + servizio.getProduttore() + "')" + ";");
                int codArt = 0;
                while (rs.next()){
                    codArt = Integer.parseInt(rs.getString("CodArt"));
                }

                for (int k=0; k<recensioni.size(); k++){

                    Recensioni recensione = recensioni.get(k);

                    statement.executeUpdate("INSERT INTO Recensioni (Nome,Cognome,Testo,Data,Email,CodArt,Indice)" + "VALUES " +
                            "("
                            + "'" + recensione.getNome() + "'" + ","
                            + "'" + recensione.getCognome() + "'" + ","
                            + "'" + recensione.getTestoRecensione() + "'" + ","
                            + "'" + recensione.getData() + "'" + ","
                            + "'" + recensione.getEmail() + "'" + ","
                            + codArt + ","
                            + k
                            + ");");

                }

            }

        }

        return 1;

    }

    public int UpdateRecensione(FakeDatabase FD, Connection connection) throws SQLException {

        Statement statement = connection.createStatement();
        statement.executeUpdate("DELETE FROM Recensioni");

        SalvaRecensioni(FD,connection);
        System.out.println("Recensioni Aggiornate");
        return 1;

    }

    public int LoadRecensioni(FakeDatabase FD, Connection connection) throws SQLException {

        Statement statement = connection.createStatement();
        List<PuntiVendita> puntiVendita = FD.getPuntivendita();

        for (int i=0; i<puntiVendita.size(); i++){

            PuntiVendita puntoVendita = puntiVendita.get(i);
            List<IProdotti> prodotti = puntoVendita.getProdotti();

            for (int j=0; j<prodotti.size(); j++){

                if (prodotti.get(j).getPC()){/*

                    ProdottoComposito prodottoComposito = (ProdottoComposito) prodotti.get(j);
                    List<IProdotti> sottoprodotti = prodottoComposito.getSottoprodotti();

                    for (int k=0; k<sottoprodotti.size(); k++){

                        Prodotti sottoprodotto = (Prodotti) sottoprodotti.get(k);
                        ResultSet rs = statement.executeQuery("SELECT * FROM Articoli WHERE (Nome = '" +  sottoprodotto.getnome() + "') AND (Prezzo = " + sottoprodotto.getPrezzo() + ") AND (NomePV = '" + puntoVendita.getNome() + "') AND (Produttore = '" + sottoprodotto.getProduttore() + "')" + ";");
                        int codArt = 0;
                        while (rs.next()){
                            codArt = Integer.parseInt(rs.getString("CodArt"));
                        }


                            rs = statement.executeQuery("SELECT * FROM Recensioni WHERE (CodArt = " + codArt + ") ORDER BY Indice;");
                            while (rs.next()){
                                Recensioni recensione = new Recensioni();
                                recensione.setNome(rs.getString("Nome"));
                                recensione.setCognome(rs.getString("Cognome"));
                                recensione.setTestoRecensione(rs.getString("Testo"));
                                recensione.setData(rs.getString("Data"));
                                recensione.setEmail(rs.getString("Email"));
                                sottoprodotto.addRecensioni(recensione);
                            }

                            sottoprodotti.set(k,sottoprodotto);
                            prodottoComposito.setSottoprodotti(sottoprodotti);
                            prodotti.set(j,prodottoComposito);


                    }

                */}else{

                    Prodotti prodotto = (Prodotti) prodotti.get(j);
                    ResultSet rs = statement.executeQuery("SELECT * FROM Articoli WHERE (Nome = '" +  prodotto.getnome() + "') AND (Prezzo = " + prodotto.getPrezzo() + ") AND (NomePV = '" + puntoVendita.getNome() + "') AND (Produttore = '" + prodotto.getProduttore() + "')" + ";");
                    int codArt = 0;
                    while (rs.next()){
                        codArt = Integer.parseInt(rs.getString("CodArt"));
                    }

                        rs = statement.executeQuery("SELECT * FROM Recensioni WHERE (CodArt = " + codArt + ") ORDER BY Indice;");
                        while (rs.next()){
                            Recensioni recensione = new Recensioni();
                            recensione.setNome(rs.getString("Nome"));
                            recensione.setCognome(rs.getString("Cognome"));
                            recensione.setTestoRecensione(rs.getString("Testo"));
                            recensione.setData(rs.getString("Data"));
                            System.out.println(rs.getString("Data"));
                            recensione.setEmail(rs.getString("Email"));
                            System.out.println(rs.getString("Email"));
                            prodotto.addRecensioni(recensione);
                        }

                        prodotti.set(j,prodotto);



                }

            }

            puntoVendita.setProdotti(prodotti);
            puntiVendita.set(i,puntoVendita);
            FD.setPuntiVendita(puntiVendita);

        }

        for (int i=0; i<puntiVendita.size(); i++){

            PuntiVendita puntoVendita = puntiVendita.get(i);
            List<Servizi> servizi = puntoVendita.getServizi();

            for (int j=0; j<servizi.size(); j++){

                Servizi servizio = servizi.get(j);
                ResultSet rs = statement.executeQuery("SELECT * FROM Articoli WHERE (Nome = '" +  servizio.getNome() + "') AND (Prezzo = " + servizio.getPrezzo() + ") AND (NomePV = '" + puntoVendita.getNome() + "') AND (Produttore = '" +servizio.getProduttore() + "')" + ";");
                int codArt = 0;
                while (rs.next()){
                    codArt = Integer.parseInt(rs.getString("CodArt"));
                }


                    rs = statement.executeQuery("SELECT * FROM Recensioni WHERE (CodArt = " + codArt + ") ORDER BY Indice;");
                    while (rs.next()){
                        Recensioni recensione = new Recensioni();
                        recensione.setNome(rs.getString("Nome"));
                        recensione.setCognome(rs.getString("Cognome"));
                        recensione.setTestoRecensione(rs.getString("Testo"));
                        recensione.setData(rs.getString("Data"));
                        recensione.setEmail(rs.getString("Email"));
                        servizio.addRecensioni(recensione);
                    }

                    servizi.set(j,servizio);



            }

            puntoVendita.setServizi(servizi);
            puntiVendita.set(i,puntoVendita);
            FD.setPuntiVendita(puntiVendita);

        }
        return 1;
    }

}
