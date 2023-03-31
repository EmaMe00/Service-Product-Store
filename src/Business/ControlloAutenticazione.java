package Business;

import Model.FakeDatabase;
import Model.Sessione;
import Model.Utenti.Manager;
import Model.Utenti.Utente;
import View.MenuPrincipale;

import javax.swing.*;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public class ControlloAutenticazione {

    public static ControlloAutenticazione instance;

    private ControlloAutenticazione(){}

    //Singleton
    public static ControlloAutenticazione getInstance(){
        if (instance == null){
            instance = new ControlloAutenticazione();
        }
        return  instance;
    }

    //Controllo autenticazione dell'amministratore
    public int ControlloAmministratore(String username, String password){
        if (username.equals("admin") && password.equals("admin")){
            System.out.println("Credenziali da amministratore corrette !");
            return 0;
        }else {
            return 1;
        }
    }

    //controlla le credenziali degli utenti presenti nel database
    public int ControlloCredenzialiUtente(String email, String password, MenuPrincipale M) throws NoSuchAlgorithmException {

        FakeDatabase DB = M.getFD();
        List<Utente> lutmp = DB.getListaUtenti();
        System.out.println(lutmp.size());

        password = md5(password);
        System.out.println(password);

        //scorre i vari utenti presenti in FD e verifica che l'utente tal dei tali sia o meno già registrato
        for (int i=0; i<lutmp.size(); i++){

            if(email.equals(lutmp.get(i).getEmail()) && password.equals(lutmp.get(i).getPassword())){
                System.out.println("Credenziali da utente corrette !");
                JOptionPane.showMessageDialog(M, "Sei entrato come: " + lutmp.get(i).getNome() + " " + lutmp.get(i).getCognome());
                Sessione sessione = new Sessione(lutmp.get(i).getEmail(),lutmp.get(i).getPassword(),i);
                DB.setSessione(sessione);
                M.SaveFD(DB);
                return 0;
            }else {
                System.out.println(lutmp.get(i).getEmail() + " " + lutmp.get(i).getPassword());
            }

            //System.out.println(lutmp.get(i).getEmail());
            //System.out.println(lutmp.get(i).getPassword());

        }

        return 1;

    }

    //controlla le credenziali dei manger presenti nel database
    public int ControlloCredenzialiManager(String email, String password, MenuPrincipale M) throws NoSuchAlgorithmException {

        FakeDatabase DB = M.getFD();
        List<Manager> mstmp = DB.getListamanager();


        password = md5(password);


        //scorre i vari manager presenti in FD e verifica che l'utente tal dei tali sia o meno già registrato
        for (int i=0; i<mstmp.size(); i++){

            if(email.equals(mstmp.get(i).getEmail()) && password.equals(mstmp.get(i).getPassword())){
                System.out.println("Credenziali da manager corrette !");
                JOptionPane.showMessageDialog(M, "Sei entrato come: " + mstmp.get(i).getNome() + " " + mstmp.get(i).getCognome());
                Sessione sessione = new Sessione();
                sessione.setEmailManager(mstmp.get(i).getEmail());
                sessione.setPasswordManager(mstmp.get(i).getPassword());
                sessione.setNumManager(i);
                DB.setSessione(sessione);
                M.SaveFD(DB);

                return mstmp.get(i).getNumPuntoVendita();
            }

            //System.out.println(lutmp.get(i).getEmail());
            //System.out.println(lutmp.get(i).getPassword());

        }

        return -1;

    }


    /*
    public int ControlloCredenzialiIterator(String email,String password,MenuPrincipale M){

        FakeDatabase Db = M.getFD();
        List<String> lutmp = Db.getListaemail();


        ListIterator iterator = lutmp.listIterator();
        while(iterator.hasNext()){
           Utente tempU = (Utente) iterator.getNext();
        }

        return 1;
    }*/

    public static String md5(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes());
        byte[] digest = md.digest();
        BigInteger bigInt = new BigInteger(1,digest);
        password = bigInt.toString();
        return password;
    }


}
