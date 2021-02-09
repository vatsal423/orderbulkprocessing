package com.project.orderbulkprocessing.configuration;

import com.project.orderbulkprocessing.dto.OrderCreateDTO;
import com.project.orderbulkprocessing.dto.OrderUpdateDTO;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConsumerConfiguration {

    @Bean
    public ConsumerFactory<String, OrderCreateDTO> consumerCreateFactory(){
        Map<String,Object> config = new HashMap<>();
        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,"127.0.0.1:9092");
        config.put(ConsumerConfig.GROUP_ID_CONFIG,"group_order_create");
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        return new DefaultKafkaConsumerFactory<>(config,new StringDeserializer()
                , new JsonDeserializer<>(OrderCreateDTO.class));
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String,OrderCreateDTO> kafkaListenerContainerCreateFactory(){
        ConcurrentKafkaListenerContainerFactory<String,OrderCreateDTO> kafkaListenerFactory = new ConcurrentKafkaListenerContainerFactory<>();
        kafkaListenerFactory.setConsumerFactory(consumerCreateFactory());
        return kafkaListenerFactory;
    }

    @Bean
    public ConsumerFactory<String, OrderUpdateDTO> consumerUpdateFactory(){
        Map<String,Object> config = new HashMap<>();
        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,"127.0.0.1:9092");
        config.put(ConsumerConfig.GROUP_ID_CONFIG,"group_order_update");
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        return new DefaultKafkaConsumerFactory<>(config,new StringDeserializer()
                , new JsonDeserializer<>(OrderUpdateDTO.class));
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String,OrderUpdateDTO> kafkaListenerContainerUpdateFactory(){
        ConcurrentKafkaListenerContainerFactory<String,OrderUpdateDTO> kafkaListenerFactory = new ConcurrentKafkaListenerContainerFactory<>();
        kafkaListenerFactory.setConsumerFactory(consumerUpdateFactory());
        return kafkaListenerFactory;
    }

}
