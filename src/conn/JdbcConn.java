package conn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcConn {
    private static JdbcConn jdbcConn;
    private Connection connection;
    private final String url="jdbc:mysql://localhost:3306/my_database";
    private final String user="root";
    private final String pass="";

    private JdbcConn(){
        try{
            Connection tempconnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/","root","123456");
            Statement stmt=tempconnection.createStatement();
            stmt.execute("CREATE DATABASE IF NOT EXISTS my_database");
            tempconnection.close();
            connection = DriverManager.getConnection(url,user,pass);

//            connection.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public static JdbcConn getInstance(){
        if(jdbcConn==null){
            synchronized (JdbcConn.class){
                if(jdbcConn==null){
                    jdbcConn=new JdbcConn();
                }
            }
        }
        return jdbcConn;
    }

    public Connection getConnection() {
        return connection;
    }
}
