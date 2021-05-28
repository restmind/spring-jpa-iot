package com.shuplat.spring.service;

import com.shuplat.spring.domain.Gym;
import com.shuplat.spring.repository.GymRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GymService implements AbstractService<Gym, Integer> {
    private final GymRepository gymRepository;

    public GymService(GymRepository gymRepository) {
        this.gymRepository = gymRepository;
    }

    @Override
    public List<Gym> getAll() {
        return gymRepository.findAll();
    }

    @Override
    public Gym getById(Integer integer) {
        return gymRepository.getOne(integer);
    }

    @Override
    public Gym create(Gym newObject) {
        return gymRepository.save(newObject);
    }

    @Override
    public Gym update(Integer integer, Gym object) {
        if (gymRepository.findById(integer).isPresent()) {
            object.setId(integer);
            return gymRepository.save(object);
        } else {
            return null;
        }
    }

    @Override
    public void deleteById(Integer id) {
        if (gymRepository.findById(id).isPresent()) {
            gymRepository.deleteById(id);
        }
    }
}
