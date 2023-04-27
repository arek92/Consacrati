package com.example.demo.session;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class SessionTimeOutManagement implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContextListener.super.contextInitialized(sce);
        sce.getServletContext().setSessionTimeout(1);
    }

}
