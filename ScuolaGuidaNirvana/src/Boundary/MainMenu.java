package Boundary;

import Controller.GestioneScuolaGuida;
import Entity.EntityCliente;

import java.sql.Date;
import java.sql.Time;
import java.util.Scanner;

public class MainMenu
{
    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);
        String selezione;
        boolean on = true;
        GestioneScuolaGuida controller = GestioneScuolaGuida.getInstance();
        BoundarySegretario segretario = new BoundarySegretario();
        BoundaryCliente cliente = new BoundaryCliente();
        EntityCliente eCliente = new EntityCliente();

        do{
            System.out.print("1.Accesso Segretario\n2.Accesso Cliente\n> ");
            selezione = input.nextLine();

            if(selezione.equals("1") || selezione.equals("2"))
                on = false;
            else
                System.out.println("Errore riprovare");
        }
        while(on);

        if(selezione.equals("1")){
            segretario.registraCliente();
        }
        else{
            do{
                System.out.println("Inserire credenziali");
                System.out.println("Username\n>");
                String username = input.nextLine();
                System.out.println("Password\n>");
                String password = input.nextLine();
                eCliente = controller.autenticazione(username, password);
            }while(eCliente == null);

            on = true;
            do{
                System.out.println("1.Prenota lezione\n2.Simula prova");
                System.out.println();
                selezione = input.nextLine();

                if(selezione.equals("1") || selezione.equals("2"))
                    on = false;
                else
                    System.out.println("Errore riprovare");
            }while(on);

            if(selezione.equals("1"))
                cliente.prenotazioneLezione();
            else
                cliente.simulazioneProva();

        }
    }
}