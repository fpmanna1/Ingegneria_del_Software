package Database;

import Entity.EntityIstruttore;

import javax.management.OperationsException;
import java.sql.*;
import java.util.ArrayList;

import Exception.DBConnectionException;

public class DisponibilitaDAO
{
    public ArrayList<EntityIstruttore> readDisponibilita(String giornoSettimana, Time ora) throws OperationsException, DBConnectionException
    {
        Connection connection = null;
        ArrayList<EntityIstruttore> istruttori = new ArrayList<>();
        try{
            connection = DBManager.getConnection();
            try{
                String query = "SELECT MATRICOLA, NOME, COGNOME, EMAIL, TELEFONO FROM ISTRUTTORI " +
                        "JOIN DISPONIBILITA ON ISTRUTTORI.MATRICOLA = DISPONIBILITA.ISTRUTTORE AND GIORNO = ? AND ORA = ?;";
                PreparedStatement statement = connection.prepareStatement(query);

                statement.setString(1, giornoSettimana);
                statement.setTime(2, ora);

                ResultSet result = statement.executeQuery();

                while(result.next())
                    istruttori.add(
                            new EntityIstruttore(
                                    result.getString(1), result.getString(2),
                                    result.getString(3), result.getString(4),
                                    result.getString(5)
                            )
                    );
            }
            catch(SQLException e){
                throw new OperationsException("Errore readDisponibilita() " + e.toString());
            }
            finally{
                DBManager.closeConnection();
            }
        }
        catch (SQLException e) {
            throw new DBConnectionException("Errore connesione database");
        }
        return istruttori;
    }
}
