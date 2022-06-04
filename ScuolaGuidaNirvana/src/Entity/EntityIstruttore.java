package Entity;


import java.util.Date;

public class EntityIstruttore
{
    public String nome;
    public String cognome;
    public String oraLibera;
    public Date   giornoLibero;
    public String eMail;
    public String telefono;

    public EntityIstruttore()
    {}

    public EntityIstruttore(String nome, String cognome, String oraLibera, Date giornoLiberao, String eMail, String telefono)
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

    public String getOraLibera() {
        return oraLibera;
    }

    public void setOraLibera(String oraLibera) {
        this.oraLibera = oraLibera;
    }

    public Date getGiornoLibero() {
        return giornoLibero;
    }

    public void setGiornoLibero(Date giornoLibera) {
        this.giornoLibero = giornoLibera;
    }

    public String getEMail() {
        return eMail;
    }

    public void setEMail(String eMail) {
        this.eMail = eMail;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
