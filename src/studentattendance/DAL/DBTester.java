/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentattendance.DAL;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import studentattendance.BE.Teacher;

/**
 *
 * @author Melchertsen
 */
public class DBTester
{
    
    public static void main(String[] args) throws SQLException
    {
        TeacherDAO tDAO = new TeacherDAO();
        List<Teacher> t = tDAO.getAllTeachers();
        for (Teacher teacher : t)
        {
            System.out.println(teacher.getId());
            System.out.println(teacher.getName());
        }
    }
}
