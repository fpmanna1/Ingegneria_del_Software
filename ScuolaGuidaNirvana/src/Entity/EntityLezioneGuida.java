package Entity;

import java.time.Clock;
import java.util.Date;

public class EntityLezioneGuida
{
    //public Clock ora;
    //public final Duration durata = Duration.ofHours(2);
    private Date      data;
    private String    ora;
    private final int durata = 2;
    private String    tipoPatente;
    private String    oraPrenotazione; //prenotazione è quando il cliente ha effettivamente prenotato la lezione
    private Date      dataPrenotazione;

    public EntityLezioneGuida(Date data, String ora, String tipoPatente, String oraPrenotazione, Date dataPrenotazione)
    {
        //TODO
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getOra() {
        return ora;
    }

    public void setOra(String ora) {
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

    public String getOraPrenotazione() {
        return oraPrenotazione;
    }

    public void setOraPrenotazione(String oraPrenotazione) {
        this.oraPrenotazione = oraPrenotazione;
    }

    public Date getDataPrenotazione() {
        return dataPrenotazione;
    }

    public void setDataPrenotazione(Date dataPrenotazione) {
        this.dataPrenotazione = dataPrenotazione;
    }
}
