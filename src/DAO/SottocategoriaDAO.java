package DAO;

import Model.FakeDatabase;
import Model.Merce.Categoria;
import Model.Merce.ICategoria;
import Model.Merce.PuntiVendita;
import Model.Merce.Sottocategoria;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class SottocategoriaDAO {

    public int SalvaSottocategoria(FakeDatabase FD, Connection connection) throws SQLException {

        Statement statement = connection.createStatement();

        List<PuntiVendita> puntiVendita = FD.getPuntivendita();

        for (int i=0; i<puntiVendita.size();i++){
            PuntiVendita puntoVendita = puntiVendita.get(i);
            List<ICategoria> categorie = puntoVendita.getCategorie();

            for (int j=0;j<categorie.size();j++){

                Categoria categoria = (Categoria) categorie.get(j);
                List<ICategoria> sottocategorie = categoria.getSottocategorie();

                ResultSet rs = statement.executeQuery("SELECT * FROM Categoria WHERE (Nome = '" + categoria.getnome() + "') AND (Indice = " + j + ") AND (NomePV = '" + puntoVendita.getNome() + "')" + ";");
                int codCat = 0;
                while (rs.next()){
                    codCat = Integer.parseInt(rs.getString("CodCat"));
                }

                for (int k=0; k<sottocategorie.size();k++){

                    statement.executeUpdate("INSERT INTO SottoCategoria (Nome,Indice,CodCat) " + "VALUES " +
                            "("
                            + "'" + sottocategorie.get(k).getnome() + "'" + ","
                            + k + ","
                            + codCat
                            + ");");

                }

            }

        }

        return 1;
    }

    public int UpdateSottocategoria(FakeDatabase FD, Connection c) throws SQLException {
        Statement statement = c.createStatement();
        statement.executeUpdate("DELETE FROM SottoCategoria;");
        SalvaSottocategoria(FD,c);

        System.out.println("SottoCategorie Aggiornate");

        return 1;
    }

    public int LoadSottocategoria(FakeDatabase FD, Connection c) throws SQLException {

        Statement statement = c.createStatement();
        List<PuntiVendita> puntiVendita = FD.getPuntivendita();

        for (int i=0; i<puntiVendita.size(); i++){

            PuntiVendita puntoVendita = puntiVendita.get(i);
            System.out.println(puntoVendita.getNome());
            List<ICategoria> categorie = puntoVendita.getCategorie();

            for (int j=0; j<categorie.size(); j++){

                Categoria categoria = (Categoria) categorie.get(j);
                System.out.println(categoria.getnome());

                ResultSet rs = statement.executeQuery("SELECT * FROM Categoria WHERE (Nome = '" + categoria.getnome() + "') AND (Indice = " + j + ") AND (NomePV = '" + puntoVendita.getNome() + "')" + ";");
                int codCat = 0;
                while (rs.next()){
                    codCat = Integer.parseInt(rs.getString("CodCat"));
                }
                System.out.println(codCat);

                rs = statement.executeQuery("SELECT * FROM SottoCategoria WHERE (CodCat = " + codCat + ") ORDER BY Indice");
                while (rs.next()){
                    Sottocategoria sottoCat = new Sottocategoria(rs.getString("Nome"));
                    System.out.println(sottoCat.getnome());
                    categoria.add(sottoCat);
                }

                categorie.set(j,categoria);

            }
            puntoVendita.setCategorie(categorie);
            puntiVendita.set(i,puntoVendita);

        }

        FD.setPuntiVendita(puntiVendita);

        return 1;
    }

    /*
    public int removeCategoria(FakeDatabase DB, Connection connection, Sottocategoria sottocategoria,Categoria categoria,PuntiVendita PV) throws SQLException {

        Statement statement = connection.createStatement();

        int codCat=-1;
        ResultSet rs1 = statement.executeQuery("SELECT * FROM Categoria WHERE (Nome = '" + categoria.getnome() + "') AND (NomePV = '" + PV.getNome() + "')");
        while (rs1.next()){
            codCat = Integer.parseInt(rs1.getString("CodCat"));
        }

        ResultSet rs2 = statement.executeQuery("DELETE FROM SottoCategoria WHERE (Nome = '" + sottocategoria.getnome() + "') AND (CodCat= "+ codCat +")");

        return 1;
    }
    */


}
