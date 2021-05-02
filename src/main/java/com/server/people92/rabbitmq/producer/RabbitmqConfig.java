package com.server.people92.rabbitmq.producer;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;

public class RabbitmqConfig {

    private static final String TEST_EXCHANGE_NAME = "rabbitmq.test.direct";
    private static final String TEST_QUEUE_NAME = "rabbitmq.test.queue";
    private static final String TEST_BIDING_KEY = "rabbitmq.test.key";

    @Bean
    TopicExchange exchange() {
        return new TopicExchange(TEST_EXCHANGE_NAME);
    }
    @Bean
    Queue queue() {
        return new Queue(TEST_QUEUE_NAME);
    }
    @Bean
    Binding binding (Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(TEST_BIDING_KEY);
    }
    @Bean
    RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory, MessageConverter messageConverter) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
        return rabbitTemplate;
    }

}
