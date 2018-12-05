package com.smalik.mongoexample.db;

import com.mongodb.MongoClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import javax.annotation.PostConstruct;

@Configuration
@EnableMongoRepositories
public class MongoConfiguration {

    private final Logger logger = LoggerFactory.getLogger(MongoConfiguration.class);

    @PostConstruct
    public void setupBeforeMongo() {
        logger.info("Setup before mongo setup");
    }

    @Bean
    public MongoDbFactory mongoDbFactory() {
        logger.info("Creating mongo db-factory");
        return new SimpleMongoDbFactory(new MongoClient("localhost"), "house-db");
    }

    @Bean
    public MongoTemplate mongoTemplate(MongoDbFactory factory) {
        logger.info("Creating mongo template");
        return new MongoTemplate(factory);
    }

    @Bean
    public ActivateNeighborhoodHouseRepository activateNeighborhoodHouseRepository(MongoTemplate template) {
        return new ActivateNeighborhoodHouseRepositoryImpl(template);
    }
}
