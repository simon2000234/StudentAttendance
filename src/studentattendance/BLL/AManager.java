/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentattendance.BLL;

import java.sql.SQLException;
import java.util.ArrayList;
import studentattendance.BE.Attendance;
import studentattendance.BE.Person;
import studentattendance.BE.Student;
import studentattendance.BE.TeacherAlert;
import studentattendance.DAL.DataAccessFacade;

/**
 *
 * @author Richart hansen
 */
public class AManager
{
    private DataAccessFacade daFacade;
    private CalenderManager cm;

    public AManager()
    {
        daFacade = new DataAccessFacade();
        cm = new CalenderManager();
    }

    public ArrayList<Person> getAllPersons() throws SQLException
    {
        return daFacade.getAllPersons();
    }

    public Attendance addAtendance(Student student, boolean isPressent, boolean isReal) throws SQLException
    {
        String timeStamp = cm.getDate();
        String dayOfTheWeek = cm.getDayOfTheWeek();

        Attendance attendance = new Attendance(isPressent, timeStamp, dayOfTheWeek, isReal, -1);
        student.getAttendance().add(attendance);
        daFacade.addAtendance(student, isPressent, isReal, timeStamp, dayOfTheWeek);
        return attendance;
    }

    public ArrayList<Student> getAllStudents() throws SQLException
    {
        return daFacade.getAllStudents();
    }

    public Attendance createAttendance(boolean isAttending, String date, String dayOfTheWeek, int studentId, boolean isReal) throws SQLException
    {
        return daFacade.createAttendance(isAttending, date, dayOfTheWeek, studentId, isReal);
    }

    public void createTeacherAlert(int TeacherId, int StudentId, int OldAttendanceId, int newAttendanceId) throws SQLException
    {
        daFacade.createTeacherAlert(TeacherId, StudentId, OldAttendanceId, newAttendanceId);
    }

    public ArrayList<TeacherAlert> getTeacherAlert(int teacherID) throws SQLException
    {
        return daFacade.getTeacherAlert(teacherID);
    }

    public void deleteTeacherAlert(int alertId) throws SQLException
    {
        daFacade.deleteTeacherAlert(alertId);
    }

    public void deleteAttendance(int attId) throws SQLException
    {
        daFacade.deleteAttendance(attId);
    }

    /**
     * A methord for getting the current day in the week
     *
     * @return String of the current weekday
     */
    public String getDayOfTheWeek()
    {
        return cm.getDayOfTheWeek();
    }

    /**
     * A methord for getting the current date
     *
     * @return A string of the current date in the format of dd-MM-yyyy
     */
    public String getDate()
    {
        return cm.getDate();
    }

    public void teacherEditAttendance(Attendance attendance, boolean isAttending, Student student) throws SQLException
    {
        daFacade.teacherEditAttendance(attendance, isAttending, student);
    }

}
