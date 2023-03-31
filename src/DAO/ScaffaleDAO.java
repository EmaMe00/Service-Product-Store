package DAO;

import Model.FakeDatabase;
import Model.Merce.Corsia;
import Model.Merce.PuntiVendita;
import Model.Merce.Scaffale;
import Model.Merce.Sottocategoria;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class ScaffaleDAO {

    public int SalvaScaffale(FakeDatabase FD, Connection connection) throws SQLException {

        Statement statement = connection.createStatement();
        List<PuntiVendita> puntiVendita = FD.getPuntivendita();

        for (int i=0; i<puntiVendita.size(); i++){

            PuntiVendita puntoVendita = puntiVendita.get(i);
            List<Corsia> corsie = puntoVendita.getCorsie();

            for (int j=0; j<corsie.size();j++){

                List<Scaffale> scaffali = corsie.get(j).getScaffali();

                ResultSet rs = statement.executeQuery("SELECT * FROM Corsia WHERE (NomeCorsia = '" + corsie.get(j).getCodCorsia() + "') AND (Indice = " + j + ") AND (NomePV = '" + puntoVendita.getNome() + "')" + ";");
                int codCors = 0;
                while (rs.next()){
                    codCors = Integer.parseInt(rs.getString("CodCorsia"));
                }

                for (int k=0; k<scaffali.size(); k++){

                    Scaffale scaffale = scaffali.get(k);

                    statement.executeUpdate("INSERT INTO Scaffale (Numero,CodCorsia,Indice) " + "VALUES " +
                            "("
                            + scaffale.getNumero() + ","
                            + codCors + ","
                            + k
                            + ");");

                }

            }


        }

        return 1;
    }

    public int UpdateScaffale(FakeDatabase FD, Connection connection) throws SQLException {

        Statement statement = connection.createStatement();
        statement.executeUpdate("DELETE FROM Scaffale;");
        SalvaScaffale(FD,connection);

        System.out.println("Scaffali Aggiornati");
        return 1;

    }

    public int LoadScaffali(FakeDatabase FD, Connection connection) throws SQLException {

        Statement statement = connection.createStatement();

        List<PuntiVendita> puntiVendita = FD.getPuntivendita();

        for (int i=0; i<puntiVendita.size();i++){

            PuntiVendita puntoVendita = puntiVendita.get(i);
            List<Corsia> corsie = puntoVendita.getCorsie();

            for (int j=0; j<corsie.size(); j++){

                Corsia corsia = corsie.get(j);

                ResultSet rs = statement.executeQuery("SELECT * FROM Corsia WHERE (NomeCorsia = '" + corsia.getCodCorsia() + "') AND (Indice = " + j + ") AND (NomePV = '" + puntoVendita.getNome() + "')" + ";");
                int codCors = 0;
                while (rs.next()){
                    codCors = Integer.parseInt(rs.getString("CodCorsia"));
                }

                rs = statement.executeQuery("SELECT * FROM Scaffale WHERE (CodCorsia = " + codCors + ") ORDER BY Indice");
                while (rs.next()){
                    Scaffale scaffale = new Scaffale(Integer.parseInt(rs.getString("Numero")));
                    corsia.add(scaffale);
                }


            }

        }

        return 1;
    }

}
