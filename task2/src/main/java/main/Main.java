package main;

import dbService.DBException;
import dbService.DBService;
import dbService.model.Student;

import java.sql.Date;

public class Main {
    public static void main(String[] args) {
        DBService dbService = new DBService();
        try {

            Student student = new Student(-1, "Petrov", "Ivan",
                    "Sidorov", Date.valueOf("2000-11-01"), 15);
            long userId = dbService.addStudent(student);
            System.out.println("Added user id: " + userId);

            Student student1 = dbService.getStudent(userId);
            System.out.println("Student: " + student1.toString());

            dbService.cleanUp();
            for(Student st : dbService.getListStudents()) {

                System.out.println(st.toString() + System.lineSeparator());
            }

        } catch (DBException e) {
            e.printStackTrace();
        }
    }
}
