package Controller;

import Entity.*;

public class GestioneScuolaGuida
{
    private static GestioneScuolaGuida instance = null;

    public static GestioneScuolaGuida getInstance()
    {
        if(instance == null)
            instance = new GestioneScuolaGuida();
        return instance;
    }

}
