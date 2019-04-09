/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentattendance.DAL;

import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import studentattendance.BE.Attendance;
import studentattendance.BE.Student;

/**
 *
 * @author Melchertsen
 */
public class StudentDAOTest
{

    /**
     * Test of all methods directly realted to the students themselves.
     */
    @Test
    public void testCreateSutdentAndGetStudentAndGetAllStudentsAndDeleteStudent() throws Exception
    {
        StudentDAO sd = new StudentDAO();
        String Username = "Testman";
        String Password = "TestPass";
        String Name = "TestName";
        int id = -1;
        sd.createSutdent(Username, Password, Name);
        List<Student> sl = sd.getAllStudents();
        for (Student student : sl)
        {
            if(student.getName().equals(Name))
            {
                id = student.getId();
            }
        }
        assertEquals(Username, sd.getStudent(id).getUsername());
        sd.deleteStudent(id);
    }

    /**
     * Test of all methods directly realted to the attendance of students.
     */
    @Test
    public void testCreateAttendanceAndGetAllAttendanceAndDeleteAttendance() throws Exception
    {
        StudentDAO sd = new StudentDAO();
        Student student = sd.getStudent(1);
        sd.createAttendance(true, "fuckOff", "nope", student.getId(), false);
        List<Attendance> la = sd.getAllAttendance(student.getId());
        Attendance attendance = null;
        for (Attendance attendancee : la)
        {
            if(attendancee.getDate().equals("fuckOff"))
            {
                attendance = attendancee;
            }
        }
        assertEquals(attendance.getDate(), "fuckOff");
        sd.deleteAttendance(attendance.getId());
    }
}
