/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentattendance.BLL;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 *
 * @author Melchertsen
 */
public class CalenderManager
{

    /**
     * A methord for getting the current day in the week
     * @return String of the current weekday
     */
    public String getDayOfTheWeek()
    {
        Calendar c = Calendar.getInstance();
        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
        String dayOfTheWeek;
        if (dayOfWeek == 2)
        {
            dayOfTheWeek = "Mandag";
        }
        else if (dayOfWeek == 3)
        {
            dayOfTheWeek = "Tirsdag";
        }
        else if (dayOfWeek == 4)
        {
            dayOfTheWeek = "Onsdag";
        }
        else if (dayOfWeek == 5)
        {
            dayOfTheWeek = "Torsdag";
        }
        else if (dayOfWeek == 6)
        {
            dayOfTheWeek = "Fredag";
        }
        else if (dayOfWeek == 7)
        {
            dayOfTheWeek = "Lørdag";
        }
        else if (dayOfWeek == 1)
        {
            dayOfTheWeek = "Søndag";
        }
        else
        {
            dayOfTheWeek = "Something went wrong";
        }
        return dayOfTheWeek;
    }
    
    /**
     * A methord for getting the current date
     * @return A string of the current date in the format of dd-MM-yyyy
     */
    public String getDate()
    {
        return new SimpleDateFormat("dd-MM-yyyy").format(Calendar.getInstance().getTime());
    }
}
