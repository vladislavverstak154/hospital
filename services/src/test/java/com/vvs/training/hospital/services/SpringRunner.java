package com.epam.training.library2016.services;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.epam.training.library.services.BookService;

public class SpringRunner {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext springContext = new ClassPathXmlApplicationContext("service-context.xml");

        String[] beanDefinitionNames = springContext.getBeanDefinitionNames();
        for (String beanName : beanDefinitionNames) {
            System.out.println(beanName);
        }

        BookService bookServiceBean = springContext.getBean(BookService.class);
        System.out.println("BookService exists:" + (bookServiceBean != null ? true : false));

        System.out.println("BookService.dao exists:" + (bookServiceBean.isDaoExist() ? true : false));
    }
}
