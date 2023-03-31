package DAO;

import Model.FakeDatabase;
import Model.Merce.*;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ArticoliDAO {

    public int SalvaArticoli(FakeDatabase FD, Connection connection) throws SQLException {

        Statement statement = connection.createStatement();
        List<PuntiVendita> puntiVendita = FD.getPuntivendita();

        for (int i=0; i<puntiVendita.size(); i++){

            PuntiVendita puntoVendita = puntiVendita.get(i);
            List<IProdotti> prodotti = puntoVendita.getProdotti();

            for (int j=0; j<prodotti.size(); j++){

                if (prodotti.get(j).getPC()){

                    ProdottoComposito prodottoComposito = (ProdottoComposito) prodotti.get(j);

                    statement.executeUpdate("INSERT INTO Articoli (Nome,Prezzo,Descrizione,Produttore,NumCat,NumSottoCat,NumCorsia,NumScaffale,NumPuntoV,Disponibilita,isServ,isPC,NomePV,CodComposito) " + "VALUES " +
                            "("
                            + "'" + prodottoComposito.getNome() + "'" + ","
                            + "'" + prodottoComposito.getPrezzo() + "'" + ","
                            + "'" + prodottoComposito.getDescrizione() + "'" + ","
                            + "'" + prodottoComposito.getProduttore() + "'" + ","
                            + "'" + prodottoComposito.getNumCategoria() + "'" + ","
                            + "'" + prodottoComposito.getNumSottocategoria() + "'" + ","
                            + "'" + prodottoComposito.getNumCorsia() + "'" + ","
                            + "'" + prodottoComposito.getNumScaffale() + "'" + ","
                            + "'" + prodottoComposito.getNumPuntoVendita() + "'" + ","
                            + "'" + prodottoComposito.getDiponivilita() + "'" + ","
                            + 0 + ","
                            + 1 + ","
                            + "'" + puntoVendita.getNome() + "'" + ","
                            + -1
                            + ");");


                    List<IProdotti> sottoprodotti = prodottoComposito.getSottoprodotti();

                    ResultSet rs = statement.executeQuery("SELECT * FROM Articoli WHERE (Nome = '" + prodottoComposito.getnome() + "') AND (isPC = 1) AND (NomePV = '" + puntoVendita.getNome() + "')" + ";");
                    int codPC = 0;
                    while (rs.next()){
                        codPC = Integer.parseInt(rs.getString("CodArt"));
                    }

                    for (int k=0; k<sottoprodotti.size(); k++){

                        Prodotti sottoprodotto = (Prodotti) sottoprodotti.get(k);

                        statement.executeUpdate("INSERT INTO Articoli (Nome,Prezzo,Descrizione,Produttore,NumCat,NumSottoCat,NumCorsia,NumScaffale,NumPuntoV,Disponibilita,isServ,isPC,NomePV,CodComposito) " + "VALUES " +
                                "("
                                + "'" + sottoprodotto.getNome() + "'" + ","
                                + "'" + sottoprodotto.getPrezzo() + "'" + ","
                                + "'" + sottoprodotto.getDescrizione() + "'" + ","
                                + "'" + sottoprodotto.getProduttore() + "'" + ","
                                + "'" + sottoprodotto.getNumCategoria() + "'" + ","
                                + "'" + sottoprodotto.getNumSottocategoria() + "'" + ","
                                + "'" + sottoprodotto.getNumCorsia() + "'" + ","
                                + "'" + sottoprodotto.getNumScaffale() + "'" + ","
                                + "'" + sottoprodotto.getNumPuntoVendita() + "'" + ","
                                + "'" + sottoprodotto.getDiponivilita() + "'" + ","
                                + 0 + ","
                                + 0  + ","
                                + "'" + puntoVendita.getNome() + "'" + ","
                                + codPC
                                + ");");

                    }
                }
                else{
                    Prodotti prodotto = (Prodotti) prodotti.get(j);
                    statement.executeUpdate("INSERT INTO Articoli (Nome,Prezzo,Descrizione,Produttore,NumCat,NumSottoCat,NumCorsia,NumScaffale,NumPuntoV,Disponibilita,isServ,isPC,NomePV,CodComposito) " + "VALUES " +
                            "("
                            + "'" + prodotto.getNome() + "'" + ","
                            + "'" + prodotto.getPrezzo() + "'" + ","
                            + "'" + prodotto.getDescrizione() + "'" + ","
                            + "'" + prodotto.getProduttore() + "'" + ","
                            + "'" + prodotto.getNumCategoria() + "'" + ","
                            + "'" + prodotto.getNumSottocategoria() + "'" + ","
                            + "'" + prodotto.getNumCorsia() + "'" + ","
                            + "'" + prodotto.getNumScaffale() + "'" + ","
                            + "'" + prodotto.getNumPuntoVendita() + "'" + ","
                            + "'" + prodotto.getDiponivilita() + "'" + ","
                            + 0 + ","
                            + 0 + ","
                            + "'" + puntoVendita.getNome() + "'" + ","
                            + -1
                            + ");");

                }

            }



        }

        for (int i=0; i<puntiVendita.size(); i++) {

            PuntiVendita puntoVendita = puntiVendita.get(i);
            List<Servizi> servizi = puntoVendita.getServizi();

            for (int j=0; j<servizi.size(); j++){

                Servizi servizio = servizi.get(j);
                statement.executeUpdate("INSERT INTO Articoli (Nome,Prezzo,Descrizione,Produttore,NumCat,NumSottoCat,NumCorsia,NumScaffale,NumPuntoV,Disponibilita,isServ,isPC,NomePV,CodComposito) " + "VALUES " +
                        "("
                        + "'" + servizio.getNome() + "'" + ","
                        + "'" + servizio.getPrezzo() + "'" + ","
                        + "'" + servizio.getDescrizione() + "'" + ","
                        + "'" + servizio.getProduttore() + "'" + ","
                        + "'" + servizio.getNumCategoria() + "'" + ","
                        + "'" + servizio.getNumSottocategoria() + "'" + ","
                        + "'" + servizio.getNumCorsia() + "'" + ","
                        + "'" + servizio.getNumScaffale() + "'" + ","
                        + "'" + servizio.getNumPuntoVendita() + "'" + ","
                        + "'" + servizio.getDiponivilita() + "'" + ","
                        + 1 + ","
                        + 0 + ","
                        + "'" + puntoVendita.getNome() + "'" + ","
                        + -1
                        + ");");

            }

        }

        return 1;

    }

    public int UpdateArticoli(FakeDatabase FD, Connection connection) throws SQLException {

        Statement statement = connection.createStatement();
        statement.executeUpdate("DELETE FROM Carrello");
        statement.executeUpdate("DELETE FROM Recensioni");
        statement.executeUpdate("DELETE FROM Voto");
        statement.executeUpdate("DELETE FROM PathIMG");
        statement.executeUpdate("DELETE FROM Articoli");

        SalvaArticoli(FD,connection);
        System.out.println("Articoli Aggiornati");

        PathIMGDAO PDAO = new PathIMGDAO();
        PDAO.UpdatePathIMG(FD,connection);

        VotoDAO VDAO = new VotoDAO();
        VDAO.UpdateVoto(FD,connection);

        RecensioniDAO RDAO = new RecensioniDAO();
        RDAO.UpdateRecensione(FD,connection);

        CarrelloDAO C1DAO = new CarrelloDAO();
        C1DAO.UpdateCarrello(FD,connection);

        CategoriaDAO C2DAO = new CategoriaDAO();
        C2DAO.UpdateCategoria(FD,connection);

        CorsieDAO CODAO = new CorsieDAO();
        CODAO.UpdateCorsia(FD,connection);

        return 1;

    }

    public int LoadArticoli(FakeDatabase FD, Connection connection) throws SQLException {

        Statement statement = connection.createStatement();
        Statement statement1 = connection.createStatement();
        Statement statement2 = connection.createStatement();
        List<PuntiVendita> puntiVendita = FD.getPuntivendita();

        for (int i=0; i<puntiVendita.size(); i++){

            PuntiVendita puntoVendita = puntiVendita.get(i);
            List<IProdotti> prodotti = new ArrayList<>();

            ResultSet rs = statement.executeQuery("SELECT * FROM Articoli WHERE (NomePV = '" + puntoVendita.getNome() + "') AND (isPC = 0) AND (isServ = 0) AND (CodComposito = -1)" + ";");


            while (rs.next()){

                Prodotti prodotto = new Prodotti(rs.getString("Nome"),Double.parseDouble(rs.getString("Prezzo")));
                prodotto.setDescrizione(rs.getString("Descrizione"));
                prodotto.setProduttore(rs.getString("Produttore"));
                prodotto.setNumCategoria(Integer.parseInt(rs.getString("NumCat")));
                prodotto.setNumSottocategoria(Integer.parseInt(rs.getString("NumSottoCat")));
                prodotto.setNumCorsia(Integer.parseInt(rs.getString("NumCorsia")));
                prodotto.setNumScaffale(Integer.parseInt(rs.getString("NumScaffale")));
                prodotto.setNumPuntoVendita(Integer.parseInt(rs.getString("NumPuntoV")));
                prodotto.setDiponivilita(Integer.parseInt(rs.getString("Disponibilita")));

                int codArt = Integer.parseInt(rs.getString("CodArt"));
                ResultSet rs2 = statement2.executeQuery("SELECT * FROM PathIMG WHERE (CodArt = " + codArt + ");");
                int ciSonoImg=0;
                while (rs2.next()){
                    prodotto.addPathIMG(Paths.get(rs2.getString("Path")));
                    ciSonoImg++;
                }
                if (ciSonoImg==0){
                    prodotto.addPathIMG(Paths.get("src/img/prodotti/StandardIMG.png"));
                }

                rs2 = statement2.executeQuery("SELECT * FROM Voto WHERE (CodArt = " + codArt + ");");
                while (rs2.next()){
                    prodotto.addVoto(Double.parseDouble(rs2.getString("Valore")));
                    prodotto.addEmailVoto(rs2.getString("Email"));
                }


                prodotti.add(prodotto);
            }

            rs = statement.executeQuery("SELECT * FROM Articoli WHERE (NomePV = '" + puntoVendita.getNome() + "') AND (isPC = 1) AND (isServ = 0)" + ";");
            while (rs.next()){
                ProdottoComposito prodottoComposito = new ProdottoComposito(rs.getString("Nome"));
                List<IProdotti> sottoprodotti = new ArrayList<>();

                ResultSet rs1 = statement1.executeQuery("SELECT * FROM Articoli WHERE (NomePV = '" + puntoVendita.getNome() + "') AND (CodComposito = " + Integer.parseInt(rs.getString("CodArt")) + ")" + ";");


                while (rs1.next()){
                    Prodotti sottoprodotto = new Prodotti(rs1.getString("Nome"),Double.parseDouble(rs1.getString("Prezzo")));
                    sottoprodotto.setDescrizione(rs1.getString("Descrizione"));
                    sottoprodotto.setProduttore(rs1.getString("Produttore"));
                    sottoprodotto.setNumCategoria(Integer.parseInt(rs1.getString("NumCat")));
                    sottoprodotto.setNumSottocategoria(Integer.parseInt(rs1.getString("NumSottoCat")));
                    sottoprodotto.setNumCorsia(Integer.parseInt(rs1.getString("NumCorsia")));
                    sottoprodotto.setNumScaffale(Integer.parseInt(rs1.getString("NumScaffale")));
                    sottoprodotto.setNumPuntoVendita(Integer.parseInt(rs1.getString("NumPuntoV")));
                    sottoprodotto.setDiponivilita(Integer.parseInt(rs1.getString("Disponibilita")));

                    int codArt = Integer.parseInt(rs1.getString("CodArt"));

                    //Path
                    ResultSet rs2 = statement2.executeQuery("SELECT * FROM PathIMG WHERE (CodArt = " + codArt + ");");
                    int ciSonoImg=0;
                    while (rs2.next()){
                        sottoprodotto.addPathIMG(Paths.get(rs2.getString("Path")));
                        ciSonoImg++;
                    }
                    if (ciSonoImg==0){
                        sottoprodotto.addPathIMG(Paths.get("src/img/prodotti/StandardIMG.png"));
                    }

                    //Voto
                    rs2 = statement2.executeQuery("SELECT * FROM Voto WHERE (CodArt = " + codArt + ");");
                    while (rs2.next()){
                        sottoprodotto.addVoto(Double.parseDouble(rs2.getString("Valore")));
                        sottoprodotto.addEmailVoto(rs2.getString("Email"));
                    }

                    sottoprodotti.add(sottoprodotto);
                }
                prodottoComposito.setSottoprodotti(sottoprodotti);
                prodotti.add(prodottoComposito);

            }

            puntoVendita.setProdotti(prodotti);
            puntiVendita.set(i,puntoVendita);

        }

        for (int i=0; i<puntiVendita.size(); i++){

            PuntiVendita puntoVendita = puntiVendita.get(i);
            List<Servizi> servizi = new ArrayList<>();

            ResultSet rs = statement.executeQuery("SELECT * FROM Articoli WHERE (NomePV = '" + puntoVendita.getNome() + "') AND (isPC = 0) AND (isServ = 1)" + ";");


            while (rs.next()){
                Servizi servizio = new Servizi(rs.getString("Nome"),Double.parseDouble(rs.getString("Prezzo")));
                servizio.setDescrizione(rs.getString("Descrizione"));
                servizio.setProduttore(rs.getString("Produttore"));
                servizio.setNumCategoria(Integer.parseInt(rs.getString("NumCat")));
                servizio.setNumSottocategoria(Integer.parseInt(rs.getString("NumSottoCat")));
                servizio.setNumCorsia(Integer.parseInt(rs.getString("NumCorsia")));
                servizio.setNumScaffale(Integer.parseInt(rs.getString("NumScaffale")));
                servizio.setNumPuntoVendita(Integer.parseInt(rs.getString("NumPuntoV")));
                servizio.setDiponivilita(Integer.parseInt(rs.getString("Disponibilita")));

                int codArt = Integer.parseInt(rs.getString("CodArt"));
                ResultSet rs2 = statement2.executeQuery("SELECT * FROM PathIMG WHERE (CodArt = " + codArt + ");");
                int ciSonoImg=0;
                while (rs2.next()){
                    servizio.addPathIMG(Paths.get(rs2.getString("Path")));
                    ciSonoImg++;
                }
                if (ciSonoImg==0){
                    servizio.addPathIMG(Paths.get("src/img/prodotti/StandardIMG.png"));
                }

                rs2 = statement2.executeQuery("SELECT * FROM Voto WHERE (CodArt = " + codArt + ");");
                while (rs2.next()){
                    servizio.addVoto(Double.parseDouble(rs2.getString("Valore")));
                    servizio.addEmailVoto(rs2.getString("Email"));
                }

                servizi.add(servizio);
            }

            puntoVendita.setServizi(servizi);
            puntiVendita.set(i,puntoVendita);

        }

        FD.setPuntiVendita(puntiVendita);

        return 1;

    }

}
