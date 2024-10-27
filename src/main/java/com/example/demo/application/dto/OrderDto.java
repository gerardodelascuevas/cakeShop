package com.example.demo.application.dto;

import com.example.demo.domain.model.Flavor;
import com.example.demo.domain.model.Order;
import com.example.demo.domain.model.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Gerardo De Las Cuevas
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDto  implements IExportable{
    private Long id;
    private List<String> flavorNames;
    private String description;
    private OrderStatus orderStatus;
    private String cakeSize;
    private String legend;
    private Long clientId;
    private String image;

    public OrderDto(List<String> flavorNames, String description, String cakeSize, String legend, Long clientId, String image) {
        this.flavorNames = flavorNames;
        this.description = description;
        this.cakeSize = cakeSize;
        this.legend = legend;
        this.clientId = clientId;
        this.image = image;
    }

    public static OrderDto fromEntity(Order order) {
        OrderDto dto = new OrderDto();
        List<String> flavorNames = order.getFlavors().stream()
                .map(Flavor::getName)
                .collect(Collectors.toList());

        dto.setId(order.getId());
        dto.setDescription(order.getDescription());
        dto.setOrderStatus(order.getOrderStatus());
        dto.setCakeSize(order.getCakeSize());
        dto.setLegend(order.getLegend());
        dto.setImage(order.getImage());
        dto.setFlavorNames(flavorNames);
        dto.setClientId(order.getClient().getId());
        return dto;
    }

    @Override
    public List<String> createHeader() {
        List<String> header = new ArrayList<>();
        header.add("Id");
        header.add("Description");
        header.add("Order Status");
        header.add("Cake Size");
        header.add("Legend");
        header.add("Image");
        header.add("Flavor Names");
        header.add("Client id");
        return header;
    }

    @Override
    public List<String> createRow() {
        List<String> row = new ArrayList<>();
        row.add(id.toString());
        row.add(description);
        row.add(orderStatus.toString());
        row.add(cakeSize);
        row.add(legend);
        row.add(image);
        row.add(String.join(", ", flavorNames));
        row.add(clientId.toString());
        return row;
    }

}
