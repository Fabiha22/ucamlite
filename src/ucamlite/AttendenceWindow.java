/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ucamlite;

/**
 *
 * @author User
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AttendenceWindow {

    private JFrame frame;
    private JPanel panel;
    private JButton submitButton;
    private List<StudentInfo> students;
    private Date currentDate;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
     private String selectedYear;
    private String selectedDateString;
    private String selectedCourse;
    private JPanel contentPanel;
    public AttendenceWindow(String year, String date, String course) {
        
        contentPanel = new JPanel();
        this.selectedYear = year;
        this.selectedDateString = date;
        this.selectedCourse = course;

        
        frame = new JFrame("Attendance Entry");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(800, 800);

        // Initialize currentDate with the current date
        currentDate = new Date();

        panel = new JPanel();
        panel.setLayout(new GridLayout(0, 4));

        panel.add(new JLabel("Student ID"));
        panel.add(new JLabel("Student Name"));
        panel.add(new JLabel("Present"));
        panel.add(new JLabel("Absent"));

        // Retrieve student data from the database
        fetchStudentDataFromDatabase();

        // Create checkboxes for each student
        for (StudentInfo student : students) {
    JLabel studentIdLabel = new JLabel(String.valueOf(student.getStudentId()));
    JLabel studentNameLabel = new JLabel(student.getStudentName());
    JCheckBox presentCheckbox = new JCheckBox("Present");
    JCheckBox absentCheckbox = new JCheckBox("Absent");

    panel.add(studentIdLabel);
    panel.add(studentNameLabel);
    panel.add(presentCheckbox);
    panel.add(absentCheckbox);

    // Add action listeners to checkboxes
    presentCheckbox.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            student.setPresent(true);
            absentCheckbox.setSelected(false);
        }
    });

    absentCheckbox.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            student.setPresent(false);
            presentCheckbox.setSelected(false);
        }
    });
}


        submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Save attendance records to the database
                saveAttendanceToDatabase();
                frame.dispose();
            }
        });

        panel.add(submitButton);
        frame.add(panel);
        frame.setVisible(true);
    }

    private void fetchStudentDataFromDatabase() {
        students = new ArrayList<>();

        String jdbcUrl = "jdbc:mysql://localhost:3306/demo";
        String dbUser = "root";
        String dbPassword = "root";
        String query = "SELECT id, name FROM student";

        try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword); PreparedStatement preparedStatement = connection.prepareStatement(query); ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                students.add(new StudentInfo(id, name));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void saveAttendanceToDatabase() {
        String jdbcUrl = "jdbc:mysql://localhost:3306/demo";
        String dbUser = "root";
        String dbPassword = "root";
        String insertQuery = "INSERT INTO attendence (id, date, status) VALUES (?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword); PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {

            for (StudentInfo student : students) {
                preparedStatement.setInt(1, student.getStudentId());
                preparedStatement.setString(2, dateFormat.format(currentDate));
                if (student.isPresent()) {
                    preparedStatement.setString(3, "Present");
                } else {
                    preparedStatement.setString(3, "Absent");
                }
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

   public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> new AttendenceWindow("SampleYear", "SampleDate", "SampleCourse"));
}

}
