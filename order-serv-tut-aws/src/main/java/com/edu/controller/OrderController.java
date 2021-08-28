package com.edu.controller;

import com.edu.model.Cart;
import com.edu.model.CheckoutResponse;
import com.edu.service.OrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public CheckoutResponse checkout(@RequestBody Cart cart) {
        return orderService.checkout(cart);
    }

    @GetMapping("/create")
    public void createQueue() {
        orderService.createQueue();
    }

    @GetMapping("/list")
    public void listQueues() {
        orderService.listQueues();
    }

    @GetMapping("/delete/{name}")
    public void deleteQueue(@PathVariable String name) {
        orderService.deleteQueue(name);
    }

    @GetMapping("/receive")
    public String receive() {
        return orderService.receive();
    }

    @GetMapping("/receiveLong")
    public String receive2() {
        return orderService.receive2();
    }

}
