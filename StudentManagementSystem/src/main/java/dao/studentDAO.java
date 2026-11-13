/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import com.myapp.studentmanagementsystem.DBConnection;
import model.StudentModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author sudharsan
 */
public interface  studentDAO {
    
    public boolean addStudent(StudentModel student);
    public List<StudentModel> viewStudent();
    StudentModel  SearchStudent(int id) throws SQLException;
    public boolean updateStudent(int id,int choice,String newValue);
    public boolean deleteStudent(int del_id);
}
