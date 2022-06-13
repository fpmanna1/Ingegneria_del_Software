package Database;

import Entity.EntityDomanda;

import javax.management.OperationsException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DomandaDAO
{
    public EntityDomanda readDomanda(int IDDomanda) throws OperationsException
    {
        EntityDomanda domanda = null;
        Connection connection = null;

        try {
            connection = DBManager.getConnection();
            try { // iddomanda, figura, tema, formulazione, rispostacorretta
                String query = "SELECT * FROM DOMANDE WHERE IDDOMANDA = ?;";
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setInt(1, IDDomanda);
                ResultSet result = statement.executeQuery();

                if(result.next())
                    domanda = new EntityDomanda(
                            result.getInt(1), result.getString(2),
                            result.getString(3), result.getString(4),
                            result.getString(5)
                    );
            }
            catch(SQLException e){
                System.out.println("");
            }
            finally{
                connection.close();
            }
        }
        catch (SQLException e){
            System.out.println("Errore connessione al database");
        }
        return domanda;
    }

    public int countDomande() throws OperationsException
    {
        Connection connection = null;
        int numDomande = 0;
        try {
            connection = DBManager.getConnection();
            try { // iddomanda, figura, tema, formulazione, rispostacorretta
                String query = "SELECT COUNT(*)  FROM DOMANDE;";
                PreparedStatement statement = connection.prepareStatement(query);
                ResultSet result = statement.executeQuery();

                if(result.next())
                    numDomande = result.getInt(1);
            }
            catch(SQLException e){
                throw new OperationsException("Non sono presenti domande nel db");
            }
            finally{
                connection.close();
            }
        }
        catch (SQLException e){
            System.out.println("Errore connessione al database");
        }
        return numDomande;
    }

    public int esitoDomanda()
    {
        return 2;
    }


}
