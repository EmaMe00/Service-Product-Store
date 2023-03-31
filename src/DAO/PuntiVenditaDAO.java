package DAO;

import Model.FakeDatabase;
import Model.Merce.PuntiVendita;
import Model.Utenti.Utente;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class PuntiVenditaDAO {

    public int SalvaPuntiVendita(FakeDatabase FD, Connection connection) throws SQLException {

        List<PuntiVendita> puntiVendita = FD.getPuntivendita();
        Statement statement = connection.createStatement();

        for (int i=0;i<puntiVendita.size();i++){

            try{
                statement.executeUpdate("INSERT INTO PuntiVendita " + "VALUES " +
                        "("
                        + "'" + puntiVendita.get(i).getNome() + "'" + ","
                        + i
                        + ");");

            }catch (Exception e ){
                System.out.println("PuntoVendita già inserito: " + puntiVendita.get(i).getNome());
            }

        }

        return 1;
    }

    public int UpdatePuntiVendita(FakeDatabase FD, Connection connection) throws SQLException {

        Statement statement = connection.createStatement();

        statement.executeUpdate("DELETE FROM Carrello");
        statement.executeUpdate("DELETE FROM Recensioni");
        statement.executeUpdate("DELETE FROM Voto");
        statement.executeUpdate("DELETE FROM PathIMG");
        statement.executeUpdate("DELETE FROM Articoli");
        statement.executeUpdate("DELETE FROM SottoCategoria");
        statement.executeUpdate("DELETE FROM Categoria");
        statement.executeUpdate("DELETE FROM Scaffale");
        statement.executeUpdate("DELETE FROM Corsia");
        statement.executeUpdate("DELETE FROM PuntiVendita;");

        SalvaPuntiVendita(FD,connection);

        CategoriaDAO CDAO = new CategoriaDAO();
        CDAO.UpdateCategoria(FD,connection);

        CorsieDAO CSDAO = new CorsieDAO();
        CSDAO.UpdateCorsia(FD,connection);

        ArticoliDAO ADAO = new ArticoliDAO();
        ADAO.UpdateArticoli(FD,connection);

        PathIMGDAO PDAO = new PathIMGDAO();
        PDAO.UpdatePathIMG(FD,connection);

        VotoDAO VDAO = new VotoDAO();
        VDAO.UpdateVoto(FD,connection);

        RecensioniDAO RDAO = new RecensioniDAO();
        RDAO.UpdateRecensione(FD,connection);

        CarrelloDAO C1DAO = new CarrelloDAO();
        C1DAO.UpdateCarrello(FD,connection);

        System.out.println("Eliminato correttamente dal DB");

        return 1;

    }

    public int LoadPuntiVendita(FakeDatabase FD, Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        List<PuntiVendita> puntiVendita = FD.getPuntivendita();
        ResultSet rs = statement.executeQuery("SELECT * FROM PuntiVendita ORDER BY Indice");

        while (rs.next()) {
            PuntiVendita puntoVendita = new PuntiVendita(rs.getString("Nome"));
            puntiVendita.add(puntoVendita);
        }
        FD.setPuntiVendita(puntiVendita);
        return 1;
    }

    public int addPuntoVendita(FakeDatabase FD, Connection connection, PuntiVendita puntoVendita, int indice) throws SQLException {
        Statement statement = connection.createStatement();
        statement.executeUpdate("INSERT INTO PuntiVendita " + "VALUES " +
                "("
                + "'" + puntoVendita.getNome() + "'" + ","
                + indice
                + ");");
        return 1;
    }


}
