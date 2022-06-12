package Boundary;

import java.util.Scanner;

public class MainMenu
{
    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);
        String tipoUtente;
        boolean on = true;

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
            BoundarySegretario segretario = new BoundarySegretario();
            segretario.registraCliente();
        }
        else{
            BoundaryCliente cliente = new BoundaryCliente();
            //TODO
        }

    }
}
