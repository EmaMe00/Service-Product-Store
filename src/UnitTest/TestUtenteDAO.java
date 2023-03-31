package UnitTest;

import DAO.ScaffaleDAO;
import DAO.UtenteDAO;
import DBInterface.ConnectionDB;
import Model.FakeDatabase;
import Model.Utenti.Manager;
import Model.Utenti.Utente;
import org.junit.Assert;
import org.junit.Test;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public class TestUtenteDAO {

    private FakeDatabase FD = FakeDatabase.getInstance();
    private ConnectionDB connection = ConnectionDB.getInstance();

    @Test
    public void SalvaUtentiEManagerDAO() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchAlgorithmException {

        FD.DefaultDatabase();

        UtenteDAO CDAOtest = new UtenteDAO();
        int value = CDAOtest.SalvaUtentiEManager(FD,connection.StartConnection());
        Assert.assertEquals(1,value);
    }

    @Test
    public void UpdateUtentiDAO() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchAlgorithmException {

        FD.DefaultDatabase();

        UtenteDAO CDAOtest = new UtenteDAO();
        int value = CDAOtest.UpdateUtenti(FD,connection.StartConnection());
        Assert.assertEquals(1,value);
    }

    @Test
    public void LoadUtentiDAO() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchAlgorithmException {

        FD.DefaultDatabase();

        UtenteDAO CDAOtest = new UtenteDAO();
        int value = CDAOtest.LoadUtenti(FD,connection.StartConnection());
        Assert.assertEquals(1,value);
    }

    @Test
    public void ChangeNumPVDAO() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchAlgorithmException {

        FD.DefaultDatabase();

        UtenteDAO CDAOtest = new UtenteDAO();
        int value = CDAOtest.ChangeNumPV(FD.getListaUtenti().get(0).getEmail(),connection.StartConnection(),FD);
        Assert.assertEquals(1,value);
    }

    @Test
    public void addUtenteDAO() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchAlgorithmException {

        FD.DefaultDatabase();

        Utente u = new Utente();
        u.setEmail("test-");
        u.setNome("test-");

        UtenteDAO CDAOtest = new UtenteDAO();
        int value = CDAOtest.addUtente(connection.StartConnection(),FD,u);
        Assert.assertEquals(1,value);
    }

    @Test
    public void addManagerDAO() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchAlgorithmException {

        FD.DefaultDatabase();
        Manager m = new Manager();
        m.setNome("test1");
        m.setEmail("test1");

        UtenteDAO CDAOtest = new UtenteDAO();
        int value = CDAOtest.addManager(connection.StartConnection(),FD,m);
        Assert.assertEquals(1,value);
    }

    @Test
    public void removeUtenteOManagerDAO() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchAlgorithmException {

        FD.DefaultDatabase();

        UtenteDAO CDAOtest = new UtenteDAO();
        int value = CDAOtest.removeUtenteOManager(connection.StartConnection(),FD,FD.getListamanager().get(0).getEmail());
        Assert.assertEquals(1,value);
    }
}
