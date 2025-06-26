import java.sql.*;
import java.util.List;
import java.util.ArrayList;

public class DAO {
    public static boolean insertUser(String name, String email, String password) {
        String sql = "INSERT INTO info (name, email, password) VALUES (?, ?, ?)";

        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, name);
            stmt.setString(2, email);
            stmt.setString(3, password);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.err.println("Error inserting user: " + e.getMessage());
            return false;
        }

    }
    public static User getUserById(int id) {
        String sql = "SELECT * FROM info WHERE id = ?";

        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new User(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("password")
                );
            }

        } catch (SQLException e) {
            System.err.println("Error retrieving user: " + e.getMessage());
        }

        return null;
    }
    public static boolean deleteUser(int id) {
        String sql = "DELETE FROM info WHERE id = ?";

        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error deleting user: " + e.getMessage());
            return false;
        }
    }

    public static boolean updateUser(int id, String name, String email, String password) {
        String sql = "UPDATE info SET name = ?, email = ?, password = ? WHERE id = ?";

        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, name);
            stmt.setString(2, email);
            stmt.setString(3, password);
            stmt.setInt(4, id);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error updating user: " + e.getMessage());
            return false;
        }
    }
    public static List<Integer> showUsers() {
        List<Integer> userIds = new ArrayList<>();
        try (Connection conn = DataBaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT id FROM info")) {

            while (rs.next()) {
                userIds.add(rs.getInt("id"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userIds;
    }
    }



        // You can add other CRUD methods here
        // public static boolean updateUser(...)
        // public static boolean deleteUser(...)
        // public static User getUserById(...)


    // You can add other CRUD methods here
    // public static boolean updateUser(...)
    // public static boolean deleteUser(...)
    // public static User getUserById(...)

