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

    public Attendance(boolean isAttending, String date, String dayOfWeek)
    {
        this.isAttending = isAttending;
        this.date = date;
        this.dayOfWeek = dayOfWeek;
    }

    public boolean isIsAttending()
    {
        return isAttending;
    }

    public void setIsAttending(boolean isAttending)
    {
        this.isAttending = isAttending;
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
        return "" + dayOfWeek + " " + date + " Attendance=" + isAttending;
    }

    

    
    

}
