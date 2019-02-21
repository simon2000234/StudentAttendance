/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentattendance.GUI;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
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
            TeacherAlert alert = lstAlerts.getSelectionModel().getSelectedItem();
            model.getOBSTeacherAlerts().remove(alert);
            //Insert Mehtord that changes database
            lblConfirmation.setText(alert.getStudent().getName()
                    + "'s change is confirmed");
        }
    }

    @FXML
    private void handleDeny(ActionEvent event)
    {
        if (lstAlerts.getSelectionModel().getSelectedItem() != null)
        {
            TeacherAlert alert = lstAlerts.getSelectionModel().getSelectedItem();
            model.getOBSTeacherAlerts().remove(lstAlerts.getSelectionModel().getSelectedItem());
            //DO NOT insert Mehtord that changes database
            lblConfirmation.setText(alert.getStudent().getName()
                    + "'s change is denied");
        }
    }

    public void setModel(SAModel model)
    {
        this.model = model;
        currentUser = (Teacher) model.getCurrentUser();
        model.getOBSTeacherAlerts().addAll(currentUser.getTeacherAlerts());
        lstAlerts.setItems(model.getOBSTeacherAlerts());
        teacherName.setText(currentUser.getName());
    }

}
