package com.server.people92.rabbitmq.producer;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class RabbitmqProducer {

    private static final String TEST_EXCHANGE_NAME = "rabbitmq.test.direct";
    private static final String TEST_BIDING_KEY = "rabbitmq.test.key";


    private RabbitTemplate rabbitTemplate;

    public RabbitmqProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @GetMapping("/producer/{message}")
    public String RabbitmqProducer(@PathVariable String message){

        rabbitTemplate.convertAndSend(TEST_EXCHANGE_NAME,TEST_BIDING_KEY, message);

        return "SUCCESS";
    }
}
