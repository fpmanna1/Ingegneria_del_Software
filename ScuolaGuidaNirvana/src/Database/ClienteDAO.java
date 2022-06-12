package Database;

import java.sql.*;
import java.util.Random;

import Entity.EntityCliente;

import javax.management.OperationsException;

public class ClienteDAO
{
    public EntityCliente readCliente(String eMail)//TODO: exception
    {
        EntityCliente cliente = null;

        try{
            Connection connection = DBManager.getConnection();
            String query = "SELECT * FROM CLIENTI WHERE EMAIL = ?;";
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

    public void autenticazione(String username, String password) throws OperationsException
    {
        Connection connection = null;
        EntityCliente cliente = null;

        try{
            connection = DBManager.getConnection();
            try {
                String query = "SELECT * FROM CLIENTI WHERE USERNAME = ? AND PASSWORD = ?";
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setString(1, username);
                statement.setString(2, password);
                ResultSet result = statement.executeQuery();

                if(result.next())
                    cliente = new EntityCliente(
                            result.getString(1), result.getString(2),
                            result.getDate(3),   result.getString(4),
                            result.getString(5), result.getString(6),
                            result.getString(7), result.getString(8)
                    );
            }
            catch(SQLException e){
                System.out.println("Utente non trovato");
                throw new OperationsException();
            }
            finally {
                connection.close();
            }
        }
        catch(SQLException e){
            System.out.print("Errore connessione al database");
        }

    }

    public void memorizzaCliente(EntityCliente cliente, String tipoPatente, Date dataConseguimento) throws OperationsException
    {
        Connection connection = null;
        try{
            connection = DBManager.getConnection();
            try{
                Random random = new Random();
                String query = "INSERT INTO CLIENTI (CARTAID, NOME, COGNOME, DATANASCITA, EMAIL, VIA, NUMEROCIVICO, CAP, USERNAME, PASSWORD) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setString(1,  cliente.getNumeroCarta());
                statement.setString(2,  cliente.getNome());
                statement.setString(3,  cliente.getCognome());
                statement.setDate(4,    cliente.getDataNascita());
                statement.setString(5,  cliente.getEMail());
                statement.setString(6,  cliente.getVia());
                statement.setString(7,  cliente.getCivico());
                statement.setString(8,  cliente.getCap());
                statement.setString(9, cliente.getNome() + cliente.getDataNascita().toLocalDate().getYear());
                statement.setString(10, cliente.getCognome() + random.nextInt(100, 1000));
                //statement.setString(9, cliente.getPatenteDaConseguire());

                statement.executeUpdate();

                query = "INSERT INTO PATENTIINPOSSESSO VALUES (?, ?, ?)";
                statement = connection.prepareStatement(query);

                statement.setString(1, tipoPatente.equals("nessuna") ? "None" : tipoPatente);
                statement.setString(2, cliente.getNumeroCarta());
                statement.setDate(3, dataConseguimento);

                statement.executeUpdate();
            }
            catch(SQLException e){
                System.out.println(e.toString());
                throw new OperationsException();
            }
            finally{
                connection.close();
            }
        }
        catch(SQLException e){
            System.out.println("Errore connesione:" + e.toString());
        }
    }


    /*
     * Debug tests only
     */
    public void selectClienti() throws OperationsException
    {
        Connection connection;
        try{
            //DriverManager.getConnection("jdbc:h2:tcp://~/GitHub/Java/canale_j_z-nirvana/ScuolaGuidaNirvana/lib/database","sa","");
            connection = DBManager.getConnection();
            try{
                String query = "SELECT * FROM CLIENTI;";
                Statement statement = connection.createStatement();
                statement.executeQuery(query);
            }
            catch(SQLException e){
                System.out.println("Errore query:" + e.toString());
            }
        }
        catch(SQLException e){
            System.out.println("Errore connesione database" + e.toString());
        }
    }


}
