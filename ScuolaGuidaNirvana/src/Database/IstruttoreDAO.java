package Database;

import Entity.EntityIstruttore;

import javax.management.OperationsException;
import java.sql.*;

import Exception.DBConnectionException;


public class IstruttoreDAO
{
    public EntityIstruttore readIstruttore(String matricola) throws OperationsException, DBConnectionException
    {
        EntityIstruttore istruttore = null;
        Connection connection = null;

        try{
            connection = DBManager.getConnection();
            try{
                String query = "SELECT * FROM ISTRUTTORI WHERE MATRICOLA = ?;";
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setString(1, matricola);

                ResultSet result = statement.executeQuery();

                if (result.next())  // MATRICOLA, NOME, COGNOME, EMAIL, TELEFONO
                    istruttore = new EntityIstruttore(
                            result.getString(1), result.getString(2),
                            result.getString(3), result.getString(4),
                            result.getString(5)
                    );
            }
            catch(SQLException e){
                throw new OperationsException("Errore readIstruttore " + e.toString());
            }
            finally{
                DBManager.closeConnection();
            }
        }
        catch(SQLException e){
            throw new DBConnectionException("Errore connessione al database");
        }
        return istruttore;
    }
}
