/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ucamlite;

/**
 *
 * @author User
 */
public class StudentInfo {
    private int id;
    private String name;
    private boolean present;

    public StudentInfo(int id, String name) {
        this.id = id;
        this.name = name;
        this.present = false; // Default to absent
    }

    public int getStudentId() {
        return id;
    }

    public String getStudentName() {
        return name;
    }

    public boolean isPresent() {
        return present;
    }

    public void setPresent(boolean present) {
        this.present = present;
    }
}
