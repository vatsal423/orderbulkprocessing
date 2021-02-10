package com.project.orderbulkprocessing.controller;

import com.project.orderbulkprocessing.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrderBulkProcessingController {

    @Autowired
    private KafkaTemplate<String,OrderCreateDTO> kafkaTemplateCreate;

    @Autowired
    private KafkaTemplate<String,OrderUpdateDTO> kafkaTemplateUpdate;

    private static final String TOPIC1 = "bulk_order";

    private static final String TOPIC2 = "bulk_update_order";

    @PostMapping("/create/bulk/order")
    public void orderBulkCreate(@RequestBody BulkOrderCreateDTO bulkOrderCreateDTO){
        for(OrderCreateDTO orderCreateDTO : bulkOrderCreateDTO.getOrders()){
            kafkaTemplateCreate.send(TOPIC1,orderCreateDTO);
        }
    }

    @PutMapping("/update/bulk/order")
    public void orderBulkUpdate(@RequestBody BulkOrderUpdateDTO bulkOrderUpdateDTO){
        for(OrderUpdateDTO orderUpdateDTO : bulkOrderUpdateDTO.getOrders()){
            kafkaTemplateUpdate.send(TOPIC2,orderUpdateDTO);
        }
    }

}
