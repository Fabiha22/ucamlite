/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ucamlite;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;

/**
 *
 * @author User
 */
public class StudentSelectionFrame extends JFrame implements ActionListener {
    private JComboBox<String> studentList;
    private JButton selectButton;

    public StudentSelectionFrame() {
        // Initialize the frame and components
        // Populate the studentList with student names or IDs
        // Add an action listener to the selectButton to pass the selected student ID to PageThreeS
    }

    @Override
   
public void actionPerformed(ActionEvent e) {
    if (e.getSource() == selectButton) {
        String selectedStudentName = (String) studentList.getSelectedItem();
        int studentID = getStudentIDByName(selectedStudentName); // Implement this method to retrieve the ID from the name
        if (studentID != -1) {
            PageThreeS pageThree = new PageThreeS(studentID);
            pageThree.setVisible(true);
            dispose(); // Close the selection frame
        } else {
            // Handle the case where the student ID is not found
        }
    }
}

private int getStudentIDByName(String studentName) {
    // Implement this method to query your database or data source
    // and return the corresponding ID for the given studentName
    int studentID = -1; // Default value if not found
    // Perform the database query here and set studentID
    return studentID;
}

    }


