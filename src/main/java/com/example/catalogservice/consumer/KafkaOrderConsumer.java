package com.example.catalogservice.consumer;

import com.example.catalogservice.entity.CatalogEntity;
import com.example.catalogservice.repository.CatalogRepository;
import com.example.catalogservice.service.CatalogService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaOrderConsumer {

    private final CatalogRepository catalogRepository;

    @KafkaListener(topics = "order-topic")
    public void processMessage(String kafkaMessage){
        log.info("kafka message ==>" + kafkaMessage);

        ObjectMapper mapper = new ObjectMapper();
        Map<Object, Object> map = new HashMap<>();

        try {
            map = mapper.readValue(kafkaMessage, new TypeReference<Map<Object, Object>>() {
            });
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        String productId = (String) map.get("productId");
        Integer qty = (Integer)map.get("qty");

        CatalogEntity catalog = catalogRepository.findByProductId(productId);
        catalog.setStock( catalog.getStock() - qty);

        catalogRepository.save(catalog);
    }
}
