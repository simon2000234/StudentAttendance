/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentattendance.GUI;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
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

    private SAModel model;
    private Student student;
    @FXML
    private Label lblName;
    @FXML
    private ListView<Attendance> lstAttendance;
    @FXML
    private Label lblAttendance;

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
        model.getOBSAttendance().addAll(student.getAttendance());
        lstAttendance.setItems(model.getOBSAttendance());
        caluclateAttendance(student);
    }

    @FXML
    private void close(ActionEvent event)
    {
        System.exit(0);
    }

    @FXML
    private void handleIsPresent(ActionEvent event)
    {
        model.getOBSAttendance().add(model.addAtendance(student, true));
        caluclateAttendance(student);
    }

    @FXML
    private void handleIsAbsent(ActionEvent event)
    {
        model.getOBSAttendance().add(model.addAtendance(student, false));
        caluclateAttendance(student);
    }
    
    private void caluclateAttendance(Student student)
    {
        ArrayList<Attendance> attendanceList = student.getAttendance();
        double total = attendanceList.size();
        double notHere = 0;
        for (Attendance attendance : attendanceList)
        {
            if (attendance.isIsAttending() == false)
            {
                notHere ++;
            }
        }
        double absence = (notHere/total)*100;
        lblAttendance.setText("Absence = " + absence + "%");
        
    }

}
