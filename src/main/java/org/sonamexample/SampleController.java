package org.sonamexample;

import com.mongodb.MongoClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

}