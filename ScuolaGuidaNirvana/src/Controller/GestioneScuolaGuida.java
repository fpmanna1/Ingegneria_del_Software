package Controller;

import Database.IstruttoreDAO;
import Database.LezioneGuidaDAO;
import Entity.*;

import javax.management.OperationsException;
import javax.swing.*;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;

public class GestioneScuolaGuida
{
    private static GestioneScuolaGuida instance = null;

    public static GestioneScuolaGuida getInstance()
    {
        if(instance == null)
            instance = new GestioneScuolaGuida();
        return instance;
    }

    public void prenotaLezione(Date data, Time ora, String matIstruttore) throws OperationsException
    {
        EntityIstruttore istruttore;
        EntityLezioneGuida lezioneGuida;
        IstruttoreDAO istruttoreDAO = null;
        LezioneGuidaDAO lezioneGuidaDAO = null;

        try{
            // controllo se la matricola dell'istruttore inserita dall'utente esiste nel db
            istruttore = istruttoreDAO.readIstruttore(matIstruttore);
            if(istruttore == null)
                throw new OperationsException("Matricola istruttore non valida/non esistente");

            // controllo se è già presente la lezione nel db
            lezioneGuida = lezioneGuidaDAO.readLezioneGuida(data, ora);
            if(lezioneGuida != null)
                throw new OperationsException("Lezione guida non prenotabile");

        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }

    }

    public boolean verificaDisponibilitaLezione(Date data, String ora, String matIstruttore)
    {
        return true;
    }

}
