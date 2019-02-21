/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentattendance.BLL;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import studentattendance.BE.Attendance;
import studentattendance.BE.Person;
import studentattendance.BE.Student;
import studentattendance.DAL.PersonDAO;

/**
 *
 * @author Richart hansen
 */
public class AManager
{

    private PersonDAO personDAO;

    public AManager()
    {
        personDAO = new PersonDAO();
    }

    public ArrayList<Person> getAllPersons()
    {
        return personDAO.getAllPersons();
    }

    public Attendance addAtendance(Student student, boolean isPressent)
    {
        String timeStamp = new SimpleDateFormat("dd-MM-yyyy").format(Calendar.getInstance().getTime());
        Calendar c = Calendar.getInstance();
        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);

        String dayOfTheWeek;
        if (dayOfWeek == 2)
        {
            dayOfTheWeek = "Mandag";
        } else if (dayOfWeek == 3)
        {
            dayOfTheWeek = "Tirsdag";
        } else if (dayOfWeek == 4)
        {
            dayOfTheWeek = "Onsdag";
        } else if (dayOfWeek == 5)
        {
            dayOfTheWeek = "Torsdag";
        } else if (dayOfWeek == 6)
        {
            dayOfTheWeek = "Fredag";
        } else if (dayOfWeek == 7)
        {
            dayOfTheWeek = "Lørdag";
        } else if (dayOfWeek == 1)
        {
            dayOfTheWeek = "Søndag";
        } else
        {
            dayOfTheWeek = "Something went wrong";
        }
        Attendance attendance = new Attendance(isPressent, timeStamp, dayOfTheWeek);
        student.getAttendance().add(attendance);
        return attendance;
    }

    public ArrayList<Student> getAllStudents()
    {
        return personDAO.getAllStudents();
    }

}
