package org.sonamexample.persistence.mongo.repostiory;

import org.sonamexample.persistence.mongo.Person;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by c14423 on 3/7/17.
 */
public interface PersonRepository extends MongoRepository<Person, String> {
}
