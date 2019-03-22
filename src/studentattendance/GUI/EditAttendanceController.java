/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentattendance.GUI;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import studentattendance.BE.Attendance;
import studentattendance.BE.Student;
import studentattendance.BE.TeacherAlert;

/**
 * FXML Controller class
 *
 * @author Melchertsen
 */
public class EditAttendanceController implements Initializable
{

    private SAModel model;
    private Student student;
    private RootLayerController rlc;

    @FXML
    private Label currentAttendance;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
    }

    @FXML
    private void handleWasThere(ActionEvent event)
    {
        try
        {
            int editPos = -1;
            ArrayList<Attendance> list = student.getAttendance();
            ArrayList<TeacherAlert> taList = model.getTeacherAlert(4);
            boolean isAlertExecist = false;
            boolean doesItChangeAttendance = true;
            for (int i = 0; i < list.size(); i++)
            {
                if (list.get(i) == model.getAttendanceEdit())
                {
                    editPos = i;

                }
            }
            Attendance newAttendance = model.createAttendance(true, list.get(editPos).getDate(), list.get(editPos).getDayOfWeek(), student.getId(), false);
            Attendance oldAttendance = list.get(editPos);
            if (newAttendance.isIsAttending() == oldAttendance.isIsAttending())
            {
                doesItChangeAttendance = false;
            }
            for (TeacherAlert teacherAlert : taList)
            {
                if (teacherAlert.getnAtendance().getDate().equals(teacherAlert.getnAtendance().getDate()))
                {
                    isAlertExecist = true;
                }
            }

            if (isAlertExecist == false && doesItChangeAttendance == true)
            {
                model.createTeacherAlert(4, student.getId(), oldAttendance.getId(), newAttendance.getId());
            }
            else
            {
                System.out.println("You have already sent an alert for this attendance chahnge, or the attendance does not change");
                model.deleteAttendance(newAttendance.getId());
            }
            Stage current = (Stage) currentAttendance.getScene().getWindow();
            current.close();
        }
        catch (SQLException ex)
        {
            System.out.println("Someting went wrong, are you connected with the schools internet?");
        }

    }

    @FXML
    private void handleWasNotThere(ActionEvent event)
    {
        try
        {
            int editPos = -1;
            ArrayList<Attendance> list = student.getAttendance();
            ArrayList<TeacherAlert> taList = model.getTeacherAlert(4);
            boolean isAlertExecist = false;
            boolean doesItChangeAttendance = true;
            for (int i = 0; i < list.size(); i++)
            {
                if (list.get(i) == model.getAttendanceEdit())
                {
                    editPos = i;

                }
            }
            Attendance newAttendance = model.createAttendance(false, list.get(editPos).getDate(), list.get(editPos).getDayOfWeek(), student.getId(), false);
            Attendance oldAttendance = list.get(editPos);
            if (newAttendance.isIsAttending() == oldAttendance.isIsAttending())
            {
                doesItChangeAttendance = false;
            }
            for (TeacherAlert teacherAlert : taList)
            {
                if (teacherAlert.getnAtendance().getDate().equals(teacherAlert.getnAtendance().getDate()))
                {
                    isAlertExecist = true;
                }
            }

            if (isAlertExecist == false && doesItChangeAttendance == true)
            {
                model.createTeacherAlert(4, student.getId(), oldAttendance.getId(), newAttendance.getId());
            }
            else
            {
                System.out.println("You have already sent an alert for this attendance chahnge, or the attendance does not change");
                model.deleteAttendance(newAttendance.getId());
            }
            Stage current = (Stage) currentAttendance.getScene().getWindow();
            current.close();
        }
        catch (SQLException ex)
        {
            System.out.println("Someting went wrong, are you connected with the schools internet?");
        }
    }

    @FXML
    private void handleClose(ActionEvent event)
    {
        Stage current = (Stage) currentAttendance.getScene().getWindow();
        current.close();
    }

    public void setMsmodel(SAModel model)
    {
        this.model = model;
        student = (Student) model.getCurrentUser();
        currentAttendance.setText(model.getAttendanceEdit().toString());

    }

    public void connectController(RootLayerController rlc)
    {
        this.rlc = rlc;
    }

}
