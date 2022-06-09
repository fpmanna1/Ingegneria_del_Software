package Database;

import Entity.EntityIstruttore;
import Entity.EntityLezioneGuida;

import java.sql.*;
import java.text.SimpleDateFormat;

public class LezioneGuidaDAO
{
    public EntityLezioneGuida readLezioneGuida(Date data, Time ora) throws SQLException
    {   //TODO: Exceptions
        /*
            TODO: Controllo input cliente (data e ora) se coincidono con giornoLibera e oraLibera rispettivamente
         */
        EntityLezioneGuida lezioneGuida = null;
        Connection connection = DBManager.getConnection();
        String query = "SELECT * FROM LEZIONEGUIDA WHERE DATA = ? AND ORA = ?;";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setDate(1, data);
        statement.setTime(2, ora);
        ResultSet result = statement.executeQuery();

        if(result.next())
            lezioneGuida = new EntityLezioneGuida(
                    result.getDate(1), new String(new Time(result.getTime(2).getTime()).toString()), result.getString(3),
                    result.getString(4), result.getDate(5)
            );

        return lezioneGuida;
    }
}
