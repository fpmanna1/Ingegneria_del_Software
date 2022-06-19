package Entity;

import javax.management.OperationsException;
import java.sql.Date;

public class EntityPatenteInPossesso
{
    private String tipo;
    private Date dataConseguimento;

    public EntityPatenteInPossesso()
    {
        tipo = "";
        dataConseguimento = new Date(0);
    }

    public EntityPatenteInPossesso(String tipo, Date dataConseguimento)
    {
        this.tipo = tipo;
        this.dataConseguimento = dataConseguimento;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) throws OperationsException
    {
        if(!(tipo.equalsIgnoreCase("A1") || tipo.equalsIgnoreCase("A2")
                || tipo.equalsIgnoreCase("AM") || tipo.equalsIgnoreCase("A")
                || tipo.equalsIgnoreCase("B"))){
            throw new OperationsException("Tipo patente errato");
        }
        this.tipo = tipo;
    }

    public Date getDataConseguimento() {
        return dataConseguimento;
    }

    public void setDataConseguimento(Date dataConseguimento) {
        this.dataConseguimento = dataConseguimento;
    }

    public void setDataConseguimento(String dataConseguimento) throws OperationsException
    {
        Date tmpData = new Date(0);
        try {
            tmpData = Date.valueOf(dataConseguimento);
        }catch (IllegalArgumentException e){
            throw new OperationsException("Formato data non valido");
        }
        setDataConseguimento(tmpData);
    }
}
