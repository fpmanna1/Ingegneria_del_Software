package Boundary;

import Controller.GestioneScuolaGuida;
import Entity.EntityCliente;

import java.util.Scanner;

public class MainMenu
{
    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);
        String selezione;
        GestioneScuolaGuida controller = GestioneScuolaGuida.getInstance();
        BoundarySegretario segretario = new BoundarySegretario();
        BoundaryCliente bCliente = new BoundaryCliente();
        EntityCliente eCliente = new EntityCliente();

        boolean on = true;
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
            System.out.println("Registrazione cliente\n");
            segretario.registraCliente();
        }
        else{
            do{
                System.out.println("Inserire credenziali");
                System.out.print(">Username: ");
                String username = input.nextLine();
                System.out.print(">Password: ");
                String password = input.nextLine();
                eCliente = controller.readCliente(username, password);
            }while(eCliente == null);

            System.out.println("Benvenuto " + eCliente.getCognome() + " " + eCliente.getNome());
            bCliente.setCliente(eCliente);

            on = true;
            do{
                System.out.println("1.Prenota lezione\n2.Simula prova");
                selezione = input.nextLine();
                if(selezione.equals("1") || selezione.equals("2"))
                    on = false;
                else
                    System.out.println("Errore riprovare");
            }while(on);

            if(selezione.equals("1"))
                bCliente.prenotazioneLezione();
            else
                bCliente.simulazioneProva();
        }
    }
}