package com.shuplat.spring.service;

import com.shuplat.spring.domain.Orders;
import com.shuplat.spring.repository.OrdersRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdersService implements AbstractService<Orders, Integer>{
    private final OrdersRepository ordersRepository;

    public OrdersService(OrdersRepository ordersRepository) {
        this.ordersRepository = ordersRepository;
    }

    @Override
    public List<Orders> getAll() {
        return ordersRepository.findAll();
    }

    @Override
    public Orders getById(Integer integer) {
        return ordersRepository.getOne(integer);
    }

    @Override
    public Orders create(Orders newObject) {
        return ordersRepository.save(newObject);
    }

    @Override
    public Orders update(Integer integer, Orders object) {
        if (ordersRepository.findById(integer).isPresent()) {
            object.setId(integer);
            return ordersRepository.save(object);
        } else {
            return null;
        }
    }

    @Override
    public void deleteById(Integer id) {
        if (ordersRepository.findById(id).isPresent()) {
            ordersRepository.deleteById(id);
        }
    }
}
