package com.filosofiadelsoftware.pruebadb.spring;

import org.apache.catalina.connector.Connector;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.stereotype.Component;

//@Component
//public class TomcatConfigInspector implements WebServerFactoryCustomizer<TomcatServletWebServerFactory> {

//    @Override
//    public void customize(TomcatServletWebServerFactory factory) {
//        factory.addConnectorCustomizers((Connector connector) -> {
//            int maxThreads = connector.getProtocolHandler().getExecutor();
//            int minSpareThreads = connector.getProtocolHandler().getExecutor().getCorePoolSize();
//
//            System.out.println("🔹 Max Threads: " + maxThreads);
//            System.out.println("🔹 Min Spare Threads: " + minSpareThreads);
//        });
//    }
//}
