/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentattendance.GUI;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import studentattendance.BE.Attendance;
import studentattendance.BE.Student;
import studentattendance.StudentAttendance;

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
    @FXML
    private Label lblHiden;

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
        try
        {
            Parent root;
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(StudentAttendance.class.getResource("GUI/FXMLDocument.fxml"));
            root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Log in");
            stage.setScene(new Scene(root));
            stage.show();

            Stage current = (Stage) lblName.getScene().getWindow();
            current.close();
        }
        catch (IOException ex)
        {
            Logger.getLogger(RootLayerController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void handleIsPresent(ActionEvent event)
    {
        String timeStamp = new SimpleDateFormat("dd-MM-yyyy").format(Calendar.getInstance().getTime());
        boolean isAlreadyThere = false;
        ArrayList<Attendance> list = student.getAttendance();
        for (Attendance attendance : list)
        {
            if (attendance.getDate().equals(timeStamp))
            {
                isAlreadyThere = true;
            }
        }
        if (isAlreadyThere == false)
        {
            try
            {
                model.getOBSAttendance().add(model.addAtendance(student, true, true));
            }
            catch (SQLException ex)
            {
                System.out.println("Something went wrong when adding attendance, are you on the schools internet");
            }
            caluclateAttendance(student);
        }
        else
        {
            System.out.println("The attendance is already there");
        }

    }

    @FXML
    private void handleIsAbsent(ActionEvent event)
    {
        String timeStamp = new SimpleDateFormat("dd-MM-yyyy").format(Calendar.getInstance().getTime());
        boolean isAlreadyThere = false;
        ArrayList<Attendance> list = student.getAttendance();
        for (Attendance attendance : list)
        {
            if (attendance.getDate().equals(timeStamp))
            {
                isAlreadyThere = true;
            }
        }
        if (isAlreadyThere == false)
        {
            try
            {
                model.getOBSAttendance().add(model.addAtendance(student, false, true));
            }
            catch (SQLException ex)
            {
                System.out.println("Something went wrong when adding attendance, are you on the schools internet");
            }
            caluclateAttendance(student);
        }
        else
        {
            System.out.println("The attendance is already there");
        }
    }

    public void caluclateAttendance(Student student)
    {
        ArrayList<Attendance> attendanceList = student.getAttendance();
        double total = attendanceList.size();
        double notHere = 0;
        for (Attendance attendance : attendanceList)
        {
            if (attendance.isIsAttending() == false)
            {
                notHere++;
            }
        }
        double absence = (notHere / total) * 100;
        String formatted = String.format("%.2f", absence);
        lblAttendance.setText("Absence = " + formatted + "%");

    }

    @FXML
    private void handleEdit(ActionEvent event)
    {
        if (lstAttendance.getSelectionModel().getSelectedItem() == null)
        {
            lblHiden.setText("Please select a day to edit");
        }
        else
        {
            model.setAttendanceEdit(lstAttendance.getSelectionModel().getSelectedItem());
            {
                try
                {
                    Parent root;
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(StudentAttendance.class.getResource("GUI/EditAttendance.fxml"));
                    root = loader.load();
                    Stage stage = new Stage();
                    stage.setTitle("Edit Attendance");
                    stage.setScene(new Scene(root, 500, 200));
                    stage.show();

                    EditAttendanceController eaController = loader.getController();
                    eaController.setMsmodel(model);
                    eaController.connectController(this);
                }
                catch (IOException ex)
                {
                    System.out.println("Someting went wrong, are you on the schools internet");
                }
            }

        }
    }

}
