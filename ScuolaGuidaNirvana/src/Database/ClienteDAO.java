package Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import Entity.EntityCliente;

import java.util.Date;

public class ClienteDAO
{
    public EntityCliente readCliente(String eMail)//TODO: exception
    {
        EntityCliente cliente = null;

        try{
            Connection connection = DBManager.getConnection();
            String query = "SELECT * FROM CLIENTE WHERE EMAIL = ?;";
            try{
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setString(1, eMail);
                ResultSet result = statement.executeQuery();

                if(result.next()) {
                    cliente = new EntityCliente(
                            result.getString(1), result.getString(2),
                            result.getDate  (3), eMail,
                            result.getString(5), result.getString(6),
                            result.getString(7), result.getString(8));
                }
            }catch(SQLException e){
                throw new RuntimeException(e); //TODO: create DAO exception class
            }
        }catch(SQLException e){
            throw new RuntimeException(e); //TODO: create exception class
        }
        return cliente;
    }
}
