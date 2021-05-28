package com.shuplat.spring.controller;


import com.shuplat.spring.DTO.OrdersDTO;
import com.shuplat.spring.domain.Orders;
import com.shuplat.spring.service.OrdersService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RequestMapping(value = "/order")
@RestController
public class OrdersController {
    private final OrdersService ordersService;

    public OrdersController(OrdersService ordersService) {
        this.ordersService = ordersService;
    }
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<OrdersDTO>> getAll() {
        List<Orders> orders = ordersService.getAll();
        List<OrdersDTO> ordersDtos = new ArrayList<>();
        for (Orders order : orders) {
            OrdersDTO clientDTO = new OrdersDTO (
                    order.getId(),
                    order.getClient().getName(),
                    order.getService().getName(),
                    order.getGym().getLocation()
            );
            ordersDtos.add(clientDTO);
        }
        return new ResponseEntity<>(ordersDtos, HttpStatus.OK);
    }


    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<OrdersDTO> getById(@PathVariable Integer id) {
        Orders order = ordersService.getById(id);
        OrdersDTO clientDTO = new OrdersDTO (
                order.getId(),
                order.getClient().getName(),
                order.getService().getName(),
                order.getGym().getLocation()
        );
        return new ResponseEntity<>(clientDTO, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Void> create(@RequestBody Orders newOrders) {
        ordersService.create(newOrders);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.PUT,
            value = "/{id}",
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<OrdersDTO> update(@PathVariable Integer id,
                                            @RequestBody Orders order) {
        Orders oldOrder = ordersService.getById(id);
        if (oldOrder != null) {
            ordersService.update(id, order);
            OrdersDTO oldOrderDTO = new OrdersDTO (
                    order.getId(),
                    order.getClient().getName(),
                    order.getService().getName(),
                    order.getGym().getLocation()
            );
            return new ResponseEntity<>(oldOrderDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Integer id) {
        if (ordersService.getById(id) != null) {
            ordersService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
