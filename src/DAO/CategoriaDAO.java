package DAO;

import Model.FakeDatabase;
import Model.Merce.Categoria;
import Model.Merce.ICategoria;
import Model.Merce.PuntiVendita;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDAO {

    public int SalvaCategoria(FakeDatabase FD, Connection connection) throws SQLException {

        List<PuntiVendita> puntiVendita = FD.getPuntivendita();
        Statement statement = connection.createStatement();

        for (int i=0; i<puntiVendita.size(); i++){

            List<ICategoria> categorie = puntiVendita.get(i).getCategorie();

            for (int j=0; j<categorie.size();j++){

                try{
                    statement.executeUpdate("INSERT INTO Categoria (Nome,NomePV,Indice) " + "VALUES " +
                            "("
                            + "'" + categorie.get(j).getnome() + "'" + ","
                            + "'" + puntiVendita.get(i).getNome() + "'" + ","
                            + j
                            + ");");

                }catch (Exception e ){
                    System.out.println("Categoria già inserita: " + categorie.get(j).getnome());
                }

            }

        }

        return 1;
    }

    public int UpdateCategoria(FakeDatabase FD, Connection c) throws SQLException {

        Statement statement = c.createStatement();
        statement.executeUpdate("DELETE FROM SottoCategoria");
        statement.executeUpdate("DELETE FROM Categoria;");
        SalvaCategoria(FD,c);

        SottocategoriaDAO SDAO = new SottocategoriaDAO();
        SDAO.SalvaSottocategoria(FD,c);

        System.out.println("Categorie Aggiornate");

        return 1;
    }

    public int LoadCategoria(FakeDatabase FD, Connection c) throws SQLException {

        Statement statement = c.createStatement();
        List<PuntiVendita> puntiVendita = FD.getPuntivendita();


        for (int i=0;i<puntiVendita.size();i++){

            List<ICategoria> categorie = new ArrayList<>();

            ResultSet rs = statement.executeQuery("SELECT * FROM Categoria WHERE (NomePV = '" + puntiVendita.get(i).getNome() + "')" + " ORDER BY Indice");
            while (rs.next()) {
                Categoria categoria = new Categoria(rs.getString("Nome"));
                categorie.add(categoria);
            }

            puntiVendita.get(i).setCategorie(categorie);

        }

        FD.setPuntiVendita(puntiVendita);

        return 1;

    }



}
