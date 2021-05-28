package com.shuplat.spring.service;

import com.shuplat.spring.domain.Service;
import com.shuplat.spring.repository.ServiceRepository;


import java.util.List;

@org.springframework.stereotype.Service
public class ServiceService implements AbstractService<Service, Integer> {
    private final ServiceRepository serviceRepository;

    public ServiceService(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    @Override
    public List<Service> getAll() {
        return serviceRepository.findAll();
    }

    @Override
    public Service getById(Integer integer) {
        return serviceRepository.getOne(integer);
    }

    @Override
    public Service create(Service newObject) {
        return serviceRepository.save(newObject);
    }

    @Override
    public Service update(Integer integer, Service object) {
        if (serviceRepository.findById(integer).isPresent()) {
            object.setId(integer);
            return serviceRepository.save(object);
        } else {
            return null;
        }
    }

    @Override
    public void deleteById(Integer id) {
        if (serviceRepository.findById(id).isPresent()) {
            serviceRepository.deleteById(id);
        }
    }
}
