package io.github.Victor5H.dao;

import io.github.Victor5H.entities.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository

public class StudentDaoImpl implements StudentDao{
    @Autowired
    @Qualifier("sessionfactory")
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional
    public int insert(Student student) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        int ret = (Integer) session.save(student);
        session.getTransaction().commit();
        return ret;
    }

    public boolean delete(int id) {
        Student s = selectOne(id);
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        if (s== null){
            System.out.println("Student not present");
            return false;
        }
        session.delete(s);
        session.getTransaction().commit();
        return true;
    }

    public int update(Student student) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        int ret = (Integer) session.save(student);
        session.getTransaction().commit();
        return ret;
    }

    public List<Student> selectAll() {
        return sessionFactory.openSession().createQuery("from empsys",Student.class).list();
    }

    public Student selectOne(int id) {
        Session session = sessionFactory.openSession();
        Student s= session.get(Student.class,id);
        return s;
    }
}
