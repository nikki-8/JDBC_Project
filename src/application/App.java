package application;

import GlobalException.ExceptionHandler;
import model.Student;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class App {
    public static void main(String[] args) {
        ExceptionHandler.isCreated();
        Student student = new Student();
        ExceptionHandler.isSavedReco(student);
        ExceptionHandler.isAllRecordVisible();
        ExceptionHandler.isAllRecordUpdated(new Student(25,"aws","abc"), 40);
        ExceptionHandler.isRecordVisibleByID(22);
        ExceptionHandler.isRecordDeletedByID(19);
        List<Student> listofStudent =new ArrayList<>();
        Collections.addAll(listofStudent,
                new Student(22,"Java","abc"),
                new Student(28,"AWS","Ham"));
        ExceptionHandler.isMulRecordSaved(listofStudent);
//        ExceptionHandler.isPhotoSaved( null,8);
    }
}
