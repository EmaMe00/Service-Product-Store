package DAO;

import Model.FakeDatabase;
import Model.Merce.Categoria;
import Model.Merce.Corsia;
import Model.Merce.PuntiVendita;
import Model.Merce.Scaffale;

import java.net.ConnectException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CorsieDAO {

    public int SalvaCorsie (FakeDatabase FD, Connection connection) throws SQLException {

        Statement statement = connection.createStatement();

        List<PuntiVendita>  puntiVendita = FD.getPuntivendita();

        for (int i=0; i<puntiVendita.size(); i++){

            PuntiVendita puntoVendita = puntiVendita.get(i);
            List<Corsia> corsie = puntoVendita.getCorsie();

            for (int k=0; k<corsie.size(); k++){

                statement.executeUpdate("INSERT INTO Corsia (NomeCorsia,NomePV,Indice)" + "VALUES " +
                        "("
                        + "'" + corsie.get(k).getCodCorsia() + "'" + ","
                        + "'" + puntoVendita.getNome() + "'" + ","
                        + k
                        + ");");

            }



        }

        return 1;

    }

    public int UpdateCorsia (FakeDatabase FD, Connection connection) throws SQLException {

        Statement statement = connection.createStatement();

        statement.executeUpdate("DELETE FROM Scaffale");
        statement.executeUpdate("DELETE FROM Corsia");
        SalvaCorsie(FD,connection);

        ScaffaleDAO SDAO = new ScaffaleDAO();
        SDAO.SalvaScaffale(FD,connection);

        System.out.println("CorsieAggiorante");
        return 1;

    }

    public int LoadCorsia (FakeDatabase FD, Connection connection) throws SQLException {

        Statement statement = connection.createStatement();
        List<PuntiVendita> puntiVendita = FD.getPuntivendita();

        for (int i=0; i<puntiVendita.size(); i++){

            List<Corsia> corsie = new ArrayList<>();

            ResultSet rs = statement.executeQuery("SELECT * FROM Corsia WHERE (NomePV = '" + puntiVendita.get(i).getNome() + "')" + " ORDER BY Indice");
            while (rs.next()) {
                Corsia corsia = new Corsia(rs.getString("NomeCorsia"));
                corsie.add(corsia);
            }

            puntiVendita.get(i).setCorsie(corsie);

        }

        FD.setPuntiVendita(puntiVendita);

        return 1;

    }

}
