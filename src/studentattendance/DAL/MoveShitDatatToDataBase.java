/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentattendance.DAL;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import studentattendance.BE.Attendance;
import studentattendance.BE.Student;
import studentattendance.BE.Teacher;
import studentattendance.BE.TeacherAlert;

/**
 *
 * @author Melchertsen
 */
public class MoveShitDatatToDataBase
{
    private MockData md;
    private TeacherDAO td;

    public MoveShitDatatToDataBase(MockData md, TeacherDAO td)
    {
        this.md = md;
        this.td = td;
    }
    
    
    public void moveData() throws SQLException
    {
        Teacher john = md.createTracherJohnKeating();
        ArrayList<TeacherAlert> list = john.getTeacherAlerts();
        for (TeacherAlert teacherAlert : list)
        {
            td.createTeacherAlert(4, teacherAlert.getStudent().getId(), 24, 26);
            
        }
        
        
        
    }
    
    
}
