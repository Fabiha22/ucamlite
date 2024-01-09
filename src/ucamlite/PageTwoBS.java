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
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author User
 */
public class PageTwoBS extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;
    private JTextField tf1, tf2, tf3, tf4;
    private JPasswordField passwordField;
    private JButton SignUpButton, BackButton;
    private JLabel label;
    private JPanel contentPanel;
    //JCheckBox1 = new javax.swing.JCheckBox();
    private JComboBox yearComboBox, courseComboBox;
    private boolean signedUp = false;

    public PageTwoBS() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(450, 190, 1014, 597);
        setResizable(false);
        contentPanel = new JPanel();
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPanel);
        contentPanel.setLayout(null);

        JLabel Label1 = new JLabel("Signing Up");
        Label1.setForeground(Color.BLACK);
        Label1.setFont(new Font("Times New Roman", Font.PLAIN, 46));
        Label1.setBounds(423, 13, 273, 93);
        contentPanel.add(Label1);

        JLabel label1 = new JLabel("Name");
        label1.setBackground(Color.BLACK);
        label1.setForeground(Color.BLACK);
        label1.setFont(new Font("Tahoma", Font.PLAIN, 20));
        label1.setBounds(125, 100, 200, 30);
        contentPanel.add(label1);

        JLabel label2 = new JLabel("Username");
        label2.setBackground(Color.BLACK);
        label2.setForeground(Color.BLACK);
        label2.setFont(new Font("Tahoma", Font.PLAIN, 20));
        label2.setBounds(125, 150, 200, 30);
        contentPanel.add(label2);
        JLabel label3 = new JLabel("Password");
        label3.setBackground(Color.BLACK);
        label3.setForeground(Color.BLACK);
        label3.setFont(new Font("Tahoma", Font.PLAIN, 20));
        label3.setBounds(125, 200, 200, 30);
        contentPanel.add(label3);

        JLabel label4 = new JLabel("Address");
        label4.setBackground(Color.BLACK);
        label4.setForeground(Color.BLACK);
        label4.setFont(new Font("Tahoma", Font.PLAIN, 20));
        label4.setBounds(125, 250, 200, 30);
        contentPanel.add(label4);
        JLabel label5 = new JLabel("Year");
        label5.setBackground(Color.BLACK);
        label5.setForeground(Color.BLACK);
        label5.setFont(new Font("Tahoma", Font.PLAIN, 20));
        label5.setBounds(125, 300, 200, 30);
        contentPanel.add(label5);
        
        JLabel label6 = new JLabel("Course");
        label6.setBackground(Color.BLACK);
        label6.setForeground(Color.BLACK);
        label6.setFont(new Font("Tahoma", Font.PLAIN, 20));
        label6.setBounds(125, 350, 200, 30);
        contentPanel.add(label6);

        tf1 = new JTextField();
        tf1.setFont(new Font("Tahoma", Font.PLAIN, 20));
        tf1.setBounds(250, 100, 280, 30);
        contentPanel.add(tf1);
        tf1.setColumns(10);
        tf2 = new JTextField();
        tf2.setFont(new Font("Tahoma", Font.PLAIN, 20));
        tf2.setBounds(250, 150, 281, 30);
        contentPanel.add(tf2);
        tf2.setColumns(10);
        tf3 = new JTextField();
        tf3.setFont(new Font("Tahoma", Font.PLAIN, 20));
        tf3.setBounds(250, 250, 281, 30);
        contentPanel.add(tf3);
        tf3.setColumns(10);

        passwordField = new JPasswordField();
        passwordField.setFont(new Font("Tahoma", Font.PLAIN, 32));
        passwordField.setBounds(250, 200, 281, 30);
        contentPanel.add(passwordField);

         yearComboBox = new JComboBox();
        yearComboBox.setFont(new Font("Tahoma", Font.PLAIN, 26));
        yearComboBox.setBounds(250, 300, 162, 28);
        contentPanel.add(yearComboBox);
        yearComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"First year", "Second year", "Third year", "Fourth year"}));

        courseComboBox = new JComboBox();
        courseComboBox.setFont(new Font("Tahoma", Font.PLAIN, 26));
        courseComboBox.setBounds(250, 350, 162, 28);
        contentPanel.add(courseComboBox);
        courseComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"CS", "EE", "GED", "MATH"}));

        SignUpButton = new JButton("SignUp");
        SignUpButton.setFont(new Font("Tahoma", Font.PLAIN, 26));
        SignUpButton.setBounds(650, 392, 162, 50);
        contentPanel.add(SignUpButton);

        tf1.addActionListener(this);
        tf2.addActionListener(this);
        tf3.addActionListener(this);
        BackButton = new JButton("Back?");
        BackButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
        BackButton.setBounds(150, 390, 200, 50);

        BackButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                dispose();
                PageOne ptb = new PageOne();
                ptb.setTitle("Select");
                ptb.setVisible(true);

            }
        });
        contentPanel.add(BackButton);
        yearComboBox.addActionListener(this);
        courseComboBox.addActionListener(this);
        SignUpButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == SignUpButton) {
            if (!signedUp) { // Ensure it's not already signed up
                saveStudentToDatabase();
                signedUp = true; // Mark as signed up
            }
        } else {
            boolean isFieldsEmpty = tf1.getText().isEmpty() || tf2.getText().isEmpty()
                    || tf3.getText().isEmpty() || passwordField.getPassword().length == 0;

            SignUpButton.setEnabled(!isFieldsEmpty && !signedUp); // Disable after successful signup
        }
    }

    private void createStudentTable() {
        try {
            java.sql.Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo",
                    "root", "root");
            Statement statement = connection.createStatement();
            // Create the Teacher table with an auto-incremented ID
            String createTableSQL = "CREATE TABLE IF NOT EXISTS Student ("
                    + "id INT AUTO_INCREMENT PRIMARY KEY, "
                    + "name VARCHAR(255), "
                    + "username VARCHAR(255), "
                    + "password VARCHAR(255), "
                    + "address VARCHAR(255), "
                    + "year VARCHAR(255)),"
                    + "course VARCHAR(255)";
            statement.execute(createTableSQL);
            connection.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(SignUpButton, "Error: " + ex.getMessage());
        }
    }

    private void saveStudentToDatabase() {
        String name = tf1.getText();
        String username = tf2.getText();
        char[] passwordChars = passwordField.getPassword();
        String password = new String(passwordChars);
        String address = tf3.getText();
        String year = yearComboBox.getSelectedItem().toString();
         String course = courseComboBox.getSelectedItem().toString();


        try {
            // Establish a database connection
            java.sql.Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo",
                    "root", "root");
            // Create a SQL INSERT statement
            String query = "INSERT INTO Student (name, username, password, address, year,course) VALUES (?, ?, ?, ?, ?, ?)";

            // Create a PreparedStatement
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, name);
            pstmt.setString(2, username);
            pstmt.setString(3, password);
            pstmt.setString(4, address);
            //String year = null;
            pstmt.setString(5, year);
             pstmt.setString(6, course);
            

            // Execute the INSERT statement to save the student's information
            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                ResultSet generatedKeys = pstmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int studentID = generatedKeys.getInt(1);
                    // Get the generated ID
                    //teacherID= id;
                    JOptionPane.showMessageDialog(SignUpButton, "You have successfully signed up as a student and your ID is " + studentID);
                } else {
                    JOptionPane.showMessageDialog(SignUpButton, "Failed to retrieve your ID.");
                }
            }

            // Close the database connection
            connection.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(SignUpButton, "Error: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    PageTwoBS frame = new PageTwoBS();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

}
