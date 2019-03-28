/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentattendance.GUI;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import studentattendance.BE.Attendance;
import studentattendance.BE.Student;
import studentattendance.StudentAttendance;

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

    private SAModel model;
    private Student student;
    @FXML
    private Label lblName;
    @FXML
    private ListView<Attendance> lstAttendance;
    @FXML
    private Label lblAttendance;
    @FXML
    private Label lblHiden;

    private String today;
    private String currentMonth;
    @FXML
    private Button btnPrevMonth;
    @FXML
    private Button btnNextMonth;
    @FXML
    private Label lblShowMonth;
    @FXML
    private Label lblEmptyListview;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        today = new SimpleDateFormat("dd-MM-yyyy").format(Calendar.getInstance().getTime());
        currentMonth = Character.toString(today.charAt(3)) + Character.toString(today.charAt(4));
    }

    public void setMsmodel(SAModel model)
    {
        this.model = model;
        lblName.setText(model.getCurrentUser().getName());
        student = (Student) model.getCurrentUser();
        ArrayList<Attendance> att = student.getAttendance();
        for (int i = 0; i < att.size(); i++)
        {
            if (att.get(i).isIsReal() == false)
            {
                att.remove(i);
            }
        }
        model.getOBSAttendance().addAll(att);
        Collections.sort(model.getOBSAttendance());
        lstAttendance.setItems(model.getOBSAttendance());
        caluclateAttendance(student);

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

            Stage current = (Stage) lblName.getScene().getWindow();
            current.close();
        } catch (IOException ex)
        {
            Logger.getLogger(RootLayerController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void handleIsPresent(ActionEvent event)
    {
        String timeStamp = new SimpleDateFormat("dd-MM-yyyy").format(Calendar.getInstance().getTime());
        boolean isAlreadyThere = false;
        ArrayList<Attendance> list = student.getAttendance();
        for (Attendance attendance : list)
        {
            if (attendance.getDate().equals(timeStamp))
            {
                isAlreadyThere = true;
            }
        }
        if (isAlreadyThere == false)
        {
            try
            {
                model.getOBSAttendance().add(model.addAtendance(student, true, true));
            } catch (SQLException ex)
            {
                System.out.println("Something went wrong when adding attendance, are you on the schools internet");
            }
            caluclateAttendance(student);
        } else
        {
            System.out.println("The attendance is already there");
        }

    }

    @FXML
    private void handleIsAbsent(ActionEvent event)
    {
        String timeStamp = new SimpleDateFormat("dd-MM-yyyy").format(Calendar.getInstance().getTime());
        boolean isAlreadyThere = false;
        ArrayList<Attendance> list = student.getAttendance();
        for (Attendance attendance : list)
        {
            if (attendance.getDate().equals(timeStamp))
            {
                isAlreadyThere = true;
            }
        }
        if (isAlreadyThere == false)
        {
            try
            {
                model.getOBSAttendance().add(model.addAtendance(student, false, true));
            } catch (SQLException ex)
            {
                System.out.println("Something went wrong when adding attendance, are you on the schools internet");
            }
            caluclateAttendance(student);
        } else
        {
            System.out.println("The attendance is already there");
        }
    }

    public void caluclateAttendance(Student student)
    {
        ArrayList<Attendance> attendanceList = student.getAttendance();
        double total = attendanceList.size();
        double notHere = 0;
        for (Attendance attendance : attendanceList)
        {
            if (attendance.isIsAttending() == false)
            {
                notHere++;
            }
        }
        double absence = (notHere / total) * 100;
        String formatted = String.format("%.2f", absence);
        lblAttendance.setText("Absence = " + formatted + "%");

    }

    @FXML
    private void handleEdit(ActionEvent event)
    {
        if (lstAttendance.getSelectionModel().getSelectedItem() == null)
        {
            lblHiden.setText("Please select a day to edit");
        } else
        {
            model.setAttendanceEdit(lstAttendance.getSelectionModel().getSelectedItem());
            {
                try
                {
                    Parent root;
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(StudentAttendance.class.getResource("GUI/EditAttendance.fxml"));
                    root = loader.load();
                    Stage stage = new Stage();
                    stage.setTitle("Edit Attendance");
                    stage.setScene(new Scene(root, 500, 200));
                    stage.show();

                    EditAttendanceController eaController = loader.getController();
                    eaController.setMsmodel(model);
                    eaController.connectController(this);
                } catch (IOException ex)
                {
                    System.out.println("Someting went wrong, are you on the schools internet");
                }
            }

        }
    }

    public void prevMonth()
    {
        //Go to previous month
        int prev = Integer.valueOf(currentMonth);
        if ((prev - 1) < 10)
        {
            prev = prev - 1;
            if (prev < 1)
            {
                Alert alertError = new Alert(Alert.AlertType.ERROR, "No attendance data from previous years", ButtonType.CLOSE);
                alertError.showAndWait();
            } else
            {
                currentMonth = "0" + prev;
            }
        } else if ((prev - 1) >= 10)
        {
            prev = prev - 1;
            currentMonth = "" + prev;
        }
    }

    public void nextMonth()
    {
        //Go to next month
        int next = Integer.valueOf(currentMonth);
        if ((next + 1) < 10)
        {
            next++;
            currentMonth = "0" + next;
        } else if ((next + 1) >= 10)
        {
            next = next + 1;
            if (next > 12)
            {
                Alert alertError = new Alert(Alert.AlertType.ERROR, "No attendance data from next year", ButtonType.CLOSE);
                alertError.showAndWait();
            } else
            {
                currentMonth = "" + next;
            }
        }
    }

    public ObservableList<Attendance> monthlyAttendanceFilter()
    {
        List<Attendance> temp = new ArrayList<>();
        ObservableList<Attendance> filtered;
        filtered = FXCollections.observableArrayList();
        String attendanceMonth;
        for (Attendance att : student.getAttendance())
        {
            attendanceMonth = Character.toString(att.getDate().charAt(3)) + Character.toString(att.getDate().charAt(4));
            if (att.isIsReal() == true && attendanceMonth.equals(currentMonth))
            {
                filtered.add(att);
            }

        }

        return filtered;

    }

    @FXML
    private void handlePrevMonth(ActionEvent event)
    {
        prevMonth();
        lstAttendance.setItems(monthlyAttendanceFilter());
        if (monthlyAttendanceFilter().isEmpty())
        {
            lblEmptyListview.setText("No attendance for this month..");
        } else
        {
            lblEmptyListview.setText("");
        }
        lblShowMonth.setText(simpleCurrentMonthTranslate(currentMonth));
    }

    @FXML
    private void handleNextMonth(ActionEvent event)
    {
        nextMonth();
        lstAttendance.setItems(monthlyAttendanceFilter());
        if (monthlyAttendanceFilter().isEmpty())
        {
            lblEmptyListview.setText("No attendance for this month..");
        } else
        {
            lblEmptyListview.setText("");
        }
        lblShowMonth.setText(simpleCurrentMonthTranslate(currentMonth));
    }

    public String simpleCurrentMonthTranslate(String currentmonth)
    {
        if (currentmonth.equals("01"))
        {
            return "January";
        }
        if (currentmonth.equals("02"))
        {
            return "February";
        }
        if (currentmonth.equals("03"))
        {
            return "March";
        }
        if (currentmonth.equals("04"))
        {
            return "April";
        }
        if (currentmonth.equals("05"))
        {
            return "May";
        }
        if (currentmonth.equals("06"))
        {
            return "June";
        }
        if (currentmonth.equals("07"))
        {
            return "July";
        }
        if (currentmonth.equals("08"))
        {
            return "August";
        }
        if (currentmonth.equals("09"))
        {
            return "September";
        }
        if (currentmonth.equals("10"))
        {
            return "October";
        }
        if (currentmonth.equals("11"))
        {
            return "November";
        }
        if (currentmonth.equals("12"))
        {
            return "December";
        }
        return "";
    }

}
