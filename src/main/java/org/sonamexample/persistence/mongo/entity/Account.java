package org.sonamexample.persistence.mongo.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by c14423 on 3/13/17.
 */
@Document
public class Account {

    @Id
    private String id;

    private int balalance;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getBalalance() {
        return balalance;
    }

    public void setBalalance(int balalance) {
        this.balalance = balalance;
    }
}
