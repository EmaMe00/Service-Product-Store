package DAO;

import Model.FakeDatabase;
import Model.Utenti.Manager;
import Model.Utenti.Utente;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class UtenteDAO {

    public int SalvaUtentiEManager(FakeDatabase FD, Connection connection) throws SQLException {

        List<Utente> utenti = FD.getListaUtenti();
        List<Manager> manager = FD.getListamanager();
        Statement statement = connection.createStatement();

        for (int i=0;i<utenti.size();i++){

            try {
                statement.executeUpdate("INSERT INTO Utente " + "VALUES " +
                        "("
                        + "'" + utenti.get(i).getNome() + "'" + ","
                        + "'" + utenti.get(i).getCognome() + "'" + ","
                        + "'" + utenti.get(i).getNickname() + "'" + ","
                        + "'" + utenti.get(i).getEmail() + "'" + ","
                        + "'" + utenti.get(i).getPassword() + "'" + ","
                        + "'" + utenti.get(i).getTelefono() + "'" + ","
                        + "'" + utenti.get(i).getCitta() + "'" + ","
                        + utenti.get(i).getEta() + ","
                        + utenti.get(i).getNumPuntoVendita() + ","
                        + 0  //0 Equivale a False
                        + ");");
            }catch (Exception e){
                System.out.println("Utente già inserito: " + utenti.get(i).getEmail());
            }

        }

        for (int i=0;i<manager.size();i++){

            try {
                statement.executeUpdate("INSERT INTO Utente " + "VALUES " +
                        "("
                        + "'" + manager.get(i).getNome() + "'" + ","
                        + "'" + manager.get(i).getCognome() + "'" + ","
                        + "'" + manager.get(i).getNickname() + "'" + ","
                        + "'" + manager.get(i).getEmail() + "'" + ","
                        + "'" + manager.get(i).getPassword() + "'" + ","
                        + "'" + manager.get(i).getTelefono() + "'" + ","
                        + "'" + manager.get(i).getCitta() + "'" + ","
                        + manager.get(i).getEta() + ","
                        + manager.get(i).getNumPuntoVendita() + ","
                        + 1 //0 Equivale a False
                        + ");");
                System.out.println("Manager aggiunto correttamente");
            }catch (Exception e){
                System.out.println("Manager già inserito: " + manager.get(i).getEmail());
            }

        }
        return 1;

    }

    /*
    public void ClearUtente(FakeDatabase FD, Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        statement.executeUpdate("DELETE FROM Utente;");
        System.out.println("ELIMINATI TUTTI I RECORD DEGLI UTENTI");
    }
     */

    public int UpdateUtenti(FakeDatabase FD, Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        statement.executeUpdate("DELETE FROM Carrello;");
        statement.executeUpdate("DELETE FROM Utente;");
        SalvaUtentiEManager(FD,connection);
        System.out.println("Utenti Aggiornati");
        return 1;
    }

    public int LoadUtenti(FakeDatabase FD, Connection connection) throws SQLException, NoSuchAlgorithmException {
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("SELECT * FROM Utente WHERE (isManager = 0);");
        List<Utente> utenti = FD.getListaUtenti();
        while (rs.next()) {
            Utente utente = new Utente();
            utente.setNome(rs.getString("Nome"));
            utente.setCognome(rs.getString("Cognome"));
            utente.setNickname(rs.getString("Nickname"));
            utente.setEmail(rs.getString("Email"));
            utente.setPasswordNOMD5(rs.getString("Password"));
            utente.setTelefono(rs.getString("Telefono"));
            utente.setCitta(rs.getString("Citta"));
            utente.setEta(Integer.parseInt(rs.getString("Eta")));
            utente.setNumPuntoVendita(Integer.parseInt(rs.getString("numPuntoVendita")));
            utenti.add(utente);
        }
        rs = statement.executeQuery("SELECT * FROM Utente WHERE (isManager = 1);");
        List<Manager> managers = FD.getListamanager();
        while (rs.next()) {
            Manager manager = new Manager();
            manager.setNome(rs.getString("Nome"));
            manager.setCognome(rs.getString("Cognome"));
            manager.setNickname(rs.getString("Nickname"));
            manager.setEmail(rs.getString("Email"));
            manager.setPasswordNOMD5(rs.getString("Password"));
            manager.setTelefono(rs.getString("Telefono"));
            manager.setCitta(rs.getString("Citta"));
            manager.setEta(Integer.parseInt(rs.getString("Eta")));
            manager.setNumPuntoVendita(Integer.parseInt(rs.getString("numPuntoVendita")));
            managers.add(manager);
        }

        FD.setListaUtenti(utenti);
        FD.setListamanager(managers);
        return 1;
    }

    public int ChangeNumPV(String email, Connection connection, FakeDatabase DB) throws SQLException {
        Statement statement = connection.createStatement();
        statement.executeUpdate("UPDATE Utente SET  numPuntoVendita=-1 WHERE (Email='" + email + "');");
        return 1;
    }

    public int addUtente(Connection connection, FakeDatabase DB,Utente utente) throws SQLException {
        Statement statement = connection.createStatement();
        statement.executeUpdate("INSERT INTO Utente " + "VALUES " +
                "("
                + "'" + utente.getNome() + "'" + ","
                + "'" + utente.getCognome() + "'" + ","
                + "'" + utente.getNickname() + "'" + ","
                + "'" + utente.getEmail() + "'" + ","
                + "'" + utente.getPassword() + "'" + ","
                + "'" + utente.getTelefono() + "'" + ","
                + "'" + utente.getCitta() + "'" + ","
                + utente.getEta() + ","
                + utente.getNumPuntoVendita() + ","
                + 0  //0 Equivale a False
                + ");");

        System.out.println("AGGIUTO CORRETTAMENTE");
        return 1;
    }

    public int addManager(Connection connection, FakeDatabase DB,Manager manager) throws SQLException {
        Statement statement = connection.createStatement();
        statement.executeUpdate("INSERT INTO Utente " + "VALUES " +
                "("
                + "'" + manager.getNome() + "'" + ","
                + "'" + manager.getCognome() + "'" + ","
                + "'" + manager.getNickname() + "'" + ","
                + "'" + manager.getEmail() + "'" + ","
                + "'" + manager.getPassword() + "'" + ","
                + "'" + manager.getTelefono() + "'" + ","
                + "'" + manager.getCitta() + "'" + ","
                + manager.getEta() + ","
                + manager.getNumPuntoVendita() + ","
                + 1 //0 Equivale a False
                + ");");

        System.out.println("AGGIUNTO CORRETTAMENTE");
        return 1;
    }

    public int removeUtenteOManager(Connection connection, FakeDatabase DB,String email) throws SQLException{
        Statement statement = connection.createStatement();
        CarrelloDAO CDAO = new CarrelloDAO();
        CDAO.removeElementoUtente(connection,DB,email);
        statement.executeUpdate("DELETE FROM Carrello WHERE (Email = '" +  email + "');");
        statement.executeUpdate("DELETE FROM Utente WHERE (Email = '" +  email + "');");
        System.out.println("ELIMINATO CORRETTAMENTE");
        return 1;
    }




}
