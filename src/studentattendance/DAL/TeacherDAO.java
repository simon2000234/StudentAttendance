/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentattendance.DAL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import studentattendance.BE.Teacher;
import studentattendance.BE.TeacherAlert;

/**
 *
 * @author Melchertsen
 */
public class TeacherDAO
{

    private DBConnectionProvider DB;
    private StudentDAO SD;

    public TeacherDAO()
    {
        DB = new DBConnectionProvider();
        SD = new StudentDAO();
    }

    /**
     * Creates a teacher in the datebase
     */
    public void CreateTeacher(String Username, String Password, String Name) throws SQLException
    {
        String SQL = "INSERT INTO Teacher(Username, Password, Name) VALUES(?,?,?);";

        try (Connection con = DB.getConnection())
        {
            PreparedStatement st = con.prepareStatement(SQL);
            st.setString(1, Username);
            st.setString(2, Password);
            st.setString(3, Name);
            st.executeUpdate();

        }
    }

    /**
     * A methord to get all Teachers in the system NOT DONE
     *
     * @return an ArrayList of Teachers
     * @throws SQLException
     */
    public List<Teacher> getAllTeachers() throws SQLException
    {
        ArrayList<Teacher> allTeachers = new ArrayList<>();

        String SQL = "SELECT * FROM Teacher;";
        try (Connection con = DB.getConnection())
        {
            PreparedStatement st = con.prepareStatement(SQL);
            ResultSet rs = st.executeQuery();
            while (rs.next())
            {
                String Username = rs.getString("Username");
                String Password = rs.getString("Password");
                String Name = rs.getString("Name");
                int ID = rs.getInt("Id");
                Teacher teacher = new Teacher(getTeacherAlert(ID), Name, Username, Password, ID);
                allTeachers.add(teacher);
            }
        }
        return allTeachers;
    }

    public Teacher getTeacher(int teacherID) throws SQLException
    {
        Teacher teacher = null;
        String SQL = "SELECT * FROM Teacher WHERE Id = ?;";
        try (Connection con = DB.getConnection())
        {
            PreparedStatement st = con.prepareStatement(SQL);
            st.setInt(1, teacherID);
            ResultSet rs = st.executeQuery();
            String Username = rs.getString("Username");
            String Password = rs.getString("Password");
            String Name = rs.getString("Name");
            teacher = new Teacher(getTeacherAlert(teacherID), Name, Username, Password, teacherID);
        }
        if(teacher == null)
        {
            System.out.println("The Teacher does not excist, or internet problem");
        }
        return teacher;
    }

    /**
     * A methord for getting all the alerts of a teacher
     *
     * @param teacherID
     * @return an ArrayList of teacherAlert
     * @throws SQLException
     */
    public ArrayList<TeacherAlert> getTeacherAlert(int teacherID) throws SQLException
    {
        ArrayList<TeacherAlert> teacherAlerts = new ArrayList<>();
        String SQL = "SELECT * FROM TeacherAlert WHERE TeacherID = ?;";
        try (Connection con = DB.getConnection())
        {
            PreparedStatement st = con.prepareStatement(SQL);
            st.setInt(1, teacherID);
            ResultSet rs = st.executeQuery();
            while (rs.next())
            {
                int StudentID = rs.getInt("StudentId");
                int oldAttendanceID = rs.getInt("OldAttendanceId");
                int newAttendanceID = rs.getInt("NewAttendanceId");
                int id = rs.getInt("Id");
                TeacherAlert ta = new TeacherAlert(SD.getStudent(StudentID),
                        SD.getAAttendance(newAttendanceID),
                        SD.getAAttendance(oldAttendanceID));
                teacherAlerts.add(ta);
            }

        }
        return teacherAlerts;
    }
    /**
     * A methord to create an alert for a teacher
     * @param TeacherId the teacher who gets the alert
     * @param StudentId the student who sent it
     * @param OldAttendanceId the students current attendance that they 
     * whish to change
     * @param newAttendanceId the attendance that the student wish to change
     * it to
     * @throws SQLException
     */
    public void createTeacherAlert(int TeacherId, int StudentId, int OldAttendanceId, int newAttendanceId) throws SQLException
    {
        String SQL = "INSERT INTO TeacherAlet("
                + "TeacherId, StudentId, OldAttendanceId, NewAttendanceId) "
                + "VALUES(?,?,?,?);";
        
        try(Connection con = DB.getConnection())
        {
            PreparedStatement st = con.prepareStatement(SQL);
            st.setInt(1, TeacherId);
            st.setInt(2, StudentId);
            st.setInt(3, OldAttendanceId);
            st.setInt(4, newAttendanceId);
            st.executeUpdate();
        }
    }

}
