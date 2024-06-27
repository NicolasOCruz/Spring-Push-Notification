package com.nicolascruz.push.core.mongock;

import com.mongodb.ReadConcern;
import com.mongodb.ReadPreference;
import com.mongodb.WriteConcern;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import io.mongock.driver.api.driver.ConnectionDriver;
import io.mongock.driver.mongodb.sync.v4.driver.MongoSync4Driver;
import io.mongock.runner.springboot.MongockSpringboot;
import io.mongock.runner.springboot.base.MongockInitializingBeanRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
public class MongockConfig {

    @Bean
    public ConnectionDriver configureDriver(MongoClient mongoClient) {
        MongoSync4Driver driver = MongoSync4Driver.withDefaultLock(mongoClient, "db_push");
        driver.setWriteConcern(WriteConcern.MAJORITY.withJournal(true).withWTimeout(1000, TimeUnit.MILLISECONDS));
        driver.setReadConcern(ReadConcern.MAJORITY);
        driver.setReadPreference(ReadPreference.primary());
        return driver;
    }

    @Bean
    public MongockInitializingBeanRunner mongockRunner(ConnectionDriver driver, ApplicationContext applicationContext) {
        return MongockSpringboot.builder()
                .setDriver(driver)
                .addMigrationScanPackage("com.nicolascruz.push.infrastructure.persistence.migrations")
                .setSpringContext(applicationContext)
                .setTrackIgnored(false)
                .setTransactionEnabled(false)
                .buildInitializingBeanRunner();
    }

    @Bean
    public MongoClient getMongoClient() {
        return MongoClients.create();
    }
}
