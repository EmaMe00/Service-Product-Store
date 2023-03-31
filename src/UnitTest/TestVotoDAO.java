package UnitTest;

import DAO.UtenteDAO;
import DAO.VotoDAO;
import DBInterface.ConnectionDB;
import Model.FakeDatabase;
import org.junit.Assert;
import org.junit.Test;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public class TestVotoDAO {

    private FakeDatabase FD = FakeDatabase.getInstance();
    private ConnectionDB connection = ConnectionDB.getInstance();

    @Test
    public void SalvaVotoDAO() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchAlgorithmException {

        FD.DefaultDatabase();

        VotoDAO CDAOtest = new VotoDAO();
        int value = CDAOtest.SalvaVoto(FD,connection.StartConnection());
        Assert.assertEquals(1,value);
    }

    @Test
    public void UpdateVotoDAO() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchAlgorithmException {

        FD.DefaultDatabase();

        VotoDAO CDAOtest = new VotoDAO();
        int value = CDAOtest.UpdateVoto(FD,connection.StartConnection());
        Assert.assertEquals(1,value);
    }
}
