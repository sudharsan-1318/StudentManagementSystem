package main;

import model.StudentModel;
import dao.studentDAO;
import dao.StudentDAOImpl;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        int choice;

        do {
            System.out.println("================STUDENT MANAGEMENT SYSTEM================");
            System.out.println("1.Add Student");
            System.out.println("2.View Student");
            System.out.println("3.Search Student");
            System.out.println("4.Update Student");
            System.out.println("5.Delete Student");
            System.out.println("6.Exit");

            System.out.println("Enter the choice:");
            String input = scan.nextLine();

            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                choice = 0;
            }
            switch (choice) {
                case 1:
//=======================Add students======================
                    //Name
                    System.out.println("Enter the name:");
                    String name;
                    while (true) {
                        name = scan.nextLine().trim();
                        if (!name.isEmpty()) {
                            break;
                        }
                        System.out.println("Name should be entered.Blank space not allowed");
                    }
                    //Email
                    System.out.println("Enter the email:");
                    String email;
                    while (true) {
                        email = scan.nextLine().trim();
                        if (!email.isEmpty() && email.contains("@")) {
                            break;
                        }
                        System.out.println("Enter valid Email");

                    }
                    //Age
                    System.out.println("Enter the age:");
                    int age;
                    while (true) {
                        String String_age = scan.nextLine();
                        try {
                            age = Integer.parseInt(String_age);
                            if (age > 0) {
                                break;
                            }
                            System.out.println("Age must be greater than 0.Try Again:");
                        } catch (NumberFormatException e) {
                            System.out.println("Please enter a whole number.Try Again");
                        }
                    }
                    //course
                    System.out.println("Enter the course:");
                    String course;
                    while (true) {
                        course = scan.nextLine().trim();
                        if (!course.isEmpty()) {
                            break;
                        }
                        System.out.println("Course should not be blank.");
                    }
                    StudentModel student = new StudentModel(name, email, age, course);

                    studentDAO addstudent = new StudentDAOImpl();

                    boolean result = addstudent.addStudent(student);

                    if (result) {
                        System.out.println("Student added Successfully");
                    } else {
                        System.out.println("could not add student.Please check the database");
                    }

                    break;

                case 2:
//=======================view students======================

                    studentDAO viewstudent = new StudentDAOImpl();

                    List<StudentModel> students = viewstudent.viewStudent();

                    for (StudentModel s : students) {
                        System.out.println(
                                "ID : " + s.getName()
                                + "| NAME : " + s.getId()
                                + "| AGE  : " + s.getAge()
                                + "| EMAIL : " + s.getEmail()
                                + "| COURSE : " + s.getCourse()
                        );
                    }
                    break;

                case 3:
//=======================Search Student======================

                    System.out.println("Enter student Id : ");
                    int id = Integer.parseInt(scan.nextLine().trim());
                    studentDAO searchstudent = new StudentDAOImpl();

                    try {
                        searchstudent.SearchStudent(id);

                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;

                case 4:
//====================Update Student========================
                    System.out.println("=======UPDATE STUDENT========");
                    System.out.println("Enter student Id : ");
                    String up_id = scan.nextLine();
                    int update_id = Integer.parseInt(up_id);

                    System.out.println("Which colum do you want to update?");
                    System.out.println("1.Name");
                    System.out.println("2.Email");
                    System.out.println("3.Age");
                    System.out.println("4.Course");
                    System.out.println("Enter your choice:");

                    int columnChoice = scan.nextInt();
                    scan.nextLine();

                    if (columnChoice < 1 || columnChoice > 4) {
                        System.out.println("Invalid Choice");
                        break;
                    }

                    System.out.println("Enter the new value");
                    String newValue = scan.nextLine().trim();

                    if (columnChoice == 1 || columnChoice == 2 || columnChoice == 4) {
                        if (newValue.isEmpty()) {
                            System.out.println("Value cannot be blank");
                            break;
                        }
                    }

                    if (columnChoice == 2
                            && !newValue.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
                        System.out.println("Please enter the a valide email");
                        break;
                    }
                    if (columnChoice == 3) {
                        try {
                            int newAge = Integer.parseInt(newValue);

                            if (newAge > 18) {
                                System.out.println("Age must be 18 or above");
                                break;
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Age must be a whole number");
                            break;
                        }
                    }
                    studentDAO updateDAO = new StudentDAOImpl();

                    boolean updated = updateDAO.updateStudent(update_id, columnChoice, newValue);

                    if (updated) {
                        System.out.println("Student updated successfully");
                    } else {
                        System.out.println("update failed or student ID not found");
                    }
                    break;

                case 5:
                    //=====================DELETE ACCOUNT=======================

                    System.out.println("========DELETE ACCOUNT========");
                    System.out.println("Enter student Id to delete : ");
                    int del_id = Integer.parseInt(scan.nextLine().trim());

                    studentDAO deleteStudent = new StudentDAOImpl();

                    boolean deleted = deleteStudent.deleteStudent(del_id);

                    if (deleted) {
                        System.out.println("Data deleted successfully");
                    } else {
                        System.out.println("Student not found or delection failed");
                    }
                    break;

                case 6:
//=========================Exit================================
                    System.out.println("Thank you for using Student Management System!!!");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again");
            }
        } while (choice != 6);

        scan.close();
    }
}
