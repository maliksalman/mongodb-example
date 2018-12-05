package com.smalik.mongoexample.db;

import com.smalik.mongoexample.NeighborhoodHouse;
import com.smalik.mongoexample.NeighborhoodHouseId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface NeighborhoodHouseRepository extends MongoRepository<NeighborhoodHouse, NeighborhoodHouseId>, ActivateNeighborhoodHouseRepository {

    List<NeighborhoodHouse> findByIdStreetAndAge(String street, int age);

    List<NeighborhoodHouse> findByActiveTrue();

}
