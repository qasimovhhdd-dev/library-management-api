package com.yourname.library.order;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<Order> create(@RequestBody Map<String, List<Integer>> body) {
        List<Long> bookIds = body.get("bookIds").stream().map(Integer::longValue).toList();
        List<Integer> quantities = body.get("quantities");

        Order order = orderService.createOrder(bookIds, quantities);
        return ResponseEntity.status(HttpStatus.CREATED).body(order);
    }
}