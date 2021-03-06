/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentattendance.GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import studentattendance.BE.Attendance;
import studentattendance.BE.Student;
import studentattendance.BE.Teacher;
import studentattendance.StudentAttendance;

/**
 * FXML Controller class
 *
 * @author Caspe
 */
public class TeacherPageController implements Initializable
{

    private SAModel model;
    @FXML
    private Label lblTeacherName;
    private Teacher teacher;
    @FXML
    private TableView<Student> tableAttendance;
    @FXML
    private TableColumn<Student, String> columnStudentName;
    @FXML
    private TableColumn<Student, Double> columnAttendance;
    private double mandag;
    private double tirsdag;
    private double onsdag;
    private double torsdag;
    private double fredag;
    // d = divide;
    private double dMandag;
    private double dTirsdag;
    private double dOnsdag;
    private double dTorsdag;
    private double dFredag;
    private BarChart<?, ?> barChart;
    @FXML
    private BorderPane borderPane;
    @FXML
    private Button lblEditStudent;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        columnStudentName.setCellValueFactory(new PropertyValueFactory<>("name"));
        columnAttendance.setCellValueFactory(new PropertyValueFactory<>("attendanceString"));

    }

    public void setMsmodel(SAModel model)
    {
        this.model = model;
        lblTeacherName.setText(model.getCurrentUser().getName());
        teacher = (Teacher) model.getCurrentUser();
        tableAttendance.setItems(model.getStudents());
        columnAttendance.setComparator(columnAttendance.getComparator().reversed());
        tableAttendance.getSortOrder().add(columnAttendance);

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

            Stage current = (Stage) lblTeacherName.getScene().getWindow();
            current.close();
        }
        catch (IOException ex)
        {
            Logger.getLogger(RootLayerController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void handleCheckAlerts(ActionEvent event)
    {
        try
        {
            Parent root;
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(StudentAttendance.class.getResource("GUI/TeacherNotifications.fxml"));
            root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Alerts");
            stage.setScene(new Scene(root));
            stage.show();

            TeacherNotificationsController tnController = loader.getController();
            tnController.setModel(model);
        }
        catch (IOException ex)
        {
            Logger.getLogger(TeacherPageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void absentDays(Student student)
    {
        mandag = 0;
        tirsdag = 0;
        onsdag = 0;
        torsdag = 0;
        fredag = 0;
        dMandag = 0;
        dTirsdag = 0;
        dOnsdag = 0;
        dTorsdag = 0;
        dFredag = 0;

        for (Attendance attendance : student.getAttendance())
        {
            if (attendance.getDayOfWeek().contains("Mandag") && attendance.isIsAttending() == false && attendance.isIsReal() == true)
            {
                mandag++;
            }
            if (attendance.getDayOfWeek().contains("Tirsdag") && attendance.isIsAttending() == false && attendance.isIsReal() == true)
            {
                tirsdag++;
            }
            if (attendance.getDayOfWeek().contains("Onsdag") && attendance.isIsAttending() == false && attendance.isIsReal() == true)
            {
                onsdag++;
            }
            if (attendance.getDayOfWeek().contains("Torsdag") && attendance.isIsAttending() == false && attendance.isIsReal() == true)
            {
                torsdag++;
            }
            if (attendance.getDayOfWeek().contains("Fredag") && attendance.isIsAttending() == false && attendance.isIsReal() == true)
            {
                fredag++;
            }
        }
        for (Attendance attendance : student.getAttendance())
        {
            if (attendance.getDayOfWeek().contains("Mandag") && attendance.isIsReal() == true)
            {
                dMandag++;
            }
            if (attendance.getDayOfWeek().contains("Tirsdag") && attendance.isIsReal() == true)
            {
                dTirsdag++;
            }
            if (attendance.getDayOfWeek().contains("Onsdag") && attendance.isIsReal() == true)
            {
                dOnsdag++;
            }
            if (attendance.getDayOfWeek().contains("Torsdag") && attendance.isIsReal() == true)
            {
                dTorsdag++;
            }
            if (attendance.getDayOfWeek().contains("Fredag") && attendance.isIsReal() == true)
            {
                dFredag++;
            }
        }
        mandag = (mandag/dMandag)*100;
        tirsdag = (tirsdag/dTirsdag)*100;
        onsdag = (onsdag/dOnsdag)*100;
        torsdag = (torsdag/dTorsdag)*100;
        fredag = (fredag/dFredag)*100;

    }

    @FXML
    private void handleSelectStudent(MouseEvent event)
    {
        model.setSelectedStudent(tableAttendance.getSelectionModel().getSelectedItem());
        borderPane.setCenter(buildBarChart());
    }

    private BarChart buildBarChart()
    {
        absentDays(model.getSelectedStudent());
        //Creating X and Y axis
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Student Absence");
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("% Absence of Each Weekday");

        //Building BarChart data
        barChart = new BarChart(xAxis, yAxis);

        XYChart.Series dataset = new XYChart.Series();
//        dataset.setName("Overview of days most absent");

        dataset.getData().add(new XYChart.Data("Mandag", mandag));
        dataset.getData().add(new XYChart.Data("Tirsdag", tirsdag));
        dataset.getData().add(new XYChart.Data("Onsdag", onsdag));
        dataset.getData().add(new XYChart.Data("Torsdag", torsdag));
        dataset.getData().add(new XYChart.Data("Fredag", fredag));

        //Add dataset to chart
        barChart.getData().add(dataset);

        return barChart;

    }

    @FXML
    private void handleEditStudentAttendance(ActionEvent event)
    {
        try
        {
            
            
            Parent root;
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(StudentAttendance.class.getResource("GUI/TeacherEditAttendance.fxml"));
            root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Edit Student Attendance");
            stage.setScene(new Scene(root));
            stage.show();

            TeacherEditAttendanceController teaController = loader.getController();
            teaController.setModel(model);
        }
        catch (IOException ex)
        {
            Logger.getLogger(TeacherPageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
