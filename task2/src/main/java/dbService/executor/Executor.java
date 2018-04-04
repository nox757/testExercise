package dbService.executor;

import java.sql.*;

public class Executor {
    private final Connection connection;

    public Executor(Connection connection) {
        this.connection = connection;
    }

    public void execUpdate(String update) throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.execute(update);
        stmt.close();
//        PreparedStatement pstmt = connection.prepareStatement(update, Statement.RETURN_GENERATED_KEYS);
//        pstmt.executeUpdate();
//        ResultSet keys = pstmt.getGeneratedKeys();
//        keys.next();
//        System.out.println(keys.getLong(1));
//        return keys.getLong(1);
    }

    public <T> T execQuery(String query,
                           ResultHandler<T> handler)
            throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.execute(query);
        ResultSet result = stmt.getResultSet();
        T value = handler.handle(result);
        result.close();
        stmt.close();
        return value;
    }
}
