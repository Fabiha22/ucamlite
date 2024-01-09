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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;

public class NoticeD extends JFrame {
    private DefaultTableModel model;
    private JButton nextButton;

    public NoticeD(String year, String course) {
        setTitle("Notice");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 400);
        setLayout(new BorderLayout());

        // Create a table model with columns: Student ID, Student Name, Remarks
        model = new DefaultTableModel(new String[]{"Student ID", "Student Name", "Remarks"}, 0);

        JTable table = new JTable(model);
        table.getColumnModel().getColumn(0).setPreferredWidth(80);
        table.getColumnModel().getColumn(1).setPreferredWidth(150);

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        nextButton = new JButton("Next");
        add(nextButton, BorderLayout.SOUTH);

        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                retrieveStudentDataFromDatabase(year, course);
            }
        });
    }

    private void retrieveStudentDataFromDatabase(String year, String course) {
        String jdbcUrl = "jdbc:mysql://localhost:3306/demo";
        String dbUser = "root";
        String dbPassword = "root";
        String query = "SELECT id, name FROM student WHERE year = ? AND course = ?";

        try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, year);
            preparedStatement.setString(2, course);
            ResultSet resultSet = preparedStatement.executeQuery();

            model.setRowCount(0);

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                model.addRow(new Object[]{id, name, "", ""});
            }
            
            // Enable the "Next" button to save data
            nextButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    saveRemarksToDatabase(year, course);
                }
            });
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error while fetching student details: " + e.getMessage());
        }
    }

    private void saveRemarksToDatabase(String year, String course) {
        String jdbcUrl = "jdbc:mysql://localhost:3306/demo";
        String dbUser = "root";
        String dbPassword = "root";
        String insertQuery = "INSERT INTO notice (id, year, notice) VALUES (?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword);
             PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {

            for (int row = 0; row < model.getRowCount(); row++) {
                int studentId = (int) model.getValueAt(row, 0);
                String notice = (String) model.getValueAt(row, 2);

                preparedStatement.setInt(1, studentId);
                preparedStatement.setString(2, year);
                preparedStatement.setString(3, notice);

                preparedStatement.executeUpdate();
            }

            JOptionPane.showMessageDialog(this, "Remarks have been saved to the database.");
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error while saving remarks: " + e.getMessage());
        }
    }
}
