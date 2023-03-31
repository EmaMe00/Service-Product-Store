package DBInterface;

import DAO.*;
import Model.FakeDatabase;

import java.security.NoSuchAlgorithmException;
import java.sql.*;

public class ConnectionDB {

    private static ConnectionDB instance;

    private static Connection connection = null;
    private static String databaseName = "Myshop2021";
    private static String url = "jdbc:mysql://localhost:3306/" + databaseName;
    private static String username = "root";
    private static String password = "Myshop2021";

    private ConnectionDB(){}

    //Singleton
    public static ConnectionDB getInstance(){
        if (instance == null){
            instance = new ConnectionDB();
        }
        return instance;
    }

    public Connection StartConnection() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        connection = DriverManager.getConnection(url,username,password);
        PreparedStatement ps = connection.prepareStatement("SELECT * FROM `Myshop2021`.`Utente`;");
        ResultSet status = ps.executeQuery();
        System.out.println("Database Connesso");
        return connection;
    }

    public int LoadDatabase(FakeDatabase FD, Connection c) throws SQLException, NoSuchAlgorithmException {

        UtenteDAO UDAO = new UtenteDAO();
        UDAO.LoadUtenti(FD,c);

        PuntiVenditaDAO PVDAO = new PuntiVenditaDAO();
        PVDAO.LoadPuntiVendita(FD,c);

        CategoriaDAO CDAO = new CategoriaDAO();
        CDAO.LoadCategoria(FD,c);

        SottocategoriaDAO SCDAO = new SottocategoriaDAO();
        SCDAO.LoadSottocategoria(FD,c);

        CorsieDAO CSDAO = new CorsieDAO();
        CSDAO.LoadCorsia(FD,c);

        ScaffaleDAO SCADAO = new ScaffaleDAO();
        SCADAO.LoadScaffali(FD,c);

        ArticoliDAO ADAO = new ArticoliDAO();
        ADAO.LoadArticoli(FD,c);

        RecensioniDAO RDAO = new RecensioniDAO();
        RDAO.LoadRecensioni(FD,c);

        CarrelloDAO C1DAO = new CarrelloDAO();
        C1DAO.LoadCarrello(FD,c);

        VotoDAO VDAO = new VotoDAO();
        VDAO.LoadVoto(FD,c);

        System.out.println("CARICAMENTO DEL DATABASE AVVENUTO CON SUCCESSO !");
        return 1;
    }

    public void SalvaDatabase(FakeDatabase FD, Connection c) throws SQLException {
        UtenteDAO UDAO = new UtenteDAO();
        UDAO.UpdateUtenti(FD,c);

        PuntiVenditaDAO PVDAO = new PuntiVenditaDAO();
        PVDAO.UpdatePuntiVendita(FD,c);
    }




}
