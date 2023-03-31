package UnitTest;

import DAO.CategoriaDAO;
import DAO.CorsieDAO;
import DBInterface.ConnectionDB;
import Model.FakeDatabase;
import org.junit.Assert;
import org.junit.Test;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public class TestCorsieDAO {

    //Inizializzazione con dati fittizzi per eseguire i test
    private FakeDatabase FD = FakeDatabase.getInstance();
    private ConnectionDB connection = ConnectionDB.getInstance();

    @Test
    public void SalvaCorsieTest() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchAlgorithmException {

        FD.DefaultDatabase();

        CorsieDAO CDAOtest = new CorsieDAO();
        int value = CDAOtest.SalvaCorsie(FD,connection.StartConnection());
        Assert.assertEquals(1,value);
    }

    @Test
    public void UpdateCorsieTest() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchAlgorithmException {

        FD.DefaultDatabase();

        CorsieDAO CDAOtest = new CorsieDAO();
        int value = CDAOtest.UpdateCorsia(FD,connection.StartConnection());
        Assert.assertEquals(1,value);
    }

    @Test
    public void LoadCorsieTest() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchAlgorithmException {

        FD.DefaultDatabase();

        CorsieDAO CDAOtest = new CorsieDAO();
        int value = CDAOtest.LoadCorsia(FD,connection.StartConnection());
        Assert.assertEquals(1,value);
    }

}
