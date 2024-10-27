package com.example.demo.presentation.controller;

import com.example.demo.application.dto.OrderDto;
import com.example.demo.application.service.ImageService;
import com.example.demo.application.service.OrderService;
import com.example.demo.config.RequiredToken;
import com.example.demo.domain.model.Order;
import com.example.demo.utils.CSVStreamingOutput;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.util.List;

/**
 * @author Gerardo De Las Cuevas
 */
@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService service;

    public OrderController(OrderService service, ImageService imageService) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Order>> getOrders() {
        return ResponseEntity.ok(service.getAllOrders());
    }

    @PostMapping
    public ResponseEntity<OrderDto> createOrder(@RequestBody OrderDto dto){
        return ResponseEntity.ok(service.storeOrder(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderDto> updateOrder(@PathVariable Long id, @RequestBody OrderDto orderDto) {
        OrderDto updatedOrder = service.updateOrder(id, orderDto);
        return ResponseEntity.ok(updatedOrder);
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<OrderDto>> getMyOrders(@PathVariable Long id){
        return ResponseEntity.ok(service.getOrderByClient(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> destroyOrder(@PathVariable Long id){
        return ResponseEntity.ok(service.destroy(id));
    }

    @RequiredToken
    @GetMapping("/export")
    public ResponseEntity<StreamingResponseBody> exportOrderCsv() {
        List<OrderDto> allOrders = service.getAllOrdersAsDto();
        StreamingResponseBody output = new CSVStreamingOutput<>(allOrders);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"packages.csv\"")
                .contentType(MediaType.parseMediaType("text/csv"))
                .body(output);
    }

}
