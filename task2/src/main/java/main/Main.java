package main;

import dbService.DBException;
import dbService.DBService;
import dbService.model.Student;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) {
        DBService dbService = new DBService();
        Scanner sc = new Scanner(System.in);

        while(true) {
            System.out.print("Choose num of command (OR other key to exit):\n 1. Add student \n 2. Delete by id \n 3. Print all student \nYour choice: ");
            int command = sc.nextInt();
            switch (command){
                case 1:
                    addStudent(dbService, sc);
                    break;
                case 2:
                    deleteStudent(dbService, sc);
                    break;
                case 3:
                    printListStudent(dbService);
                    break;
                default:
                    System.exit(0);
                    break;
            }
        }
    }

    private static void deleteStudent(DBService dbService, Scanner sc) {
        System.out.println("Enter id student (number > 0): ");
        long studentId = sc.nextLong();
        try {

            long id = dbService.deleteStudent(studentId);
            System.out.println("Success delete student with id" + id);

        }  catch (DBException e) {
            System.err.println("Something wrong, we can't delete record with student by id=" + studentId);
        }
    }

    private static void addStudent(DBService dbService, Scanner sc) {
        try {
            Student student = inputStudent(sc);
            long studentId;
            if(student.getName() != null) {
                studentId = dbService.addStudent(student);
                System.out.println("Success add student with id" + studentId);
            }

        }  catch (DBException e) {
            System.err.println("Something wrong, we can't add record with student");
        }
    }

    private static void printListStudent(DBService dbService) {
        try {
            List<Student> students = dbService.getListStudents();
            if(students.isEmpty()){
                System.out.println("No students in table");
            }
            for (Student st : dbService.getListStudents()) {
                System.out.println(st.toString() + System.lineSeparator());
            }
        }  catch (DBException e) {
                System.err.println("Something wrong, we can't print students");
        }
    }

    private static Student inputStudent(Scanner sc) {
        Student student = new Student();
        System.out.println("Enter surname:  ");
        student.setSurname(sc.nextLine());
        System.out.println("Enter name:  ");
        student.setName(sc.nextLine());
        System.out.println("Enter second name:  ");
        student.setSecondName(sc.nextLine());
        System.out.println("Enter born date:  ");
        student.setDateBorn(Date.valueOf(sc.nextLine()));
        System.out.println("Enter groupID (number):  ");
        student.setGroupID(sc.nextLong());
        return student;
    }
}
