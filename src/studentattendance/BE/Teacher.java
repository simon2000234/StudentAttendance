/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentattendance.BE;

/**
 *
 * @author Melchertsen
 */
public class Teacher
{
    private String name;
    private String username;
    private String password;
    private int id;

    public Teacher(String name, String username, String password, int id)
    {
        this.name = name;
        this.username = username;
        this.password = password;
        this.id = id;
    }

    
    
    public int getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }

    public String getUsername()
    {
        return username;
    }

    public String getPassword()
    {
        return password;
    }
    
    
}
