package Database;

import Entity.EntityCliente;
import Entity.EntityProva;

import javax.management.OperationsException;
import java.sql.*;

public class ProvaDAO
{
    public void createProva(EntityProva prova, String cartaID) throws OperationsException
    {
        Connection connection = null;
        ResultSet result = null;

        long milliseconds = System.currentTimeMillis();
        Date date = new Date(milliseconds);

        try{
            connection = DBManager.getConnection();
            try { // IDPROVA, CLIENTE, DATA, ESITO
                String query = "INSERT INTO PROVE(CLIENTE, DATA, ESITO) VALUES (?, ?, ?);";
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setString(1, cartaID);
                statement.setDate(2, date);
                statement.setString(3, prova.getEsito());

                statement.executeUpdate();
                System.out.println("execute");

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
