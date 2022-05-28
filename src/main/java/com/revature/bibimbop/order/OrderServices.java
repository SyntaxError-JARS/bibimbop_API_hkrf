package com.revature.bibimbop.order;

import com.revature.bibimbop.util.interfaces.Serviceable;

import java.io.IOException;
import java.util.List;

public class OrderServices implements Serviceable<Order> {
    private final OrderDao orderDao;


    public OrderServices(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    @Override
    public Order create(Order newOrder) {
        return orderDao.create(newOrder);
    }

    @Override
    public List<Order> readAll() {
        return orderDao.findAll();
    }

    @Override
    public Order readById(String id) {
        return orderDao.findById(id);
    }

    @Override
    public Order update(Order updatedOrder) {
        return null;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }

    @Override
    public boolean validateInput(Order object) {
        return false;
    }
}
