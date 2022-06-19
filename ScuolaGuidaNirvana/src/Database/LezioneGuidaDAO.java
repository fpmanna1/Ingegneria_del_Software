package Database;

import Entity.EntityCliente;
import Entity.EntityLezioneGuida;

import javax.management.OperationsException;
import java.sql.*;

import Exception.DBConnectionException;

public class LezioneGuidaDAO
{
    public EntityLezioneGuida readLezioneGuida(Date data, Time ora, String idIstruttore) throws OperationsException, DBConnectionException
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
                statement.setString(3, idIstruttore);

                ResultSet result = statement.executeQuery();

                if(result.next())
                    lezioneGuida = new EntityLezioneGuida(
                            result.getDate(2),
                            result.getTime(3),
                            result.getString(5)
                    );
            }
            catch(SQLException e){
                throw new OperationsException("Errore in verificaDisponibilitaLezione():\n" + e.toString());
            }
            finally{
                DBManager.closeConnection();
            }
        }
        catch(SQLException e){
            throw new DBConnectionException("Errore connessione database");
        }
        return lezioneGuida;
    }

    public void createLezioneGuida(Date data, Time ora, String idIstruttore, EntityCliente cliente, String patenteLezione) throws OperationsException, DBConnectionException
    {
        try{
            Connection connection = DBManager.getConnection();
            try{
                String query = "INSERT INTO LEZIONIGUIDA (DATA, ORA, DURATA, TIPOPATENTE, ISTRUTTORE, CLIENTE) " +
                        "VALUES (?, ?, ?, ?, ?, ?);";

                PreparedStatement statement = connection.prepareStatement(query);
                System.out.println(cliente.getPatenteDaConseguire());
                statement.setDate(1, data);
                statement.setTime(2, ora);
                statement.setInt(3, 60);
                statement.setString(4, patenteLezione);
                statement.setString(5, idIstruttore);
                statement.setString(6, cliente.getNumeroCarta());

                statement.executeUpdate();
            }
            catch(SQLException e){
                throw new OperationsException("Errore in createLezioneGuida()\n" + e.toString());
            }
            finally {
                DBManager.closeConnection();
            }
        }
        catch(SQLException e){
            throw new OperationsException("Errore connessione database");
        }
    }
}
