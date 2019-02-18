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
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import studentattendance.BE.Attendance;
import studentattendance.BE.Student;

/**
 * FXML Controller class
 *
 * @author Caspe
 */
public class RootLayerController implements Initializable
{

    @FXML
    private Button btnPresent;
    @FXML
    private Button btnAbsent;
    @FXML
    private Button btnEdit;
    @FXML
    private PieChart chartPieChart;

    private SAModel model;
    private Student student;
    @FXML
    private Label lblName;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
    }

    public void setMsmodel(SAModel model)
    {
        this.model = model;
        lblName.setText(model.getCurrentUser().getName());
        student = (Student) model.getCurrentUser();

    }

    @FXML
    private void close(ActionEvent event)
    {
        System.exit(0);
    }

    @FXML
    private void handleIsPresent(ActionEvent event)
    {
        model.addAtendance(student, true);

    }

    @FXML
    private void handleIsAbsent(ActionEvent event)
    {
        model.addAtendance(student, false);
    }

}
