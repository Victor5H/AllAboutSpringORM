package io.github.Victor5H.dao;

import io.github.Victor5H.entities.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository

public class StudentDaoImpl implements StudentDao {
    @Autowired
    @Qualifier("sessionfactory")
    private SessionFactory sessionFactory;
    @Autowired
    @Qualifier("template")
    private HibernateTemplate hibernateTemplate;

    public HibernateTemplate getHibernateTemplate() {
        return hibernateTemplate;
    }

    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }

    public StudentDaoImpl(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional // only required for write operations such as update insert not in select
    public int insert(Student student) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        int ret = (Integer) session.save(student);
        session.getTransaction().commit();
        return ret;
        //return (Integer) hibernateTemplate.save(student);
    }

    @Transactional
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

//        Student s = selectOne(id);
//        if (s == null)
//            return false;
//        hibernateTemplate.delete(s);
//        return true;
    }

    @Transactional
    public void update(Student student) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        int ret = (Integer) session.save(student);
        session.getTransaction().commit();
//        return ret;

//        hibernateTemplate.update(student);
    }

    public List<Student> selectAll() {
        return sessionFactory.openSession().createQuery("from student",Student.class).list();
//        return hibernateTemplate.loadAll(Student.class);
    }

    public Student selectOne(int id) {
        Session session = sessionFactory.openSession();
        Student s= session.get(Student.class,id);
        return s;
//        return hibernateTemplate.get(Student.class, id);
    }
}
