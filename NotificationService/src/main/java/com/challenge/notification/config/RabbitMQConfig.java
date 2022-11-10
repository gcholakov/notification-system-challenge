package com.challenge.notification.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * RabbitMQ queues creation/configuration.
 */
@Configuration
public class RabbitMQConfig {

    @Value("${spring.rabbitmq.host}")
    String host;
    @Value("${spring.rabbitmq.username}")
    String username;
    @Value("${spring.rabbitmq.password}")
    String password;
    @Value("${spring.rabbitmq.exchange}")
    private String exchange;

    @Value("${spring.rabbitmq.routingkey.sms}")
    private String routingKeySMS;
    @Value("${spring.rabbitmq.routingkey.email}")
    private String routingKeyEmail;
    @Value("${spring.rabbitmq.routingkey.slack}")
    private String routingKeySlack;

    @Value("${spring.rabbitmq.queue.sms}")
    private String queueSMS;
    @Value("${spring.rabbitmq.queue.email}")
    private String queueEmail;
    @Value("${spring.rabbitmq.queue.slack}")
    private String queueSlack;

    @Bean
    CachingConnectionFactory connectionFactory() {

        CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory(host);
        cachingConnectionFactory.setUsername(username);
        cachingConnectionFactory.setPassword(password);
        return cachingConnectionFactory;
    }

    @Bean
    public MessageConverter jsonMessageConverter() {

        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {

        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }

    @Bean
    Queue queueSMS() {

        return new Queue(queueSMS, true);
    }

    @Bean
    Queue queueEmail() {

        return new Queue(queueEmail, true);
    }

    @Bean
    Queue queueSlack() {

        return new Queue(queueSlack, true);
    }

    @Bean
    Exchange exchange() {

        return ExchangeBuilder.directExchange(exchange).durable(true).build();
    }

    @Bean
    Binding bindingSMS() {

        return BindingBuilder
                .bind(queueSMS())
                .to(exchange())
                .with(routingKeySMS)
                .noargs();
    }

    @Bean
    Binding bindingEmail() {

        return BindingBuilder
                .bind(queueEmail())
                .to(exchange())
                .with(routingKeyEmail)
                .noargs();
    }

    @Bean
    Binding bindingSlack() {

        return BindingBuilder
                .bind(queueSlack())
                .to(exchange())
                .with(routingKeySlack)
                .noargs();
    }
}
