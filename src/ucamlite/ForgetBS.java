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
import static java.awt.Color.RED;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

public class ForgetBS extends JFrame implements ActionListener {
private JTextField tf1;
    private JPasswordField passwordField;
    private JButton SubmitButton, BackButton;
    private JLabel label;
    private JPanel contentPanel;
   // JFrame frame = new JFrame();

        // Set the background color of the frame
        

    public ForgetBS() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(450, 190, 1014, 597);
        setResizable(false);
        contentPanel = new JPanel();
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPanel);
        contentPanel.setLayout(null);
        JLabel Label1 = new JLabel("Changing Password");
        Label1.setForeground(Color.BLACK);
        Label1.setFont(new Font("Times New Roman", Font.PLAIN, 24));
        Label1.setBounds(150, 13, 200, 50);
         getContentPane().setBackground(new Color(170, 152, 169));

        contentPanel.add(Label1);

        JLabel label2 = new JLabel("Username");
        label2.setForeground(Color.BLACK);
        label2.setFont(new Font("Tahoma", Font.PLAIN, 16));
        label2.setBounds(50, 100, 100, 20);
        contentPanel.add(label2);
        
        JLabel label3 = new JLabel("New Password");
        label3.setForeground(Color.BLACK);
        label3.setFont(new Font("Tahoma", Font.PLAIN, 16));
        label3.setBounds(50, 150, 120, 20);
        contentPanel.add(label3);
        
        tf1 = new JTextField();
        tf1.setFont(new Font("Tahoma", Font.PLAIN, 16));
        tf1.setBounds(200, 100, 200, 30);
        contentPanel.add(tf1);
        tf1.setColumns(10);

        passwordField = new JPasswordField();
        passwordField.setFont(new Font("Tahoma", Font.PLAIN, 16));
        passwordField.setBounds(200, 150, 200, 30);
        contentPanel.add(passwordField);

        SubmitButton = new JButton("Submit");
        SubmitButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
        SubmitButton.setBounds(200, 200, 100, 30);
        SubmitButton.addActionListener(this);
        contentPanel.add(SubmitButton);

        BackButton = new JButton("Back");
        BackButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
        BackButton.setBounds(50, 200, 100, 30);
        BackButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                PageOne ptb = new PageOne();
                ptb.setTitle("Select");
                ptb.setVisible(true);
            }
        });
        contentPanel.add(BackButton);
    }

    @Override
     public void actionPerformed(ActionEvent e) {
        if (e.getSource() == SubmitButton) {
            String userName = tf1.getText();
            String newPassword = new String(passwordField.getPassword());

            if (updatePassword(userName, newPassword)) {
                JOptionPane.showMessageDialog(this, "Password updated successfully!");
                dispose();
                PageTwoS pt = new PageTwoS();
                pt.setVisible(true);
                pt.setTitle("Login");
            } else {
                JOptionPane.showMessageDialog(this, "Password update failed.");
            }
        }
    }

    public boolean updatePassword(String userName, String newPassword) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/studentdemo", "root", "root");
            String query = "UPDATE Student SET password = ? WHERE username = ?";
            PreparedStatement st = connection.prepareStatement(query);
            st.setString(1, newPassword);
            st.setString(2, userName);
            int rowsUpdated = st.executeUpdate();
            connection.close();
            
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ForgetBS frame = new ForgetBS();
            frame.setTitle("Change Password");
            frame.setVisible(true);
        });
    }
}