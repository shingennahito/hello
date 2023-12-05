    package duan;
    import java.sql.Connection;
    import java.sql.DriverManager;
    import java.sql.PreparedStatement;
    import java.sql.SQLException;
    import java.sql.ResultSet;
    public class DataHandler {
        private final String URL = "jdbc:sqlite:D:\\java\\JavaDB\\test.db";

        public void addToDatabase(String text) {
            final String INSERT_SQL = "INSERT INTO stu VALUES (?, ?,?,?);";
            try (Connection conn = DriverManager.getConnection(URL);
                PreparedStatement ps = conn.prepareStatement(INSERT_SQL)) {
                ps.setInt(1, 2023002);
                ps.setString(2, text);
                ps.setString(3, text);
                ps.setString(3, text);
                ps.execute();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        public String fetchData(int id) {
            final String SELECT_SQL = "SELECT Text FROM stu WHERE ID = ?;";
            String text = "";
            try (Connection conn = DriverManager.getConnection(URL);
                PreparedStatement ps = conn.prepareStatement(SELECT_SQL)) {
                ps.setInt(1, id);
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        text = rs.getString("Que");
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return text;
        }
    public String fetchaiaisatsu(int id) {
            final String SELECT_SQL = "SELECT aisatsu FROM stu WHERE ID = ?;";
            String aisatsu = "";
            try (Connection conn = DriverManager.getConnection(URL);
                PreparedStatement ps = conn.prepareStatement(SELECT_SQL)) {
                ps.setInt(1, id);
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        aisatsu = rs.getString("aisatsu"); 
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            return aisatsu;
        }
         public String fetchaiAwn(int id) {
            final String SELECT_SQL = "SELECT Awn FROM stu WHERE ID = ?;";
            String Awn = "";
            try (Connection conn = DriverManager.getConnection(URL);
                PreparedStatement ps = conn.prepareStatement(SELECT_SQL)) {
                ps.setInt(1, id);
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        Awn = rs.getString("Awn"); 
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            return Awn;
        }
        public void deleteFromDatabase(int id) {
            final String DELETE_SQL = "DELETE FROM stu WHERE ID = ?;";
            try (Connection conn = DriverManager.getConnection(URL);
                PreparedStatement ps = conn.prepareStatement(DELETE_SQL)) {
                ps.setInt(1, id);
                ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
