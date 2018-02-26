package org.sonamexample.config;

import org.apache.cxf.jaxrs.swagger.Swagger2Feature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sonamexample.service.repository.PersonRepositoryService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {
    private static final Logger LOG = LoggerFactory.getLogger(BeanConfig.class);

    @Bean
    public PersonRepositoryService personService() {
        LOG.info("creating personService");
        return new PersonRepositoryService();
    }
}
