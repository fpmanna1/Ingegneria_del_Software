import java.util.Date;
import java.time.*;



public class LezioneGuida
{
    public Date data;
    public Clock ora;
    public final Duration durata = Duration.ofHours(2);
    public String tipoPatente;


    public LezioneGuida()
    {}

    public LezioneGuida(Date data, Clock ora, String tipoPatente)
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

    public Duration getDurata() {
        return durata;
    }

    public String getTipoPatente() {
        return tipoPatente;
    }

    public void setTipoPatente(String tipoPatente) {
        this.tipoPatente = tipoPatente;
    }
}
