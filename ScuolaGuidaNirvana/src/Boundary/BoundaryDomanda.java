package Boundary;

import java.io.File;

public class BoundaryDomanda
{
    public File figura;
    public String tema;
    public String formulazione;
    public String rispostaCorretta;


    public BoundaryDomanda(File figura, String tema, String formulazione, String rispostaCorretta) {
        //TODO
    }

    public File getFigura() {
        return figura;
    }

    public void setFigura(File figura) {
        this.figura = figura;
    }

    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    public String getFormulazione() {
        return formulazione;
    }

    public void setFormulazione(String formulazione) {
        this.formulazione = formulazione;
    }

    public String getRispostaCorretta() {
        return rispostaCorretta;
    }

    public void setRispostaCorretta(String rispostaCorretta) {
        this.rispostaCorretta = rispostaCorretta;
    }
}
