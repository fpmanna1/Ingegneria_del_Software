package Entity;

import java.sql.Date;

public class EntityIscrizione
{
    private Date data;

    public EntityIscrizione()
    {
        data = new Date(0);
    }
    private Date getData() {
        return data;
    }
    public void setData(Date data){
        this.data = data;
    }
}
