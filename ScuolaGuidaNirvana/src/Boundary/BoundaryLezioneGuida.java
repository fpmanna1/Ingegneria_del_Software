package Boundary;

import java.time.Clock;
import java.time.Duration;
import java.util.Date;

public class BoundaryLezioneGuida
{
    public Date data;
    public Clock ora;
    //public final Duration durata = Duration.ofHours(2);
    public final int durata = 2;
    public String tipoPatente;


    public BoundaryLezioneGuida()
    {}

    public BoundaryLezioneGuida(Date data, Clock ora, String tipoPatente)
    {
        //TODO
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Clock getOra() {
        return ora;
    }

    public void setOra(Clock ora) {
        this.ora = ora;
    }

    /*public Duration getDurata() {
        return durata;
    }*/

    public int gerDurata(){
        return durata;
    }

    public String getTipoPatente() {
        return tipoPatente;
    }

    public void setTipoPatente(String tipoPatente) {
        this.tipoPatente = tipoPatente;
    }
}
