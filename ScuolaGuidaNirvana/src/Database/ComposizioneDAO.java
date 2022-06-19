package Database;

import Entity.EntityDomanda;
import Entity.EntityProva;
import Exception.DBConnectionException;

import javax.management.OperationsException;
import java.sql.*;
import java.util.ArrayList;

public class ComposizioneDAO {
    public void memorizzaComposizioneProva(int idDomanda, int idProva) throws OperationsException, DBConnectionException
    {
        Connection connection = null;
        try{
            connection = DBManager.getConnection();
            try {
                String query = "INSERT INTO COMPOSIZIONE VALUES (? , ?);";
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setInt(1, idProva);
                statement.setInt(2, idDomanda);
                statement.executeUpdate();
            }
            catch(SQLException e){
                throw new OperationsException("Errore inserimento composizione");
            }
            finally {
                DBManager.closeConnection();
            }
        }
        catch(SQLException e){
            throw new DBConnectionException("Errore connessione al db");
        }
    }
}
