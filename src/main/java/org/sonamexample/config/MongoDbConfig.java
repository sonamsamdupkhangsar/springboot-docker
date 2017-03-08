package org.sonamexample.config;


import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.authentication.UserCredentials;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

//@Configuration
//@EnableMongoRepositories(basePackages = "org.sonamexample.persistence.mongo.repostiory")
public class MongoDbConfig extends AbstractMongoConfiguration {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Value("${mongo.database.name}")
    private String databaseName;

    @Value("${mongo.database.hostname}")
    private String hostName;
/*

    @Value("${mongo.database.username}")
    private String userName;

    @Value("${mongo.database.password}")
    private String password;
*/

    @Override
    protected String getDatabaseName() {
        logger.info("mongo database name is set to {}", databaseName);
        return databaseName;
    }

    @Bean
    @Override
    public Mongo mongo() throws Exception {
        return new MongoClient(hostName);
    }


    /*@Override
    protected UserCredentials getUserCredentials() {
        UserCredentials userCredentials = new UserCredentials(userName, password);
        return userCredentials;
    }*/

    @Override
    public MongoDbFactory mongoDbFactory() throws Exception {
        return new SimpleMongoDbFactory((MongoClient)mongo(), getDatabaseName());
    }

    @Bean
    public MongoTemplate getMongoTemplate() throws Exception {
        logger.debug("setting up MongoTemplate");
        MongoTemplate mongoTemplate = new MongoTemplate(mongoDbFactory());
        return mongoTemplate;
    }

}