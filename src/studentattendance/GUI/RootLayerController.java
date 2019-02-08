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
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;

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
    @FXML
    private PieChart chartPieChart;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
    }    

    @FXML
    private void close(ActionEvent event)
    {
        System.exit(0);
    }
    
}
