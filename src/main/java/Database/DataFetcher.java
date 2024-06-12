package Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataFetcher {
    private static final String URL = "jdbc:postgresql://localhost:5432/FilChan";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "forever4";
    private Connection con;
    public DataFetcher() {
        try {
            Class.forName("org.postgresql.Driver");
            this.con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            //System.out.println(con.isClosed());
        } catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }
    }
    public List<Double> getDataFromDatabase() {
        List<Double> data = new ArrayList<>();
        try (Statement statement = con.createStatement()) {
            // выбираем все значения измерения за последние сутки
            String query = "SELECT value FROM measurements WHERE timestamp >= NOW() - INTERVAL '1 day'";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                double value = resultSet.getDouble("value");
                data.add(value);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }
}
