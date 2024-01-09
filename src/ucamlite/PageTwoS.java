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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author User
 */
public class PageTwoS extends JFrame {

    private static final long serialVersionUID = 1L;
    private JTextField textField;
    private JPasswordField passwordField;
    private JButton LoginButton, BackButton,forgetbutton;
    private JLabel label;
    private JPanel contentPanel;
    private int id;

    public PageTwoS() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(450, 190, 1014, 597);
        setResizable(false);
         contentPanel = new JPanel(){
            @Override
            
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                try {
                    Image backgroundImage = ImageIO.read(new File("C:\\Users\\User\\Downloads\\bup.jpg"));
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

        JLabel Label1 = new JLabel("Login");
        Label1.setForeground(Color.BLACK);
        Label1.setFont(new Font("Times New Roman", Font.PLAIN, 46));
        Label1.setBounds(550, 13, 273, 93);
        contentPanel.add(Label1);

        textField = new JTextField();
        textField.setFont(new Font("Tahoma", Font.PLAIN, 32));
        textField.setBounds(725, 170, 225, 40);
        contentPanel.add(textField);
        textField.setColumns(10);

        passwordField = new JPasswordField();
        passwordField.setFont(new Font("Tahoma", Font.PLAIN, 32));
        passwordField.setBounds(725, 286, 225, 50);
        contentPanel.add(passwordField);

        JLabel lblUsername = new JLabel("Username");
        lblUsername.setBackground(Color.BLACK);
        lblUsername.setForeground(Color.BLACK);
        lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 31));
        lblUsername.setBounds(525, 166, 193, 52);
        contentPanel.add(lblUsername);

        JLabel lblPassword = new JLabel("Password");
        lblPassword.setForeground(Color.BLACK);
        lblPassword.setBackground(Color.CYAN);
        lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 31));
        lblPassword.setBounds(525, 286, 193, 52);
        contentPanel.add(lblPassword);
        BackButton = new JButton("Back?");
        contentPanel.add(BackButton);
        BackButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
        BackButton.setBounds(325, 400, 200, 30);
        BackButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                dispose();
                PageOne ptb = new PageOne();
                ptb.setTitle("Select");
                ptb.setVisible(true);

            }
        });
        forgetbutton = new JButton("forget paasword?");
        contentPanel.add(forgetbutton);
        forgetbutton.setFont(new Font("Tahoma", Font.PLAIN, 20));
        forgetbutton.setBounds(700, 415, 250, 35);
        forgetbutton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                dispose();
                ForgetS ptb = new ForgetS();
                ptb.setTitle("Select");
                ptb.setVisible(true);

            }
        });
        LoginButton = new JButton("Login");
        LoginButton.setFont(new Font("Tahoma", Font.PLAIN, 26));
        LoginButton.setBounds(700, 375, 250, 30);
        LoginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String userName = textField.getText();
                String password = passwordField.getText();
               // int id;
                try {
                    Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/demo",
                            "root", "root");

                    PreparedStatement st = (PreparedStatement) connection.prepareStatement("Select name, password from Student where username=? and password=?");

                    st.setString(1, userName);
                    st.setString(2, password);
                    ResultSet rs = st.executeQuery("select * from Student");
                    if (rs.next()) {
            

                        dispose();
                        PageThreeS ah = new PageThreeS(id);
                        ah.setTitle("Welcome");
                        ah.setVisible(true);
                        JOptionPane.showMessageDialog(LoginButton, "You have successfully logged in");
                    } else {
                        JOptionPane.showMessageDialog(LoginButton, "Wrong Username & Password");
                    }
                } catch (SQLException sqlException) {
                    sqlException.printStackTrace();
                }
            }
        });

        contentPanel.add(LoginButton);

        label = new JLabel("");
        label.setBounds(0, 0, 1008, 562);
        contentPanel.add(label);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    PageTwoS frame = new PageTwoS();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

   
}
