/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentattendance.BLL;

import java.util.ArrayList;
import studentattendance.BE.Person;
import studentattendance.DAL.PersonDAO;
/**
 *
 * @author Richart hansen
 */
public class AManager
{
    private PersonDAO personDAO;

    public AManager()
    {
        personDAO = new PersonDAO();
    }
    
    
    
     public ArrayList<Person> getAllPersons()
     {
         return personDAO.getAllPersons();
     }
    
}
