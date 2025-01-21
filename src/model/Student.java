package model;

public class Student {

    private int id;
    private String name;
    private String course;
    private byte age;

    public Student() {
    }
    public Student(int age, String course, String name) {
        this.age= (byte) age;
        this.course=course;
        this.name=name;

    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getCourse() {
        return course;
    }
    public void setCourse(String course) {
        this.course = course;
    }
    public byte getAge() {
        return age;
    }
    public  void setAge(byte age) {
        this.age = age;
    }

}
