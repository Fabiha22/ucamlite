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
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class NoticeT extends JFrame implements ActionListener {
    private JComboBox<String> yearComboBox;
    private JComboBox<String> courseComboBox;
    //private JComboBox<String> assessmentTypeComboBox;
    private JButton nextButton;

    public NoticeT() {
        setTitle("Notice For Students");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 200);
        setLayout(new FlowLayout());

        String[] yearOptions = {"First Year", "Second Year", "Third Year", "Fourth Year"};
        String[] courseOptions = {"CS", "EE", "GED", "MATH"};
        //String[] assessmentOptions = {"Quiz", "Mid", "Final"};

        yearComboBox = new JComboBox<>(yearOptions);
        courseComboBox = new JComboBox<>(courseOptions);
        //assessmentTypeComboBox = new JComboBox<>(assessmentOptions);
        nextButton = new JButton("Next");

        yearComboBox.setPreferredSize(new Dimension(150, 30));
        courseComboBox.setPreferredSize(new Dimension(150, 30));
        //assessmentTypeComboBox.setPreferredSize(new Dimension(150, 30));
        nextButton.setPreferredSize(new Dimension(100, 30));

        add(new JLabel("Select Year: "));
        add(yearComboBox);
        add(new JLabel("Select Course: "));
        add(courseComboBox);
        //add(new JLabel("Select Assessment Type: "));
        //add(assessmentTypeComboBox);
        add(nextButton);

        nextButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == nextButton) {
            String selectedYear = (String) yearComboBox.getSelectedItem();
            String selectedCourse = (String) courseComboBox.getSelectedItem();
            //String selectedAssessmentType = (String) assessmentTypeComboBox.getSelectedItem();

            // You can now use the selectedYear, selectedCourse, and selectedAssessmentType
            // for further processing, such as opening a new window or performing other actions.

            // Close the current window
            this.dispose();

            // Create a new window to handle the selected assessment type
            NoticeD noticed = new NoticeD(selectedYear, selectedCourse);
            noticed.setTitle("Notice");
            noticed.setVisible(true);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            DetailsT details = new DetailsT();
            details.setVisible(true);
        }); 
    }
}