package org.sonamexample;

import com.mongodb.MongoClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sonamexample.persistence.mongo.Person;
import org.sonamexample.persistence.mongo.repostiory.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@EnableAutoConfiguration
public class SampleController {

    private Logger logger = LoggerFactory.getLogger(getClass().toString());

    @Autowired
    private PersonRepository personRepository;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseBody
    private String home() {
        logger.info("home controller called");
        logger.info("saving person with name Sonam Wangyal");
        Person person = new Person();
        person.setFullName("Sonam Wangyal");

        person = personRepository.save(person);
        logger.debug("person saved: {}", person);
        return "Hello World!";

    }

    @RequestMapping(value = "/exception", method = RequestMethod.GET)
    @ResponseBody
    private String throwException() {
	logger.info("throwing exception");

	if (true)
	    throw new RuntimeException("test throwing exception");
	return "exception generator!";
    }

    /*
     * public static void main(String[] args) throws Exception {
     * SpringApplication.run(SampleController.class, args); }
     */

    @RequestMapping(value = "/mongo", method = RequestMethod.GET)
    @ResponseBody
    public String mongo() {
        try {
            MongoClient mongoClient = new MongoClient("'mongodb://mongo-replica-svc-a:27017,mongo-replica-svc-b:27017,mongo-replica-svc-c:27017/your_db?replicaSet=my_replica_set");
            SimpleMongoDbFactory simpleMongoDbFactory = new SimpleMongoDbFactory(mongoClient, "localdb");
            List<String> list = mongoClient.getDatabaseNames();
            for(String db: list) {
                logger.debug("db: {}", db);
            }
        } catch (Exception e) {
            logger.error("exception", e);
        }
        return "mongo";
    }

}