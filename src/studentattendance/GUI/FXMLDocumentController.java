/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentattendance.GUI;

import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import studentattendance.BE.Person;
import studentattendance.BE.Student;
import studentattendance.BE.Teacher;
import studentattendance.BLL.AManager;
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
    private TextField txtPassword;
    private SAModel model;

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        model = new SAModel();
    }
    
    

    @FXML
    private void handleOkBtn(ActionEvent event)
    {
        String username = txtUsername.getText();
        String password = txtPassword.getText();
        if (isStudent() == true)
        {
            try
            {
                Parent root;
                FXMLLoader loader = new FXMLLoader();//(getClass().getClassLoader().getResource("GUI/RootLayer.fxml"));
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

//        FXMLLoader loader = new FXMLLoader();
//        loader.setLocation(StudentAttendance.class.getResource("GUI/RootLayer.fxml"));
            } catch (IOException ex)
            {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (isTeacher())
        {
            System.out.println("Teacher login is not yet supported");
        } else
        {
            System.out.println("Wrong Password or Username");

        }

    }
    
    public boolean isTeacher()
    {
        AManager am = new AManager();
        boolean isTeacher = false;
        ArrayList<Person> list = am.getAllPersons();
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
        return isTeacher;
    }

    public boolean isStudent()
    {
        AManager am = new AManager();
        boolean isStudent = false;
        ArrayList<Person> list = am.getAllPersons();
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
