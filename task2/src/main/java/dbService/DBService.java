package dbService;

import dbService.dao.StudentDAO;
import dbService.model.Student;
import org.h2.jdbcx.JdbcDataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class DBService {
    private final Connection connection;

    public DBService() {
        this.connection = getH2Connection();
    }

    public Student getStudent(long id) throws DBException {
        try {
            return (new StudentDAO(connection).get(id));
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    public long deleteStudent(long id) throws DBException {
        try {
            return (new StudentDAO(connection).deleteStudent(id));
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    public long addStudent(Student student) throws DBException {
        try {
            connection.setAutoCommit(false);
            StudentDAO dao = new StudentDAO(connection);
            dao.createTable();
            dao.insertStudent(student);
            connection.commit();
            return dao.getStudentId(student.getName());
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ignore) {
            }
            throw new DBException(e);
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ignore) {
            }
        }
    }

    public List<Student> getListStudents() throws DBException {
        try {
            return (new StudentDAO(connection).getAll());
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    public void cleanUp() throws DBException {
        StudentDAO dao = new StudentDAO(connection);
//        try {
//            dao.dropTable();
//        } catch (SQLException e) {
//            throw new DBException(e);
//        }
    }


    public static Connection getH2Connection() {
        try {

            String url = "jdbc:h2:./h2db";
            String name = "login";
            String pass = "password";

            JdbcDataSource ds = new JdbcDataSource();
            ds.setURL(url);
            ds.setUser(name);
            ds.setPassword(pass);

            Connection connection = DriverManager.getConnection(url, name, pass);
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
