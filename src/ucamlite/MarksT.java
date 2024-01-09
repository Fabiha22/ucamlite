/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ucamlite;

/**
 *
 * @author User
 */
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author sumai
 */
public class MarksT extends JFrame implements ActionListener {

    private JComboBox<String> yearComboBox;
    private JComboBox<String> courseComboBox;
    private JComboBox<String> assessmentTypeComboBox;
    private JButton nextButton, backbutton;
    private JPanel contentPanel;
    String username;

    public MarksT() {
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
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPanel);
        contentPanel.setLayout(null);
        JLabel label1 = new JLabel("Year");
        label1.setBackground(Color.BLACK);
        label1.setForeground(Color.BLACK);
        label1.setFont(new Font("Tahoma", Font.PLAIN, 20));
        label1.setBounds(625, 100, 200, 30);
        contentPanel.add(label1);

        JLabel label2 = new JLabel("Course");
        label2.setBackground(Color.BLACK);
        label2.setForeground(Color.BLACK);
        label2.setFont(new Font("Tahoma", Font.PLAIN, 20));
        label2.setBounds(625, 150, 200, 30);
        contentPanel.add(label2);
        JLabel label3 = new JLabel("Exam Type");
        label3.setBackground(Color.BLACK);
        label3.setForeground(Color.BLACK);
        label3.setFont(new Font("Tahoma", Font.PLAIN, 20));
        label3.setBounds(625, 200, 200, 30);
        contentPanel.add(label3);
        backbutton = new JButton("Back");
        contentPanel.add(backbutton);
        backbutton.setFont(new Font("Tahoma", Font.PLAIN, 20));
        backbutton.setBounds(545, 390, 150, 30);
        backbutton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int a = JOptionPane.showConfirmDialog(backbutton, "Are you sure?");
                if (a == JOptionPane.YES_OPTION) {
                    dispose();
                    PageThreeT obj = new PageThreeT();
                    obj.setTitle("Student-Login");
                    obj.setVisible(true);
                } else {
                    dispose();
                    MarksT obj = new MarksT();
                    obj.setTitle("Student-Login");
                    obj.setVisible(true);

                }
            }
        });
      
        yearComboBox = new JComboBox();
        yearComboBox.setFont(new Font("Tahoma", Font.PLAIN, 26));
        yearComboBox.setBounds(750, 100, 162, 28);

        courseComboBox = new JComboBox();
        courseComboBox.setFont(new Font("Tahoma", Font.PLAIN, 26));
        courseComboBox.setBounds(750, 150, 162, 28);

        assessmentTypeComboBox = new JComboBox();
        assessmentTypeComboBox.setFont(new Font("Tahoma", Font.PLAIN, 26));
        assessmentTypeComboBox.setBounds(750, 200, 162, 28);
 yearComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"first year", "second year", "third year", "fourth year"}));
 courseComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"CS", "EE", "GED", "MATH"}));
 assessmentTypeComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Quiz", "Mid", "Final"}));

        nextButton = new JButton("Next");
        nextButton.setFont(new Font("Tahoma", Font.PLAIN, 26));
        nextButton.setBounds(750, 392, 162, 30);
      
        contentPanel.add(yearComboBox);
        
        contentPanel.add(courseComboBox);
        
        contentPanel.add(assessmentTypeComboBox);
        contentPanel.add(nextButton);

        nextButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == nextButton) {
            String selectedYear = (String) yearComboBox.getSelectedItem();
            String selectedCourse = (String) courseComboBox.getSelectedItem();
            String selectedAssessmentType = (String) assessmentTypeComboBox.getSelectedItem();

            // You can now use the selectedYear, selectedCourse, and selectedAssessmentType
            // for further processing, such as opening a new window or performing other actions.
            // Close the current window
            this.dispose();

            // Create a new window to handle the selected assessment type
            MarksDetails marksDetailsWindow = new MarksDetails(selectedYear, selectedCourse, selectedAssessmentType);
            marksDetailsWindow.setTitle("Marks Details");
            marksDetailsWindow.setVisible(true);
        }
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MarksT frame = new MarksT();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
   });
}
}