/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentattendance.GUI;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import studentattendance.BE.Attendance;
import studentattendance.BE.Student;

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
        int editPos = -1;
        ArrayList<Attendance> list = student.getAttendance();
        for (int i = 0; i < list.size(); i++)
        {
            if (list.get(i) == model.getAttendanceEdit())
            {
                editPos = i;

            }
        }
        Attendance newAttendance = new Attendance(true, list.get(editPos).getDate(),
                list.get(editPos).getDayOfWeek());
        student.getAttendance().remove(editPos);
        student.getAttendance().add(newAttendance);
        model.getOBSAttendance().remove(editPos);
        model.getOBSAttendance().add(editPos, newAttendance);
        rlc.caluclateAttendance(student);
        Stage current = (Stage) currentAttendance.getScene().getWindow();
                current.close();

    }

    @FXML
    private void handleWasNotThere(ActionEvent event)
    {
        int editPos = -1;
        ArrayList<Attendance> list = student.getAttendance();
        for (int i = 0; i < list.size(); i++)
        {
            if (list.get(i) == model.getAttendanceEdit())
            {
                editPos = i;

            }
        }
        Attendance newAttendance = new Attendance(false, list.get(editPos).getDate(),
                list.get(editPos).getDayOfWeek());
        student.getAttendance().remove(editPos);
        student.getAttendance().add(newAttendance);
        model.getOBSAttendance().remove(editPos);
        model.getOBSAttendance().add(editPos, newAttendance);
        rlc.caluclateAttendance(student);
        Stage current = (Stage) currentAttendance.getScene().getWindow();
                current.close();
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
