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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author sumai
 */
public class ParentOne extends JFrame implements ActionListener {

     private static final long serialVersionUID = 1L;
    private JTextField textField;
    private JPasswordField passwordField;
    private JButton LoginButton, BackButton, forgetbutton,SignUpButton;
    private JLabel label;
    private JPanel contentPanel;
    private JTextField tf1, tf2, tf3, tf4;
    //private JComboBox ComboBox;
    private boolean signedUp = false;
    public ParentOne(String selection) {
        
       setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(450, 190, 1014, 597);
        setResizable(false);
        contentPanel = new JPanel();
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPanel);
        contentPanel.setLayout(null);

        getContentPane().setBackground(new Color(125, 105, 170));

        JLabel Label1 = new JLabel("Login");
        Label1.setForeground(Color.BLACK);
        Label1.setFont(new Font("Times New Roman", Font.PLAIN, 46));
        Label1.setBounds(423, 13, 273, 93);
        contentPanel.add(Label1);

        textField = new JTextField();
        textField.setFont(new Font("Tahoma", Font.PLAIN, 32));
        textField.setBounds(481, 170, 225, 50);
        contentPanel.add(textField);
        textField.setColumns(10);

        passwordField = new JPasswordField();
        passwordField.setFont(new Font("Tahoma", Font.PLAIN, 32));
        passwordField.setBounds(481, 286, 225, 50);
        contentPanel.add(passwordField);

