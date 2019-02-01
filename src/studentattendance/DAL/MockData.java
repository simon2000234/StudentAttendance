/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentattendance.DAL;

import java.util.ArrayList;
import java.util.List;
import studentattendance.BE.Attendance;
import studentattendance.BE.Student;

/**
 *
 * @author Melchertsen
 */
public class MockData
{
    
    
    
    
    
    
    
    
    public Student createStudentJohn()
    {
        Attendance attendanceday1 = new Attendance(true, "28-01-2019", "Mandag");
        Attendance attendanceday2 = new Attendance(true, "29-01-2019", "Tirsdag");
        Attendance attendanceday3 = new Attendance(true, "30-01-2019", "Onsdag");
        Attendance attendanceday4 = new Attendance(true, "31-01-2019", "Torsdag");
        Attendance attendanceday5 = new Attendance(true, "01-02-2019", "Fredag");
        
        ArrayList<Attendance> attendanceList = new ArrayList();
        attendanceList.add(attendanceday1);
        attendanceList.add(attendanceday2);
        attendanceList.add(attendanceday3);
        attendanceList.add(attendanceday4);
        attendanceList.add(attendanceday5);
        
        Student John = new Student("John Hitler", "john123@mail.com", "1234", attendanceList, 1);
        
        return John;
    }
    
    public Student createStudentJake()
    {
        Attendance attendanceday1 = new Attendance(false, "28-01-2019", "Mandag");
        Attendance attendanceday2 = new Attendance(true, "29-01-2019", "Tirsdag");
        Attendance attendanceday3 = new Attendance(true, "30-01-2019", "Onsdag");
        Attendance attendanceday4 = new Attendance(true, "31-01-2019", "Torsdag");
        Attendance attendanceday5 = new Attendance(false, "01-02-2019", "Fredag");
        
        ArrayList<Attendance> attendanceList = new ArrayList();
        attendanceList.add(attendanceday1);
        attendanceList.add(attendanceday2);
        attendanceList.add(attendanceday3);
        attendanceList.add(attendanceday4);
        attendanceList.add(attendanceday5);
        
        Student Jake = new Student("Jake Paul", "Suicide@mail.com", "1234", attendanceList, 2);
        
        return Jake;
    }
    
    public Student createStudentKarl()
    {
        Attendance attendanceday1 = new Attendance(false, "28-01-2019", "Mandag");
        Attendance attendanceday2 = new Attendance(false, "29-01-2019", "Tirsdag");
        Attendance attendanceday3 = new Attendance(false, "30-01-2019", "Onsdag");
        Attendance attendanceday4 = new Attendance(false, "31-01-2019", "Torsdag");
        Attendance attendanceday5 = new Attendance(false, "01-02-2019", "Fredag");
        
        ArrayList<Attendance> attendanceList = new ArrayList();
        attendanceList.add(attendanceday1);
        attendanceList.add(attendanceday2);
        attendanceList.add(attendanceday3);
        attendanceList.add(attendanceday4);
        attendanceList.add(attendanceday5);
        
        Student Karl = new Student("Karl Timsen", "DeKing@mail.com", "1234", attendanceList, 3);
        
        return Karl;
    }
    
    public Student createStudentIna()
    {
        Attendance attendanceday1 = new Attendance(false, "28-01-2019", "Mandag");
        Attendance attendanceday2 = new Attendance(true, "29-01-2019", "Tirsdag");
        Attendance attendanceday3 = new Attendance(false, "30-01-2019", "Onsdag");
        Attendance attendanceday4 = new Attendance(true, "31-01-2019", "Torsdag");
        Attendance attendanceday5 = new Attendance(false, "01-02-2019", "Fredag");
        
        ArrayList<Attendance> attendanceList = new ArrayList();
        attendanceList.add(attendanceday1);
        attendanceList.add(attendanceday2);
        attendanceList.add(attendanceday3);
        attendanceList.add(attendanceday4);
        attendanceList.add(attendanceday5);
        
        Student Ina = new Student("Ina Olsen", "Ina@mail.com", "1234", attendanceList, 4);
        
        return Ina;
    }
    
    public Student createStudentAlex()
    {
        Attendance attendanceday1 = new Attendance(true, "28-01-2019", "Mandag");
        Attendance attendanceday2 = new Attendance(true, "29-01-2019", "Tirsdag");
        Attendance attendanceday3 = new Attendance(true, "30-01-2019", "Onsdag");
        Attendance attendanceday4 = new Attendance(false, "31-01-2019", "Torsdag");
        Attendance attendanceday5 = new Attendance(false, "01-02-2019", "Fredag");
        
        ArrayList<Attendance> attendanceList = new ArrayList();
        attendanceList.add(attendanceday1);
        attendanceList.add(attendanceday2);
        attendanceList.add(attendanceday3);
        attendanceList.add(attendanceday4);
        attendanceList.add(attendanceday5);
        
        Student Alex = new Student("Alex Click", "Click@mail.com", "1234", attendanceList, 5);
        
        return Alex;
    }
}
