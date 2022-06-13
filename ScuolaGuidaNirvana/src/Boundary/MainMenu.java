package Boundary;

import Controller.GestioneScuolaGuida;

import java.sql.Date;
import java.sql.Time;
import java.util.Scanner;

public class MainMenu
{
    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);
        String tipoUtente;
        boolean on = true;
        GestioneScuolaGuida controller = GestioneScuolaGuida.getInstance();
        BoundarySegretario segretario = new BoundarySegretario();
        BoundaryCliente cliente = new BoundaryCliente();

        do{
            System.out.print("1.Accesso Segretario\n2.Accesso Cliente\n> ");
            tipoUtente = input.nextLine();

            if(tipoUtente.equals("1") || tipoUtente.equals("2"))
                on = false;
            else
                System.out.println("Errore riprovare");
        }
        while(on);

        if(tipoUtente.equals("1")){
            segretario.registraCliente();
        }
        else{
            boolean datiValidi = false;
            do{
                System.out.print("Inserire username e password\n>");
                String username = input.nextLine();
                String password = input.nextLine();
                datiValidi = controller.autenticazione(username, password);
            }while(!datiValidi);

            cliente.prenotazioneLezione();
        }
    }
}
