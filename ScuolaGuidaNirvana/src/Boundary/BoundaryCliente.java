package Boundary;

public class BoundaryCliente
{
    public void prenotazioneLezione()
    {
        GestioneScuolaGuida controller = GestioneScuolaGuida.getInstance();
        Date data = null;
        Time ora = null;
        String matIstruttore = null;

        Scanner input = new Scanner(System.in);
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
            controller.prenotaLezione(data, ora, matIstruttore);
        }
        catch(OperationsException e){
            System.out.println("Lezione non disponibile");
        }
    }


    public void simulazioneProva()
    {
        //TODO
    }
}
