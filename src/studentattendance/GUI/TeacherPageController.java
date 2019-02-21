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
    private int Mandag;
    private int Tirsdag;
    private int Onsdag;
    private int Torsdag;
    private int Fredag;
    @FXML
    private BarChart<?, ?> barChart;
    @FXML
    private BorderPane borderPane;

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
        } catch (IOException ex)
        {
            Logger.getLogger(RootLayerController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void absentDays(Student student)
    {
        Mandag = 0;
        Tirsdag = 0;
        Onsdag = 0;
        Torsdag = 0;
        Fredag = 0;
        
            for (Attendance attendance : student.getAttendance())
            {
                if (attendance.getDayOfWeek().contains("Mandag") && attendance.isIsAttending()==false)
                {
                    Mandag++;
                }
                if (attendance.getDayOfWeek().contains("Tirsdag") && attendance.isIsAttending()==false)
                {
                    Tirsdag++;
                }
                if (attendance.getDayOfWeek().contains("Onsdag") && attendance.isIsAttending()==false)
                {
                    Onsdag++;
                }
                if (attendance.getDayOfWeek().contains("Torsdag") && attendance.isIsAttending()==false)
                {
                    Torsdag++;
                }
                if (attendance.getDayOfWeek().contains("Fredag") && attendance.isIsAttending()==false)
                {
                    Fredag++;
                }
            }
            
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
        yAxis.setLabel("# of days absent");

        //Building BarChart data
        barChart = new BarChart(xAxis, yAxis);

        XYChart.Series dataset = new XYChart.Series();
        dataset.setName("Overview of days most absent");

        dataset.getData().add(new XYChart.Data("Mandag", Mandag));
        dataset.getData().add(new XYChart.Data("Tirsdag", Tirsdag));
        dataset.getData().add(new XYChart.Data("Onsdag", Onsdag));
        dataset.getData().add(new XYChart.Data("Torsdag", Torsdag));
        dataset.getData().add(new XYChart.Data("Fredag", Fredag));

        //Add dataset to chart
        barChart.getData().add(dataset);

        return barChart;

    }

}
