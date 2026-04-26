package school.sptech.cr_metais.config.rabbitmq;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "broker")
public record RabbitPropertiesConfiguration(
        Exchange exchange,
        Queue queue,
        RoutingKey routingKey
) {
    public record Exchange(String name) {}
    public record Queue(String name) {}
    public record RoutingKey(String name) {}
}