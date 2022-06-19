package Entity;


import java.util.Date;

public class EntityIstruttore
{
    private String matricola;
    private String nome;
    private String cognome;
    private String eMail;
    private String telefono;
    private String oraLibera;
    private Date   giornoLibero;

    public EntityIstruttore()
    {
        matricola = "";
        nome = "";
        cognome = "";
        oraLibera = "";
        giornoLibero = new Date(0);
        eMail = "";
        telefono = "";
    }
    public EntityIstruttore(
            String matricola,
            String nome,
            String cognome,
            String eMail,
            String telefono)
    {
        this.matricola = matricola;
        this.nome = nome;
        this.cognome = cognome;
        this.eMail = eMail;
        this.telefono = telefono;
    }

    public String getMatricola(){
        return matricola;
    }

    public void setMatricola(String matricola){
        this.matricola = matricola;
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
