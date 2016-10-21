package com.epam.training.library2016.services;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.epam.training.library.datamodel.Book;

public class SpringScopeTest {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext springContext = new ClassPathXmlApplicationContext("service-context.xml");

        System.out.println(springContext.getBean(Book.class).hashCode());
        System.out.println(springContext.getBean(Book.class).hashCode());
    }
}
