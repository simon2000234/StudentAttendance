/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentattendance.DAL;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import studentattendance.BE.Person;
import studentattendance.BE.Teacher;

/**
 *
 * @author Melchertsen
 */
public class DBTester
{

    public static void main(String[] args) throws SQLException
    {
        
        TeacherDAO td = new TeacherDAO();
        StudentDAO sd = new StudentDAO();
        ArrayList<Teacher> list = td.getAllTeachers();
        for (Teacher teacher : list)
        {
            System.out.println(teacher.getName());
        }
    }
}
