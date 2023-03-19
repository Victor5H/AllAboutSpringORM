package io.github.Victor5H;

import io.github.Victor5H.config.JavaConfig;
import io.github.Victor5H.dao.StudentDao;
import io.github.Victor5H.entities.Student;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


import java.util.Scanner;

public class App {
    static StudentDao studentDao;
    static Scanner sc;

    public static void main(String[] args) {
        ApplicationContext con = new AnnotationConfigApplicationContext(JavaConfig.class);
        studentDao = con.getBean("studentDao", StudentDao.class);
        String menuBorder = "--------------------------------------";
        String menu = "1) Insert new Student\n2) Insert multiple student\n3) Update existing student\n4) Delete student\n5) Select one student\n6) Display all students\n7) Exit\nEnter your choice:";
        sc = new Scanner(System.in).useDelimiter("\n");
        int ch;
        while (true) {
            System.out.println(menuBorder);
            System.out.println(menu);
            ch = sc.nextInt();
            int id;
            String name;
            double height;
            switch (ch) {
                case 1:
                    System.out.println(menuBorder);
                    System.out.println("Insert Student:");
                    insert();
                    break;
                case 2:
                    System.out.println(menuBorder);
                    System.out.println("Insert multiple Students:");
                    System.out.println("Enter number of entries");
                    int n = sc.nextInt();
                    for (int i = 0; i < n; i++) {
                        insert();
                    }
                    break;
                case 3:
                    System.out.println(menuBorder);
                    System.out.println("Update Student:");
                    System.out.println("Enter id of existing student");
                    id = sc.nextInt();
                    if (studentDao.selectOne(id) == null) {
                        System.out.println("No student with id " + id);
                    } else {
                        System.out.println("Enter name");
                        name = sc.next();
                        System.out.println("Enter height");
                        height = sc.nextDouble();
                        studentDao.update(new Student(id, name, height));
                    }
                    break;
                case 4:
                    System.out.println(menuBorder);
                    System.out.println("Delete Student:");
                    System.out.println("Enter id");
                    id = sc.nextInt();
                    if (studentDao.delete(id)) {
                        System.out.println("Student deleted");
                    } else {
                        System.out.println("Student not deleted");
                    }
                    break;
                case 5:
                    System.out.println(menuBorder);
                    System.out.println("Select one Student:");
                    System.out.println("Enter id");
                    id = sc.nextInt();
                    Student s = studentDao.selectOne(id);
                    if (s == null) {
                        System.out.println("No student with id " + id);
                    } else {
                        printStudent(s);
                    }
                    break;
                case 6:
                    System.out.println(menuBorder);
                    System.out.println("Select all Students:");
                    studentDao.selectAll().forEach(App::printStudent);
                    break;
                case 7:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Enter valid choice");
            }
        }


    }
    public static void printStudent(Student s){
        System.out.println("--------------------------------------");
        System.out.println("ID: "+s.getRoll());
        System.out.println("Name: "+s.getName());
        System.out.println("Height: "+s.getHeight());
    }

    public static void insert() {
        String name;
        double height;
        System.out.println("Enter name:");
        name = sc.next();
        System.out.println("Enter height in decimal point:");
        height = sc.nextDouble();
        int id;
        id = studentDao.insert(new Student(name, height));
        if (id > 0) {
            System.out.println("Student inserted at id: " + id);
        } else {
            System.out.println("Student not inserted");
        }
    }
}
