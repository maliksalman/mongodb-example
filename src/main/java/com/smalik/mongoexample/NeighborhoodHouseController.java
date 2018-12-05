package com.smalik.mongoexample;

import com.smalik.mongoexample.db.NeighborhoodHouseRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
public class NeighborhoodHouseController {

    private NeighborhoodHouseRepository repository;

    public NeighborhoodHouseController(NeighborhoodHouseRepository repository) {
        this.repository = repository;
    }

    @PostMapping("/houses")
    public NeighborhoodHouse addHouse(
            @RequestParam("age") int age,
            @RequestParam("street") String street,
            @RequestParam("number") String number,
            @RequestParam("attributes") String attributes
    ) {

        HashMap<String, String> attributesMap = new HashMap<>();
        for (String pair : attributes.split(",")) {
            String[] split = pair.split("\\|");
            attributesMap.put(split[0], split[1]);
        }

        NeighborhoodHouse house = new NeighborhoodHouse();
        house.setId(new NeighborhoodHouseId(street, number));
        house.setAge(age);
        house.setActive(false);
        house.setAttributes(attributesMap);

        return repository.save(house);
    }

    @GetMapping("/houseById/{street}/{number}")
    public ResponseEntity<NeighborhoodHouse> getHouseById(
            @PathVariable("street") String street,
            @PathVariable("number") String number
    ) {
        Optional<NeighborhoodHouse> optionalHouse = repository.findById(new NeighborhoodHouseId(street, number));
        if (optionalHouse.isPresent()) {
            return ResponseEntity.ok(optionalHouse.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/housesByAge/{street}/{age}")
    public List<NeighborhoodHouse> getHousesByAge(
            @PathVariable("street") String street,
            @PathVariable("age") int age
    ) {
        return repository.findByIdStreetAndAge(street, age);
    }

    @GetMapping("/houses")
    public List<NeighborhoodHouse> getHouses() {
        return repository.findAll();
    }

    @GetMapping("/houses/active")
    public List<NeighborhoodHouse> getActiveHouses() {
        return repository.findByActiveTrue();
    }

    @PutMapping("/housesByAge/{street}/{age}")
    public long activateHousesByAge(
            @PathVariable("street") String street,
            @PathVariable("age") int age
    ) {
        return repository.activateHousesByIdStreetAndAge(street, age);
    }
}
