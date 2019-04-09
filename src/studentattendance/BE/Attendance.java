/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentattendance.BE;

import java.time.LocalDate;
import java.util.Comparator;

/**
 *
 * @author Melchertsen
 */
public class Attendance implements Comparable<Attendance>
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

    @Override
    public int compareTo(Attendance o)
    {
        int c;
        String monthTHIS = Character.toString(this.date.charAt(3)) + Character.toString(this.date.charAt(4));
        String monthOTHER = Character.toString(o.date.charAt(3)) + Character.toString(o.date.charAt(4));
        c = monthTHIS.compareTo(monthOTHER);
        if (c == 0)
        {
            return this.date.compareTo(o.date);
        }
        if (c != 0)
        {
            return c;
        }
        return this.date.compareTo(o.date);
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
        {
            return true;
        }
        if (obj == null)
        {
            return false;
        }
        if (getClass() != obj.getClass())
        {
            return false;
        }
        final Attendance other = (Attendance) obj;
        if (this.id != other.id)
        {
            return false;
        }
        return true;
    }
    

}
