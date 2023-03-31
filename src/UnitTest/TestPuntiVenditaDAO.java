package UnitTest;

import DAO.PathIMGDAO;
import DAO.PuntiVenditaDAO;
import DBInterface.ConnectionDB;
import Model.FakeDatabase;
import Model.Merce.PuntiVendita;
import org.junit.Assert;
import org.junit.Test;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public class TestPuntiVenditaDAO {

    private FakeDatabase FD = FakeDatabase.getInstance();
    private ConnectionDB connection = ConnectionDB.getInstance();

    @Test
    public void UpdatePuntiVenditaDAO() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchAlgorithmException {

        FD.DefaultDatabase();

        PuntiVenditaDAO CDAOtest = new PuntiVenditaDAO();
        int value = CDAOtest.UpdatePuntiVendita(FD,connection.StartConnection());
        Assert.assertEquals(1,value);
    }

    @Test
    public void SalvaPuntiVenditaDAO() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchAlgorithmException {

        FD.DefaultDatabase();

        PuntiVenditaDAO CDAOtest = new PuntiVenditaDAO();
        int value = CDAOtest.SalvaPuntiVendita(FD,connection.StartConnection());
        Assert.assertEquals(1,value);
    }

    @Test
    public void LoadPuntiVenditaDAO() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchAlgorithmException {

        FD.DefaultDatabase();

        PuntiVenditaDAO CDAOtest = new PuntiVenditaDAO();
        int value = CDAOtest.LoadPuntiVendita(FD,connection.StartConnection());
        Assert.assertEquals(1,value);
    }

    @Test
    public void AddPuntiVenditaDAO() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchAlgorithmException {

        FD.DefaultDatabase();
        PuntiVendita pv = new PuntiVendita("Test.");

        PuntiVenditaDAO CDAOtest = new PuntiVenditaDAO();
        int value = CDAOtest.addPuntoVendita(FD,connection.StartConnection(),pv,100);
        Assert.assertEquals(1,value);
    }



}
