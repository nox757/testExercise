package dbService.dao;

import dbService.executor.Executor;
import dbService.model.Student;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {
    private Executor executor;

    public StudentDAO(Connection connection) {
        this.executor = new Executor(connection);
    }

    public Student get(long id) throws SQLException {
        return executor.execQuery("select * from students where id=" + id, result -> {
            result.next();
            return new Student(result.getLong(1), result.getString(2),
                    result.getString(3), result.getString(4),
                    result.getDate(5), result.getLong(6));
        });
    }

    public long getStudentId(String name) throws SQLException {
        return executor.execQuery("select * from students where name='" + name + "'", result -> {
            result.next();
            return result.getLong(1);
        });
    }

    public void insertUser(Student student) throws SQLException {
        executor.execUpdate(String.format("insert into students (name, surname, second_name, date_born, group_id) values ('%s', '%s', '%s', '%s', '%s')", student.getName(), student.getSurname(), student.getSecondName(), student.getDateBorn().toString(), Long.toString(student.getGroupID())));
    }

    public void createTable() throws SQLException {
        executor.execUpdate("create table if not exists students (id bigint auto_increment, surname varchar(256), name varchar(256), second_name varchar(256), date_born date, group_id bigint, primary key (id))");
    }

    public void dropTable() throws SQLException {
        executor.execUpdate("drop table student");
    }

    public List<Student> getAll() throws SQLException{
        return executor.execQuery("select * from students", result -> {
            List<Student> listStudent = new ArrayList<>();
            while(result.next()) {
                listStudent.add(new Student(result.getLong(1), result.getString(2),
                        result.getString(3), result.getString(4),
                        result.getDate(5), result.getLong(6)));
            }
            return listStudent;
        });
    }
}
