package Boundary;

import Controller.GestioneScuolaGuida;
import Entity.EntityCliente;
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
    private EntityCliente cliente;

    public void setCliente(EntityCliente cliente) {
        this.cliente = cliente;
    }

    public void prenotazioneLezione()
    {
        GestioneScuolaGuida controller = GestioneScuolaGuida.getInstance();
        EntityLezioneGuida lezioneGuida = null;
        Scanner input = new Scanner(System.in);

        /*Viene chiesto al cliente data e ora per verificare successivamente se vi sono istruttori liberi.*/
        Date data = new Date(0);
        Time ora = null;
        try{
            System.out.println("Inserisci data lezione (aaaa-mm-gg)");
            data = Date.valueOf(input.nextLine());

            System.out.println("Inserisci ora lezione (hh:mm)");
            String tmpOrario = input.nextLine();
            ora = new Time(new SimpleDateFormat("hh:mm").parse(tmpOrario).getTime());
        }
        catch(IllegalArgumentException | ParseException e){
            System.out.println("Errore nell'acquisizione di data e ora");
            return;
        }

        boolean on = true;
        String patenteLezione = null;
        do {
            System.out.println("Inserire patente lezione rispettando il formato (AX)/(B): ");
            patenteLezione = input.nextLine();
            if (!(patenteLezione.equals("A1") || patenteLezione.equals("A2")
                    || patenteLezione.equals("AM") || patenteLezione.equals("A")
                    || patenteLezione.equals("B"))) {
                System.out.println("Formato patente non valido");
            }
            else
                on = false;
        }while(on);

        String giornoSettimana = data.toLocalDate().getDayOfWeek().toString();
        /*
         * Legge dal database gli istruttori disponibili in funzione della data e ora.
         * Se non vi sono istruttori disponibili la prenotazione fallisce.
         */
        ArrayList<EntityIstruttore> istruttori;
        try {
            istruttori = controller.readDisponibilita(giornoSettimana, ora);
        } catch (OperationsException e) {
            System.out.println(e.getMessage());
            istruttori = new ArrayList<>();
        }

        if(istruttori.isEmpty()){
            System.out.println("Nessun istruttore disponibile");
            return;
        }

        /*
         * Mostra a schermo gli istruttori disponibili in modo che il cliente possa scegliere.
         */
        System.out.println("Istruttori disponibili per ora " + ora.toLocalTime().getHour() + " del giorno " + data.toString());
        String scelta;
        String matricolaIstruttore = null;
        on = true;
        do{
            /*
             * Stampa a schermo gli istruttori disponibile per prenotare la lezione.
             * */
            for(EntityIstruttore istruttore : istruttori) {
                System.out.println("> " +
                        "\t" + istruttore.getCognome() +
                        "\t" + istruttore.getNome() +
                        "\t" + istruttore.getMatricola());
            }


            /*
             * Viene chiesto al cliente di scegliere l'istruttore tramite la matricola mostrata a schermo.
             * */
            System.out.print(">Inserire matricola istruttore rispettando il formato indicato (Axxx)/(Bxxx) oppure entrambe (Cxxx): ");
            scelta = input.nextLine();
            if(scelta.equalsIgnoreCase("A000") || scelta.equalsIgnoreCase("B000") || scelta.equalsIgnoreCase("C000")) {
                System.out.println("Matricola non valida");
                return;
            }

            /*
           on = false;
           do{
               System.out.print(">Inserire matricola istruttore rispettando il formato indicato (Axxx)/(Bxxx) oppure entrambe (Cxxx): ");
               scelta = input.nextLine();
               char tipoPatente = Character.toUpperCase(scelta.charAt(0));

               if(tipoPatente != 'A' && tipoPatente != 'B' && tipoPatente != 'C') {
                   System.out.println("Tipo patente non valido\nTipi validi: A, B e infine C per entrami i tipi.\nRiprovare.");
                   System.out.println();
                   continue;
               }

               //System.out.println("Scelta " + scelta);
               String codiceIstruttore = scelta.substring(1, 4);
               //System.out.println("Codice " + codiceIstruttore);

               try{
                    int val = Integer.parseInt(codiceIstruttore);
                    System.out.println(val);
                    if(val <= 0 || val > 999){
                        System.out.println("Formato matricola non valido, inserire numero compreso tra 001 e 999\nRiprovare");
                        System.out.println();
                        continue;
                    }
               }catch(NumberFormatException e){
                   System.out.println("Formato matricola non valido, inserire numero come codice istruttore\nRiprovare");
                   System.out.println();
                   continue;
               }

               on = true;
           }while(!on);*/

            /*Viene controllate se la matricola inserita dal cliente corrisponde con una di quella mostrate a schermo*/
            boolean valido = false;
            for(EntityIstruttore istruttore : istruttori) {
                if (scelta.equalsIgnoreCase(istruttore.getMatricola())) {
                    System.out.println(istruttore.getMatricola() + " " + scelta);
                    valido = true;
                    break;
                }
            }

            //System.out.println(valido);
            /*Se ma matricola risulta valida allora esce dal ciclo, altrimenti gli viene richiesto d'inserire.*/
            if(valido){
                matricolaIstruttore = scelta;
                on = false;
            }
            else
                System.out.println("Scegliere istruttore corretto");

        }while(on);

        /*
         * Verifica se la lezione guida, caratterizzata da data, ora e istruttore, è stata già prenotata.
         */
        try {
            lezioneGuida = controller.verificaDisponibilitaLezione(data, ora, matricolaIstruttore);
        } catch (OperationsException e) {
            System.out.println(e.getMessage());
            return;
        }
        if(lezioneGuida != null){
            System.out.println("La lezione di guida è già stata prenotata");
            return;
        }

        System.out.println("Lezione disponibile.\nconfermare ? (Y\\N)");
        String conferma = input.nextLine();
        if(conferma.equalsIgnoreCase("y")){
            try {
                controller.prenotazioneLezione(data, ora, matricolaIstruttore, this.cliente, patenteLezione);
            } catch (OperationsException e) {
                System.out.println(e.getMessage());
                return;
            }
            System.out.println("Prenotazione avvenuta con successo");
        }
        else
            System.out.println("Prenotazione annullata");
    }

    public void simulazioneProva()
    {

        GestioneScuolaGuida controller = GestioneScuolaGuida.getInstance();
        EntityProva prova = new EntityProva();

        /*
         * array che conterrà la lista delle risposte che l'utente darà alle domande che il sistema gli presenterà
         */
        ArrayList<String>  listaRisposte = new ArrayList<String>(EntityProva.NUM_DOMANDE);

        /*
         * numero di errori commessi dall'utente nello svolgimento della prova
         */
        int numErrori;
        Scanner input = new Scanner(System.in);

        try {
            prova = controller.simulazioneProva();
        }
        catch(OperationsException e)
        {
            System.out.println("Simulazione prova temporaneamente non disponibile, riprovare piu' tardi...");
            //e.printStackTrace();
            return;
        }
        prova.getDomande().add(null);
        boolean ok;
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
                /*
                 * se ho inserito v o f allora inserisco la risposta nella lista delle risposte
                 */
                if(ok)
                    listaRisposte.add(temp);
                if(i==EntityProva.NUM_DOMANDE-1) { // se sono arrivato all'ultima domanda
                    System.out.println("Premere per continuare...");
                    input.nextLine();
                }
            }while(!ok);
        }

        numErrori = controller.calcolaPunteggio(listaRisposte, prova);

        /* memorizza la prova nella tabella Prova e le domande di cui è composta nella tabella Composizione */
        try{
            int idProva = controller.memorizzaProva(prova, cliente.getNumeroCarta());
            controller.memorizzaComposizioneProva(prova, idProva);
        }
        catch(OperationsException e)
        {
            System.out.println("La prova non e' stata memorizzata...");
        }

        if(numErrori >= 5)
            System.out.println("Prova non superata, hai commesso " + numErrori + " errori");
        else
            System.out.println("Prova superata, hai commesso " + numErrori + " errori");
    }

}

