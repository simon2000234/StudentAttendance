/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentattendance.BE;

import java.util.ArrayList;

/**
 *
 * @author Melchertsen
 */
public class Student
{
    private String name;
    private String username;
    private String password;
    private ArrayList<Attendance> attendance;
    private int id;

    public Student(String name, String username, String password, ArrayList<Attendance> attendance, int id)
    {
        this.name = name;
        this.username = username;
        this.password = password;
        this.attendance = attendance;
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

    public ArrayList<Attendance> getAttendance()
    {
        return attendance;
    }
}
