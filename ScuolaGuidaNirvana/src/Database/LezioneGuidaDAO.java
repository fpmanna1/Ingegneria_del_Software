package Database;

import Entity.EntityCliente;
import Entity.EntityLezioneGuida;

import javax.management.OperationsException;
import java.sql.*;

public class LezioneGuidaDAO
{
    public EntityLezioneGuida verificaDisponibilitaLezione(Date data, Time ora, String matIstruttore) throws OperationsException
    {
        EntityLezioneGuida lezioneGuida = null;
        Connection connection = null;

        try{
            connection = DBManager.getConnection();
            try{
                String query = "SELECT * FROM LEZIONIGUIDA WHERE DATA = ? AND ORA = ? AND ISTRUTTORE = ?;";
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setDate(1, data);
                statement.setTime(2, ora);
                statement.setString(3, matIstruttore);

                ResultSet result = statement.executeQuery();

                if(result.next())
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

    public void createLezione(EntityLezioneGuida lezioneGuida, String matIstruttore) throws OperationsException
    {
        try{
            Connection connection = DBManager.getConnection();
            String query = "INSERT INTO LEZIONIGUIDA VALUES (null, ?, ?, ?, ?, ?, ?);";

            try{
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setDate(1,   lezioneGuida.getData());
                statement.setTime(2,   lezioneGuida.getOra());
                statement.setInt(3, lezioneGuida.getDurata());
                statement.setString(4, lezioneGuida.getTipoPatente());
                statement.setString(5, matIstruttore);

                EntityCliente cliente = null;
                String query2 = "SELECT CARTAID FROM CLIENTI WHERE  = ?;";
                PreparedStatement statement1 = connection.prepareStatement(query2);
                statement1.setString(1, );


                statement.setString(6, );


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
