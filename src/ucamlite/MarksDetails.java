/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ucamlite;

/**
 *
 * @author User
 */
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author sumai
 */
public class MarksDetails extends JFrame {
    private DefaultTableModel model;
    private JButton nextButton;
    private String assessmentType;
     private JPanel contentPanel;
    String username;
    
    public MarksDetails(){
        
    }

    public MarksDetails(String year, String course, String selectedAssessmentType) {
        this.assessmentType = selectedAssessmentType;
         setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(450, 190, 1014, 597);
        setResizable(false);
        contentPanel = new JPanel() {
            @Override

            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                try {
                    Image backgroundImage = ImageIO.read(new File("D:\\Third Semester\\WhatsApp Image 2023-10-30 at 09.26.14_a50d2529.jpg"));
                    if (backgroundImage != null) {
                        g.drawImage(backgroundImage, 0, 0, 500, getHeight(), this);
                    } else {
                        System.out.println("Failed to load the image.");
                    }
                } catch (IOException e) {
                    System.out.println("An error occurred while loading the image: " + e.getMessage());
                    e.printStackTrace();
                }
            }
        };

        // Create a table model with columns: Student ID, Student Name, Exam Marks, Obtained Marks
        model = new DefaultTableModel(new String[]{"Student ID", "Student Name", "Exam Marks", "Obtained Marks"}, 0);

        JTable table = new JTable(model);
        table.getColumnModel().getColumn(0).setPreferredWidth(80);
        table.getColumnModel().getColumn(1).setPreferredWidth(150);

        // Add the table to a scroll pane
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // Create a "Next" button
        nextButton = new JButton("Next");
        add(nextButton, BorderLayout.SOUTH);

        // Add an action listener to the "Next" button
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                retrieveStudentDataFromDatabase(year, course);
            }
        });
    }

    private void retrieveStudentDataFromDatabase(String year, String course) {
        // Replace these values with your database connection details
        String jdbcUrl = "jdbc:mysql://localhost:3306/demo";
        String dbUser = "root";
        String dbPassword = "root";

        String query = "SELECT id, name FROM student WHERE year = ? AND course = ?";

        try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, year);
            preparedStatement.setString(2, course);
            ResultSet resultSet = preparedStatement.executeQuery();

            // Clear existing data in the table
            model.setRowCount(0);

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                model.addRow(new Object[]{id, name, "", ""});
            }
            
            // Enable the "Next" button to save data
            nextButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    saveMarksToDatabase(year, course, assessmentType);
                }
            });
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error while fetching student details: " + e.getMessage());
        }
    }

   



    public void saveMarksToDatabase(String year, String course, String assessmentType) {
    String jdbcUrl = "jdbc:mysql://localhost:3306/demo";
    String dbUser = "root";
    String dbPassword = "root";
    String insertQuery = "INSERT INTO marks (id, year, course, assessment_type, exam_marks, obtained_marks) VALUES (?, ?, ?, ?, ?, ?)";

    try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword);
         PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {

        for (int row = 0; row < model.getRowCount(); row++) {
            int studentId = (int) model.getValueAt(row, 0);
            String examMarks = model.getValueAt(row, 2).toString();
            String obtainedMarks = model.getValueAt(row, 3).toString();

            preparedStatement.setInt(1, studentId);
            preparedStatement.setString(2, year);
            preparedStatement.setString(3, course);
            preparedStatement.setString(4, assessmentType);

            // Handle empty strings and convert them to null or appropriate default values
            if (examMarks.isEmpty()) {
                preparedStatement.setNull(5, java.sql.Types.INTEGER);
            } else {
                preparedStatement.setInt(5, Integer.parseInt(examMarks));
            }

            if (obtainedMarks.isEmpty()) {
                preparedStatement.setNull(6, java.sql.Types.INTEGER);
            } else {
                preparedStatement.setInt(6, Integer.parseInt(obtainedMarks));
            }

            preparedStatement.executeUpdate();
        }

        JOptionPane.showMessageDialog(this, "Marks have been saved to the database.");
        dispose();
         PageThreeT obj = new PageThreeT();
                    obj.setTitle("Student-Login");
                    obj.setVisible(true);
    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error while saving marks: " + e.getMessage());
    }
}
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MarksDetails frame = new MarksDetails();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
    });
}

}