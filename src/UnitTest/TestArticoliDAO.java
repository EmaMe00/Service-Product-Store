package UnitTest;

import DAO.ArticoliDAO;
import DBInterface.ConnectionDB;
import Model.FakeDatabase;
import org.junit.Assert;
import org.junit.Test;


import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.SQLException;

public class TestArticoliDAO {

    //Inizializzazione con dati fittizzi per eseguire i test
    private FakeDatabase FD = FakeDatabase.getInstance();
    private ConnectionDB connection = ConnectionDB.getInstance();


    @Test
    public void SalvaArticoliTest() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchAlgorithmException {

        FD.DefaultDatabase();

        ArticoliDAO ADAOtest = new ArticoliDAO();
        int value = ADAOtest.SalvaArticoli(FD,connection.StartConnection());
        Assert.assertEquals(1,value);
    }

    @Test
    public void LoadArticoliTest() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchAlgorithmException {

        FD.DefaultDatabase();

        ArticoliDAO ADAOtest = new ArticoliDAO();
        int value = ADAOtest.LoadArticoli(FD,connection.StartConnection());
        Assert.assertEquals(1,value);
    }

    @Test
    public void UpdateArticoliTest() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchAlgorithmException {

        FD.DefaultDatabase();

        ArticoliDAO ADAOtest = new ArticoliDAO();
        int value = ADAOtest.UpdateArticoli(FD,connection.StartConnection());
        Assert.assertEquals(1,value);
    }



}
