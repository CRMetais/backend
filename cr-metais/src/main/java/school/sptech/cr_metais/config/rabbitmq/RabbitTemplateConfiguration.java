package school.sptech.cr_metais.config.rabbitmq;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.*;

@Configuration
@RequiredArgsConstructor
@EnableConfigurationProperties(RabbitPropertiesConfiguration.class)
public class RabbitTemplateConfiguration {

    private final RabbitPropertiesConfiguration properties;

    @Bean
    public Declarables rabbitDeclarables() {
        DirectExchange exchange = new DirectExchange(properties.exchange().name());

        Queue queue = QueueBuilder
                .durable(properties.queue().name())
                .build();

        Binding binding = BindingBuilder
                .bind(queue)
                .to(exchange)
                .with(properties.routingKey().name());

        return new Declarables(exchange, queue, binding);
    }

    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    // ✅ CORRIGIDO: RabbitTemplate precisa do converter configurado explicitamente
    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(messageConverter());
        return template;
    }
}