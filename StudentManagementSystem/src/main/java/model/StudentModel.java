package model;

import com.myapp.studentmanagementsystem.DBConnection;
import main.Main;

public class StudentModel {

    private int id;
    private String name;
    private String email;
    private int age;
    private String course;

    public StudentModel() {

    }

    public StudentModel(String name, String email, int age, String course) {
        this.name = name;
        this.email = email;
        this.age = age;
        this.course = course;
    }

    public StudentModel(int id, String name, String email, int age, String course) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.age = age;
        this.course = course;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setAge(int age){
        this.id = id;
    }
    
    public int getAge() {
        return age;
    }
    
    public String getCourse() {
        return course;
    }
    
    public void setCourse(String course) {
        this.course = course;
    }  
}
