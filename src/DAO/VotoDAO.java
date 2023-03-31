package DAO;

import Model.FakeDatabase;
import Model.Merce.*;
import Model.Recensioni;

import java.nio.file.Path;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class VotoDAO {

    public int SalvaVoto(FakeDatabase FD, Connection connection) throws SQLException {

        Statement statement = connection.createStatement();
        List<PuntiVendita> puntiVendita = FD.getPuntivendita();

        for (int i=0; i<puntiVendita.size(); i++){

            PuntiVendita puntoVendita = puntiVendita.get(i);
            List<IProdotti> prodotti = puntoVendita.getProdotti();

            for (int l=0; l<prodotti.size(); l++){

                if (prodotti.get(l).getPC()){/*

                    ProdottoComposito prodottoComposito = (ProdottoComposito) prodotti.get(l);
                    List<IProdotti> sottoprodotti = prodottoComposito.getSottoprodotti();

                    for (int j=0; j<sottoprodotti.size();j++){

                        Prodotti sottoprodotto = (Prodotti) sottoprodotti.get(j);

                        List<String> emailVoto = sottoprodotto.getEmailVoto();
                        List<Double> voto = sottoprodotto.getVoto();

                        ResultSet rs = statement.executeQuery("SELECT * FROM Articoli WHERE (Nome = '" + sottoprodotto.getnome() + "') AND (Prezzo = " + sottoprodotto.getPrezzo() + ") AND (NomePV = '" + puntoVendita.getNome() + "') AND (Produttore = '" + sottoprodotto.getProduttore() + "')" + ";");
                        int codArt = 0;
                        while (rs.next()){
                            codArt = Integer.parseInt(rs.getString("CodArt"));
                        }


                            for (int k=0; k<voto.size(); k++){

                                statement.executeUpdate("INSERT INTO Voto (Email,Valore,CodArt)" + "VALUES " +
                                        "("
                                        + "'" + emailVoto.get(k) + "'" + ","
                                        + "'" + voto.get(k) + "'" + ","
                                        + codArt
                                        + ");");

                            }




                    }

               */ }else{
                    Prodotti prodotto = (Prodotti) prodotti.get(l);

                    List<String> emailVoto = prodotto.getEmailVoto();
                    List<Double> voto = prodotto.getVoto();

                    ResultSet rs = statement.executeQuery("SELECT * FROM Articoli WHERE (Nome = '" + prodotto.getnome() + "') AND (Prezzo = " + prodotto.getPrezzo() + ") AND (NomePV = '" + puntoVendita.getNome() + "') AND (Produttore = '" + prodotto.getProduttore() + "')" + ";");
                    int codArt = 0;
                    while (rs.next()){
                        codArt = Integer.parseInt(rs.getString("CodArt"));
                    }

                        for (int j=0; j<voto.size(); j++){

                            statement.executeUpdate("INSERT INTO Voto (Email,Valore,CodArt)" + "VALUES " +
                                    "("
                                    + "'" + emailVoto.get(j) + "'" + ","
                                    + "'" + voto.get(j) + "'" + ","
                                    + codArt
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
                List<String> emailVoto = servizio.getEmailVoto();
                List<Double> voto = servizio.getVoto();

                ResultSet rs = statement.executeQuery("SELECT * FROM Articoli WHERE (Nome = '" + servizio.getNome() + "') AND (Prezzo = " + servizio.getPrezzo() + ") AND (NomePV = '" + puntoVendita.getNome() + "') AND (Produttore = '" + servizio.getProduttore() + "')" + ";");
                int codArt = 0;
                while (rs.next()){
                    codArt = Integer.parseInt(rs.getString("CodArt"));
                }


                    for (int k=0; k<voto.size(); k++){

                        statement.executeUpdate("INSERT INTO Voto (Email,Valore,CodArt)" + "VALUES " +
                                "("
                                + "'" + emailVoto.get(k) + "'" + ","
                                + "'" + voto.get(k) + "'" + ","
                                + codArt
                                + ");");

                    }




            }

        }

        return 1;
    }

    public int UpdateVoto(FakeDatabase FD, Connection connection) throws SQLException {

        Statement statement = connection.createStatement();
        statement.executeUpdate("DELETE FROM Voto");

        SalvaVoto(FD,connection);
        System.out.println("Voti Aggiornate");
        return 1;
    }

    public int LoadVoto(FakeDatabase FD, Connection connection) throws SQLException{

        Statement statement = connection.createStatement();
        List<PuntiVendita> puntiVendita = FD.getPuntivendita();

        for (int i=0; i<puntiVendita.size(); i++){

            PuntiVendita puntoVendita = puntiVendita.get(i);
            List<IProdotti> prodotti = puntiVendita.get(i).getProdotti();

            for (int j=0; j< prodotti.size(); j++){

                if (!prodotti.get(j).getPC()){
                    Prodotti prodotto = (Prodotti) prodotti.get(j);
                    ResultSet rs = statement.executeQuery("SELECT * FROM Articoli WHERE (Nome = '" +  prodotto.getnome() + "') AND (Prezzo = " + prodotto.getPrezzo() + ") AND (NomePV = '" + puntoVendita.getNome() + "') AND (Produttore = '" + prodotto.getProduttore() + "')" + ";");
                    int codArt = 0;
                    while (rs.next()){
                        codArt = Integer.parseInt(rs.getString("CodArt"));
                    }
                    rs = statement.executeQuery("SELECT * FROM Voto WHERE (CodArt = " + codArt + ");");
                    ArrayList<String> EmailV = new ArrayList<>();
                    ArrayList<Double> Vot = new ArrayList<>();
                    while (rs.next()){
                        String EmailVoto = new String();
                        EmailVoto = rs.getString("Email");
                        double Voto = 0;
                        Voto = Double.parseDouble(rs.getString("Valore"));
                        EmailV.add(EmailVoto);
                        Vot.add(Voto);
                    }
                    prodotto.setEmailVoto(EmailV);
                    prodotto.setVoto(Vot);
                    prodotti.set(j,prodotto);
                }
            }
            puntoVendita.setProdotti(prodotti);

            List<Servizi> servizi = puntoVendita.getServizi();

            for (int j=0; j< servizi.size(); j++){

                    Servizi servizio = servizi.get(j);
                    ResultSet rs = statement.executeQuery("SELECT * FROM Articoli WHERE (Nome = '" +  servizio.getNome() + "') AND (Prezzo = " + servizio.getPrezzo() + ") AND (NomePV = '" + puntoVendita.getNome() + "') AND (Produttore = '" + servizio.getProduttore() + "')" + ";");
                    int codArt = 0;
                    while (rs.next()){
                        codArt = Integer.parseInt(rs.getString("CodArt"));
                    }
                    rs = statement.executeQuery("SELECT * FROM Voto WHERE (CodArt = " + codArt + ");");
                    ArrayList<String> EmailV = new ArrayList<>();
                    ArrayList<Double> Vot = new ArrayList<>();
                    while (rs.next()){
                        String EmailVoto = new String();
                        EmailVoto = rs.getString("Email");
                        double Voto = 0;
                        Voto = Double.parseDouble(rs.getString("Valore"));
                        EmailV.add(EmailVoto);
                        Vot.add(Voto);
                    }
                    servizio.setEmailVoto(EmailV);
                    servizio.setVoto(Vot);
                    servizi.set(j,servizio);

            }
            puntoVendita.setServizi(servizi);
            puntiVendita.set(i,puntoVendita);


        }
        FD.setPuntiVendita(puntiVendita);


        return 1;
    }

}
