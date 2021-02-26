/*
 * 
 */
package person;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import connectionDB.ConnectionFactory;
import java.util.List;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Fabiano
 */
public class Person
{
    private int id;
    private String firstName;
    private String lastName;
    private String doc;

    public Person(String firstName, String lastName, String doc) 
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.doc = doc;
    }

    public Person() {
         //To change body of generated methods, choose Tools | Templates.
    }
    
    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public String getDoc()
    {
        return doc;
    }

    public void setDoc(String doc)
    {
        this.doc = doc;
    }
    
    public boolean save()
    {
        String query = "insert into peoples(name, last_name, doc) values(?,?,?)";
        Connection conn;
        PreparedStatement pstmt;
        boolean res = false;
        try
        {
            conn = ConnectionFactory.getConnection();
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, this.getFirstName());
            pstmt.setString(2, this.getLastName());
            pstmt.setString(3, this.getDoc());
            
            //ResultSet rs = pstmt.executeQuery();
            if (pstmt.execute())
            {
                res = true;
            }
            pstmt.close();
            conn.close();
            return res;
        } 
        catch (SQLException e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return false;
        }
        
    }
    
    public boolean update()
    {
        String query = "UPDATE peoples set name=?, last_name=?, doc=? where id=?";
        try
        {
            Connection conn = ConnectionFactory.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, this.getFirstName());
            pstmt.setString(2, this.getLastName());
            pstmt.setString(3, this.getDoc());
            pstmt.setInt(4, this.getId());
            
            boolean res = pstmt.execute();
            conn.close();
            pstmt.close();
            if (res) 
            {
                return true;
            }
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return false;
    }
    
    public boolean delete()
    {
        String query = "DELETE FROM peoples WHERE ID =?";
        try
        {
            Connection conn = ConnectionFactory.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, this.getId());
            
            boolean res = pstmt.execute();
            
            pstmt.close();
            conn.close();
            if (res) 
            {
                return true;
            }
        }
        catch (SQLException e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return false;
    }
    
    public List<Person> readAll() 
    {
        String query = "SELECT * FROM peoples";
        List<Person> person = new ArrayList<Person>();
        try
        {
            Connection conn = ConnectionFactory.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next())
            {
                
                this.setId(rs.getInt("id"));
                this.setFirstName(rs.getString("name"));
                this.setLastName(rs.getString("last_name"));
                this.setDoc(rs.getString("doc"));
                person.add(this);
            }
            rs.close();
            pstmt.close();
            conn.close();
            
        }
        catch(SQLException e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return person;
    }
    
    
    

    @Override
    public String toString() {
        return "Person{" + "firstName=" + firstName + ", lastName=" + lastName + ", region=" + doc + '}';
    }   
}
