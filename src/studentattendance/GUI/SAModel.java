/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentattendance.GUI;

import studentattendance.BE.Person;
import studentattendance.BE.Student;
import studentattendance.BLL.AManager;

/**
 *
 * @author Melchertsen
 */
public class SAModel
{

    private AManager AM;

    private Person currentUser;

    public SAModel()
    {
        AM = new AManager();
    }

    /**
     * Get the value of currentUser
     *
     * @return the value of currentUser
     */
    public Person getCurrentUser()
    {
        return currentUser;
    }

    /**
     * Set the value of currentUser
     *
     * @param currentUser new value of currentUser
     */
    public void setCurrentUser(Person currentUser)
    {
        this.currentUser = currentUser;
    }

    public void addAtendance(Student student, boolean isPressent)
    {
        AM.addAtendance(student, isPressent);
    }

}
