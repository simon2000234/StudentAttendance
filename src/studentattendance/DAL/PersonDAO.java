/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentattendance.DAL;

import java.sql.SQLException;
import java.util.ArrayList;
import studentattendance.BE.Person;
import studentattendance.BE.Student;
import studentattendance.BE.Teacher;

/**
 *
 * @author Melchertsen
 */
public class PersonDAO
{

    private StudentDAO sd;
    private TeacherDAO td;

    public PersonDAO()
    {
        sd = new StudentDAO();
        td = new TeacherDAO();
    }
    
    public ArrayList<Person> getAllPersons() throws SQLException
    {
        
        ArrayList<Person> persons = new ArrayList<>();
        ArrayList<Student> students = sd.getAllStudents();
        for (Student student : students)
        {
            persons.add(student);
        }
        ArrayList<Teacher> teachers = td.getAllTeachers();
        for (Teacher teacher : teachers)
        {
            persons.add(teacher);
        }

        return persons;
    }
}
