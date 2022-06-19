package Database;

import java.sql.*;
import java.util.ArrayList;

import Entity.EntityCliente;
import Entity.EntityPatenteInPossesso;
import Exception.DBConnectionException;

import javax.management.OperationsException;

public class ClienteDAO
{
    public EntityCliente readCliente(String username, String password) throws OperationsException, DBConnectionException
    {
        Connection connection = null;
        EntityCliente cliente = null;
        try{
            connection = DBManager.getConnection();
            try {
                String query = "SELECT * FROM CLIENTI WHERE USERNAME = ? AND PASSWORD = ?;";
                String query1 = "select corso from iscrizioni where cliente = ?;";
                String query2 = "select tipopatente, dataconseguimento from patentiinpossesso where cliente = ?;";

                PreparedStatement statement = connection.prepareStatement(query);
                statement.setString(1, username);
                statement.setString(2, password);

                ResultSet result = statement.executeQuery();

                if(result.next())
                    cliente = new EntityCliente(
                            result.getString(1), result.getString(2),
                            result.getString(3), result.getDate(4),
                            result.getString(5), result.getString(6),
                            result.getString(7), result.getString(8),
                            new ArrayList<EntityPatenteInPossesso>(),null
                    );

                if(cliente != null){
                    statement = connection.prepareStatement(query1);
                    statement.setString(1, cliente.getNumeroCarta());

                    result = statement.executeQuery();
                    if(result.next())
                        cliente.setPatenteDaConseguire(result.getString(1));
                }

                if(cliente != null){
                    ArrayList<EntityPatenteInPossesso> patenti = new ArrayList<>();
                    statement = connection.prepareStatement(query2);
                    statement.setString(1, cliente.getNumeroCarta());
                    result = statement.executeQuery();
                    while(result.next())
                        patenti.add(new EntityPatenteInPossesso(result.getString(1), result.getDate(2)));
                    cliente.setPatenti(patenti);
                }
            }
            catch(SQLException e){
                throw new OperationsException("Errore autenticazione " + e.toString());
            }
            finally{
                DBManager.closeConnection();
            }
        }
        catch(SQLException e){
            System.out.println("Errore connessione al database");
            throw new DBConnectionException("Errore connessione al database");
        }
        return cliente;
    }

    /*
     * Metodo per l'inserimento del cliente all'interno del database.
     * Le credenziali vengono generate automaticamente.
     */
    public void createCliente(EntityCliente cliente) throws OperationsException, DBConnectionException
    {
        Connection connection = null;
        try{
            connection = DBManager.getConnection();
            try{
                String query = "INSERT INTO CLIENTI (CARTAID, NOME, COGNOME, DATANASCITA, EMAIL, VIA, NUMEROCIVICO, CAP, USERNAME, PASSWORD) "
                        + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

                PreparedStatement statement = connection.prepareStatement(query);

                statement.setString(1, cliente.getNumeroCarta());
                statement.setString(2, cliente.getNome());
                statement.setString(3, cliente.getCognome());
                statement.setDate(4,   cliente.getDataNascita());
                statement.setString(5, cliente.getEMail());
                statement.setString(6, cliente.getVia());
                statement.setString(7, cliente.getCivico());
                statement.setString(8, cliente.getCap());
                statement.setString(9, cliente.getUsername());
                statement.setString(10, cliente.getPassword());

                statement.executeUpdate();

                /*
                 * Se l'entità cliente dovesse essere in possesso di una o più patenti, allora quest'ultime
                 * andranno inserite nella tabella PatentiInPossesso.
                 */
                if(cliente.getPatenti().size() > 0){
                    /*
                     * Per ogni patente presente nel vettore di patenti in possesso, vi si aggiorna la tabella
                     * inserendo la tupla composta da tipo patente, cliente (numero carta) e la data conseguimento della patente.
                     */
                    ArrayList<EntityPatenteInPossesso> patentiInPossesso = cliente.getPatenti();
                    for(EntityPatenteInPossesso patente : patentiInPossesso){
                        query = "INSERT INTO PATENTIINPOSSESSO (TIPOPATENTE, CLIENTE, DATACONSEGUIMENTO) VALUES (?, ?, ?);";
                        statement = connection.prepareStatement(query);

                        statement.setString(1, patente.getTipo());
                        statement.setString(2, cliente.getNumeroCarta());
                        statement.setDate(3, patente.getDataConseguimento());
                        statement.executeUpdate();
                    }
                }
            }
            catch(SQLException e){
                throw new OperationsException("Errore inserimento cliente");
            }
            finally{
                DBManager.closeConnection();
            }
        }
        catch(SQLException e){
            throw new DBConnectionException("Errore connessione");
        }
    }
}
