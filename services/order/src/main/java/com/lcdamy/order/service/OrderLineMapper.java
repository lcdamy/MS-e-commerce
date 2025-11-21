package com.lcdamy.order.service;

import com.lcdamy.order.model.Order;
import com.lcdamy.order.model.OrderLine;
import com.lcdamy.order.record.OrderLineRequest;
import com.lcdamy.order.record.OrderLineResponse;
import com.lcdamy.order.record.OrderResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderLineMapper {
    public OrderLine toOrderLine(OrderLineRequest orderLineRequest) {
        return OrderLine.builder()
                .id(orderLineRequest.id())
                .quantity(orderLineRequest.quantity())
                .order(
                        Order.builder()
                            .id(orderLineRequest.orderId())
                            .build()

                )
                .productId(orderLineRequest.productId())
                .build();

    }

    public OrderLineResponse toOrderLineResponse(OrderLine orderLine) {
        return new OrderLineResponse(
                orderLine.getId(),
                orderLine.getQuantity()
        );
    }
}
