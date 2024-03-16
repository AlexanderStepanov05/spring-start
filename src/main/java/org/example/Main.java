package org.example;


import org.example.database.pool.ConnectionPool;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        try (var context = new ClassPathXmlApplicationContext("application.xml")) {
            System.out.println(context.getBean("pool1", ConnectionPool.class));
        }
    }
}