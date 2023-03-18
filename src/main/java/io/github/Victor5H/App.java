package io.github.Victor5H;

import io.github.Victor5H.config.JavaConfig;
import io.github.Victor5H.dao.StudentDao;
import io.github.Victor5H.entities.Student;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.orm.hibernate5.HibernateTemplate;

import java.util.Scanner;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        ApplicationContext con = new AnnotationConfigApplicationContext(JavaConfig.class);
        StudentDao studentDao = con.getBean("studentDao", StudentDao.class);
       System.out.println(studentDao.insert(new Student("Harshit",5.9)));
//        System.out.println(studentDao.delete(3));
        //System.out.println(studentDao.selectOne(1));
        studentDao.selectAll().forEach(System.out::println);
//        String menuBorder = "---------------------";
//        String menu = " 1) Insert new Student\n2) Update existing student\n3) Delete student\n 4) Select one student\n5) Display all students\n 6) Exit\n Enter your choice:";
//        Scanner sc = new Scanner(System.in);
//        int ch;
//        while (true){
//            System.out.println(menuBorder);
//            System.out.println(menu);
//            ch = sc.nextInt();
//            switch (ch){
//                case 1:
//                    String name;int id; float height;
//                    System.out.println("");
//                    System.out.println("Enter name:");
//                    name = sc.nextLine();
//
//            }
//        }

    }
}
