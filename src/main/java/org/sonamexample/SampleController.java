package org.sonamexample;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sonamexample.persistence.jpa.entity.Person;
import org.sonamexample.persistence.mongo.entity.Account;
import org.sonamexample.persistence.mongo.repo.AccountRepository;
import org.sonamexample.service.repository.Personable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@EnableAutoConfiguration
public class SampleController {

    private Logger logger = LoggerFactory.getLogger(getClass().toString());

    @Autowired
    private Personable personable;

    @Autowired
    private AccountRepository accountRepository;

    @Value("${developer.env.name}")
    private String environmentName;

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

    @RequestMapping(value="/spring/person/add/{fullName}", method=RequestMethod.POST)
    public Person addPerson(@PathVariable("fullName")String fullName) {
      return personable.addPerson(fullName);
    }

    @RequestMapping(value="/spring/person/{id}", method = RequestMethod.GET)
    public Person getPerson(@PathVariable("id") long id) {
      return personable.getPerson(id);
    }

    @RequestMapping(value="/spring/person/fullname/{fullName}", method = RequestMethod.GET)
    public List<Person> getPerson(@PathVariable("fullName") final String fullName) {
        return personable.getPersons(fullName);
    }

    @RequestMapping(value="/spring/account/add/{balance}", method = RequestMethod.POST)
    public Account addAccount(@PathVariable("balance")int balance) {
        logger.info("create account with balance {}", balance);

        Account account = new Account();
        account.setBalalance(balance);

        account = accountRepository.save(account);
        logger.debug("account created {}", account);
        return account;
    }

    @RequestMapping(value="/spring/account/{id}", method = RequestMethod.GET)
    public Account getAccount(@PathVariable("id")String id) {
        logger.info("get account by id {}", id);
        Account account = accountRepository.findOne(id);

        logger.debug("found account {}", account);
        return account;
    }

    @RequestMapping(value="/env", method=RequestMethod.GET)
    public String getEnvironment() {
        logger.info("environment name is {}", environmentName);
        return environmentName;
    }

}
