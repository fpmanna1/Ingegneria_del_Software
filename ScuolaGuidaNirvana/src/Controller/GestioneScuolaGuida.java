package Controller;

import Database.*;
import Entity.*;
import Exception.DBConnectionException;

import javax.management.OperationsException;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;

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

    public void createCliente(EntityCliente cliente) throws OperationsException
    {
        ClienteDAO clienteDAO = new ClienteDAO();
        try{
            clienteDAO.createCliente(cliente);
        }
        catch(OperationsException | DBConnectionException e){
            throw new OperationsException(e.getMessage());
        }
        System.out.println("Cliente inserito con successo");
    }

    public EntityCliente readCliente(String username, String password)
    {
        EntityCliente cliente = new EntityCliente();
        ClienteDAO clienteDAO = new ClienteDAO();

        try{
            cliente = clienteDAO.readCliente(username, password);
        }
        catch(OperationsException e){
            System.out.println(e.getMessage());
            return null;
        }
        catch (DBConnectionException e){
            e.printStackTrace();
        }
        return cliente;
    }

    public boolean inviaCredenziali(String eMailDestinatario, String testoEMail)
    {
        System.out.println(testoEMail);
        System.out.println("Invio credenziali a " + eMailDestinatario + ". . .");

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            System.out.println("Non è stato possibile inviare la e-mail");
            return false;
        }
        System.out.println("Invio e-mail è andato a buon fine");
        return true;
    }
    public void createIscrizione(EntityCliente cliente) throws OperationsException {
        IscrizioneDAO iscrizioneDAO = new IscrizioneDAO();

        try {
            iscrizioneDAO.createIscrizione(cliente);
        } catch (OperationsException | DBConnectionException e) {
            throw new OperationsException(e.toString());
        }
        System.out.println("Cliente iscritto con successo");
    }

    public void prenotazioneLezione(Date data, Time ora, String idIstruttore, EntityCliente cliente, String patenteLezione) throws OperationsException {
        LezioneGuidaDAO lezioneGuidaDAO = new LezioneGuidaDAO();

        try{
            lezioneGuidaDAO.createLezioneGuida(data, ora, idIstruttore, cliente, patenteLezione);
        }
        catch(OperationsException | DBConnectionException e){
            throw new OperationsException("Errore prenotazione lezione\n" + e.toString());

        }
        System.out.println("Prenotazione effettuata con successo");
    }

    public EntityIstruttore readIstruttore(String matricola) throws OperationsException {
        EntityIstruttore istruttore = new EntityIstruttore();
        IstruttoreDAO istruttoreDAO = new IstruttoreDAO();

        try {
            istruttore = istruttoreDAO.readIstruttore(matricola);
        } catch (OperationsException | DBConnectionException e) {
            throw new OperationsException(e.toString());
        }
        return istruttore;
    }

    public ArrayList<EntityIstruttore> readDisponibilita(String giornoSettimana, Time ora) throws OperationsException
    {
        DisponibilitaDAO disponibilitaDAO = new DisponibilitaDAO();
        ArrayList<EntityIstruttore> istruttori = new ArrayList<>();
        try {
            istruttori = disponibilitaDAO.readDisponibilita(giornoSettimana, ora);
        } catch (OperationsException | DBConnectionException e) {
            throw new OperationsException(e.getMessage());
        }
        return istruttori;
    }

    public EntityLezioneGuida verificaDisponibilitaLezione(Date data, Time ora, String idIstruttore) throws OperationsException {
        EntityLezioneGuida lezioneGuida = new EntityLezioneGuida();
        LezioneGuidaDAO lezioneGuidaDAO = new LezioneGuidaDAO();

        try {
            lezioneGuida = lezioneGuidaDAO.readLezioneGuida(data, ora, idIstruttore);
        } catch (OperationsException | DBConnectionException e) {
            throw new OperationsException(e.toString());
        }
        return lezioneGuida;
    }
    public void generaCredenziali(EntityCliente cliente)
    {
        String nome = cliente.getNome();
        String cognome = cliente.getCognome();
        int anno = cliente.getDataNascita().toLocalDate().getYear();

        String username = nome + anno;
        String password = cognome + new Random().nextInt(100, 999);

        /*if(nome.length() >= EntityCliente.FIXED_USERNAME_LEN)
            username.append(nome, 0, EntityCliente.FIXED_USERNAME_LEN);
        else
            username.append(nome);
        username.append(anno);

        if(cognome.length() > EntityCliente.FIXED_PASSWORD_LEN)
            password.append(cognome, 0, EntityCliente.FIXED_PASSWORD_LEN);
        else
            password.append(cognome);
        password.append(new Random().nextInt(100, 999));*/

        cliente.setUsername(username.toString());
        cliente.setPassword(password.toString());
    }


    /*
     * Metodi per la simulazione della prova
     * */
    public EntityProva simulazioneProva() throws OperationsException
    {
        EntityProva prova = new EntityProva();
        /* variabile che conterrà il numero di domande totali presenti nel database */
        int numDomandeTotali = 0;
        /* vettore che conterrà gli id delle domande che formeranno la prova  */
        ArrayList<Integer> idDomandeCasuali = new ArrayList<Integer>(EntityProva.NUM_DOMANDE);
        EntityDomanda domanda = new EntityDomanda();
        DomandaDAO domandaDAO = new DomandaDAO();

        /*
         * conto il numero di domande presenti attualmente nel db
         */
        try{
            numDomandeTotali = domandaDAO.countDomande();
            if(numDomandeTotali < EntityProva.NUM_DOMANDE && numDomandeTotali != 0)
                throw new OperationsException("Non sono presenti abbastanza domande nel DB");
        }
        catch(OperationsException | DBConnectionException dbex)
        {
            throw new OperationsException("Riscontrato problema interno all'applicazione");
        }
        try {
            /*
             * genera 40 numeri casuali, scelti tra 1 e il massimo numero di domande presenti
             * nel DB e restituisce un arraylist di tipo integer, che sono gli id delle domande messi casualmente
             */
            idDomandeCasuali = generaNumeriCasuali(numDomandeTotali);

        }
        catch(IllegalArgumentException e)
        {
            throw new OperationsException("Non sono presenti domande nel db...");
        }

        /* aggiunta domande casuali alla prova */
        try{
            for(int i=0; i<EntityProva.NUM_DOMANDE; i++) {
                /* prelevo una domanda dal db con indice pari al numero presente
                 * in posizione i-esima dell'array list idDomandeCasuali
                 */
                domanda = domandaDAO.prelevaDomanda(idDomandeCasuali.get(i));
                prova.getDomande().add(domanda);
            }
        }
        catch(OperationsException | DBConnectionException dbex)
        {
            throw new OperationsException("Riscontrato problema interno applicazione...");
        }

        return prova;
    }

    /*
     * genero 40 numeri casuali tra 0 e il numero di domande totali presenti nel db
     */

    private ArrayList<Integer> generaNumeriCasuali(int numMax) //throws IllegalArgumentException
    {
        Random rng = new Random();
        ArrayList<Integer> num_generati = new ArrayList<>(EntityProva.NUM_DOMANDE);
        int x = EntityProva.NUM_DOMANDE;   // quantità di numeri da generare
        int y = numMax;    // numero massimo tra l'insieme dei numeri tra cui generare

        Integer next;
        for (int i = 0; i < x; i++) {
            while (true) {
                next = rng.nextInt(y) + 1;
                if (!num_generati.contains(next)) {
                    num_generati.add(next);
                    break;
                }
            }
        }
        return num_generati;
    }

    public int calcolaPunteggio(ArrayList<String> listaRisposte, EntityProva prova)
    {
        int numErrori = 0;

        // confronto risposte
        for(int i=0; i<EntityProva.NUM_DOMANDE; i++)
        {
            // se la risposta data dall'utente non è uguale alla risposta corretta effettiva, incremento il numero di errori
            if (!(listaRisposte.get(i).equalsIgnoreCase(prova.getDomande().get(i).getRispostaCorretta())))
                numErrori++;
        }

        if(numErrori >= 5)
            prova.setEsito("n"); // prova non passata
        else
            prova.setEsito("p"); // prova passata

        return numErrori;
    }

    public int memorizzaProva(EntityProva prova, String cartaID) throws OperationsException
    {
        ProvaDAO provaDAO = new ProvaDAO();
        int idProva = 0;
        try{
            idProva = provaDAO.memorizzaProva(prova, cartaID);
        }
        catch(OperationsException e){
            throw new OperationsException("Errore inserimento prova");
        }
        catch(DBConnectionException dbex)
        {
            throw new OperationsException("Errore interno...");
        }
        return idProva;
    }

    public void memorizzaComposizioneProva(EntityProva prova, int idProva) throws OperationsException
    {
        ComposizioneDAO composizioneDAO = new ComposizioneDAO();
        try{
            for(int i=0; i<EntityProva.NUM_DOMANDE; i++) // data una certa prova con idProva, memorizza in composizione le domande di cui + composta
                composizioneDAO.memorizzaComposizioneProva(prova.getDomande().get(i).getIDDomanda(), idProva);
        }
        catch (OperationsException e){
            throw new OperationsException("Errore inserimento composizione");
        }
        catch(DBConnectionException ex)
        {
            throw new OperationsException("Errore interno...");
        }
    }
}





