package Entity;

import java.util.ArrayList;
import java.util.Date;
import Entity.EntityDomanda;

import javax.swing.text.html.parser.Entity;

public class EntityProva
{
    private Date    data;
    private boolean esito;

    private ArrayList<EntityDomanda> domande;

    public EntityProva(){
        esito = false;
        data = new java.sql.Date(1,1,1);
        domande = new ArrayList<EntityDomanda>(40);
    }
    public EntityProva(Date data, boolean esito, ArrayList<EntityDomanda> domande) {
        this.data = data;
        this.esito = esito;
        this.domande =  new ArrayList<EntityDomanda>(40);
    }

    public ArrayList<EntityDomanda> getDomande(){ return this.domande; }

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
