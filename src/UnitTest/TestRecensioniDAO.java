package UnitTest;

import DAO.PuntiVenditaDAO;
import DAO.RecensioniDAO;
import DBInterface.ConnectionDB;
import Model.FakeDatabase;
import org.junit.Assert;
import org.junit.Test;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public class TestRecensioniDAO {

    private FakeDatabase FD = FakeDatabase.getInstance();
    private ConnectionDB connection = ConnectionDB.getInstance();

    @Test
    public void SalvaRecensioniDAO() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchAlgorithmException {

        FD.DefaultDatabase();

        RecensioniDAO CDAOtest = new RecensioniDAO();
        int value = CDAOtest.SalvaRecensioni(FD,connection.StartConnection());
        Assert.assertEquals(1,value);
    }

    @Test
    public void UpdateRecensioniDAO() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchAlgorithmException {

        FD.DefaultDatabase();

        RecensioniDAO CDAOtest = new RecensioniDAO();
        int value = CDAOtest.UpdateRecensione(FD,connection.StartConnection());
        Assert.assertEquals(1,value);
    }

    @Test
    public void LoadRecensioniDAO() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchAlgorithmException {

        FD.DefaultDatabase();

        RecensioniDAO CDAOtest = new RecensioniDAO();
        int value = CDAOtest.LoadRecensioni(FD,connection.StartConnection());
        Assert.assertEquals(1,value);
    }


}
