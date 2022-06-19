package Database;

import Entity.EntityProva;
import Exception.DBConnectionException;

import javax.management.OperationsException;
import java.sql.*;

public class ProvaDAO
{
    public int memorizzaProva(EntityProva prova, String cartaID) throws OperationsException, DBConnectionException
    {
        Connection connection = null;
        ResultSet result = null;
        int idProva = 0;
        /*
         * ottengo la data odierna
         */
        Date date = new Date(System.currentTimeMillis());

        try{
            connection = DBManager.getConnection();
            try {
                String query = "INSERT INTO PROVE(CLIENTE, DATA, ESITO) VALUES (?, ?, ?);";
                PreparedStatement statement = connection.prepareStatement(query);

                statement.setString(1, cartaID);
                statement.setDate(2, date);
                statement.setString(3, prova.getEsito());
                statement.executeUpdate();

                String query2 = "SELECT MAX(IDPROVA) FROM PROVE;";
                PreparedStatement statement2 = connection.prepareStatement(query2);
                result = statement2.executeQuery();
                if(result.next()) {
                    /* mette l'id della prova appena memorizzata nella variable idProva */
                    idProva = result.getInt(1);
                }
            }
            catch(SQLException e){
                throw new OperationsException("Errore inserimento prova");
            }
            finally {
                DBManager.closeConnection();
            }
        }
        catch(SQLException e){
            throw new DBConnectionException("Errore connessione al db");
        }
        return idProva;
    }
}
