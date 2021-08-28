package com.edu.service;

import com.edu.model.Cart;
import com.edu.model.CheckoutResponse;

public interface OrderService {
    CheckoutResponse checkout(Cart cart);

    String receive();
    String receive2();

    void createQueue();

    void listQueues();

    void deleteQueue(String queueName);
}
