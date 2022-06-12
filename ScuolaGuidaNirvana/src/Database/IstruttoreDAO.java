package Database;

import Entity.EntityIstruttore;

import javax.management.OperationsException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class IstruttoreDAO
{
    public EntityIstruttore readIstruttore(String matricola) throws OperationsException
    { //TODO: Exceptions
        EntityIstruttore istruttore = null;
        Connection connection = null;

        try{
            connection = DBManager.getConnection();
            try{
                // Preaparazione query
                String query = "SELECT * FROM ISTRUTTORI WHERE ID = ?;";
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setString(1, matricola);
                // Esegui query
                ResultSet result = statement.executeQuery();

                if (result.next())
                    istruttore = new EntityIstruttore(
                            result.getString(1), result.getString(2), result.getString(3),
                            result.getDate(4), result.getString(5), result.getString(6)
                    );
            }
            catch(SQLException e){
                System.out.println("Istruttore non trovato");
                throw new OperationsException();
            }
            finally{
                connection.close();
            }
        }
        catch(SQLException e){
            System.out.println("Errore connesione al database");
        }

        return istruttore;
    }
}
