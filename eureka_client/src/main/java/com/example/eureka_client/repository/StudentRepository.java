package com.example.eureka_client.repository;


import com.example.my_springcloud.entity.Student;

import java.util.Collection;

public interface StudentRepository {
    Collection<Student> findAll();

    Student findById(long id);

    void saveOrUpdate(Student Student);

    void deleteById(long id);

}
