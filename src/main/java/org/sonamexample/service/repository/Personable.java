package org.sonamexample.service.repository;

import org.sonamexample.persistence.jpa.entity.Person;

import java.util.List;

/**
 * Interface for Person service capabilities
 */
public interface Personable {
    //this will retrieve all Person with fullName
    List<Person> getPersons(String fullName);
    //this will get Person by id
    Person getPerson(Long id);
    //add a person to PersonRepository
    Person addPerson(String fullName);
}
