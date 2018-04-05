package dbService.executor;

import java.sql.*;

public class Executor {
    private final Connection connection;

    public Executor(Connection connection) {
        this.connection = connection;
    }

    public long execUpdate(String update) throws SQLException {
//        Statement stmt = connection.createStatement();
//        stmt.execute(update);
//        stmt.close();
        PreparedStatement stmt = connection.prepareStatement(update, Statement.RETURN_GENERATED_KEYS);
        stmt.executeUpdate();
        ResultSet keys = stmt.getGeneratedKeys();
        if(keys.next()) {
            //System.out.println(keys.getLong(1));
            return keys.getLong(1);
        }
        return 0;
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
