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
public class Teacher extends Person
{

    
    private ArrayList<TeacherAlert> teacherAlerts;
    
    public Teacher(String name, String username, String password, int id)
    {
        super(name, username, password, id);
    }

    public ArrayList<TeacherAlert> getTeacherAlerts()
    {
        return teacherAlerts;
    }

    public void setTeacherAlerts(ArrayList<TeacherAlert> teacherAlerts)
    {
        this.teacherAlerts = teacherAlerts;
    }
    
    
    
    
}
