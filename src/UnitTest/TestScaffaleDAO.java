package UnitTest;

import DAO.RecensioniDAO;
import DAO.ScaffaleDAO;
import DBInterface.ConnectionDB;
import Model.FakeDatabase;
import org.junit.Assert;
import org.junit.Test;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public class TestScaffaleDAO {

    private FakeDatabase FD = FakeDatabase.getInstance();
    private ConnectionDB connection = ConnectionDB.getInstance();

    @Test
    public void SalvaScaffaleDAO() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchAlgorithmException {

        FD.DefaultDatabase();

        ScaffaleDAO CDAOtest = new ScaffaleDAO();
        int value = CDAOtest.SalvaScaffale(FD,connection.StartConnection());
        Assert.assertEquals(1,value);
    }

    @Test
    public void UpdateScaffaleDAO() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchAlgorithmException {

        FD.DefaultDatabase();

        ScaffaleDAO CDAOtest = new ScaffaleDAO();
        int value = CDAOtest.UpdateScaffale(FD,connection.StartConnection());
        Assert.assertEquals(1,value);
    }

    @Test
    public void LoadScaffaleDAO() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchAlgorithmException {

        FD.DefaultDatabase();

        ScaffaleDAO CDAOtest = new ScaffaleDAO();
        int value = CDAOtest.LoadScaffali(FD,connection.StartConnection());
        Assert.assertEquals(1,value);
    }

}
