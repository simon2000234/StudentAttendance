/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentattendance.BLL;

import java.util.ArrayList;
import studentattendance.BE.Student;
import studentattendance.DAL.MockData;
/**
 *
 * @author Richart hansen
 */
public class AManager
{
    private MockData mockData;
    public ArrayList<Student> getAllStudents()
    {
        ArrayList<Student> Stud = new ArrayList();
        
        Stud.add(mockData.createStudentJohn());
        Stud.add(mockData.createStudentAlex());
        Stud.add(mockData.createStudentIna());
        Stud.add(mockData.createStudentJake());
        Stud.add(mockData.createStudentKarl());
        
        return Stud;
    }
    
}
