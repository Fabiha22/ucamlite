/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ucamlite;

/**
 *
 * @author User
 */
import com.sun.jdi.connect.spi.Connection;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PageThreeT extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;
    private JLabel label;
    private JPanel contentPanel;
    private JButton Details, Attendance, Marks, Remarks, NoticeBoard, BackButton;
    String username;

    public PageThreeT() {
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
                    PageThreeT obj = new PageThreeT();
                    obj.setTitle("Teacher-Login");
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

            displayDetails();
        } else if (e.getSource() == Attendance) {

            AttendenceSelectionPage attendancePage = new AttendenceSelectionPage();
            attendancePage.setTitle("Attendance Selection");
            attendancePage.setVisible(true);

        } else if (e.getSource() == Marks) {
            //JOptionPane.showMessageDialog(Marks, "Marks: ");
            MarksT marksWindow = new MarksT();
            marksWindow.setTitle("Marks Selection");
            marksWindow.setVisible(true);
        } else if (e.getSource() == Remarks) {
            //JOptionPane.showMessageDialog(Remarks, "Remarks: ");
            /* Display remarks here */
            DetailsT details = new DetailsT();
            details.setTitle("Remarks for students");
            details.setVisible(true);
        } else if (e.getSource() == NoticeBoard) {
            // JOptionPane.showMessageDialog(NoticeBoard, "Notice Board: ");
            /* Display notice board information here */
            NoticeT noticet = new NoticeT();
            noticet.setTitle("Remarks for students");
            noticet.setVisible(true);
        }
    }

    private void displayDetails() {
        try {
            java.sql.Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo", "root", "root");
            String sql = "SELECT name, id, address, coursetype FROM Teacher WHERE username=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, this.username); // Use the instance variable directly
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
            public void run() {
                try {
                    PageThreeT frame = new PageThreeT();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
