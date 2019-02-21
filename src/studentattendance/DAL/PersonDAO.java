/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentattendance.DAL;

import java.util.ArrayList;
import studentattendance.BE.Person;
import studentattendance.BE.Student;

/**
 *
 * @author Melchertsen
 */
public class PersonDAO
{

    public ArrayList<Person> getAllPersons()
    {
        MockData md = new MockData();
        ArrayList<Person> persons = new ArrayList<>();
        persons.add(md.createStudentAlex());
        persons.add(md.createStudentIna());
        persons.add(md.createStudentJake());
        persons.add(md.createStudentJohn());
        persons.add(md.createStudentKarl());
        persons.add(md.createTracherJohnKeating());

        return persons;
    }

    public ArrayList<Student> getAllStudents()
    {
        MockData md = new MockData();
        ArrayList<Student> students = new ArrayList<>();
        students.add(md.createStudentAlex());
        students.add(md.createStudentIna());
        students.add(md.createStudentJake());
        students.add(md.createStudentJohn());
        students.add(md.createStudentKarl());
        
        return students;
    }

}
