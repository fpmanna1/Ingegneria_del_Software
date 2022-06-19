package Database;

import Entity.EntityCliente;
import Entity.EntityCorso;

import javax.management.OperationsException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import Exception.DBConnectionException;

public class IscrizioneDAO
{
    public void createIscrizione(EntityCliente cliente) throws OperationsException, DBConnectionException
    {
        Connection connection = null;
        try{
            connection = DBManager.getConnection();
            PreparedStatement statement = null;
            try{
                String query = "INSERT INTO ISCRIZIONI (CLIENTE, CORSO, DATAISCRIZIONE) VALUES (?, ?, ?);";
                statement = connection.prepareStatement(query);

                /*
                 * Calcolo data d'iscrizione del cliente al corso.
                 * La data coincide con il giorno stesso in cui avviene la memorizzazione del cliente nel database.
                 */
                Date dataIscrizione = new Date(System.currentTimeMillis());

                statement.setString(1, cliente.getNumeroCarta());
                statement.setString(2, cliente.getPatenteDaConseguire());
                statement.setDate(3, dataIscrizione);
                statement.executeUpdate();
            }
            catch(SQLException e){
                throw new OperationsException("Errore esecuzione query " + e.toString());
            }
            finally{
                DBManager.closeConnection();
            }
        }
        catch(SQLException e){
            throw new DBConnectionException("Errore connesione al database");
        }
    }
}
