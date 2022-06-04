package Boundary;

import java.util.Date;

public class BoundaryCliente
{
    public String nome;
    public String cognome;
    public Date dataNascita;
    public String eMail;
    public String via;
    public String civico;
    public String cap;
    public String patenteDaConseguire;

    public BoundaryCliente()
    {}

    public BoundaryCliente(
            String nome,
            String cognome,
            Date dataNascita,
            String eMail,
            String via,
            String civico,
            String cap,
            String patenteDaConseguire)
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

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
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

}

