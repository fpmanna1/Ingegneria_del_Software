package Database;

import Entity.EntityProva;

import javax.management.OperationsException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ProvaDAO
{
    public void createProva(EntityProva prova) throws OperationsException
    {
        try{
            Connection connection = DBManager.getConnection();
            String query = "INSERT INTO PROVE VALUES (null, ?, ?, ?);";

            try { // IDPROVA, CLIENTE, DATA, ESITO
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setString(1, "CA00000AA");
                statement.setDate(2, (Date)prova.getData());
                statement.setString(3, prova.getEsito());
                statement.executeUpdate();
            }
            catch(SQLException e){
                System.out.println("Inserimento lezione fallita");
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
