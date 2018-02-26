package org.sonamexample.jaxrs;

import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sonamexample.persistence.jpa.entity.Person;
import org.sonamexample.service.repository.PersonRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Api("/personImpl")
@Service
public class PersonRestService implements PersonJaxrs {
    private Logger LOG = LoggerFactory.getLogger(getClass());

    @Autowired
    private PersonRepositoryService personRepositoryService;

    @Override
    public List<Person> getPersons( String fullName) {
        LOG.debug("get persons with name {}", fullName);
        return personRepositoryService.getPersons(fullName);
    }

    @Override
    public Person getPerson(Long id) {
        LOG.debug("get persons with id {}", id);
        return personRepositoryService.getPerson(id);
    }

    @Override
    public Person addPerson(String fullName) {
        LOG.debug("add person with fullname {}", fullName);
        return personRepositoryService.addPerson(fullName);
    }
}
