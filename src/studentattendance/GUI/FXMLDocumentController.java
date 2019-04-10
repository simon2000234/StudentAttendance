/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentattendance.GUI;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import studentattendance.BE.Person;
import studentattendance.BE.Student;
import studentattendance.BE.Teacher;
import studentattendance.StudentAttendance;

/**
 *
 * @author Melchertsen
 */
public class FXMLDocumentController implements Initializable
{

    @FXML
    private TextField txtUsername;
    @FXML
    private PasswordField txtPassword;
    private SAModel model;

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        model = new SAModel();
    }

    @FXML
    private void handleOkBtn(ActionEvent event)
    {
        if (isStudent() == true)
        {
            try
            {
                Parent root;
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(StudentAttendance.class.getResource("GUI/RootLayer.fxml"));
                root = loader.load();
                Stage stage = new Stage();
                stage.setTitle("Student");
                stage.setScene(new Scene(root, 600, 450));
                stage.show();

                RootLayerController rlController = loader.getController();
                rlController.setMsmodel(model);

                Stage current = (Stage) txtUsername.getScene().getWindow();
                current.close();

            }
            catch (IOException ex)
            {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else if (isTeacher() == true)
        {
            try
            {
                Parent root;
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(StudentAttendance.class.getResource("GUI/TeacherPage.fxml"));
                root = loader.load();
                Stage stage = new Stage();
                stage.setTitle("Teacher");
                stage.setScene(new Scene(root));
                stage.show();

                TeacherPageController tpController = loader.getController();
                tpController.setMsmodel(model);

                Stage current = (Stage) txtUsername.getScene().getWindow();
                current.close();

            }
            catch (IOException ex)
            {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else
        {
            System.out.println("Wrong Password or Username");

        }

    }

    public boolean isTeacher()
    {
        boolean isTeacher = false;
        ArrayList<Person> list;
        try
        {
            list = model.getAllPersons();
            for (Person person : list)
            {
                if (txtUsername.getText().equals(person.getUsername())
                        && txtPassword.getText().equals(person.getPassword())
                        && person.getClass() == Teacher.class)
                {
                    isTeacher = true;
                    model.setCurrentUser(person);
                }
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return isTeacher;
    }

    public boolean isStudent()
    {
        boolean isStudent = false;
        ArrayList<Person> list;
        try
        {
            list = model.getAllPersons();
            for (Person person : list)
            {
                if (txtUsername.getText().equals(person.getUsername())
                        && txtPassword.getText().equals(person.getPassword())
                        && person.getClass() == Student.class)
                {
                    isStudent = true;
                    model.setCurrentUser(person);
                }
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return isStudent;
    }

    @FXML
    private void handleCancelBtn(ActionEvent event)
    {
        //Closes the aplication
        Stage current = (Stage) txtUsername.getScene().getWindow();
        current.close();
    }

}
