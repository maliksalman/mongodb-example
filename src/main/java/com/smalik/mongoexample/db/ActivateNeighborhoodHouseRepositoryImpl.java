package com.smalik.mongoexample.db;

import com.mongodb.client.result.UpdateResult;
import com.smalik.mongoexample.NeighborhoodHouse;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

public class ActivateNeighborhoodHouseRepositoryImpl implements ActivateNeighborhoodHouseRepository {

    private MongoTemplate template;

    public ActivateNeighborhoodHouseRepositoryImpl(MongoTemplate template) {
        this.template = template;
    }

    @Override
    public long activateHousesByIdStreetAndAge(String street, int age) {

        Query query = new Query(Criteria
                .where("_id.street").is(street)
                .andOperator(Criteria.where("age").is(age)));

        Update update = Update.update("active", true);

        UpdateResult updateResult = template.updateMulti(query, update, NeighborhoodHouse.class);
        if (updateResult != null) {
            return updateResult.getModifiedCount();
        }

        return 0;
    }
}
