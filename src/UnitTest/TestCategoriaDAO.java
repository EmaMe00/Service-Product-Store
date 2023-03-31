package UnitTest;

import DAO.CarrelloDAO;
import DAO.CategoriaDAO;
import DBInterface.ConnectionDB;
import Model.FakeDatabase;
import org.junit.Assert;
import org.junit.Test;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public class TestCategoriaDAO {

    //Inizializzazione con dati fittizzi per eseguire i test
    private FakeDatabase FD = FakeDatabase.getInstance();
    private ConnectionDB connection = ConnectionDB.getInstance();

    @Test
    public void SalvaCategoriaTest() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchAlgorithmException {

        FD.DefaultDatabase();

        CategoriaDAO CDAOtest = new CategoriaDAO();
        int value = CDAOtest.SalvaCategoria(FD,connection.StartConnection());
        Assert.assertEquals(1,value);
    }

    @Test
    public void UpdateCategoriaTest() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchAlgorithmException {

        FD.DefaultDatabase();

        CategoriaDAO CDAOtest = new CategoriaDAO();
        int value = CDAOtest.UpdateCategoria(FD,connection.StartConnection());
        Assert.assertEquals(1,value);
    }

    @Test
    public void LoadCategoriaTest() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchAlgorithmException {

        FD.DefaultDatabase();

        CategoriaDAO CDAOtest = new CategoriaDAO();
        int value = CDAOtest.LoadCategoria(FD,connection.StartConnection());
        Assert.assertEquals(1,value);
    }

}
