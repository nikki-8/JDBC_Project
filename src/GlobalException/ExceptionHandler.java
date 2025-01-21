package GlobalException;

import dao.StudentUtil;
import model.Student;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.List;

public class ExceptionHandler {
      public static void isCreated(){
          try {
              StudentUtil.createStudentTable();
              System.out.println("Table created sucessfully");
          } catch (SQLException e) {
              System.out.println("Table not created"+e.getMessage());
          }catch (Exception e){
              e.printStackTrace();
          }
      }
      public static void isSavedReco(Student st){
        try {
            StudentUtil.SavedReco(st);
            System.out.println("Record saved successfully");
        } catch (SQLException e) {
            System.out.println("Record not saved"+e.getMessage());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
      public static void isAllRecordVisible(){
        try {
            if(StudentUtil.ShowAllReco().size()<1){
                System.out.println("Here are records");
                StudentUtil.ShowAllReco();
            }
            else
             System.out.println("No Records");

        } catch (SQLException e) {
            System.out.println("Exception "+e.getMessage());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
      public static void isAllRecordUpdated(Student student,int id){
        try {
            boolean updated = StudentUtil.UpdatedReco(student, id);
            if (updated) {
                System.out.println("Here is updated record " + id);
                StudentUtil.ShowAllReco();
            }
            else
                System.out.println("No Records with "+id);
        } catch (SQLException e) {
            System.out.println("Exception occurred"+e.getMessage());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
      public static void isRecordVisibleByID(int id){
        try {
            if(StudentUtil.ShowRecoByID(id)){
                System.out.println("Here is record "+id);
                StudentUtil.ShowRecoByID(id);
            }
            else
                System.out.println("No Records");

        } catch (SQLException e) {
            System.out.println("Exception "+e.getMessage());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
      public static void isRecordDeletedByID(int id){
        try {
            if(StudentUtil.DelRecoByID(id)){
                System.out.println("Record deleted "+id);
                StudentUtil.DelRecoByID(id);
                StudentUtil.ShowRecoByID(id);
            }
            else
                System.out.println("No Record found with "+id);

        } catch (SQLException e) {
            System.out.println("Exception "+e.getMessage());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
      public static void isMulRecordSaved(List<Student>st){
        try {
            StudentUtil.SaveMulRec(st);
            if (st.size()>0){
            System.out.println("Record saved successfully");
            StudentUtil.ShowAllReco();
            }
            else
                System.out.println("Record not saved");
        } catch (SQLException e) {
            System.out.println("Exception"+e.getMessage());
        }catch (Exception e){
            e.printStackTrace();
        }
//          return 0;
      }
      //before running this function alter the table to include photo column
      public static void isPhotoSaved(File file, int id){
        try {

            if (StudentUtil.uploadPic(file,id)){
                System.out.println("Photo saved successfully");
            }
            else
                System.out.println("Photo not saved");
        } catch (SQLException e) {
            System.out.println("Exception"+e.getMessage());
        }catch (FileNotFoundException e){
            System.out.println("File not there in mentioned path");
        }
        catch (Exception e){
            e.printStackTrace();
        }
//          return 0;
    }
      }
