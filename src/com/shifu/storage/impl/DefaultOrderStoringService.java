package com.shifu.storage.impl;

import com.shifu.entities.Order;
import com.shifu.storage.OrderStoringService;

import java.io.*;
import java.util.List;

public class DefaultOrderStoringService implements OrderStoringService {
    private static final String ORDERS_DATA_FILE_NAME = "orders.data";
    private static final String CURRENT_TASK_RESOURCE_FOLDER = "finaltask";
    private static final String RESOURCES_FOLDER = "resources";

    private static DefaultOrderStoringService instance;

    private DefaultOrderStoringService() {
    }
    @Override
    public void saveOrders(List<Order> orders) {
        try(var oos = new ObjectOutputStream(new FileOutputStream(RESOURCES_FOLDER + File.separator + CURRENT_TASK_RESOURCE_FOLDER + File.separator + ORDERS_DATA_FILE_NAME))){
            oos.writeObject(orders);
        }catch (IOException e){
            e.printStackTrace();
        }

    }

    @Override
    public List<Order> loadOrders() {
        try (var ois = new ObjectInputStream(new FileInputStream(
                RESOURCES_FOLDER + File.separator + CURRENT_TASK_RESOURCE_FOLDER
                        + File.separator + ORDERS_DATA_FILE_NAME
        ))) {
            return (List<Order>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static DefaultOrderStoringService getInstance() {
        if (instance == null) {
            instance = new DefaultOrderStoringService();
        }
        return instance;
    }


}
