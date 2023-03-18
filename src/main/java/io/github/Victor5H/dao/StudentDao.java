package io.github.Victor5H.dao;

import io.github.Victor5H.entities.Student;

import java.util.List;

public interface StudentDao {
    int insert(Student student);
    boolean delete(int id);
    void update(Student student);
    List<Student> selectAll();
    Student selectOne(int id);

}
