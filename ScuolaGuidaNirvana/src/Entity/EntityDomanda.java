package Entity;


import java.util.ArrayList;

public class EntityDomanda
{
    private int IDDomanda;
    private String formulazione;
    private String rispostaCorretta;
    private String   figura;
    private String tema;

    public EntityDomanda(
            int IDDomanda,
            String figura,
            String tema,
            String formulazione,
            String rispostaCorretta)
    {
        this.IDDomanda = IDDomanda;
        this.figura = figura;
        this.tema = tema;
        this.formulazione = formulazione;
        this.rispostaCorretta = rispostaCorretta;
    }

    public EntityDomanda(){
        this.figura = "";
        this.tema = "";
        this.formulazione = "";
        this.rispostaCorretta = "";
        this.IDDomanda = 0;
    }

    public String getFormulazione() {
        return formulazione;
    }

    public void setFormulazione(String formulazione) {
        this.formulazione = formulazione;
    }

    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    public String getFigura() {
        return figura;
    }

    public void setFigura(String figura) {
        this.figura = figura;
    }

    public String getRispostaCorretta() {
        return rispostaCorretta;
    }

    public void setRispostaCorretta(String rispostaCorretta) {
        this.rispostaCorretta = rispostaCorretta;
    }

    public int getIDDomanda() {
        return IDDomanda;
    }

    void setIDDomanda(int IDDomanda) {
        this.IDDomanda = IDDomanda;
    }
}
