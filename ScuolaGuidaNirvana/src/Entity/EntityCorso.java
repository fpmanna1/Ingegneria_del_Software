package Entity;

import javax.management.OperationsException;

public class EntityCorso
{
    private String tipoPatente;
    public String getTipoPatente() {
        return tipoPatente;
    }
    public void setTipoPatente(String tipoPatente) throws OperationsException
    {
        if(!(tipoPatente.equalsIgnoreCase("A1") || tipoPatente.equalsIgnoreCase("A2")
                || tipoPatente.equalsIgnoreCase("AM") || tipoPatente.equalsIgnoreCase("B"))){
            throw new OperationsException("Tipo patente errato");
        }
        this.tipoPatente = tipoPatente;
    }
}
