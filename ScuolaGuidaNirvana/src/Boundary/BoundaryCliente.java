package Boundary;

import Controller.GestioneScuolaGuida;
import Database.IstruttoreDAO;
import Database.LezioneGuidaDAO;
import Entity.EntityDomanda;
import Entity.EntityIstruttore;
import Entity.EntityLezioneGuida;
import Entity.EntityProva;

import javax.management.OperationsException;
import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class BoundaryCliente
{
    public void prenotazioneLezione()
    {
        GestioneScuolaGuida controller = GestioneScuolaGuida.getInstance();
        Date data = null;
        Time ora = null;
        String matIstruttore = null;
        EntityLezioneGuida lezioneGuida = null;
        Scanner input = new Scanner(System.in);

        /*
        System.out.println(">Nome utente:");
        String username = input.nextLine();

        System.out.println(">Password:");
        String password = input.nextLine();


        controller.autenticazione(username, password);*/

        try{
            System.out.println("Inserisci data lezione (AAAA-MM-GG)");
            String tmpData = input.nextLine();
            data = Date.valueOf(tmpData);

            System.out.println("Inserisci ora lezione (HH:mm)");
            String tmpOrario = input.nextLine();
            ora = new Time(new SimpleDateFormat("HH:mm").parse(tmpOrario).getTime());
        }
        catch(IllegalArgumentException | ParseException e){
            System.out.println("Errore nell'acquisizione di data e ora");
        }

        System.out.println("Inserisci matricola istruttore (Axxx)/(Bxxx)/(Cxxx)");
        matIstruttore = input.next();

        lezioneGuida = controller.prenotaLezione(data, ora, matIstruttore);

        System.out.println("Lezione disponibile.\nconfermare ? (y\\n)");
        String conferma = input.nextLine();

        if(!conferma.equalsIgnoreCase("y")){
            System.out.println("Prenotazione annullata");
            return;
        }

        controller.creaLezione(lezioneGuida, matIstruttore);
    }
    public void simulazioneProva()
    {

        GestioneScuolaGuida controller = GestioneScuolaGuida.getInstance();
        EntityProva prova = new EntityProva();
       // prova.setDomande() = new ArrayList<EntityDomanda>(EntityProva.NUM_DOMANDE);
        ArrayList<String>  listaRisposte = new ArrayList<String>(EntityProva.NUM_DOMANDE);
        int numErrori;


        Scanner input = new Scanner(System.in);

        // inserimento numero di carta di identità dell'utente

        System.out.println("Inserire numero della carta di identita' : ");
        String cartaID = input.nextLine();

        // controllo se la carta di identità inserita dall'utente è presente nel DB
        try {
            prova = controller.simulaProva(cartaID);
        }
        catch(OperationsException e){
            System.out.println("Non è possibile iniziare la simulazione, numero carta identità non valido");
            return;
        }
        prova.getDomande().add(null);
        for(int i=0; i<EntityProva.NUM_DOMANDE; i++)
        {
            System.out.println("Domanda numero " + (i+1));
            System.out.println(prova.getDomande().get(i).getFormulazione() + "\n[V o F]");

            listaRisposte.add((input.nextLine()));

            System.out.println("\nPremere per continuare...");
            input.nextLine();
        }

        // devo confrontare il vettore delle risposte con le risposte delle domande a cui ho risposto

        numErrori = controller.calcolaPunteggio(listaRisposte, prova);


        // memorizzare la prova nel db
        try{
            controller.creaProva(prova, cartaID);
        }
        catch(OperationsException e)
        {
            System.out.println("prova");
        }

        // COMPOSIZIONE

        }
    }





/*
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
    } */