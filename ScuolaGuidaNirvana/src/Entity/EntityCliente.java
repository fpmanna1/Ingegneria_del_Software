package Entity;

import java.util.Date;

public class EntityCliente
{
    public String nome;
    public String cognome;
    public Date   dataNascita;
    public String via;
    public String civico;
    public String cap;
    public String patenteDaConseguire;
    public String eMail;

    public EntityCliente()
    {}

    public EntityCliente(
            String  nome,
            String  cognome,
            Date    dataNascita,
            String  eMail,
            String  via,
            String  civico,
            String  cap,
            String  patenteDaConseguire)
    {
        //TODO
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

