package com.nicolascruz.push.infrastructure.persistence.migrations;

import com.nicolascruz.push.domain.model.User;
import io.mongock.api.annotations.ChangeUnit;
import io.mongock.api.annotations.Execution;
import io.mongock.api.annotations.RollbackExecution;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.UUID;


@ChangeUnit(id = "InsertUserChangelog", order = "1", author = "Nicolas Cryz")
public class InsertUserChangelog {

    @Execution
    public void populateUsersCollection(MongoTemplate mongoTemplate) {
        for (int i = 0; i <= 20; i++) {
            User user = new User();
            user.setFirstName("FirstName" + i);
            user.setLastName("LastName" + i);
            user.setEmail("user" + i + "@example.com");
            user.setPassword("password" + i);
            user.setId(UUID.randomUUID());
            mongoTemplate.save(user);
        }
    }

    @RollbackExecution
    public void rollback() {

    }
}
