import Business.GestioneEmail;
import Business.GestisciServizi;
import DAO.UtenteDAO;
import DBInterface.ConnectionDB;
import Model.FakeDatabase;
import Model.Utenti.Utente;
import View.MenuPrincipale;

import javax.mail.MessagingException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.SQLException;

public class Main {


    public static void main(String args[]) throws IOException, ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException, NoSuchAlgorithmException {

        MenuPrincipale M = new MenuPrincipale();
        M.StartMenuPrincipale();

        return;

    }


}
