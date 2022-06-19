package Entity;

import javax.management.OperationsException;
import java.sql.Date;
import java.util.ArrayList;

public class EntityCliente
{
    private String nome;
    private String cognome;
    private Date dataNascita;
    private String numeroCarta;
    private String eMail;
    private String via;
    private String civico;
    private String cap;
    private ArrayList<EntityPatenteInPossesso> patenti;
    private String patenteDaConseguire;
    private String username;
    private String password;



    public EntityCliente()
    {
        numeroCarta         = "";
        nome                = "";
        cognome             = "";
        dataNascita         = new Date(0);
        eMail               = "";
        via                 = "";
        civico              = "";
        cap                 = "";
        patenti             = new ArrayList<EntityPatenteInPossesso>();
        patenteDaConseguire = "";
    }

    public EntityCliente(
            String numeroCarta,
            String nome,
            String cognome,
            Date dataNascita,
            String eMail,
            String via,
            String civico,
            String cap,
            ArrayList<EntityPatenteInPossesso> patenti,
            String patenteDaConseguire)
    {
        this.nome                = nome;
        this.cognome             = cognome;
        this.dataNascita         = dataNascita;
        this.numeroCarta         = numeroCarta;
        this.eMail               = eMail;
        this.via                 = via;
        this.civico              = civico;
        this.cap                 = cap;
        this.patenti             = new ArrayList<EntityPatenteInPossesso>(patenti);
        this.patenteDaConseguire = patenteDaConseguire;
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) throws OperationsException
    {
        if(nome.length() > 20)
            throw new OperationsException("Nome deve essere di massimo 20 lettere");
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) throws OperationsException
    {
        if(cognome.length() > 20)
            throw new OperationsException("Cognome deve essere di massimo 20 lettere");
        this.cognome = cognome;
    }

    public Date getDataNascita() {
        return dataNascita;
    }

    public void setDataNascita(Date dataNascita) {
        this.dataNascita = dataNascita;
    }

    public String getNumeroCarta(){
        return numeroCarta;
    }

    public void setNumeroCarta(String numeroCarta) throws OperationsException
    {
        if(numeroCarta.length() > 9)
            throw new OperationsException("Numero carta deve essere di 9 caratteri");
        this.numeroCarta = numeroCarta;
    }

    public String getVia() {
        return via;
    }

    public void setVia(String via) throws OperationsException
    {
        if(via.length() > 50)
            throw new OperationsException("La via deve essere di massimo 50 caratteri");
        this.via = via;
    }

    public String getCivico() {
        return civico;
    }

    public void setCivico(String civico) throws OperationsException
    {
        if(civico.length() > 4)
            throw new OperationsException("Il civico deve essere massimo 4 cifre");
        this.civico = civico;
    }

    public String getCap() {
        return cap;
    }

    public void setCap(String cap) throws OperationsException
    {
        if(cap.length() > 5)
            throw new OperationsException("Il cap deve essere di 5 cifre");
        this.cap = cap;
    }

    public ArrayList<EntityPatenteInPossesso> getPatenti(){
        return patenti;
    }

    public void setPatenti(ArrayList<EntityPatenteInPossesso> patenti) {
        this.patenti = new ArrayList<>(patenti);
    }

    public void setPatenti(EntityPatenteInPossesso patente) throws OperationsException
    {
        if(patente.getTipo().length() > 2)
            throw new OperationsException("Errore formato patente");
        if(!patenti.contains(patente))
            patenti.add(patente);
    }

    public String getPatenteDaConseguire() {
        return patenteDaConseguire;
    }

    public void setPatenteDaConseguire(String patenteDaConseguire) throws OperationsException
    {
        if(!(patenteDaConseguire.equalsIgnoreCase("A1") || patenteDaConseguire.equalsIgnoreCase("A2")
                || patenteDaConseguire.equalsIgnoreCase("AM") || patenteDaConseguire.equalsIgnoreCase("A")
                || patenteDaConseguire.equalsIgnoreCase("B"))){
            throw new OperationsException("Tipo patente errato");
        }
        this.patenteDaConseguire = patenteDaConseguire;
    }

    public String getEMail() {
        return eMail;
    }

    public void setEMail(String eMail) throws OperationsException
    {
        if(eMail.length() > 50)
            throw new OperationsException("La e-mail deve essere massimo 50 caratteri");
        else if(!eMail.contains("@"))
            throw new OperationsException("Formato e-mail non valido, manca carattere @");
        this.eMail = eMail;
    }

    public String getUsername() {
        return username;
    }

    /*
     *Non viene effettuato il controllo sullo username poichè viene autogenerato, rispettando i vincoli.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    /*
     *Non viene effettuato il controllo sullo username poichè viene autogenerato, rispettando i vincoli.
     */
    public void setPassword(String password) {
        this.password = password;
    }
}

