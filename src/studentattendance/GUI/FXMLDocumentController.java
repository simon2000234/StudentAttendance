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
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

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
       
        
    }    

    @FXML
    private void handleOkBtn(ActionEvent event)
    {
    }

    @FXML
    private void handleCancelBtn(ActionEvent event)
    {
    }
    
}
