package com.project.orderbulkprocessing.service;

import com.project.orderbulkprocessing.dto.*;
import com.project.orderbulkprocessing.model.*;
import com.project.orderbulkprocessing.repository.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OrderBulkProcessingConsumerService {

    Logger log = LogManager.getLogger(OrderBulkProcessingConsumerService.class);

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    OrderStatusRepository orderStatusRepository;

    @Autowired
    ShippingRepository shippingRepository;

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    PaymentsTypeRepository paymentsTypeRepository;

    @Autowired
    PaymentRepository paymentRepository;

    @Autowired
    OrderItemRepository orderItemRepository;


    @KafkaListener(topics = "bulk_order",groupId ="group_order_create",
            containerFactory = "kafkaListenerContainerCreateFactory")
    public void consumeOrderCreate(OrderCreateDTO orderCreateDTO){
        orderCreateService(orderCreateDTO);
    }

    @KafkaListener(topics = "bulk_order_update",groupId = "group_order_update",
            containerFactory = "kafkaListenerContainerUpdateFactory")
    public void consumeOrderUpdate(OrderUpdateDTO orderUpdateDTO){
        orderUpdateService(orderUpdateDTO);
    }

    private void orderUpdateService(OrderUpdateDTO orderUpdateDTO) {
        Optional<Orders> optionalOrder = orderRepository.findById(UUID.fromString(orderUpdateDTO.getOrderId()));
        if(optionalOrder.isEmpty()){
            log.error("Invalid Order Id!");
            return;
        }
        Optional<OrderStatus> optionalOrderStatus = orderStatusRepository.findById(UUID.fromString(orderUpdateDTO.getOrderStatusId()));
        if(optionalOrderStatus.isEmpty()){
            log.error("Invalid Order Status!");
            return;
        }
        Orders order = optionalOrder.get();
        order.setOrderStatus(optionalOrderStatus.get());
        order.setModifiedDate(new Date());
        orderRepository.save(order);
        log.info("Order Updated Successfully!");
    }


    public void orderCreateService(OrderCreateDTO orderCreateDTO) {
        try {
            Orders order = new Orders();

            UUID orderId = UUID.randomUUID();
            order.setId(orderId);

            Optional<Customers> customer = customerRepository.findById(UUID.fromString(orderCreateDTO.getCustomerId()));
            if (customer.isEmpty()) {
                log.error("Invalid Customer!");
                return;
            } else
                order.setCustomers(customer.get());

            OrderStatus orderStatus = orderStatusRepository.getStatusByName("ORDERED");
            order.setOrderStatus(orderStatus);

            Optional<Shipping> shipping = shippingRepository.findById(UUID.fromString(orderCreateDTO.getShippingId()));
            if (shipping.isEmpty()) {
                log.error("Invalid Shipping Method!");
                return;
            } else
                order.setShipping(shipping.get());

            BigDecimal preTaxAmount = new BigDecimal(0);
            List<ItemCreateDTO> itemCreateDTOList = orderCreateDTO.getItems();
            for (int i = 0; i < itemCreateDTOList.size(); i++) {
                Optional<Items> item = itemRepository.findById(UUID.fromString(itemCreateDTOList.get(i).getItemId()));
                if (item.isEmpty()) {
                    log.error("Invalid Item" + i + 1 + "!");
                    return;
                } else {
                    BigDecimal quantity = new BigDecimal(itemCreateDTOList.get(i).getQuantity());
                    preTaxAmount = preTaxAmount.add(item.get().getPrice().multiply(quantity));
                }
            }
            order.setAmountPreTax(preTaxAmount);

            BigDecimal taxAmount = preTaxAmount.multiply(new BigDecimal(0.08));
            order.setTaxAmount(taxAmount);

            BigDecimal shippingAmount = new BigDecimal(10);
            order.setShippingAmount(shippingAmount);

            BigDecimal totalAmount = preTaxAmount.add(taxAmount).add(shippingAmount);
            order.setTotalAmount(totalAmount);

            Date date = new Date();
            order.setCreatedDate(date);
            order.setModifiedDate(date);

            Orders insertedOrder = orderRepository.save(order);

            paymentItemCreateService(orderCreateDTO, insertedOrder);
            log.info("Order Placed");
        } catch (Exception e) {
            log.error("Error Placing Order", e);
            return;
        }
    }

    private void paymentItemCreateService(OrderCreateDTO orderCreateDTO, Orders order) {

        List<ItemCreateDTO> itemCreateDTOList = orderCreateDTO.getItems();
        for (int i = 0; i < itemCreateDTOList.size(); i++) {
            OrderItems orderItem = new OrderItems();
            orderItem.setId(UUID.randomUUID());
            orderItem.setOrders(order);
            Optional<Items> item = itemRepository.findById(UUID.fromString(itemCreateDTOList.get(i).getItemId()));
            orderItem.setItems(item.get());
            orderItem.setQuantity(itemCreateDTOList.get(i).getQuantity());
            orderItemRepository.save(orderItem);
        }

        List<PaymentCreateDTO> paymentCreateDTOList = orderCreateDTO.getPayments();
        for (int i = 0; i < paymentCreateDTOList.size(); i++) {
            Payments payment = new Payments();
            payment.setId(UUID.randomUUID());
            Optional<PaymentsType> paymentsType = paymentsTypeRepository.findById(UUID.fromString(paymentCreateDTOList.get(i).getPaymentTypeId()));
            if (paymentsType.isEmpty()) {
                log.error("Invalid Payment Method!");
                return;
            } else
                payment.setPaymentsType(paymentsType.get());
            payment.setOrders(order);
            payment.setAddressLine1(paymentCreateDTOList.get(i).getAddressLine1());
            payment.setCardNo(paymentCreateDTOList.get(i).getCardNo());
            payment.setAddressLine2(paymentCreateDTOList.get(i).getAddressLine2());
            payment.setCity(paymentCreateDTOList.get(i).getCity());
            payment.setState(paymentCreateDTOList.get(i).getState());
            payment.setZip(paymentCreateDTOList.get(i).getZip());
            payment.setAmount(paymentCreateDTOList.get(i).getAmount());
            payment.setDate(new Date());
            payment.setConfirmationNumber(UUID.randomUUID().toString());
            paymentRepository.save(payment);
        }
    }

}