package br.com.senac.ThallesNascimentoEmissor.configs;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import jakarta.annotation.PostConstruct;

@Component
public class MQConfig {
    @Autowired
    private AmqpAdmin amqpAdmin;

    @PostConstruct
    private void Create (){
        Queue queue = new Queue("permissoes", true, false, false);

        // Criar o direct exchange
        DirectExchange directExchange = new DirectExchange("banco");

        // Create the binding
        Binding binding = new Binding(queue.getName(), Binding.DestinationType.QUEUE, directExchange.getName(), queue.getName(), null);

        amqpAdmin.declareQueue(queue);
        amqpAdmin.declareExchange(directExchange);
        amqpAdmin.declareBinding(binding);
    }

    @Bean
    public Jackson2JsonMessageConverter messageConverter(){
        ObjectMapper objectMapper = new ObjectMapper();
        return new Jackson2JsonMessageConverter(objectMapper);
    }

}
