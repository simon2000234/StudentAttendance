/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentattendance.BLL;

import studentattendance.GUI.RootLayerController;

/**
 *
 * @author Melchertsen
 */
public class Tester
{

    public static void main(String[] args)
    {
        RootLayerController rlc = new RootLayerController();
        rlc.prevMonth();
        rlc.nextMonth();
    
    }
}
