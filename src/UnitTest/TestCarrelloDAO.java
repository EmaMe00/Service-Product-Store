package UnitTest;

import DAO.ArticoliDAO;
import DAO.CarrelloDAO;
import DBInterface.ConnectionDB;
import Model.FakeDatabase;
import org.junit.Assert;
import org.junit.Test;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public class TestCarrelloDAO {

    //Inizializzazione con dati fittizzi per eseguire i test
    private FakeDatabase FD = FakeDatabase.getInstance();
    private ConnectionDB connection = ConnectionDB.getInstance();

    @Test
    public void SalvaCarrelloTest() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchAlgorithmException {

        FD.DefaultDatabase();

        CarrelloDAO CDAOtest = new CarrelloDAO();
        int value = CDAOtest.SalvaCarrello(FD,connection.StartConnection());
        Assert.assertEquals(1,value);
    }

    @Test
    public void UpdateCarrelloTest() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchAlgorithmException {

        FD.DefaultDatabase();

        CarrelloDAO CDAOtest = new CarrelloDAO();
        int value = CDAOtest.UpdateCarrello(FD,connection.StartConnection());
        Assert.assertEquals(1,value);
    }

    @Test
    public void LoadCarrelloTest() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchAlgorithmException {

        FD.DefaultDatabase();

        CarrelloDAO CDAOtest = new CarrelloDAO();
        int value = CDAOtest.LoadCarrello(FD,connection.StartConnection());
        Assert.assertEquals(1,value);
    }

    @Test
    public void removeElementoUtenteTest() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchAlgorithmException {

        FD.DefaultDatabase();

        CarrelloDAO CDAOtest = new CarrelloDAO();
        int value = CDAOtest.removeElementoUtente(connection.StartConnection(),FD,"emanuele.mele@studenti.unisalento.it");
        Assert.assertEquals(1,value);
    }


}
