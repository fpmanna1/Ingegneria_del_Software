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
        boolean ok = true;
        String temp;
        for(int i=0; i<EntityProva.NUM_DOMANDE; i++)
        {
            do {
                ok = true;
                System.out.println("Domanda numero " + (i + 1));
                System.out.println(prova.getDomande().get(i).getFormulazione() + "\n[V o F]");
                temp = input.nextLine();
                if(!(temp.equalsIgnoreCase("v") || temp.equalsIgnoreCase("f")))
                {
                    System.out.println("Devi inserire v o f!!");
                    ok = false;
                }
                if(ok)
                    listaRisposte.add(temp);
                System.out.println("Premere per continuare...");
                input.nextLine();
            }while(!ok);
        }
        System.out.println(listaRisposte);

        // devo confrontare il vettore delle risposte con le risposte delle domande a cui ho risposto

        numErrori = controller.calcolaPunteggio(listaRisposte, prova);


        // memorizzare la prova nel db con le rispettive domande in composizione
        try{
            controller.creaProva(prova, cartaID);
        }
        catch(OperationsException e)
        {
            System.out.println("Prova non memorizzata nel db");
        }

        // COMPOSIZIONE 2
        // gestire caso v o f quando l'utente fa la prova  1 fatto

        // inserire patenti in possesso in classe cliente (vedere eventuale contenimento)  4
        // gestione eccezioni e implementazione classi eccezione  5

        // vedere se chiedere cartà di identità all'inizio da simulazione prova  3

        }
    }
