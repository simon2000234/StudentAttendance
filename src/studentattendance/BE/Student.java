/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentattendance.BE;

import java.util.ArrayList;

/**
 *
 * @author Melchertsen
 */
public class Student extends Person
{
    private ArrayList<Attendance> attendance;
    private double attendanceString;
    private String viewAttendance;


    public Student(String name, String username, String password, int id, ArrayList<Attendance> attendance)
    {
        super(name, username, password, id);
        this.attendance = attendance;
        String temp = String.format("%.2f", caluclateAttendance());
        this.attendanceString = Double.valueOf(temp);
        
        
    }


    public ArrayList<Attendance> getAttendance()
    {
        return attendance;
    }
    
    public double caluclateAttendance()
    {
        ArrayList<Attendance> attendanceList = getAttendance();
        double total = attendanceList.size();
        double notRealAttendance = 0;
        for (Attendance attendance1 : attendanceList)
        {
            if (attendance1.isIsReal() == false)
            {
                notRealAttendance--;
            }
        }
        double notHere = 0;
        for (Attendance attendance : attendanceList)
        {
            if (attendance.isIsAttending() == false && attendance.isIsReal() == true)
            {
                notHere++;
            }
        }
        double absence = (notHere / (total - notRealAttendance)) * 100;
        return absence;

    }

    public double getAttendanceString()
    {
        return attendanceString;
    }
    
    
    
}
