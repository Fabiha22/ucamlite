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
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class DetailsS extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;
    private JPanel contentPanel;
    private JButton BackButton;
    private JLabel detailsLabel;
    int id;

    public DetailsS() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(450, 190, 1014, 597);
        setResizable(false);
        contentPanel = new JPanel();
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPanel);
        contentPanel.setLayout(null);

        BackButton = new JButton("Back to Dashboard");
        BackButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
        BackButton.setBounds(650, 390, 250, 50);
        BackButton.addActionListener(this);

        contentPanel.add(BackButton);

        detailsLabel = new JLabel();
        detailsLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
        detailsLabel.setBounds(100, 100, 800, 300);
        contentPanel.add(detailsLabel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == BackButton) {
            dispose(); // Close the DetailsS window and go back to the main dashboard
        }
    }

    public void displayStudentDetails() {
        try {
            String url = "jdbc:mysql://your-mysql-server:3306/demo"; // Replace with your MySQL server and database details
            String username = "root";
            String password = "root";

            Connection conn = DriverManager.getConnection(url, username, password);

            String query = "SELECT id, name, address, year FROM student WHERE username = ? AND password = ? ";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String address = resultSet.getString("address");
                String year = resultSet.getString("year");

                String studentDetails = "ID: " + id + "\nName: " + name + "\nAddress: " + address + "\nYear: " + year;
                detailsLabel.setText(studentDetails);

                setTitle("Student Details");
                setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "Student not found.");
            }

            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error: Unable to retrieve student details.");
        }
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    DetailsS frame = new DetailsS();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}

