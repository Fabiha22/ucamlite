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
public class ForgetS extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;
    private JTextField tf1, tf2, tf3, tf4;
    private JPasswordField passwordField;
    private JButton SubmitButton, BackButton;
    private JLabel label;
    private JPanel contentPanel;
    private JComboBox ComboBox;

    public ForgetS() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(450, 190, 1014, 597);
        setResizable(false);
        contentPanel = new JPanel();
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPanel);
        contentPanel.setLayout(null);
 getContentPane().setBackground(new Color(251, 217, 211));

        JLabel Label1 = new JLabel("Fill Form For Recovery!");
        Label1.setForeground(Color.BLACK);
        Label1.setFont(new Font("Times New Roman", Font.PLAIN, 22));
        Label1.setBounds(423, 13, 273, 93);
        contentPanel.add(Label1);

        JLabel label1 = new JLabel("ID");
        label1.setBackground(Color.BLACK);
        label1.setForeground(Color.BLACK);
        label1.setFont(new Font("Tahoma", Font.PLAIN, 20));
        label1.setBounds(125, 100, 200, 30);
        contentPanel.add(label1);

        JLabel label2 = new JLabel("Name");
        label2.setBackground(Color.BLACK);
        label2.setForeground(Color.BLACK);
        label2.setFont(new Font("Tahoma", Font.PLAIN, 20));
        label2.setBounds(125, 150, 200, 30);
        contentPanel.add(label2);
        JLabel label3 = new JLabel("Username");
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

        JLabel label5 = new JLabel("Batch");
        label5.setBackground(Color.BLACK);
        label5.setForeground(Color.BLACK);
        label5.setFont(new Font("Tahoma", Font.PLAIN, 20));
        label5.setBounds(125, 300, 200, 30);
        contentPanel.add(label5);

        tf1 = new JTextField();
        tf1.setFont(new Font("Tahoma", Font.PLAIN, 20));
        tf1.setBounds(250, 150, 280, 30);
        contentPanel.add(tf1);
        tf1.setColumns(10);
        tf2 = new JTextField();
        tf2.setFont(new Font("Tahoma", Font.PLAIN, 20));
        tf2.setBounds(250, 200, 281, 30);
        contentPanel.add(tf2);
        tf2.setColumns(10);
        tf3 = new JTextField();
        tf3.setFont(new Font("Tahoma", Font.PLAIN, 20));
        tf3.setBounds(250, 250, 281, 30);
        contentPanel.add(tf3);
        tf3.setColumns(10);

        tf4 = new JTextField();
        tf4.setFont(new Font("Tahoma", Font.PLAIN, 20));
        tf4.setBounds(250, 100, 281, 30);
        contentPanel.add(tf4);
        tf4.setColumns(10);

        ComboBox = new JComboBox();
        ComboBox.setFont(new Font("Tahoma", Font.PLAIN, 26));
        ComboBox.setBounds(250, 300, 162, 28);
        contentPanel.add(ComboBox);
        ComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"2020", "2021", "2022", "2023"}));

        SubmitButton = new JButton("Submit");
        SubmitButton.setFont(new Font("Tahoma", Font.PLAIN, 26));
        SubmitButton.setBounds(650, 392, 162, 50);
        SubmitButton.addActionListener(this);
        contentPanel.add(SubmitButton);
        SubmitButton.setEnabled(false);
        SubmitButton.addActionListener(this);
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

        ComboBox.setToolTipText("Select");
        ComboBox.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == SubmitButton) {
            if (isExistingStudent(tf4.getText(), tf1.getText(), tf2.getText(), tf3.getText(), ComboBox.getSelectedItem().toString())) {
                dispose();
                ForgetBS pts = new ForgetBS();
                pts.setTitle("Login");
                pts.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "Please give right information!");
            }
        } else {
            if (!tf3.getText().isEmpty() || !tf4.getText().isEmpty() || !tf1.getText().isEmpty() || !tf2.getText().isEmpty()) {
                SubmitButton.setEnabled(true);
            } else {
                SubmitButton.setEnabled(false);
            }
        }
    }

    private boolean isExistingStudent(String id, String name, String username, String address, String batch) {
        boolean exists = false;
        try {
            java.sql.Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/studentdemo",
                    "root", "root");
            String query = "SELECT * FROM Student WHERE id=? AND name = ? AND username = ? AND address = ? AND batch = ?";
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, id);
            pstmt.setString(2, name);
            pstmt.setString(3, username);
            pstmt.setString(4, address);
            pstmt.setString(5, batch);
            ResultSet resultSet = pstmt.executeQuery();
            exists = resultSet.next();
            connection.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return exists;
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ForgetS frame = new ForgetS();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

}
