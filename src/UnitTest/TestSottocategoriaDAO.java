package UnitTest;

import DAO.ScaffaleDAO;
import DAO.SottocategoriaDAO;
import DBInterface.ConnectionDB;
import Model.FakeDatabase;
import org.junit.Assert;
import org.junit.Test;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public class TestSottocategoriaDAO {

    private FakeDatabase FD = FakeDatabase.getInstance();
    private ConnectionDB connection = ConnectionDB.getInstance();

    @Test
    public void SalvaSottocategoriaDAO() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchAlgorithmException {

        FD.DefaultDatabase();

        SottocategoriaDAO CDAOtest = new SottocategoriaDAO();
        int value = CDAOtest.SalvaSottocategoria(FD,connection.StartConnection());
        Assert.assertEquals(1,value);
    }

    @Test
    public void UpdateSottocategoriaDAO() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchAlgorithmException {

        FD.DefaultDatabase();

        SottocategoriaDAO CDAOtest = new SottocategoriaDAO();
        int value = CDAOtest.UpdateSottocategoria(FD,connection.StartConnection());
        Assert.assertEquals(1,value);
    }

    @Test
    public void LoadSottocategoriaDAO() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchAlgorithmException {

        FD.DefaultDatabase();

        SottocategoriaDAO CDAOtest = new SottocategoriaDAO();
        int value = CDAOtest.LoadSottocategoria(FD,connection.StartConnection());
        Assert.assertEquals(1,value);
    }


}
