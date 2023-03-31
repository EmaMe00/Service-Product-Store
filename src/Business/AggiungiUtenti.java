package Business;

import DAO.UtenteDAO;
import Model.FakeDatabase;
import Model.Utenti.Utente;
import View.MenuPrincipale;
import View.Registrazione;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class AggiungiUtenti {

    public AggiungiUtenti(){}

    public void aggiungiUtente(MenuPrincipale M, Registrazione R) throws SQLException, NoSuchAlgorithmException {

        FakeDatabase DB = M.getFD();//creo una variabile di appoggio di FakeDatabase dove metterò i nuovi utenti
        List<Utente> lutmp = DB.getListaUtenti();//creo una lista temporanea dove salverò tutto

        int giaRegistrato=0;
        for (int i=0; i<DB.getListaUtenti().size(); i++){
            if (R.getEmail().equals(DB.getListaUtenti().get(i).getEmail())){
                giaRegistrato=1;
            }
        }

        if (giaRegistrato == 1){
            JOptionPane.showMessageDialog(R, "Email già esistente !");
            return;
        }

        //Creo il mio utente temporaneo e lo aggiungo nella mia lista
        try{
            Utente utmp = new Utente.Builder(R.getNome())
                    .setCognome(R.getCognome())
                    .setNickname(R.getNickname())
                    .setEmail(R.getEmail())
                    .setEta(Integer.parseInt(R.getEta()))
                    .setPassword(R.getPassword())
                    .setTelefono(R.getTelefono())
                    .setCitta(R.getCitta()).build();

            lutmp.add(utmp);

            //Salvo la mia lista aggiornata
            DB.setListaUtenti(lutmp);
            M.SaveFD(DB);

            Connection C = M.getConnection();
            UtenteDAO UDAO = new UtenteDAO();
            UDAO.addUtente(C,M.getFD(),utmp);

            JOptionPane.showMessageDialog(R, "Registrato Correttamente !");
        }catch (Exception e){
            JOptionPane.showMessageDialog(R, "Inserisci tutti i dati !");
        }


    }

}

