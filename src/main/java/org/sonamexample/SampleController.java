package org.sonamexample;

import com.mongodb.MongoClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sonamexample.persistence.jpa.entity.Person;
import org.sonamexample.persistence.jpa.repo.PersonRepository;
import org.sonamexample.persistence.mongo.entity.Account;
import org.sonamexample.persistence.mongo.repo.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@EnableAutoConfiguration
public class SampleController {

    private Logger logger = LoggerFactory.getLogger(getClass().toString());

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private AccountRepository accountRepository;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseBody
    private String home() {
        logger.info("home controller called");
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
        String connected = "";

        try {//mongo-replica-node-0:27017,mongo-replica-svc-b:27017,
            MongoClient mongoClient = new MongoClient("mongo-replica-node-0", 27017);
            ///test?replicaSet=my_replica_set");
            SimpleMongoDbFactory simpleMongoDbFactory = new SimpleMongoDbFactory(mongoClient, "test");
            List<String> list = mongoClient.getDatabaseNames();
            connected = "- got connected to Mongo at mongo-replica-node-0 27017";
            if(list != null) {
                logger.debug("the size of databasenames is {}", list.size());
                connected += " - list of database: " + list.size();

            }
            else {
                logger.error("list is null for database names");
            }

            for(String db: list) {
                logger.debug("db: {}", db);
                connected += ", db: " + db+" ";
            }
        } catch (Exception e) {
            logger.error("exception", e);
            connected = " - got exception: " + e.getMessage();
        }
        return "mongo - " + connected;
    }

    @RequestMapping(value="/person/add/{fullName}", method=RequestMethod.POST)
    public Person addPerson(@PathVariable("fullName")String fullName) {
        logger.info("add person with fullName: '{}'", fullName);

        Person person = new Person();
        person.setFullName(fullName);
        person = personRepository.save(person);

        logger.debug("saved person {}", person);
        return person;
    }

    @RequestMapping(value="/person/{id}", method = RequestMethod.GET)
    public Person getPerson(@PathVariable("id") long id) {
        logger.info("find person by id {}", id);
        Person person = personRepository.findOne(id);
        logger.debug("for id: {} found person {}", id, person);
        return person;
    }

    @RequestMapping(value="/account/add/{balance}", method = RequestMethod.POST)
    public Account addAccount(@PathVariable("balance")int balance) {
        logger.info("create account with balance {}", balance);

        Account account = new Account();
        account.setBalalance(balance);

        account = accountRepository.save(account);
        logger.debug("account created {}", account);
        return account;
    }

    @RequestMapping(value="/account/{id}", method = RequestMethod.GET)
    public Account getAccount(@PathVariable("id")String id) {
        logger.info("get account by id {}", id);
        Account account = accountRepository.findOne(id);

        logger.debug("found account {}", account);
        return account;
    }

}
