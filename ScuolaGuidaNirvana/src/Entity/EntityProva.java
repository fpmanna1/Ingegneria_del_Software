package Entity;

import java.util.ArrayList;
import java.sql.Date;

public class EntityProva
{
    public final static int NUM_DOMANDE = 40;
    private Date data;
    private String esito;

    private ArrayList<EntityDomanda> domande;

    public EntityProva(){
        esito = "";
        data = new Date(0);
        domande = new ArrayList<EntityDomanda>(NUM_DOMANDE);
    }

    public EntityProva(Date data, String esito, ArrayList<EntityDomanda> domande) {
        this.data = data;
        this.esito = esito;
        this.domande = new ArrayList<EntityDomanda>(domande);
    }

    public ArrayList<EntityDomanda> getDomande(){
        return this.domande;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getEsito() {
        return esito;
    }

    public void setEsito(String esito) {
        this.esito = esito;
    }


}
