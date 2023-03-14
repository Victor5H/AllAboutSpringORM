package io.github.Victor5H;

import io.github.Victor5H.config.JavaConfig;
import io.github.Victor5H.dao.StudentDao;
import io.github.Victor5H.entities.Student;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.orm.hibernate5.HibernateTemplate;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        ApplicationContext con = new AnnotationConfigApplicationContext(JavaConfig.class);
        StudentDao studentDao = con.getBean("studentDao",StudentDao.class);
//        System.out.println(studentDao.insert(new Student(3,"Harshit",5.9)));
//        System.out.println(studentDao.delete(3));
//        System.out.println(studentDao.selectOne(3));
        System.out.println(studentDao.selectAll());

    }
}
