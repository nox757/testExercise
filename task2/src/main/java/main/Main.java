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
            System.out.println("Choose num of command (OR other key to exit):\n 1. Add student \n 2. Delete by id \n 3. Print all student \nYour choice: ");
            if(sc.hasNextInt()) {
                int command = sc.nextInt();
                switch (command) {
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
            } else {
                System.exit(0);
            }
        }
    }

    private static void deleteStudent(DBService dbService, Scanner sc) {
        long studentId = getLong(sc, "Enter id student to delete: ");
        try {
            if(dbService.getStudent(studentId) != null) {
                dbService.deleteStudent(studentId);
                System.out.println("Success delete student with id=" + studentId);
            } else {
                System.err.println("No student with id=" + studentId);
            }

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
                System.out.println("Success add student with id=" + studentId);
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
        student.setSurname(getString(sc, "Enter surname:  "));
        student.setName(getString(sc, "Enter name:  "));
        student.setSecondName(getString(sc, "Enter second name:  "));
        student.setDateBorn(getDate(sc, "Enter born date(year-month-day):  "));
        student.setGroupID(getLong(sc, "Enter groupID (number):  "));
        return student;
    }

    private static String getString(Scanner sc, String message) {
        String input;
        System.out.print("\r" + message);
        input = sc.nextLine();
        while(input.isEmpty()) {
            System.out.print("\r" + message);
            input = sc.nextLine();
        }
        return input;
    }

    private static Date getDate(Scanner sc, String message) {
        Date date;
        System.out.print("\r" + message);
        String input = sc.nextLine();
        while(!isCorrectFormatDate(input)) {
            System.out.print("\r" + message);
            input = sc.nextLine();
        }
        return Date.valueOf(input);
    }

    private static boolean isCorrectFormatDate(String input) {
        try {
            Date.valueOf(input);
        } catch (IllegalArgumentException e) {
            return false;
        }
        return true;
    }

    private static long getLong(Scanner sc, String message) {
        System.out.print("\r" + message);
        while(!sc.hasNextLong()) {
            System.out.print("\r" + message);
            sc.next();
        }
        return sc.nextLong();
    }

}
