package Controller;

import Entity.*;

public class GestioneScuolaGuida
{
    private static GestioneScuolaGuida istance = null;

    public static GestioneScuolaGuida getIstance()
    {
        if(istance == null)
            istance = new GestioneScuolaGuida();
        return istance;
    }

}
