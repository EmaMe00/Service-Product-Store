package DAO;

import Model.FakeDatabase;
import Model.Merce.*;

import java.nio.file.Path;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PathIMGDAO {

    public int SalvaPathIMG(FakeDatabase FD, Connection connection) throws SQLException {

        Statement statement = connection.createStatement();
        List<PuntiVendita> puntiVendita = FD.getPuntivendita();

        for (int i=0; i<puntiVendita.size(); i++){

            PuntiVendita puntoVendita = puntiVendita.get(i);
            List<IProdotti> prodotti = puntoVendita.getProdotti();

            for (int j=0; j<prodotti.size(); j++){

                if (prodotti.get(j).getPC()){

                    ProdottoComposito prodottoComposito = (ProdottoComposito) prodotti.get(j);
                    List<IProdotti> sottoprodotti = prodottoComposito.getSottoprodotti();

                    for (int k=0; k<sottoprodotti.size(); k++){
                        Prodotti sottoprodotto = (Prodotti) sottoprodotti.get(k);
                        List<Path> pathsIMG = sottoprodotto.getPathIMG();

                        ResultSet rs = statement.executeQuery("SELECT * FROM Articoli WHERE (Nome = '" + sottoprodotto.getnome() + "') AND (Prezzo = " + sottoprodotto.getPrezzo() + ") AND (NomePV = '" + puntoVendita.getNome() + "') AND (Produttore = '" + sottoprodotto.getProduttore() + "')" + ";");
                        int codArt = 0;
                        while (rs.next()){
                            codArt = Integer.parseInt(rs.getString("CodArt"));
                        }

                        for (int l=0; l<pathsIMG.size(); l++){
                            Path pathIMG = pathsIMG.get(l);
                            statement.executeUpdate("INSERT INTO PathIMG (Path,CodArt)" + "VALUES " +
                                    "("
                                    + "'" + pathIMG.toString() + "'" + ","
                                    + codArt
                                    + ");");
                        }
                    }

                }else{
                    Prodotti prodotto = (Prodotti) prodotti.get(j);
                    List<Path> pathsIMG = prodotto.getPathIMG();

                    ResultSet rs = statement.executeQuery("SELECT * FROM Articoli WHERE (Nome = '" + prodotto.getnome() + "') AND (Prezzo = " + prodotto.getPrezzo() + ") AND (NomePV = '" + puntoVendita.getNome() + "') AND (Produttore = '" + prodotto.getProduttore() + "')" + ";");
                    ArrayList<Integer> codArt = new ArrayList<>();
                    while (rs.next()){
                        codArt.add(Integer.parseInt(rs.getString("CodArt")));
                    }

                    for (int l=0; l<codArt.size();l++){

                        for (int k=0; k<pathsIMG.size(); k++){

                            Path pathIMG = pathsIMG.get(k);

                            statement.executeUpdate("INSERT INTO PathIMG (Path,CodArt)" + "VALUES " +
                                    "("
                                    + "'" + pathIMG.toString() + "'" + ","
                                    + codArt.get(l)
                                    + ");");

                        }

                    }



                }

            }

        }

        for (int i=0; i<puntiVendita.size(); i++){

            PuntiVendita puntoVendita = puntiVendita.get(i);
            List<Servizi> servizi = puntoVendita.getServizi();

            for (int j=0; j<servizi.size(); j++){

                Servizi servizio = servizi.get(j);
                List<Path> pathsIMG = servizio.getPathIMG();

                ResultSet rs = statement.executeQuery("SELECT * FROM Articoli WHERE (Nome = '" + servizio.getNome() + "') AND (Prezzo = " + servizio.getPrezzo() + ") AND (NomePV = '" + puntoVendita.getNome() + "') AND (Produttore = '" +  servizio.getProduttore() + "')" + ";");
                int codArt = 0;
                while (rs.next()){
                    codArt = Integer.parseInt(rs.getString("CodArt"));
                }

                for (int k=0;k<pathsIMG.size();k++){

                    Path pathIMG = pathsIMG.get(k);

                    statement.executeUpdate("INSERT INTO PathIMG (Path,CodArt)" + "VALUES " +
                            "("
                            + "'" + pathIMG.toString() + "'" + ","
                            + codArt
                            + ");");

                }

            }

        }

        return 1;

    }

    public int UpdatePathIMG(FakeDatabase FD, Connection connection) throws SQLException {

        Statement statement = connection.createStatement();
        statement.executeUpdate("DELETE FROM PathIMG");

        SalvaPathIMG(FD,connection);
        System.out.println("Path Aggiornate");

        return 1;

    }



}

