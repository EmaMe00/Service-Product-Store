package UnitTest;

import DAO.CorsieDAO;
import DAO.PathIMGDAO;
import DBInterface.ConnectionDB;
import Model.FakeDatabase;
import org.junit.Assert;
import org.junit.Test;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public class TestPathIMGDAO {

    //Inizializzazione con dati fittizzi per eseguire i test
    private FakeDatabase FD = FakeDatabase.getInstance();
    private ConnectionDB connection = ConnectionDB.getInstance();

    @Test
    public void SalvaPathIMGTest() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchAlgorithmException {

        FD.DefaultDatabase();

        PathIMGDAO CDAOtest = new PathIMGDAO();
        int value = CDAOtest.SalvaPathIMG(FD,connection.StartConnection());
        Assert.assertEquals(1,value);
    }

    @Test
    public void UpdatePathIMGTest() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchAlgorithmException {

        FD.DefaultDatabase();

        PathIMGDAO CDAOtest = new PathIMGDAO();
        int value = CDAOtest.UpdatePathIMG(FD,connection.StartConnection());
        Assert.assertEquals(1,value);
    }



}
