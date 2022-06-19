package Entity;

import java.sql.Time;
import java.time.Clock;
import java.sql.Date;

public class EntityLezioneGuida
{
    private Date      data;
    private Time      ora;
    private final int durata = 60; //minuti

    /*Tipo patente da conseguire */
    private String    tipoPatente;

    public EntityLezioneGuida()
    {
        this.data = new Date(0);
        this.ora = new Time(0);
        this.tipoPatente = "";
    }

    public EntityLezioneGuida(Date data, Time ora, String tipoPatente)
    {
        this.data = data;
        this.ora = ora;
        this.tipoPatente = tipoPatente;
    }


    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Time getOra() {
        return ora;
    }

    public void setOra(Time ora) {
        this.ora = ora;
    }

    public int getDurata(){
        return durata;
    }

    public String getTipoPatente() {
        return tipoPatente;
    }

    public void setTipoPatente(String tipoPatente) {
        this.tipoPatente = tipoPatente;
    }
}
