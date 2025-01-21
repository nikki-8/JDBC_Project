package dao;
import conn.JdbcConn;
import model.Student;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentUtil {
    private static Connection connectToDb(){
        return JdbcConn.getInstance().getConnection();
    }
    public static boolean createStudentTable() throws SQLException {
        Statement stmt = connectToDb().createStatement();
            String createTableQuery = "CREATE TABLE IF NOT EXISTS Student (stu_id INT PRIMARY KEY AUTO_INCREMENT, Stud_Name VARCHAR(20), Stud_course VARCHAR(15), Stu_age TINYINT);";

            stmt.execute(createTableQuery);
            return true;
    }
    public static boolean SavedReco(Student st) throws SQLException {
        String query="insert into student (Stud_Name,Stud_course,Stu_age) values(?,?,?)";

           PreparedStatement psmt=connectToDb().prepareStatement(query);

               psmt.setString(1,st.getName());
               psmt.setString(2,st.getCourse());
               psmt.setInt(3,st.getAge());

//           psmt.executeUpdate()>=1?"record added with id:"
          int re=psmt.executeUpdate();
        return re>0;}
    public static List<Student> ShowAllReco() throws SQLException{
        List <Student> student=new ArrayList<>();
        Statement stmt=connectToDb().createStatement();
            String query="SELECT * FROM Student";
            ResultSet rs=stmt.executeQuery(query);
            while ((rs.next())) {
                System.out.println(rs.getInt("stu_id") + "\t" + rs.getString("Stud_Name")+ "\t" + rs.getString("Stud_course")+ "\t" + rs.getString("Stu_age"));
            }
            return student;

//        return student;
    }
    public static boolean UpdatedReco(Student upst,int id) throws SQLException {
        String query=" update student set Stud_Name=?,Stud_course=?,Stu_age=? where stu_id=?";

        PreparedStatement psmt=connectToDb().prepareStatement(query);

            psmt.setString(1,upst.getName());
            psmt.setString(2,upst.getCourse());
            psmt.setInt(3,upst.getAge());
            psmt.setInt(4,id);
//           psmt.executeUpdate()>=1?"record added with id:"
            int re=psmt.executeUpdate();
            return re>0;
    }
    public static boolean ShowRecoByID(int id) throws SQLException {
        String query=" select * from Student where stu_id=?";

        PreparedStatement psmt=connectToDb().prepareStatement(query);
            psmt.setInt(1,id);
//           psmt.executeUpdate()>=1?"record added with id:"
            ResultSet rs=psmt.executeQuery();
            if(rs.next())
             System.out.println(rs.getInt("stu_id") + "\t" + rs.getString("Stud_Name")+ "\t" + rs.getString("Stud_course")+ "\t" + rs.getString("Stu_age"));
//
            return true;}
    public static boolean DelRecoByID(int id) throws SQLException {
        String query=" delete  from Student where stu_id=?";

        PreparedStatement psmt=connectToDb().prepareStatement(query);
            psmt.setInt(1, id);
//           psmt.executeUpdate()>=1?"record added with id:"
            int rs = psmt.executeUpdate();
//            if (rs > 0) {
//                System.out.println("Deleted Record with id :" + id);
//            } else{
//                System.out.println("No id found");
//                return false;
            return true;}
    public static int SaveMulRec(List<Student> ls) throws SQLException {
        String query="insert into student (Stud_Name,Stud_course,Stu_age) values(?,?,?)";
       Connection connection= connectToDb();
       connection.setAutoCommit(false);
       PreparedStatement p=connection.prepareStatement(query);
//        try(PreparedStatement psmt=connectToDb().prepareStatement(query)){
           int [] recs;
           for (Student student:ls) {
               p.setString(1, student.getName());
               p.setString(2, student.getCourse());
               p.setInt(3, student.getAge());
               p.addBatch();

           }
           recs=p.executeBatch();
           connection.commit();
           return  recs.length;
//           psmt.executeUpdate()>=1?"record added with id:"
//            int re=psmt.executeUpdate();
//            return re>0;}
//        catch (SQLException e) {
//            e.printStackTrace();
//            return false;
//        }
        }
    public static boolean uploadPic(File file,int id) throws SQLException, IOException {
        PreparedStatement psmt =connectToDb().prepareStatement("update student Set Photo=? where stu_id=?");
        FileInputStream fis=new FileInputStream(file);
//        psmt.setBlob(1,fis);
        psmt.setBinaryStream(1,fis,fis.available());
        psmt.setInt(2,id);
        return   (psmt.executeUpdate() >= 1);
    }
}
