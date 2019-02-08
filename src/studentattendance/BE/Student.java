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
public class Student extends Person
{
    private ArrayList<Attendance> attendance;


    public Student(String name, String username, String password, int id, ArrayList<Attendance> attendance)
    {
        super(name, username, password, id);
        this.attendance = attendance;
        
    }


    public ArrayList<Attendance> getAttendance()
    {
        return attendance;
    }
}
