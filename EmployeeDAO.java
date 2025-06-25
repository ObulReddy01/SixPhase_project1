import java.sql.*;

public class EmployeeDAO {
    private Connection conn;

    public EmployeeDAO() throws SQLException {
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/yourdb", "user", "password");
    }

    public void insertEmployee(Employee emp) throws SQLException {
        String sql = "INSERT INTO employees VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, emp.id);
            stmt.setString(2, emp.name);
            stmt.setString(3, emp.department);
            stmt.setDouble(4, emp.salary);
            stmt.executeUpdate();
        }
    }

    public void close() throws SQLException {
        if (conn != null) conn.close();
    }
}
