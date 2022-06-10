package Controller;

import Database.IstruttoreDAO;
import Database.LezioneGuidaDAO;
import Entity.*;

import javax.management.OperationsException;
import java.sql.Date;
import java.sql.Time;
import java.sql.SQLException;

public class GestioneScuolaGuida
{
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
        IstruttoreDAO istruttoreDAO = null;
        LezioneGuidaDAO lezioneGuidaDAO = null;

        try{
            // controllo se la matricola dell'istruttore inserita dall'utente esiste nel db
            istruttore = istruttoreDAO.readIstruttore(matIstruttore);
            if(istruttore == null)
                throw new OperationsException("Matricola istruttore non valida/non esistente");

            // controllo se è già presente la lezione nel db
            lezioneGuida = lezioneGuidaDAO.verificaDisponibilitaLezione(data, ora, matIstruttore);
            if(lezioneGuida != null)
                throw new OperationsException("Lezione guida non prenotabile");

        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }

        return lezioneGuida;
    }

    public void creaLezione(EntityLezioneGuida lezioneGuida)
    {
        LezioneGuidaDAO lezioneGuidaDAO = null;

        try {
            lezioneGuidaDAO.createLezione(lezioneGuida);
        } catch (SQLException e) {
            System.out.println("Errore prenotazione lezione");
        }
    }
}
