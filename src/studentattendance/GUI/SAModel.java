/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentattendance.GUI;

import java.util.ArrayList;
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

    public SAModel()
    {
        AM = new AManager();
        this.attendance = FXCollections.observableArrayList();
        OBSTeacherAlerts = FXCollections.observableArrayList();
        this.students = FXCollections.observableArrayList();
        students.addAll(AM.getAllStudents());
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

    public Attendance addAtendance(Student student, boolean isPressent)
    {
        return AM.addAtendance(student, isPressent);
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
    
}
