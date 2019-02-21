/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentattendance.GUI;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import studentattendance.BE.Student;
import studentattendance.BE.Teacher;

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
//        for (Student student : model.getStudents())
//        {
//            System.out.println(""+student.);
//        }
    }
    
}
