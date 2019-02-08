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
import javafx.scene.control.TextField;
import javafx.stage.Stage;
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
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
    }    

    @FXML
    private void handleOkBtn(ActionEvent event)
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
            
            Stage current = (Stage) txtUsername.getScene().getWindow();
            current.close();
        
//        FXMLLoader loader = new FXMLLoader();
//        loader.setLocation(StudentAttendance.class.getResource("GUI/RootLayer.fxml"));
        } catch (IOException ex)
        {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void handleCancelBtn(ActionEvent event)
    {
        Stage current = (Stage) txtUsername.getScene().getWindow();
        current.close();
    }
    
}
