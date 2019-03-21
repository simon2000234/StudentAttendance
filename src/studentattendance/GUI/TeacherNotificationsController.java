/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentattendance.GUI;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import studentattendance.BE.Attendance;
import studentattendance.BE.Teacher;
import studentattendance.BE.TeacherAlert;

/**
 * FXML Controller class
 *
 * @author Melchertsen
 */
public class TeacherNotificationsController implements Initializable
{

    @FXML
    private Label teacherName;
    @FXML
    private ListView<TeacherAlert> lstAlerts;
    private SAModel model;
    private Teacher currentUser;
    @FXML
    private Label lblConfirmation;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
    }

    @FXML
    private void handleConfirm(ActionEvent event)
    {
        if (lstAlerts.getSelectionModel().getSelectedItem() != null)
        {
            try {
                TeacherAlert alert = lstAlerts.getSelectionModel().getSelectedItem();
                model.getOBSTeacherAlerts().remove(alert);
                model.deleteTeacherAlert(alert.getId());
                Attendance newRealAttendance = new Attendance(alert.getnAtendance().isIsAttending(), alert.getnAtendance().getDate(), alert.getnAtendance().getDayOfWeek(), true, -1);
                model.createAttendance(newRealAttendance.isIsAttending(), newRealAttendance.getDate(), newRealAttendance.getDayOfWeek(), alert.getStudent().getId(), true);
                model.deleteAttendance(alert.getoAttendance().getId());
                model.deleteAttendance(alert.getnAtendance().getId());
                lblConfirmation.setText(alert.getStudent().getName()
                        + "'s change is confirmed");
            }
            catch (SQLException ex) {
                System.out.println("Sometinh went wrong with the connection, are you on the schools internet");
            }
        }
    }

    @FXML
    private void handleDeny(ActionEvent event)
    {
        if (lstAlerts.getSelectionModel().getSelectedItem() != null)
        {
            try
            {
                TeacherAlert alert = lstAlerts.getSelectionModel().getSelectedItem();
                model.getOBSTeacherAlerts().remove(lstAlerts.getSelectionModel().getSelectedItem());
                model.deleteTeacherAlert(alert.getId());
                model.deleteAttendance(alert.getnAtendance().getId());
                lblConfirmation.setText(alert.getStudent().getName()
                        + "'s change is denied");
            }
            catch (SQLException ex)
            {
                System.out.println("Sometinh went wrong with the connection, are you on the schools internet");
            }
        }
    }

    public void setModel(SAModel model)
    {
        this.model = model;
        currentUser = (Teacher) model.getCurrentUser();
        model.getOBSTeacherAlerts().clear();
        model.getOBSTeacherAlerts().addAll(currentUser.getTeacherAlerts());
        lstAlerts.setItems(model.getOBSTeacherAlerts());
        teacherName.setText(currentUser.getName());
    }

}
