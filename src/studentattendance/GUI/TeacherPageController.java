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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
    }

    public void setMsmodel(SAModel model)
    {
        this.model = model;
        lblTeacherName.setText(model.getCurrentUser().getName());
        teacher = (Teacher) model.getCurrentUser();
    }
    
}
