/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentattendance.BE;

/**
 *
 * @author Melchertsen
 */
public class TeacherAlert
{
    private Student student;
    private Attendance nAtendance;
    private Attendance oAttendance;

    public TeacherAlert(Student student, Attendance nAtendance, Attendance oAttendance)
    {
        this.student = student;
        this.nAtendance = nAtendance;
        this.oAttendance = oAttendance;
    }
    
    public Student getStudent()
    {
        return student;
    }

    public Attendance getnAtendance()
    {
        return nAtendance;
    }

    public Attendance getoAttendance()
    {
        return oAttendance;
    }

    @Override
    public String toString()
    {
        String oIsThere;
        String nIsThere;
        if (oAttendance.isIsAttending() == true)
        {
            oIsThere = "Attending";
        }
        else
        {
            oIsThere = "not Attending";
        }
        if (nAtendance.isIsAttending() == true)
        {
            nIsThere = "Attending";
        }
        else
        {
             nIsThere = "not Attending";
        }
        return student.getName() + " wants to change his/hers Attendance on " +
                oAttendance.getDate() + " from " + oIsThere + " to " + nIsThere;
    }
    
    


    
}
