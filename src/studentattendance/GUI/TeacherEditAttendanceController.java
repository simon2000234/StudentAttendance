/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentattendance.GUI;

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
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import studentattendance.BE.Attendance;
import studentattendance.BE.Student;

/**
 * FXML Controller class
 *
 * @author Caspe
 */
public class TeacherEditAttendanceController implements Initializable
{

    @FXML
    private Label lblSelectedStudent;
    @FXML
    private Button btnPresent;
    @FXML
    private Button btnAbsent;
    @FXML
    private ListView<Attendance> lstStudentAttendance;
    @FXML
    private Button btnPrevMonth;
    @FXML
    private Button btnNextMonth;
    @FXML
    private Label lblShowMonth;

    private String today;
    private String currentMonth;
    private SAModel model;
    private Student student;
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
        lblShowMonth.setText(simpleCurrentMonthTranslate(currentMonth));
    }

    @FXML
    private void handlePrevMonth(ActionEvent event)
    {
        prevMonth();
        lstStudentAttendance.setItems(monthlyAttendanceFilter());
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
        lstStudentAttendance.setItems(monthlyAttendanceFilter());
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
    private void handlePresent(ActionEvent event)
    {
        Attendance attendance = lstStudentAttendance.getSelectionModel().getSelectedItem();
        if (attendance != null)
        {
            try
            {
                model.teacherEditAttendance(attendance, true, model.getSelectedStudent());
                lstStudentAttendance.setItems(monthlyAttendanceFilter());
                model.updateStudents();
            } catch (SQLException ex)
            {
                Logger.getLogger(TeacherEditAttendanceController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }

    @FXML
    private void handleAbsent(ActionEvent event)
    {
        Attendance attendance = lstStudentAttendance.getSelectionModel().getSelectedItem();
        if (attendance != null)
        {
            try
            {
                model.teacherEditAttendance(attendance, false, model.getSelectedStudent());
                lstStudentAttendance.setItems(monthlyAttendanceFilter());
                model.updateStudents();
            } catch (SQLException ex)
            {
                Logger.getLogger(TeacherEditAttendanceController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void setModel(SAModel model)
    {
        this.model = model;
        lblSelectedStudent.setText(model.getSelectedStudent().getName() + "'s attendance");
        student = (Student) model.getSelectedStudent();
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
        lstStudentAttendance.setItems(monthlyAttendanceFilter());
        if (monthlyAttendanceFilter().isEmpty())
        {
            lblEmptyListview.setText("No attendance for this month..");
        }
    }

    public void prevMonth()
    {
        //Go to previous month
        int prev = Integer.parseInt(currentMonth);
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
        int next = Integer.parseInt(currentMonth);
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
