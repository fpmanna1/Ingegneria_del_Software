package Entity;

import java.util.ArrayList;
import java.sql.Date;
import Entity.EntityDomanda;

import javax.swing.text.html.parser.Entity;

public class EntityProva
{
    private Date    data;
    private String esito;

    private String cliente;

    private ArrayList<EntityDomanda> domande;

    public EntityProva(){
        esito = "";
        data = new java.sql.Date(1,1,1);
        domande = new ArrayList<EntityDomanda>(40);
    }
    public EntityProva(Date data, String esito, ArrayList<EntityDomanda> domande) {
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

    public String getEsito() { return esito; }

    public void setEsito(String esito) {
        this.esito = esito;
    }

    public String getCliente(){ return cliente; }

    public void setCliente(String cliente) { this.cliente = cliente;}


}
