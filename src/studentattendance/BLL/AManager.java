/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentattendance.BLL;

import java.util.ArrayList;
import studentattendance.BE.Person;
import studentattendance.BE.Student;
import studentattendance.DAL.MockData;
import studentattendance.DAL.PersonDAO;
/**
 *
 * @author Richart hansen
 */
public class AManager
{
    private PersonDAO personDAO;
    
     public ArrayList<Person> getAllPersons()
     {
         personDAO = new PersonDAO();
         return personDAO.getAllPersons();
     }
    
}
