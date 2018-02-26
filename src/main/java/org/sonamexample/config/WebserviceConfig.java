package org.sonamexample.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sonamexample.jaxrs.PersonJaxrs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
//import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;

import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.endpoint.Server;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.jaxrs.swagger.Swagger2Feature;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import java.util.Arrays;

@Configuration
@EnableAutoConfiguration
public class WebserviceConfig {
    private static final Logger LOG = LoggerFactory.getLogger(WebserviceConfig.class);

    @Autowired
    private Bus bus;

    @Autowired
    private PersonJaxrs personJaxrs;

    @Bean(destroyMethod = "destroy") @DependsOn("cxf")
    public Server jaxRsServer() {
        final JAXRSServerFactoryBean factory = new JAXRSServerFactoryBean();

        factory.setServiceBean(personJaxrs);
        factory.setProvider(new JacksonJsonProvider());
        factory.setBus(bus);
        factory.setAddress("");
        factory.setFeatures(Arrays.asList(createSwaggerFeature()));

        return factory.create();
    }


  /*  @Bean
    public Server rsServer() {
        JAXRSServerFactoryBean endpoint = new JAXRSServerFactoryBean();
        endpoint.setBus(bus);
        endpoint.setAddress("/jaxrs");
        // Register 2 JAX-RS root resources supporting "/sayHello/{id}" and "/sayHello2/{id}" relative paths
        endpoint.setServiceBeans(Arrays.<Object>asList(personJaxrs));
        endpoint.setFeatures(Arrays.asList(new Swagger2Feature()));
        return endpoint.create();
    }*/

    public Swagger2Feature createSwaggerFeature() {
        Swagger2Feature swagger2Feature = new Swagger2Feature();
        swagger2Feature.setPrettyPrint(true);
        swagger2Feature.setTitle("Spring Boot CXF REST Application");
        swagger2Feature.setContact("The Apache CXF team");
        swagger2Feature.setDescription("This sample project demonstrates how to use CXF JAX-RS services"
                + " with Spring Boot. This demo has two JAX-RS class resources being"
                + " deployed in a single JAX-RS endpoint.");
        swagger2Feature.setVersion("1.0.0");
        return swagger2Feature;
    }
}