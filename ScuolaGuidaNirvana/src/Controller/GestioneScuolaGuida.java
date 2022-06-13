package Controller;

import Database.*;
import Entity.*;

import javax.management.OperationsException;
import javax.swing.text.html.parser.Entity;
import java.sql.Date;
import java.sql.Time;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
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

    public EntityLezioneGuida prenotaLezione(Date data, Time ora, String matIstruttore)
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

    public void creaLezione(EntityLezioneGuida lezioneGuida, String matIstruttore)
    {
        try{
            new LezioneGuidaDAO().createLezione(lezioneGuida, matIstruttore);
        }
        catch(OperationsException e){
            System.out.println("Errore prenotazione lezione");
        }
    }

    /*public boolean autenticazione(String username, String password)
    {
        try{
            new ClienteDAO().autenticazione(username, password);
        }
        catch(OperationsException e){
            System.out.println("Errore inserimento dati utente");
            return false;
        }
        return true;
    }
*/
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

    public EntityProva simulaProva(String numeroCartaIDCliente) throws OperationsException
    {
        EntityProva prova = new EntityProva();
        int numDomande = 0;
        ArrayList<Integer> randomNumbers = new ArrayList<Integer>(EntityProva.NUM_DOMANDE); // vettore idDomande
        EntityDomanda domanda = new EntityDomanda();
        EntityCliente cliente = new EntityCliente();
        ClienteDAO clienteDAO = new ClienteDAO();
        DomandaDAO domandaDAO = new DomandaDAO();
        ProvaDAO provaDAO = new ProvaDAO();

        try{
            cliente = clienteDAO.leggiCliente(numeroCartaIDCliente);
            if(cliente == null){
                System.out.println("Cliente non trovato");
                throw new OperationsException();
            }
        }
        catch(OperationsException e){
            System.out.println("Numero carta identità inserito non valido/esistente");
            throw new OperationsException();
        }
        // conto il numero di domande presenti nel db v
        // generare 40 numeri casuali senza ripetizioni fino a num domande
        // prelevo 40 domande
        // le aggiungo alla prova

        try{
            numDomande = domandaDAO.countDomande();
        }
        catch(OperationsException e){
            System.out.println(e.getMessage());
        }

        randomNumbers = randomQuestions(numDomande);

        try{ // aggiunta domande casuali alla prova
            for(int i=0; i<EntityProva.NUM_DOMANDE; i++) {
                domanda = domandaDAO.readDomanda(randomNumbers.get(i));
                prova.getDomande().add(domanda);
            }
        }
        catch(OperationsException e)
        {
            System.out.println("Non sono presenti domande nel db");
        }
        return prova;
    }

    public void selectClienti()
    {

        try {
            new ClienteDAO().selectClienti();
        }
        catch (OperationsException e){
            System.out.println(e.toString());
        }
    }
    private ArrayList<Integer> randomQuestions(int numDomande) {
        return(genNumber(numDomande));
    }
    private ArrayList<Integer> genNumber(int numMax){
        Random rng = new Random();
        ArrayList<Integer> generated = new ArrayList<Integer>(EntityProva.NUM_DOMANDE);
        int x = EntityProva.NUM_DOMANDE;    //numbers to get
        int y = numMax;    //max number
        for (int i = 0; i < x; i++)
        {
            while(true)
            {
                Integer next = rng.nextInt(y) + 1;
                if (!generated.contains(next))
                {
                    generated.add(next);
                    break;
                }
            }
        }
        return generated;
    }

    public int calcolaPunteggio(ArrayList<String> listaRisposte, EntityProva prova)
    {
        ArrayList<Integer> listaDomande = new ArrayList<Integer>(EntityProva.NUM_DOMANDE);
        int numErrori = 0;

        for(int i=0; i<EntityProva.NUM_DOMANDE; i++)
            listaDomande.add(prova.getDomande().get(i).getIDDomanda()); // a dx ho le domande a cui devo rispondere

        // confronto risposte
        for(int i=0; i<EntityProva.NUM_DOMANDE; i++)
        {
            if (!(listaRisposte.get(i).equalsIgnoreCase(prova.getDomande().get(i).getRispostaCorretta())))
                numErrori++;
        }

        if(numErrori >= 5)
            prova.setEsito("n");
            else
                prova.setEsito("p");

        return numErrori;
    }

    public void creaProva(EntityProva prova, String cartaID) throws OperationsException
    {
        ProvaDAO provaDAO = new ProvaDAO();
        try{
            provaDAO.createProva(prova, cartaID);
        }
        catch(OperationsException e){
            System.out.println("Errore inserimento prova");
        }
    }
}





