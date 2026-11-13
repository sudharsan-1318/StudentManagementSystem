package dao;

import com.myapp.studentmanagementsystem.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.StudentModel;

/**
 *
 * @author sudharsan
 */
public class StudentDAOImpl implements studentDAO {

    //Connection con = null;
    //PreparedStatement ps = null;
//method Addstudent
    @Override
    public boolean addStudent(StudentModel student) {

        String addQuery = "INSERT INTO Student_details(name,email,age,course) VALUES(?,?,?,?)";
        Connection con = null;
        PreparedStatement ps = null;

        try {

            con = DBConnection.getConnection();
            ps = con.prepareStatement(addQuery);

            ps.setString(1, student.getName());
            ps.setString(2, student.getEmail());
            ps.setInt(3, student.getAge());
            ps.setString(4, student.getCourse());

            int studentRows = ps.executeUpdate();

            return studentRows > 0;

        } catch (SQLException e) {
            System.err.println("Could not add the student because of a database error.");
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
//Method viewStudent

    @Override
    public List<StudentModel> viewStudent() {
        List<StudentModel> students = new ArrayList();

        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = DBConnection.getConnection();
            String viewQuery = "SELECT * FROM Student_details";
            ps = con.prepareStatement(viewQuery);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                StudentModel student = new StudentModel(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getString(5));

                students.add(student);
            }

        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

       return students;

    }

//method SearchStudent
    @Override
    public StudentModel SearchStudent(int id) throws SQLException{
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBConnection.getConnection();
            String searchQuery = "SELECT * FROM Student_details WHERE id = ?";
            ps = con.prepareStatement(searchQuery);

            ps.setInt(1, id);

            boolean found = false;

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                found = true;
                System.out.println(
                        rs.getString(2) + "|"
                        + rs.getInt(1) + "|"
                        + rs.getString(3) + "|"
                        + rs.getInt(4) + "|"
                        + rs.getString(5));
            }
            if (!found) {
                System.out.println("No Student found with ID : " + id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

// Method Update Student
    @Override
    public boolean updateStudent(int id, int choice, String value) {
        Connection con = null;
        PreparedStatement ps = null;

        String column = null;

        switch (choice) {
            case 1:
                column = "name";
                break;
            case 2:
                column = "email";
                break;
            case 3:
                column = "age";
                break;
            case 4:
                column = "course";
                break;
            default:
                System.out.println("Invalid choice");
                return false;

        }
        try {
            con = DBConnection.getConnection();
            String update_query = "UPDATE Student_details SET " + column + " = ? WHERE id = ?";
            ps = con.prepareStatement(update_query);

            if (choice == 3) {
                ps.setInt(1, Integer.parseInt(value));
            } else {
                ps.setString(1, value);
            }

            ps.setInt(2, id);

            int rows = ps.executeUpdate();

            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
//Delete Method

    @Override
    public boolean deleteStudent(int del_id) {
        Connection con = null;
        PreparedStatement ps = null;
        try {

            con = DBConnection.getConnection();
            String deleteData = "DELETE FROM Student_details WHERE id = ?";
            ps = con.prepareStatement(deleteData);

            ps.setInt(1, del_id);

            int rows = ps.executeUpdate();

            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
