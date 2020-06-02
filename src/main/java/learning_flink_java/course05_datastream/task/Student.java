package learning_flink_java.course05_datastream.task;

/**
 * 数据库、表创建
 * CREATE DATABASE bigdata;
 * use bigdata;
 * CREATE TABLE student(
 * id int(11) not null auto_increment,
 * name VARCHAR(25),
 * age int(10),
 * PRIMARY KEY (id)
 * );
 * select * from student;
 */
public class Student {
    private int id;
    private String name;
    private int age;

    public Student() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
