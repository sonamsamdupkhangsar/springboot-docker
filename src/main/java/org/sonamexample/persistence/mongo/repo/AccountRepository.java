package org.sonamexample.persistence.mongo.repo;

import org.sonamexample.persistence.mongo.entity.Account;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by c14423 on 3/13/17.
 */
public interface AccountRepository extends CrudRepository<Account, String> {
}
