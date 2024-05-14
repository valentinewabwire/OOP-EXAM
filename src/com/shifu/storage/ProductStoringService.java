package com.shifu.storage;

import com.shifu.entities.Product;

import java.util.List;

public interface ProductStoringService {
    List<Product> loadProducts();
}
