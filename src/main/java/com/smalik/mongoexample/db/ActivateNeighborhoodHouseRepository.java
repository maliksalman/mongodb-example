package com.smalik.mongoexample.db;

public interface ActivateNeighborhoodHouseRepository {

    long activateHousesByIdStreetAndAge(String street, int age);
}
