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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
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
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(StudentAttendance.class.getResource("GUi/RootLayer.fxml"));
    }

    @FXML
    private void handleCancelBtn(ActionEvent event)
    {
    }
    
}
