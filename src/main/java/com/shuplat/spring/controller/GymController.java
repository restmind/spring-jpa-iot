package com.shuplat.spring.controller;


import com.shuplat.spring.DTO.GymDTO;
import com.shuplat.spring.domain.Gym;
import com.shuplat.spring.service.GymService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequestMapping(value = "/gym")
@RestController
public class GymController {
    private final GymService gymService;

    public GymController(GymService gymService) {
        this.gymService = gymService;
    }


    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<GymDTO>> getAll() {
        List<Gym> gyms = gymService.getAll();
        List<GymDTO> gymDtos = new ArrayList<>();
        for (Gym gym : gyms) {
            GymDTO gymtDTO = new GymDTO (
                    gym.getId(),
                    gym.getLocation()
            );
            gymDtos.add(gymtDTO);
        }
        return new ResponseEntity<>(gymDtos, HttpStatus.OK);
    }


    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<GymDTO> getById(@PathVariable Integer id) {
        Gym gym = gymService.getById(id);
        GymDTO gymDTO = new GymDTO (
                gym.getId(),
                gym.getLocation()
        );
        return new ResponseEntity<>(gymDTO, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Void> create(@RequestBody Gym newGym) {
        gymService.create(newGym);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.PUT,
            value = "/{id}",
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<GymDTO> update(@PathVariable Integer id,
                                            @RequestBody Gym gym) {
        Gym oldGym = gymService.getById(id);
        if (oldGym != null) {
            gymService.update(id, gym);
            GymDTO oldGymDTO = new GymDTO (
                    gym.getId(),
                    gym.getLocation()
            );
            return new ResponseEntity<>(oldGymDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Integer id) {
        if (gymService.getById(id) != null) {
            gymService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
