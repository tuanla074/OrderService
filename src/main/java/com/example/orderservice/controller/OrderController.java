package com.example.orderservice.controller;

import com.example.orderservice.model.Order;
import com.example.orderservice.service.OrderService;
import com.example.orderservice.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/placeOrder")
    public ResponseEntity<String> placeOrder(@RequestHeader("Authorization") String token, @RequestBody Order order) {
        String jwt = token.substring(7); // Remove "Bearer " prefix
        Long userId = jwtUtil.extractUserId(jwt);
        order.setUserId(userId);
        orderService.placeOrder(order);
        return ResponseEntity.ok("Order placed successfully");
    }

    @DeleteMapping("/deleteOrder/{orderId}")
    public ResponseEntity<String> deleteOrder(@RequestHeader("Authorization") String token, @PathVariable Long orderId) {
        orderService.deleteOrder(orderId);
        return ResponseEntity.ok("Order deleted successfully");
    }

    @GetMapping("/getOrders")
    public ResponseEntity<List<Order>> getOrders(@RequestHeader("Authorization") String token) {
        String jwt = token.substring(7); // Remove "Bearer " prefix
        Long userId = jwtUtil.extractUserId(jwt);
        List<Order> orders = orderService.getOrdersByUserId(userId);
        return ResponseEntity.ok(orders);
    }
}
