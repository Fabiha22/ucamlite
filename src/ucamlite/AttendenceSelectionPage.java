/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ucamlite;

/**
 *
 * @author User
 */
import com.toedter.calendar.JDateChooser;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.border.EmptyBorder;
import java.lang.String;

public class AttendenceSelectionPage extends JFrame implements ActionListener {

    private JPanel contentPanel;
    public JButton back, next;
    public JComboBox<String> courseComboBox, yearComboBox;
    public JDateChooser dateChooser;
    String year;

    public AttendenceSelectionPage() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(450, 190, 1014, 597);
        setResizable(false);
        contentPanel = new JPanel();
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPanel);
        contentPanel.setLayout(null);

        JLabel lblNewLabel = new JLabel("Attendance Selection");
        lblNewLabel.setForeground(Color.BLACK);
        lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 30));
        lblNewLabel.setBounds(423, 13, 273, 50);
        contentPanel.add(lblNewLabel);

        dateChooser = new JDateChooser();
        dateChooser.setBounds(100, 100, 300, 50);
        contentPanel.add(dateChooser);
        courseComboBox = new JComboBox<>();
        courseComboBox.addItem("Select Course");
        courseComboBox.addItem("CS");
        courseComboBox.addItem("EE");
        courseComboBox.addItem("GED");
        courseComboBox.addItem("MATH");
        courseComboBox.setBounds(100, 160, 300, 50);
        contentPanel.add(courseComboBox);

        yearComboBox = new JComboBox<>();
        yearComboBox.addItem("Select Year");
        yearComboBox.addItem("First Year");
        yearComboBox.addItem("Second Year");
        yearComboBox.addItem("Third Year");
        yearComboBox.addItem("Fourth Year");
        yearComboBox.setBounds(100, 220, 300, 50);
        contentPanel.add(yearComboBox);

        back = new JButton("Back");
        back.setFont(new Font("Tahoma", Font.PLAIN, 20));
        back.setBounds(100, 280, 150, 50);
        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Handle back button action
                dispose();
                PageThreeT pageThreeT = new PageThreeT();
                pageThreeT.setTitle("Dashboard");
                pageThreeT.setVisible(true);
            }
        });
        contentPanel.add(back);

        next = new JButton("Next");
        next.setFont(new Font("Tahoma", Font.PLAIN, 20));
        next.setBounds(250, 280, 150, 50);
        next.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
        // Check if all options are selected
        Date selectedDate = dateChooser.getDate();
        if (selectedDate != null
                && courseComboBox.getSelectedIndex() != 0
                && yearComboBox.getSelectedIndex() != 0) {
            // Format the date as a string
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String selectedDateString = dateFormat.format(selectedDate);

            // Get the selected year and course
            String selectedYear = (String) yearComboBox.getSelectedItem();
            String selectedCourse = (String) courseComboBox.getSelectedItem();

            // Proceed to the next page (Attendance Window)
            dispose();
            AttendenceWindow attendanceWindow = new AttendenceWindow(selectedYear, selectedDateString, selectedCourse);
//            attendanceWindow.setTitle("Attendance Window");
//            attendanceWindow.setVisible(true);
        } else {
            // Show an error message if not all options are selected
            JOptionPane.showMessageDialog(next, "Please select all options.");
        }
    }
});


        contentPanel.add(next);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Handle any other action events if needed
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                AttendenceSelectionPage attendancePage = new AttendenceSelectionPage();
                attendancePage.setTitle("Attendance Selection");
                attendancePage.setVisible(true);
            }
        });
    }
}
