package Entity;

import java.io.File;

public class EntityDomanda
{
    public String formulazione;
    public String tema;
    public File   figura;
    public String rispostaCorretta;

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

    public File getFigura() {
        return figura;
    }

    public void setFigura(File figura) {
        this.figura = figura;
    }

    public String getRispostaCorretta() {
        return rispostaCorretta;
    }

    public void setRispostaCorretta(String rispostaCorretta) {
        this.rispostaCorretta = rispostaCorretta;
    }
}
