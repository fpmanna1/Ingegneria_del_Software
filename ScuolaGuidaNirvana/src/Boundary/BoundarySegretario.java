package Boundary;

import Controller.GestioneScuolaGuida;
import Entity.EntityCliente;
import Entity.EntityCorso;
import Entity.EntityPatenteInPossesso;

import javax.management.OperationsException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Scanner;

public class BoundarySegretario
{
    public void registraCliente()
    {
        GestioneScuolaGuida controller = GestioneScuolaGuida.getInstance();
        EntityCliente cliente = new EntityCliente();
        EntityCorso corso = new EntityCorso();

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

        boolean on = true;
        do{
            try{
                controllaDatiInseriti(cliente);
                on = false;
            }catch(OperationsException e){
                System.out.println(e.getMessage());
                //return;
            }
        }while(on);


        controller.generaCredenziali(cliente);
        try {
            controller.createCliente(cliente);
            controller.createIscrizione(cliente);
        } catch (OperationsException e) {
            System.out.println(e.getMessage());
            return;
        }


        String testoEMail = "Gentile " + cliente.getCognome() + " "  + cliente.getNome() +
                "\nfra le credenziali sono:\n" +
                "Username: " + cliente.getUsername() +
                "Password: " + cliente.getPassword();

        boolean inviate = controller.inviaCredenziali(cliente.getEMail(), testoEMail);
        System.out.println("Le credenziali del cliente "
                + cliente.getNome() + " "
                + cliente.getCognome() + " "
                + (inviate ? "inviate con successo" : "non sono state inviate"));
    }

    private void controllaDatiInseriti(EntityCliente cliente) throws OperationsException
    {
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

        System.out.println("Il cliente ha gia' qualche patente?");
        String temp = input.nextLine();
        ArrayList<EntityPatenteInPossesso> patenti = new ArrayList<>();
        if(temp.equalsIgnoreCase("s")) {
            System.out.println(">Inserire la/le patente/i in possesso");
            EntityPatenteInPossesso tmpPatenteInPossesso;
            do {
                tmpPatenteInPossesso = new EntityPatenteInPossesso();

                System.out.print(">Tipo patente: ");
                //String tipo = input.nextLine();
                tmpPatenteInPossesso.setTipo(input.nextLine());

                //cliente.setPatenteInPossesso();

                System.out.print(">Data conseguimento (aaaa-mm-gg): ");
                //String dataConseguimento = input.nextLine();
                tmpPatenteInPossesso.setDataConseguimento(input.nextLine());

                //tmpPatenteInPossesso = new EntityPatenteInPossesso(tipo, tmpData);

                patenti.add(tmpPatenteInPossesso);

                System.out.print("Continuare ? (Y\\N)");
            } while (input.nextLine().equalsIgnoreCase("y"));


        /* For debug tests only
        for(EntityPatenteInPossesso p : patenti){
            System.out.println(p.getTipo());
        }*/

            cliente.setPatenti(patenti);
        }
        System.out.print(">Patente da conseguire (A)(A1)(A2)(AM)(B): ");
        String patenteDaConseguire = input.nextLine();

        //Controllo nel caso viene inserita una patente da conseguire che il cliente abbia già in possesso, per rispettare i vincoli di prova.
        for(EntityPatenteInPossesso p : patenti) {
            String patenteInPossesso = p.getTipo();
            if(patenteInPossesso.equalsIgnoreCase(patenteDaConseguire)){
                throw new OperationsException("La patente " + patenteDaConseguire + " è già in possesso");
            }
            if(patenteInPossesso.equalsIgnoreCase("A")){
                if(!patenteDaConseguire.equalsIgnoreCase("B")){
                    throw new OperationsException("Se si ha in possesso patente " + patenteInPossesso + " non è possibile conseguire " + patenteDaConseguire);
                }
            }
            else if(patenteInPossesso.equalsIgnoreCase("A1")){
                if(!patenteDaConseguire.equalsIgnoreCase("A")
                        && !patenteDaConseguire.equalsIgnoreCase("A2")){ //TODO aggiungere B
                    throw new OperationsException("Se si ha in possesso patente " + patenteInPossesso + " non è possibile conseguire " + patenteDaConseguire);
                }
            }
            else if(patenteInPossesso.equalsIgnoreCase("A2")){
                if(!patenteDaConseguire.equalsIgnoreCase("A")
                        && !patenteDaConseguire.equalsIgnoreCase("B")){
                    throw new OperationsException("Se si ha in possesso patente " + patenteInPossesso + " non è possibile conseguire " + patenteDaConseguire);
                }
            }
            else if(patenteInPossesso.equalsIgnoreCase("B")){
                if(!patenteDaConseguire.equalsIgnoreCase("A")
                        && !patenteDaConseguire.equalsIgnoreCase("A1")
                        && !patenteDaConseguire.equalsIgnoreCase("A2")){
                    throw new OperationsException("Se si ha in possesso patente " + patenteInPossesso + " non è possibile conseguire " + patenteDaConseguire);
                }
            }
        }

        cliente.setPatenteDaConseguire(patenteDaConseguire);
    }
}
