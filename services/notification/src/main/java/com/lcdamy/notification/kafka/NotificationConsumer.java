package com.lcdamy.notification.kafka;

import com.lcdamy.notification.model.Notification;
import com.lcdamy.notification.model.NotificationType;
import com.lcdamy.notification.record.OrderConfirmation;
import com.lcdamy.notification.record.PaymentConfirmation;
import com.lcdamy.notification.record.Product;
import com.lcdamy.notification.repository.NotificationRepository;
import com.lcdamy.notification.service.EmailService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationConsumer {
    private final NotificationRepository notificationRepository;
    private final EmailService emailService;


    @KafkaListener(topics = "payment-topic", groupId = "notification-group")
    public void consumePaymentSuccessNotification(Map<String, Object> payload) throws MessagingException {
        String orderReference = (String) payload.get("orderReference");
        Object amount = payload.get("amount");
        String email = (String) payload.get("customerEmail");
        String name = payload.get("customerFirstname") + " " + payload.get("customerLastname");

        emailService.sentPaymentSuccessEmail(
                email,
                name,
                new BigDecimal(amount.toString()),
                orderReference
        );
    }

    @KafkaListener(topics = "order-topic", groupId = "notification-group")
    public void consumeOrderSuccessNotification(Map<String, Object> payload) throws MessagingException {
        log.info("Consuming the message from order-topic:: {}", payload);

        // extract fields from the map
        String orderReference = (String) payload.get("orderReference");
        Object totalAmount = payload.get("totalAmount");
        String paymentMethod = (String) payload.get("paymentMethod");

        Map<String, Object> customer = (Map<String, Object>) payload.get("customer");
        String email = customer != null ? (String) customer.get("email") : null;
        String customerName = customer != null
                ? customer.get("firstname") + " " + customer.get("lastname")
                : null;

        List<Map<String, Object>> products =
                (List<Map<String, Object>>) payload.get("products");

        // save whatever you want
        notificationRepository.save(
                Notification.builder()
                        .type(NotificationType.ORDER_CONFIRMATION)
                        .notificationDate(LocalDateTime.now())
                        // you can store the raw payload or parts of it
                        .build()
        );

        // send email
        emailService.sentOrderSuccessEmail(
                email,
                customerName,
                new BigDecimal(totalAmount.toString()),
                orderReference,
                products.stream()
                        .map(p -> new Product(
                                ((Number) p.get("productId")).intValue(),
                                (String) p.get("name"),
                                (String) p.get("description"),
                                new BigDecimal(p.get("price").toString()),
                                ((Number) p.get("quantity")).intValue()
                        ))
                        .toList()
        );
    }



}
