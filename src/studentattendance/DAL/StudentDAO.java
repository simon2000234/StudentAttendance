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
import java.util.ArrayList;
import java.util.List;
import studentattendance.BE.Attendance;
import studentattendance.BE.Student;

/**
 *
 * @author Melchertsen
 */
public class StudentDAO
{

    private DBConnectionProvider DB;

    public StudentDAO()
    {
        DB = new DBConnectionProvider();
    }

    public void createSutdent(String Username, String Password, String Name) throws SQLException
    {
        String SQL = "INSERT INTO Sutdent(Username, Password, Name) VALUES(?,?,?)";

        try (Connection con = DB.getConnection())
        {
            PreparedStatement st = con.prepareStatement(SQL);
            st.setString(1, Username);
            st.setString(2, Password);
            st.setString(3, Name);
            st.executeUpdate();
        }
    }

    public void createAttendance(boolean isAttending, String date, String dayOfTheWeek, int studentId, boolean isReal) throws SQLException
    {
        String SQL = "INSERT INTO Attendance(isAttending, date, dayOfTheWeek,"
                + " studentId, isReal) VALUES(?,?,?,?,?)";

        try (Connection con = DB.getConnection())
        {
            PreparedStatement st = con.prepareStatement(SQL);
            st.setBoolean(1, isAttending);
            st.setString(2, date);
            st.setString(3, dayOfTheWeek);
            st.setInt(4, studentId);
            st.setBoolean(5, isReal);
            st.executeUpdate();
        }
    }

    /**
     * A methord for getting a Student
     *
     * @param StudentID
     * @return a student
     * @throws SQLException
     */
    public Student getStudent(int StudentID) throws SQLException
    {
        Student student = null;
        String SQL = "SELECT * FROM Sutdent WHERE Id = ?";

        try (Connection con = DB.getConnection())
        {
            PreparedStatement st = con.prepareStatement(SQL);
            st.setInt(1, StudentID);
            ResultSet rs = st.executeQuery();
            while (rs.next())
            {
                String Name = rs.getString("Name");
                String Username = rs.getString("Username");
                String Password = rs.getString("Password");
                ArrayList<Attendance> ListAttendance = getAllAttendance(StudentID);
                student = new Student(Name, Username, Password, StudentID, ListAttendance);
            }
        }

        if (student == null)
        {
            System.out.println("The Student does not exist, or internet problem");
        }
        return student;
    }

    public ArrayList<Student> getAllStudents() throws SQLException
    {
        String SQL = "SELECT * FROM Sutdent;";
        ArrayList<Student> allStudents = new ArrayList<>();
        try (Connection con = DB.getConnection())
        {
            PreparedStatement st = con.prepareStatement(SQL);

            ResultSet rs = st.executeQuery();
            while (rs.next())
            {
                String Name = rs.getString("Name");
                String Username = rs.getString("Username");
                String Password = rs.getString("Password");
                int Id = rs.getInt("Id");
                ArrayList<Attendance> ListAttendance = getAllAttendance(Id);
                Student student = new Student(Name, Username, Password, Id, ListAttendance);
                allStudents.add(student);
            }
        }
        return allStudents;
    }

    /**
     * a methord for getting the attendance of a student
     *
     * @param studentId
     * @return an arraylist of a students attendance
     * @throws SQLException
     */
    public ArrayList<Attendance> getAllAttendance(int studentId) throws SQLException
    {
        ArrayList<Attendance> listAtt = new ArrayList<>();
        String SQL = "SELECT * FROM Attendance WHERE studentId = ?";
        try (Connection con = DB.getConnection())
        {
            PreparedStatement st = con.prepareStatement(SQL);
            st.setInt(1, studentId);
            ResultSet rs = st.executeQuery();
            while (rs.next())
            {
                boolean isAttending;
                if (rs.getBoolean("isAttending") == true)
                {
                    isAttending = true;
                }
                else
                {
                    isAttending = false;
                }

                String Date = rs.getString("date");
                String DayOfWeek = rs.getString("dayOfTheWeek");
                boolean isReal = rs.getBoolean("isReal");

                Attendance att = new Attendance(isAttending, Date, DayOfWeek, isReal);

                listAtt.add(att);
            }

        }
        return listAtt;
    }

    public Attendance getAAttendance(int attendanceId) throws SQLException
    {
        Attendance att = null;
        String SQL = "SELECT * FROM Attendance WHERE Id = ?";
        try (Connection con = DB.getConnection())
        {
            PreparedStatement st = con.prepareStatement(SQL);
            st.setInt(1, attendanceId);
            ResultSet rs = st.executeQuery();
            while(rs.next())
            {
            boolean isAttending;
            if (rs.getBoolean("isAttending") == true)
            {
                isAttending = true;
            }
            else
            {
                isAttending = false;
            }

            String Date = rs.getString("date");
            String DayOfWeek = rs.getString("dayOfTheWeek");
            boolean isReal = rs.getBoolean("isReal");

            att = new Attendance(isAttending, Date, DayOfWeek, isReal);
            }
            
        }
        return att;
    }

}
