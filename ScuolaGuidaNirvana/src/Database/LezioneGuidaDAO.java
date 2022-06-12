package Database;

import Entity.EntityIstruttore;
import Entity.EntityLezioneGuida;

import javax.management.OperationsException;
import java.sql.*;
import java.text.SimpleDateFormat;

public class LezioneGuidaDAO
{
    public EntityLezioneGuida verificaDisponibilitaLezione(Date data, Time ora, String matIstruttore) throws OperationsException
    {   //TODO: Exceptions
        /*
            TODO: Controllo input cliente (data e ora) se coincidono con giornoLibera e oraLibera rispettivamente
         */
        EntityLezioneGuida lezioneGuida = null;
        Connection connection = null;

        try {
            connection = DBManager.getConnection();
            try {
                String query = "SELECT * FROM LEZIONIGUIDA WHERE DATA = ? AND ORA = ? AND ISTRUTTORE = ?;";
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setDate(1, data);
                statement.setTime(2, ora);
                statement.setString(3, matIstruttore);

                ResultSet result = statement.executeQuery();

                if (result.next())
                    lezioneGuida = new EntityLezioneGuida(
                            result.getDate(1),
                            new Time(result.getTime(2).getTime()).toString(),
                            result.getString(3),
                            result.getString(4),
                            result.getDate(5)
                    );
            }
            catch(SQLException e){
                System.out.println("Lezione guida non esistente");
                throw new OperationsException();
            }
            finally{
                connection.close();
            }
        }
        catch(SQLException e){
            System.out.println("Errore connesione database");
        }
        return lezioneGuida;
    }

    public void createLezione(EntityLezioneGuida lezioneGuida) throws OperationsException
    {
        try{
            Connection connection = DBManager.getConnection();
            String query = "INSERT INTO LEZIONIGUIDA VALUES (? ? ? ? ?);";

            try {
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setDate(1,   lezioneGuida.getData());
                statement.setTime(2,   lezioneGuida.getOra());
                statement.setString(3, lezioneGuida.getTipoPatente());
                statement.setString(4, lezioneGuida.getOraPrenotazione());
                statement.setDate(5,   lezioneGuida.getDataPrenotazione());

                statement.executeUpdate();
            }
            catch(SQLException e){
                System.out.println("Errore inserimento nel database");
                throw new OperationsException();
            }
            finally {
                DBManager.closeConnection();
            }
        }
        catch(SQLException e){
            System.out.println("Errore connesione database");
        }
    }
}
