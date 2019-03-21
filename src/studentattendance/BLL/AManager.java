/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentattendance.BLL;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import studentattendance.BE.Attendance;
import studentattendance.BE.Person;
import studentattendance.BE.Student;
import studentattendance.BE.TeacherAlert;
import studentattendance.DAL.PersonDAO;
import studentattendance.DAL.StudentDAO;
import studentattendance.DAL.TeacherDAO;

/**
 *
 * @author Richart hansen
 */
public class AManager
{

    private PersonDAO personDAO;
    private StudentDAO studentDAO;
    private TeacherDAO teacherDAO;

    public AManager()
    {
        personDAO = new PersonDAO();
        studentDAO = new StudentDAO();
        teacherDAO = new TeacherDAO();
    }

    public ArrayList<Person> getAllPersons() throws SQLException
    {
        return personDAO.getAllPersons();
    }

    public Attendance addAtendance(Student student, boolean isPressent, boolean isReal) throws SQLException
    {
        String timeStamp = new SimpleDateFormat("dd-MM-yyyy").format(Calendar.getInstance().getTime());
        Calendar c = Calendar.getInstance();
        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);

        String dayOfTheWeek;
        if (dayOfWeek == 2)
        {
            dayOfTheWeek = "Mandag";
        }
        else if (dayOfWeek == 3)
        {
            dayOfTheWeek = "Tirsdag";
        }
        else if (dayOfWeek == 4)
        {
            dayOfTheWeek = "Onsdag";
        }
        else if (dayOfWeek == 5)
        {
            dayOfTheWeek = "Torsdag";
        }
        else if (dayOfWeek == 6)
        {
            dayOfTheWeek = "Fredag";
        }
        else if (dayOfWeek == 7)
        {
            dayOfTheWeek = "Lørdag";
        }
        else if (dayOfWeek == 1)
        {
            dayOfTheWeek = "Søndag";
        }
        else
        {
            dayOfTheWeek = "Something went wrong";
        }
        Attendance attendance = new Attendance(isPressent, timeStamp, dayOfTheWeek, isReal, -1);
        student.getAttendance().add(attendance);
        studentDAO.createAttendance(isPressent, timeStamp, dayOfTheWeek, student.getId(), isReal);
        return attendance;
    }

    public ArrayList<Student> getAllStudents() throws SQLException
    {
        return studentDAO.getAllStudents();
    }

    public Attendance createAttendance(boolean isAttending, String date, String dayOfTheWeek, int studentId, boolean isReal) throws SQLException
    {
        return studentDAO.createAttendance(isAttending, date, dayOfTheWeek, studentId, isReal);
    }

    public void createTeacherAlert(int TeacherId, int StudentId, int OldAttendanceId, int newAttendanceId) throws SQLException
    {
        teacherDAO.createTeacherAlert(TeacherId, StudentId, OldAttendanceId, newAttendanceId);
    }

    public ArrayList<TeacherAlert> getTeacherAlert(int teacherID) throws SQLException
    {
        return teacherDAO.getTeacherAlert(teacherID);
    }

    public void deleteTeacherAlert(int alertId) throws SQLException
    {
        teacherDAO.deleteTeacherAlert(alertId);
    }

    public void deleteAttendance(int attId) throws SQLException
    {
        studentDAO.deleteAttendance(attId);
    }
}
