/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentattendance.DAL;

import java.sql.SQLException;
import java.util.ArrayList;
import studentattendance.BE.Attendance;
import studentattendance.BE.Person;
import studentattendance.BE.Student;
import studentattendance.BE.TeacherAlert;

/**
 *
 * @author Caspe
 */
public class DataAccessFacade
{
    private PersonDAO pDAO;
    private StudentDAO sDAO;
    private TeacherDAO tDAO;

    public DataAccessFacade()
    {
        this.pDAO = new PersonDAO();
        this.sDAO = new StudentDAO();
        this.tDAO = new TeacherDAO();
    }
    
    public ArrayList<Person> getAllPersons() throws SQLException
    {
        return pDAO.getAllPersons();
    }

    public Attendance addAtendance(Student student, boolean isPressent, boolean isReal,String timeStamp, String dayOfTheWeek) throws SQLException
    {
        Attendance attendance = new Attendance(isPressent, timeStamp, dayOfTheWeek, isReal, -1);
        student.getAttendance().add(attendance);
        sDAO.createAttendance(isPressent, timeStamp, dayOfTheWeek, student.getId(), isReal);
        return attendance;
    }

    public ArrayList<Student> getAllStudents() throws SQLException
    {
        return sDAO.getAllStudents();
    }

    public Attendance createAttendance(boolean isAttending, String date, String dayOfTheWeek, int studentId, boolean isReal) throws SQLException
    {
        return sDAO.createAttendance(isAttending, date, dayOfTheWeek, studentId, isReal);
    }

    public void createTeacherAlert(int TeacherId, int StudentId, int OldAttendanceId, int newAttendanceId) throws SQLException
    {
        tDAO.createTeacherAlert(TeacherId, StudentId, OldAttendanceId, newAttendanceId);
    }

    public ArrayList<TeacherAlert> getTeacherAlert(int teacherID) throws SQLException
    {
        return tDAO.getTeacherAlert(teacherID);
    }

    public void deleteTeacherAlert(int alertId) throws SQLException
    {
        tDAO.deleteTeacherAlert(alertId);
    }

    public void deleteAttendance(int attId) throws SQLException
    {
        sDAO.deleteAttendance(attId);
    }
    
    public void teacherEditAttendance(Attendance attendance, boolean isAttending, Student student) throws SQLException
    {
        sDAO.teacherEditAttendance(attendance, isAttending, student);
    }


    
    
    
}
