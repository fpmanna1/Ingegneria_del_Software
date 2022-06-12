package Boundary;

import Controller.GestioneScuolaGuida;
import Entity.EntityCliente;

import java.sql.Date;
import java.util.Scanner;

public class BoundarySegretario
{
    public void registraCliente()
    {
        GestioneScuolaGuida controller = GestioneScuolaGuida.getInstance();
        EntityCliente cliente = new EntityCliente();

        /*
        String nome = null;
        String cognome = null;
        Date   dataNascita = null;
        String numeroCarta = null;
        String eMail = null;
        String via = null;
        String civico = null;
        String cap = null;
        String patenteDaConseguire = null;
        String patenteInPossesso = null;
        */



        System.out.println("Inserire dati utente");

        Scanner input = new Scanner(System.in);

        System.out.print(">Nome: ");
        cliente.setNome(input.nextLine());

        System.out.print(">Cognome: ");
        cliente.setCognome(input.nextLine());
        System.out.print(">Data nascita (aaaa-mm-gg): ");
        cliente.setDataNascita(Date.valueOf(input.nextLine()));
        System.out.print(">Numero Carta: ");
        cliente.setNumeroCarta(input.nextLine());
        System.out.print(">E-mail: ");
        cliente.setEMail(input.nextLine());
        System.out.print(">Via: ");
        cliente.setVia(input.nextLine());
        System.out.print(">Civico: ");
        cliente.setCivico(input.nextLine());
        System.out.print(">Cap: ");
        cliente.setCap(input.nextLine());
        System.out.print(">Patente in possesso (Nessuna se sprovvisto di patente): ");
        String patenteInPossesso = input.nextLine();
        Date conseguimento = new Date(1, 1, 1);
        if(!patenteInPossesso.equalsIgnoreCase("nessuna")){
            System.out.print(">Data conseguimento patente: ");
            conseguimento = Date.valueOf(input.nextLine());
        }
        System.out.print(">Patente da conseguire: ");
        cliente.setPatenteDaConseguire(input.nextLine());

        /*
        nome = input.nextLine();
        cognome = input.nextLine();
        dataNascita = Date.valueOf(input.nextLine());
        numeroCarta = input.nextLine();
        eMail = input.nextLine();
        via = input.nextLine();
        civico = input.nextLine();
        cap = input.nextLine();
        patenteInPossesso = input.nextLine();
        patenteDaConseguire = input.nextLine();*/

        //boolean datiValidi = controllaDatiInseriti(nome, cognome, dataNascita, numeroCarta, eMail, via, civico, cap, patenteInPossesso, patenteDaConseguire);
        boolean datiValidi = controllaDatiInseriti(cliente);

       // if(!datiValidi);
            // TODO

        controller.memorizzaCliente(cliente, patenteInPossesso, conseguimento);

        boolean inviate = controller.inviaCredenziali(cliente.getEMail());
        System.out.println("Le credenziali del cliente"
                            + cliente.getNome() + " "
                            + cliente.getCognome() + ""
                            + (inviate ? "inviate con successo" : "non sono state inviate"));

    }

    public void inserisciIstruttore()
    {}

    private boolean controllaDatiInseriti(
            String nome,
            String cognome,
            Date   dataNascita,
            String numeroCarta,
            String eMail,
            String via,
            String civico,
            String cap,
            String patenteInPossesso,
            String patenteDaConseguire
    )
    {
        //TODO
        return true;
    }

    private boolean controllaDatiInseriti(EntityCliente cliente)
    {

        return true;
    }


}
