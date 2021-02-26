/*
 * 
 */
package connectionDB;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Fabiano
 */
public class ConnectionFactory
{
    private static final String URL = "jdbc:mysql://localhost:3306/crud";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    
    public ConnectionFactory() throws ClassNotFoundException
    {
        Class.forName(DRIVER);
    }
    
    // Responsavel por criar uma conexão
    public static Connection getConnection()
    {
        try 
        {
            // inicialização da conexão
            return DriverManager.getConnection(URL, USERNAME, PASSWORD); 
        } 
        catch (SQLException e) 
        {
            JOptionPane.showMessageDialog(null,"Error ocurred to connect the DB" );
            JOptionPane.showMessageDialog(null, e.getMessage());
            return null;
        }
    }
    
    
}
