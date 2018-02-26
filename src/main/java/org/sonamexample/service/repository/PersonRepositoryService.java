package org.sonamexample.service.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sonamexample.persistence.jpa.entity.Person;
import org.sonamexample.persistence.jpa.repo.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Personable implementation
 */
public class PersonRepositoryService implements Personable {
    private static final Logger LOG = LoggerFactory.getLogger(PersonRepositoryService.class);

    @Autowired
    private PersonRepository personRepository;

    @Override
    public List<Person> getPersons(String fullName) {
        LOG.info("find person by fullName {}", fullName);
        List<Person> persons = personRepository.findByFullName(fullName);
        LOG.debug("for id: {} found person {}", fullName, persons);
        return persons;
    }

    @Override
    public Person getPerson(Long id) {
        LOG.info("find person by id {}", id);
        Person person = personRepository.findOne(id);
        LOG.debug("for id: {} found person {}", id, person);
        return person;
    }

    @Override
    public Person addPerson(String fullName) {
        LOG.info("add person with fullName: '{}'", fullName);

        Person person = new Person();
        person.setFullName(fullName);
        person = personRepository.save(person);

        LOG.debug("saved person {}", person);
        return person;
    }
}
