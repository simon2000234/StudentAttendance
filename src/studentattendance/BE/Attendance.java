/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentattendance.BE;

import java.time.LocalDate;

/**
 *
 * @author Melchertsen
 */
public class Attendance
{

    private boolean isAttending;
    private String date;
    private String dayOfWeek;
    private boolean isReal;
    private int id;

    public Attendance(boolean isAttending, String date, String dayOfWeek, boolean isReal, int id)
    {
        this.isAttending = isAttending;
        this.date = date;
        this.dayOfWeek = dayOfWeek;
        this.isReal = isReal;
        this.id = id;
    }

    public boolean isIsAttending()
    {
        return isAttending;
    }

    public void setIsAttending(boolean isAttending)
    {
        this.isAttending = isAttending;
    }

    public String readableAttendance()
    {
        String status = "";
        if (isAttending == true)
        {
            status = "Present";
        } else
        {
            status = "Absent";
        }
        return status;
    }

    public String getDate()
    {
        return date;
    }

    public void setDate(String date)
    {
        this.date = date;
    }

    public String getDayOfWeek()
    {
        return dayOfWeek;
    }

    @Override
    public String toString()
    {
        return "" + dayOfWeek + " " + date + " - " + readableAttendance();
    }

    public boolean isIsReal()
    {
        return isReal;
    }

    public int getId()
    {
        return id;
    }
    
    

}
