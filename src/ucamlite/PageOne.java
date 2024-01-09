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
import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author sumai
 */
public class PageOne extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;
    private JLabel label;
    private JPanel contentPanel;
    public JRadioButton studentbutton, teacherbutton;
    private JButton login, signup;
    String selection ;

    public PageOne() {
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
        getContentPane().setBackground(new Color(173, 216, 230));

        JLabel lblNewLabel = new JLabel("WELCOME!");
        lblNewLabel.setForeground(Color.BLUE);
        lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 35));
        lblNewLabel.setBounds(600, 15, 273, 93);
        contentPanel.add(lblNewLabel);
        studentbutton = new JRadioButton("Student");
        studentbutton.setBounds(550, 205, 125, 45);
        teacherbutton = new JRadioButton("Teacher");
        teacherbutton.setBounds(550, 150, 125, 45);
        studentbutton.setFont(new Font("Arial", Font.BOLD, 25));
        teacherbutton.setFont(new Font("Arial", Font.BOLD, 25));
        login = new JButton("Login");
        login.setFont(new Font("Tahoma", Font.PLAIN, 26));
        login.setBounds(545, 392, 162, 30);
        signup = new JButton("New?Sign Up!");
        signup.setFont(new Font("Tahoma", Font.PLAIN, 26));
        signup.setBounds(545, 450, 250, 30);

        ButtonGroup Group = new ButtonGroup();
        Group.add(studentbutton);
        Group.add(teacherbutton);
       
        teacherbutton.addActionListener(this);
        studentbutton.addActionListener(this);
  
        login.addActionListener(this);
        signup.addActionListener(this);
        this.add(studentbutton);
        this.add(teacherbutton);
        this.add(login);
        this.add(signup);
        login.setEnabled(false);
        signup.setEnabled(false);

    }


    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == login) {
            if (studentbutton.isSelected()) {
                dispose();
                PageTwoS pts = new PageTwoS();
                pts.setTitle("Login");
                pts.setVisible(true);
            } else if (teacherbutton.isSelected()) {
                dispose();
                PageTwoT ptf = new PageTwoT();
                ptf.setTitle("Login");
                ptf.setVisible(true);
            }
        }

        if (e.getSource() == signup) {
            if (studentbutton.isSelected()) {
                dispose();
                PageTwoBS pts = new PageTwoBS();
                pts.setTitle("SignUp");
                pts.setVisible(true);
            } else if (teacherbutton.isSelected()) {
                dispose();
                PageTwoBT ptf = new PageTwoBT();
                ptf.setTitle("SignUp");
                ptf.setVisible(true);

            } 
        } else {

            if (studentbutton.isSelected() || teacherbutton.isSelected() ) {
                login.setEnabled(true);
                signup.setEnabled(true);
            } else {
                login.setEnabled(false);
                signup.setEnabled(false);
            }
        }
    }
    

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    PageOne frame = new PageOne();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

}
