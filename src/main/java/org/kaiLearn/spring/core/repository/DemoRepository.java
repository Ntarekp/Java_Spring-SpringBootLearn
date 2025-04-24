package org.kaiLearn.spring.core.repository;


import org.springframework.stereotype.Repository;

@Repository
public class DemoRepository {

    public String hello(){
        return "Hello Repository";
    }
}
