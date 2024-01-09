/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ucamlite;

/**
 *
 * @author User
 */
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class PageThreeS extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;
    private JLabel label;
    private JPanel contentPanel;
    private JButton Details, Attendance, Marks, Remarks, NoticeBoard, BackButton;
    public int id;

    public PageThreeS(int id) {
        this.id = id;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(450, 190, 1014, 597);
        setResizable(false);
        contentPanel = new JPanel();
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPanel);
        contentPanel.setLayout(null);
        JLabel lblNewLabel = new JLabel("Dashboard");
        lblNewLabel.setForeground(Color.BLACK);
        lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 46));
        lblNewLabel.setBounds(423, 13, 273, 93);
        contentPanel.add(lblNewLabel);

        BackButton = new JButton("Logout");
        contentPanel.add(BackButton);
        BackButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
        BackButton.setBounds(650, 390, 200, 50);
        BackButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                int a = JOptionPane.showConfirmDialog(BackButton, "Are you sure?");
                if (a == JOptionPane.YES_OPTION) {
                    dispose();
                    PageOne ptb = new PageOne();
                    ptb.setTitle("Select");
                    ptb.setVisible(true);
                } else {
                    dispose();
                    PageThreeS obj = new PageThreeS(id);
                    obj.setTitle("Student-Login");
                    obj.setVisible(true);
                }
            }
        });

        Details = new JButton("Details");
        Details.setFont(new Font("Tahoma", Font.PLAIN, 26));
        Details.setBounds(100, 100, 300, 50);
        Details.addActionListener(this);
        contentPanel.add(Details);

        Attendance = new JButton("Attendance");
        Attendance.setFont(new Font("Tahoma", Font.PLAIN, 26));
        Attendance.setBounds(100, 160, 300, 50);
        Attendance.addActionListener(this);
        contentPanel.add(Attendance);

        Marks = new JButton("Marks");
        Marks.setFont(new Font("Tahoma", Font.PLAIN, 26));
        Marks.setBounds(100, 220, 300, 50);
        Marks.addActionListener(this);
        contentPanel.add(Marks);

        Remarks = new JButton("Remarks");
        Remarks.setFont(new Font("Tahoma", Font.PLAIN, 26));
        Remarks.setBounds(100, 280, 300, 50);
        Remarks.addActionListener(this);
        contentPanel.add(Remarks);

        NoticeBoard = new JButton("Notice Board");
        NoticeBoard.setFont(new Font("Tahoma", Font.PLAIN, 26));
        NoticeBoard.setBounds(100, 340, 300, 50);
        NoticeBoard.addActionListener(this);
        contentPanel.add(NoticeBoard);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == Details) {
            //displayStudentDetails();
        } else if (e.getSource() == Attendance) {
            //JOptionPane.showMessageDialog(Attendance, "Attendance: ");
            //int studentID=2; // Replace with the actual student ID
            AttendenceS attendanceWindow = new AttendenceS(id);
            attendanceWindow.setVisible(true);
            /* Display attendance information here */
        } else if (e.getSource() == Marks) {
            // JOptionPane.showMessageDialog(Marks, "Marks: ");
            /* Display marks here */
            displayStudentMarks();
        } else if (e.getSource() == Remarks) {
            //JOptionPane.showMessageDialog(Remarks, "Remarks: ");
            /* Display remarks here */
//             DetailsT details = new DetailsT();
//        details.setTitle("Remarks for Students");
//        details.setVisible(true);
            displayStudentRemarks();
        } else if (e.getSource() == NoticeBoard) {
            //JOptionPane.showMessageDialog(NoticeBoard, "Notice Board: ");
            /* Display notice board information here */
            NoticeT notice = new NoticeT();
            notice.setTitle("Notice for Students");
        notice.setVisible(true);
        }
    }

    private void displayStudentMarks() {
        // Replace these values with your database connection details
        String jdbcUrl = "jdbc:mysql://localhost:3306/demo";
        String dbUser = "root";
        String dbPassword = "root";

        String query = "SELECT assessment_type, exam_marks, obtained_marks FROM marks WHERE id = ?";

        try (
                 java.sql.Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo", "root", "root");  PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            StringBuilder marksDetails = new StringBuilder("Marks Dashboard:\n");

            while (resultSet.next()) {
                String assessmentType = resultSet.getString("assessment_type");
                int examMarks = resultSet.getInt("exam_marks");
                int obtainedMarks = resultSet.getInt("obtained_marks");

                marksDetails.append("Assessment Type: ").append(assessmentType)
                        .append(", Exam Marks: ").append(examMarks)
                        .append(", Obtained Marks: ").append(obtainedMarks)
                        .append("\n");
            }

            JOptionPane.showMessageDialog(this, marksDetails.toString());
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error while fetching marks: " + ex.getMessage());
        }
    }

    private void displayStudentRemarks() {
        // Replace these values with your database connection details
        String jdbcUrl = "jdbc:mysql://localhost:3306/demo";
        String dbUser = "root";
        String dbPassword = "root";

        String query = "SELECT year, comment FROM remarks WHERE id = ?";

        try (
                 java.sql.Connection connection = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword);  PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            StringBuilder remarksDetails = new StringBuilder("Remarks:\n");

            while (resultSet.next()) {
                String year = resultSet.getString("year");
                String comment = resultSet.getString("comment");

                if (comment != null && !comment.isEmpty()) {
                    remarksDetails.append("Year: ").append(year)
                            .append(", Comment: ").append(comment)
                            .append("\n");
                }
            }

            if (remarksDetails.length() == 0) {
                remarksDetails.append("No remarks available.");
            }

            JOptionPane.showMessageDialog(this, remarksDetails.toString());
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error while fetching remarks: " + ex.getMessage());
        }
    }

    private void displayDetails() {
        try {
            java.sql.Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo", "root", "root");
            String sql = "SELECT name, id, address, coursetype FROM Teacher WHERE username=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            //statement.setString(1, this.username); // Use the instance variable directly
            ResultSet result = statement.executeQuery();

            if (result.next()) {
                String name = result.getString("name");
                int id = result.getInt("id");
                String address = result.getString("address");
                String course = result.getString("course");

                String detailsMessage = "Name: " + name + "\nID: " + id + "\nAddress: " + address + "\ncourse: " + course;
                JOptionPane.showMessageDialog(Details, detailsMessage);
            } else {
                JOptionPane.showMessageDialog(Details, "Teacher not found");
            }

            result.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(Details, "Error while fetching teacher details: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            int id = 12;

            public void run() {
                try {
                    // Pass the username to the constructor of PageThreeS
                    //int id = id; // Replace with the actual username
                    PageThreeS frame = new PageThreeS(id);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
