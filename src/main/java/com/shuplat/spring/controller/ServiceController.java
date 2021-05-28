package com.shuplat.spring.controller;

import com.shuplat.spring.DTO.ServiceDTO;
import com.shuplat.spring.domain.Service;
import com.shuplat.spring.service.ServiceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequestMapping(value = "/service")
@RestController
public class ServiceController {
    private final ServiceService serviceService;

    public ServiceController(ServiceService serviceService) {
        this.serviceService = serviceService;
    }


    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<ServiceDTO>> getAll() {
        List<Service> services = serviceService.getAll();
        List<ServiceDTO> serviceDtos = new ArrayList<>();
        for (Service service : services) {
            ServiceDTO serviceDTO = new ServiceDTO (
                    service.getId(),
                    service.getName(),
                    service.getPrice()
            );
            serviceDtos.add(serviceDTO);
        }
        return new ResponseEntity<>(serviceDtos, HttpStatus.OK);
    }


    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<ServiceDTO> getById(@PathVariable Integer id) {
        Service service = serviceService.getById(id);
        ServiceDTO serviceDTO = new ServiceDTO (
                service.getId(),
                service.getName(),
                service.getPrice()
        );
        return new ResponseEntity<>(serviceDTO, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Void> create(@RequestBody Service newService) {
        serviceService.create(newService);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.PUT,
            value = "/{id}",
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ServiceDTO> update(@PathVariable Integer id,
                                         @RequestBody Service service) {
        Service oldService = serviceService.getById(id);
        if (oldService != null) {
            serviceService.update(id, service);
            ServiceDTO oldServiceDTO = new ServiceDTO (
                    service.getId(),
                    service.getName(),
                    service.getPrice()
            );
            return new ResponseEntity<>(oldServiceDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Integer id) {
        if (serviceService.getById(id) != null) {
            serviceService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
