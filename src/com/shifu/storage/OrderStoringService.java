package com.shifu.storage;

import com.shifu.entities.Order;

import java.util.List;

public interface OrderStoringService {
    void saveOrders(List<Order> order);

    List<Order> loadOrders();
}
