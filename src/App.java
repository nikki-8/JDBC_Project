import java.sql.*;

public class App {
    public static <PreaparedStatement> void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/db"; // Correct JDBC URL
        String user = "root"; // Replace with your MySQL username
        String password = "123456"; // Replace with your MySQL password

        try {
            // Establish a connection to the database
            Connection connection = DriverManager.getConnection(url, user, password);

            // Create a statement
            Statement statement = connection.createStatement();

            // Create a table
            String createTableSQL = "CREATE TABLE IF NOT EXISTS demo (" +
                    "col1 INT PRIMARY KEY, " +
                    "col2 VARCHAR(20)" +
                    ");";
            statement.execute(createTableSQL);

            // Insert data into the table
            String insertSQL = "INSERT INTO demo (col1, col2) VALUES (1, 'assd');";
            int rowsInserted = statement.executeUpdate(insertSQL);
            System.out.println("Rows inserted: " + rowsInserted);

//            PreaparedStatement stmt= connection.prepareStatement("delete from employee where emp id=?");
//            stmt.setInt(1,101);
//            stmt.set
//            Result rs =stmt.executeUpdate();
//
//            Result rs =stmt.executeQuery();

            // Query the table
            String querySQL = "SELECT * FROM demo;";
            ResultSet rs = statement.executeQuery(querySQL);
            while (rs.next()) {
                System.out.println(rs.getInt("col1") + "\t" + rs.getString("col2"));
            }

            // Close resources
            rs.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