        JLabel lblUsername = new JLabel("Username");
        lblUsername.setBackground(Color.BLACK);
        lblUsername.setForeground(Color.BLACK);
        lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 31));
        lblUsername.setBounds(250, 166, 193, 52);
        contentPanel.add(lblUsername);

        JLabel lblPassword = new JLabel("Password");
        lblPassword.setForeground(Color.BLACK);
        lblPassword.setBackground(Color.CYAN);
        lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 31));
        lblPassword.setBounds(250, 286, 193, 52);
        contentPanel.add(lblPassword);

        BackButton = new JButton("Back");
        contentPanel.add(BackButton);
        BackButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
        BackButton.setBounds(225, 375, 200, 30);
        BackButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                PageOne ptb = new PageOne();
                ptb.setTitle("Select");
                ptb.setVisible(true);
            }
        });

        forgetbutton = new JButton("Forget Password");
        contentPanel.add(forgetbutton);
        forgetbutton.setFont(new Font("Tahoma", Font.PLAIN, 20));
        forgetbutton.setBounds(700, 415, 250, 35);
        forgetbutton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                ForgetT ptb = new ForgetT();
                ptb.setTitle("Fill up");
                ptb.setVisible(true);
            }
        });

        LoginButton = new JButton("Login");
        LoginButton.setFont(new Font("Tahoma", Font.PLAIN, 26));
        LoginButton.setBounds(700, 375, 250, 30);
        LoginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == LoginButton) {
                    String username = textField.getText();
                    String password = new String(passwordField.getPassword()); // Get the password as a string
                    if (validateTeacherLogin(username, password)) {
                        dispose();
//                        PageThreeT pageThreeT = new PageThreeT(username); // Pass the username to PageThreeT
//                        pageThreeT.setTitle("Welcome ");
//                        pageThreeT.setVisible(true);
                        JOptionPane.showMessageDialog(LoginButton, "You have successfully logged in");
                    } else {
                        JOptionPane.showMessageDialog(LoginButton, "Wrong Username & Password");
                    }
                }
            }

            private boolean validateTeacherLogin(String userName, String password) {
                try {
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/studentdemo", "root", "root");
                    PreparedStatement st = connection.prepareStatement("SELECT name, password FROM Parent WHERE username=? AND password=?");
                    st.setString(1, userName);
                    st.setString(2, password);
                    ResultSet rs = st.executeQuery();
                    boolean isValidLogin = rs.next();
                    rs.close();
                    st.close();
                    connection.close();
                    return isValidLogin;
                } catch (SQLException sqlException) {
                    sqlException.printStackTrace();
                    return false;
                }
            }
        });

        contentPanel.add(LoginButton);

        label = new JLabel("");
        label.setBounds(0, 0, 1008, 562);
        contentPanel.add(label);
    }
    public ParentOne() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(450, 190, 1014, 597);
        setResizable(false);
        contentPanel = new JPanel();
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPanel);
        contentPanel.setLayout(null);
        getContentPane().setBackground(new Color(150, 123, 182));

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
        JLabel label5 = new JLabel("Course");
        label5.setBackground(Color.BLACK);
        label5.setForeground(Color.BLACK);
        label5.setFont(new Font("Tahoma", Font.PLAIN, 20));
        label5.setBounds(125, 300, 200, 30);
        contentPanel.add(label5);

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
//
//        ComboBox = new JComboBox();
//        ComboBox.setFont(new Font("Tahoma", Font.PLAIN, 26));
//        ComboBox.setBounds(250, 300, 162, 28);
//
//        contentPanel.add(ComboBox);
//       // ComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"CS", "EE", "MATH", "GED"}));

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
                ptb.setTitle("Welcome");
                ptb.setVisible(true);

            }
        });
        contentPanel.add(BackButton);
        SignUpButton.addActionListener(this);
      //  SignUpButton.setEnabled(false);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == SignUpButton) {
            if (!signedUp) { // Ensure it's not already signed up
                saveTeacherToDatabase();
                signedUp = true; // Mark as signed up
            }
        } else {
            boolean isFieldsEmpty = tf1.getText().isEmpty() || tf2.getText().isEmpty()
                    || tf3.getText().isEmpty() || passwordField.getPassword().length == 0;

            SignUpButton.setEnabled(!isFieldsEmpty && !signedUp); // Disable after successful signup
        }
    }

    private void createTeacherTable() {
        try {
            java.sql.Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/studentdemo",
                    "root", "root");
            Statement statement = connection.createStatement();
            // Create the Teacher table with an auto-incremented ID
            String createTableSQL = "CREATE TABLE IF NOT EXISTS Parent ("
                    + "id INT AUTO_INCREMENT PRIMARY KEY, "
                    + "name VARCHAR(255), "
                    + "username VARCHAR(255), "
                    + "password VARCHAR(255), "
                    + "address VARCHAR(255) ";
                   
            statement.execute(createTableSQL);
            connection.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(SignUpButton, "Error: " + ex.getMessage());
        }
    }

    private void saveTeacherToDatabase() {
        String name = tf1.getText();
        String username = tf2.getText();
        char[] passwordChars = passwordField.getPassword();
        String password = new String(passwordChars);
        String address = tf3.getText();
        
        try {
           
            java.sql.Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/studentdemo",
                    "root", "root");
            String query = "INSERT INTO Parent (name, username, password, address) VALUES (?, ?, ?, ?)";

            PreparedStatement pstmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, name);
            pstmt.setString(2, username);
            pstmt.setString(3, password);
            pstmt.setString(4, address);
         
            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                // Retrieve the generated keys (ID)
                ResultSet generatedKeys = pstmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int teacherID = generatedKeys.getInt(1);
                    // Get the generated ID
                    //teacherID= id;
                    JOptionPane.showMessageDialog(SignUpButton, "You have successfully signed up as a teacher and your ID is " + teacherID);
                } else {
                    JOptionPane.showMessageDialog(SignUpButton, "Failed to retrieve your ID.");
                }
                dispose();
                String selection=null;
                ParentOne pt = new ParentOne(selection);
                pt.setVisible(true);
                pt.setTitle("Login");
            } else {
                JOptionPane.showMessageDialog(SignUpButton, "Failed to save information to the database.");
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
                    ParentOne frame = new ParentOne();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}



   
