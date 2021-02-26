/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlls;

import connectionDB.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import person.Person;

/**
 *
 * @author Fabiano
 */
public class PersonControl 
{
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
                Person p = new Person();
                p.setId(rs.getInt("id"));
                p.setFirstName(rs.getString("name"));
                p.setLastName(rs.getString("last_name"));
                p.setDoc(rs.getString("doc"));
                person.add(p);
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
    
    public boolean insert(String name, String lastName, String doc)
    {
        Person p = new Person(name, lastName, doc);
        return p.save();
    }
    
    public void update(int id, String name, String lastName, String doc)
    {
        Person p = new Person();
        p.setId(id);
        p.setFirstName(name);
        p.setLastName(lastName);
        p.setDoc(doc);
        p.update();
    }
    
    public void delete(int id)
    {
        Person p = new Person();
        p.setId(id);
        p.delete();
    }
    
}
