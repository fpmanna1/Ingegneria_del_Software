package Controller;

import Database.ClienteDAO;
import Database.IstruttoreDAO;
import Database.LezioneGuidaDAO;
import Entity.*;

import javax.management.OperationsException;
import java.sql.Date;
import java.sql.Time;
import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

public class GestioneScuolaGuida
{
    //private final ClienteDAO cliente = new ClienteDAO();

    private static GestioneScuolaGuida instance = null;
    private GestioneScuolaGuida(){}
    public static GestioneScuolaGuida getInstance()
    {
        if(instance == null)
            instance = new GestioneScuolaGuida();
        return instance;
    }

    public EntityLezioneGuida prenotaLezione(Date data, Time ora, String matIstruttore) throws OperationsException
    {
        EntityIstruttore istruttore = null;
        EntityLezioneGuida lezioneGuida = null;
        IstruttoreDAO istruttoreDAO = new IstruttoreDAO();
        LezioneGuidaDAO lezioneGuidaDAO = new LezioneGuidaDAO();

        // controllo se la matricola dell'istruttore inserita dall'utente esiste nel db
        try {
            istruttore = istruttoreDAO.readIstruttore(matIstruttore);
        }
        catch(OperationsException e){
            System.out.println("Matricola istruttore non valida/esistente");
        }

        // controllo se è già presente la lezione nel db
        try {
            lezioneGuida = lezioneGuidaDAO.verificaDisponibilitaLezione(data, ora, matIstruttore);
        }
        catch(OperationsException e){
            System.out.println("Lezione guida non prenotabile");
        }

        return lezioneGuida;
    }

    public void creaLezione(EntityLezioneGuida lezioneGuida)
    {
        try{
            new LezioneGuidaDAO().createLezione(lezioneGuida);
        }
        catch(OperationsException e){
            System.out.println("Errore prenotazione lezione");
        }
    }

    public void autenticazione(String username, String password)
    {
        try{
            new ClienteDAO().autenticazione(username, password);
        }
        catch(OperationsException e){
            System.out.println("Errore inserimento dati utente");
        }
    }

    public void memorizzaCliente(EntityCliente cliente, String tipoPatente, Date conseguimento)
    {
        try{
            new ClienteDAO().memorizzaCliente(cliente, tipoPatente, conseguimento);
        }
        catch(OperationsException e){
            System.out.println("Errore inserimento cliente");
        }
    }

    public boolean inviaCredenziali(String eMailDestinatario)
    {
        final String template = "La registrazione del cliente ";
        System.out.println("Invio email a " + eMailDestinatario + "...");

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            System.out.println(template + "non è andata a buon fine");
            return false;
        }
        System.out.println(template + "è andata a buon fine");
        return true;
    }

    public void simulazioneProva(String numeroCartaCliente)
    {

    }

    /*
    * Debug tests only
    */
    public void selectClienti()
    {
        try {
            new ClienteDAO().selectClienti();
        }
        catch (OperationsException e){
            System.out.println(e.toString());
        }
    }
}
