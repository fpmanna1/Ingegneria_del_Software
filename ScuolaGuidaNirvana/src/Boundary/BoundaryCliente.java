package Boundary;

import Controller.GestioneScuolaGuida;
import Entity.EntityIstruttore;
import Entity.EntityLezioneGuida;

import javax.management.OperationsException;
import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

        System.out.println(">Nome utente:");
        String username = input.nextLine();

        System.out.println(">Password:");
        String password = input.nextLine();


        controller.autenticazione(username, password);

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

        System.out.println("Inserisci matrica istruttore (Axxx)/(Bxxx)/(Cxxx)");
        String tmpMat = input.next();

        try{
            lezioneGuida = controller.prenotaLezione(data, ora, matIstruttore);
        }
        catch(OperationsException e){
            System.out.println("Lezione non disponibile");
        }

        System.out.println("Lezione disponibile.\nconfermare ? (y\\n)");
        String conferma = input.nextLine();

        if(!conferma.toLowerCase().equals("y")){
            System.out.println("Prenotazione annullata");
            return;
        }
        controller.creaLezione(lezioneGuida);
    }
    public void simulazioneProva()
    {
        //TODO
    }
}
