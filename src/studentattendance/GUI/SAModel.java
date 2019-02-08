/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentattendance.GUI;

import studentattendance.BE.Person;

/**
 *
 * @author Melchertsen
 */
public class SAModel
{
    
    private Person currentUser;

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

}
