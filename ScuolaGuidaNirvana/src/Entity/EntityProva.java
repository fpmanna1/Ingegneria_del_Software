package Entity;

import java.util.Date;

public class EntityProva
{
    public Date    data;
    public boolean esito;

    public EntityProva(Date data, boolean esito) {
        //TODO
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public boolean getEsito() {
        return esito;
    }

    public void setEsito(boolean esito) {
        this.esito = esito;
    }
}
