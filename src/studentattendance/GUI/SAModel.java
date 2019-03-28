/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentattendance.GUI;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import studentattendance.BE.Attendance;
import studentattendance.BE.Person;
import studentattendance.BE.Student;
import studentattendance.BE.TeacherAlert;
import studentattendance.BLL.AManager;

/**
 *
 * @author Melchertsen
 */
public class SAModel
{

    private AManager AM;

    private Person currentUser;

    private ObservableList<Attendance> attendance;

    private ObservableList<TeacherAlert> OBSTeacherAlerts;

    private Attendance attendanceEdit;
    private ObservableList<Student> students;
    private Student selectedStudent;

    public SAModel()
    {
        AM = new AManager();
        this.attendance = FXCollections.observableArrayList();
        OBSTeacherAlerts = FXCollections.observableArrayList();
        this.students = FXCollections.observableArrayList();
        try
        {
            students.addAll(AM.getAllStudents());
        }
        catch (SQLException ex)
        {
            System.out.println("Someting went wrong with getting alle the students, maybe internet is gone");
        }
    }

    public ObservableList<Attendance> getOBSAttendance()
    {
        return attendance;
    }

    public ObservableList<TeacherAlert> getOBSTeacherAlerts()
    {
        return OBSTeacherAlerts;
    }

    /**
     * Get the value of currentUser
     *
     * @return the value of currentUser
     */
    public Person getCurrentUser()
    {
        return currentUser;
    }

    /**
     * Set the value of currentUser
     *
     * @param currentUser new value of currentUser
     */
    public void setCurrentUser(Person currentUser)
    {
        this.currentUser = currentUser;
    }

    public Attendance addAtendance(Student student, boolean isPressent, boolean isReal) throws SQLException
    {
        return AM.addAtendance(student, isPressent, isReal);
    }

    public Attendance getAttendanceEdit()
    {
        return attendanceEdit;
    }

    public void setAttendanceEdit(Attendance attendanceEdit)
    {
        this.attendanceEdit = attendanceEdit;
    }

    public ObservableList<Student> getStudents()
    {
        return students;
    }

    public void setStudents(ObservableList<Student> students)
    {
        this.students = students;
    }

    public Student getSelectedStudent()
    {
        return selectedStudent;
    }

    public void setSelectedStudent(Student selectedStudent)
    {
        this.selectedStudent = selectedStudent;
    }

    public Attendance createAttendance(boolean isAttending, String date, String dayOfTheWeek, int studentId, boolean isReal) throws SQLException
    {
        return AM.createAttendance(isAttending, date, dayOfTheWeek, studentId, isReal);
    }

    public void createTeacherAlert(int TeacherId, int StudentId, int OldAttendanceId, int newAttendanceId) throws SQLException
    {
        AM.createTeacherAlert(TeacherId, StudentId, OldAttendanceId, newAttendanceId);
    }

    public ArrayList<TeacherAlert> getTeacherAlert(int teacherID) throws SQLException
    {
        return AM.getTeacherAlert(teacherID);
    }

    public void deleteTeacherAlert(int alertId) throws SQLException
    {
        AM.deleteTeacherAlert(alertId);
    }

    public void deleteAttendance(int attId) throws SQLException
    {
        AM.deleteAttendance(attId);
    }
    
    /**
     * A methord for getting the current day in the week
     * @return String of the current weekday
     */
    public String getDayOfTheWeek()
    {
        return AM.getDayOfTheWeek();
    }

    /**
     * A methord for getting the current date
     * @return A string of the current date in the format of dd-MM-yyyy
     */
    public String getDate()
    {
        return AM.getDate();
    }

}
