package ru.andreyb34rus.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/*
 * AbstractAnnotationConfigDispatcherServletInitializer имплементируют WebApplicationInitializer, который позволяет
 * избавиться от web.xml
 */
public class AppInit extends AbstractAnnotationConfigDispatcherServletInitializer {

    // Метод, указывающий на класс конфигурации
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{
/*                HiberConfig.class,
                SecurityConfig.class,*/
                WebConfig.class
        };
    }

    // Добавление конфигурации, в которой инициализируем ViewResolver, для корректного отображения jsp.
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[]{
                WebConfig.class
        };
    }

    /* Данный метод указывает url, на котором будет базироваться приложение, т.е. все запросы
     * от клиента будут перенаправляться на dispatcher servlet */
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

/*    @Override
    protected Filter[] getServletFilters() {
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceEncoding(true);
        return new Filter[] {characterEncodingFilter};
    }*/
}