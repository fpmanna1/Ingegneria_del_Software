package Entity;

import java.sql.Date;

public class EntityCliente
{
    private String nome;
    private String cognome;
    private Date   dataNascita;
    private String numeroCarta;
    private String eMail;
    private String via;
    private String civico;
    private String cap;
    //private String patenteInPossesso;
    private String patenteDaConseguire;

    private String username;
    private String password;

    public EntityCliente(
            String nome,
            String cognome,
            Date   dataNascita,
            String eMail,
            String via,
            String civico,
            String cap,
            String patenteDaConseguire)
    {
        this.nome = nome;
        this.cognome = cognome;
        this.dataNascita = dataNascita;
        this.eMail = eMail;
        this.via = via;
        this.civico = civico;
        this.cap = cap;
        this.patenteDaConseguire = patenteDaConseguire;
    }

    public EntityCliente() {

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
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

    public void setNumeroCarta(String numeroCarta){
        this.numeroCarta = numeroCarta;
    }

    public String getVia() {
        return via;
    }

    public void setVia(String via) {
        this.via = via;
    }

    public String getCivico() {
        return civico;
    }

    public void setCivico(String civico) {
        this.civico = civico;
    }

    public String getCap() {
        return cap;
    }

    public void setCap(String cap) {
        this.cap = cap;
    }
   /* public String getPatenteInPossesso(){
        return patenteInPossesso;
    }
    public void setPatenteInPossesso(String patenteInPossesso) {
        this.patenteInPossesso = patenteInPossesso;
    }*/

    public String getPatenteDaConseguire() {
        return patenteDaConseguire;
    }

    public void setPatenteDaConseguire(String patenteDaConseguire) {
        this.patenteDaConseguire = patenteDaConseguire;
    }

    public void setEMail(String eMail) {
        this.eMail = eMail;
    }

    public String getEMail() {
        return eMail;
    }

}

