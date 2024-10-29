package com.example.demo.application.service;

import com.example.demo.application.dto.OrderDto;
import com.example.demo.domain.model.*;
import com.example.demo.domain.repository.ClientRepository;
import com.example.demo.domain.repository.FlavorRepository;
import com.example.demo.domain.repository.OrderRepository;
import com.example.demo.domain.repository.SizeRepository;
import com.example.demo.domain.service.OrderDomainService;
import com.example.demo.infraestructure.persistence.repository.ClientJpaRepository;
import com.example.demo.infraestructure.persistence.repository.FlavorJpaRepository;
import com.example.demo.infraestructure.persistence.repository.ImageMongoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Gerardo De Las Cuevas
 */

@Service
public class OrderService implements OrderDomainService {

    private final OrderRepository orderRepository;
    private final ClientRepository clientRepository;
    private final FlavorRepository flavorRepository;
    private final SizeRepository sizeRepository;
    private final ImageService imageService;

    public OrderService(OrderRepository orderRepository, ClientRepository clientRepository, FlavorJpaRepository flavorRepository, ImageMongoRepository imageRepository, SizeRepository sizeRepository, ImageService imageService) {
        this.orderRepository = orderRepository;
        this.clientRepository = clientRepository;
        this.flavorRepository = flavorRepository;
        this.sizeRepository = sizeRepository;
        this.imageService = imageService;
    }

    @Override
    public Order store(Order order) {
        if (!isCakeAvailable(order.getCakeSize())) {
            throw new IllegalArgumentException("Sorry, size not available at moment.");
        }
        return orderRepository.save(order);
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public List<Order> getByClientId(Long id) {
       return orderRepository.findByClientId(id);
    }

    @Override
    public List<Order> getByDate(LocalDateTime startDate, LocalDateTime endDate) {
        List<Order> allOrders = orderRepository.findAll();
        return allOrders.stream()
                .filter(order -> order.getCreatedAt().isAfter(startDate) && order.getCreatedAt().isBefore(endDate))
                .collect(Collectors.toList());
    }

    public OrderDto storeOrder(OrderDto dto) {
        Optional<Client> optionalClient = clientRepository.findById(dto.getClientId());

        if (optionalClient.isEmpty()) {
            throw new IllegalArgumentException("Client not found");
        }
        String imageName = dto.getImage();
        if(imageName != null){
            imageService.getImageByName(imageName);
        }
        List<Flavor> flavors = getFlavors(dto);
        Client client = optionalClient.get();
        Order newOrder = new Order(flavors, dto.getDescription(), OrderStatus.NEW, dto.getCakeSize(), dto.getLegend(), client, dto.getImage());

        Order storedOrder = store(newOrder);
        dto.setId(storedOrder.getId());
        return dto;
    }

    public OrderDto updateOrder(Long orderId, OrderDto orderDto) {
        Optional<Order> optionalOrder = orderRepository.findById(orderId);

        if (optionalOrder.isPresent()) {
            Order order = optionalOrder.get();

            if (order.getOrderStatus() == OrderStatus.NEW) {
                if (!isCakeAvailable(orderDto.getCakeSize())) {
                    throw new IllegalArgumentException("Sorry, size not available at moment.");
                }
                List<Flavor> flavors = getFlavors(orderDto);
                order.setFlavors(flavors);
                order.setDescription(orderDto.getDescription());

                Order updateOrder = orderRepository.save(order);
                orderDto.setId(updateOrder.getId());
                orderDto.setOrderStatus(updateOrder.getOrderStatus());
                return orderDto;
            } else {
                throw new IllegalArgumentException("Order cannot be updated at this moment, Sorry.");
            }
        } else {
            throw new EntityNotFoundException("Order not found.");
        }
    }

    public List<OrderDto> getOrderByClient(Long id) {
        List<Order> clientOrders = getByClientId(id);
        return clientOrders.stream()
                .map(OrderDto::fromEntity)
                .collect(Collectors.toList());
    }

    public String destroy(Long id){
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Order not found with id: " + id));
        if(order.getOrderStatus().equals(OrderStatus.IN_PROGRESS) || order.getOrderStatus().equals(OrderStatus.DELIVERED)){
            throw new IllegalArgumentException("Order cannot be canceled");
        }
        orderRepository.deleteById(id);
        return "Order deleted completely";
    }

    public List<OrderDto> getAllOrdersAsDto(){
        List<Order> allOrder = orderRepository.findAll();

        return allOrder.stream()
                .map(OrderDto::fromEntity)
                .collect(Collectors.toList());
    }

    public List<Flavor> getFlavors(OrderDto dto){
        if (dto.getFlavorNames().size() > 3) {
            throw new IllegalArgumentException("A maximum of 3 flavors is allowed.");
        }
        List<Flavor> flavors = flavorRepository.findByNameIn(dto.getFlavorNames());
        if (flavors.isEmpty()) {
            throw new IllegalArgumentException("Invalid flavors provided");
        }
        return flavors;
    }

    private Boolean isCakeAvailable(String sizeName) {
        Optional<Size> optionalSize = sizeRepository.findBySizeName(sizeName);
        return optionalSize.map(Size::isAvailable).orElse(false);
    }

}