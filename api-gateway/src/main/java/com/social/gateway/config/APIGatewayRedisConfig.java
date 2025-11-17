package com.social.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class APIGatewayRedisConfig {

    /**
     * Configure ReactiveRedisTemplate for reactive (non-blocking) Redis operations
     * Used in AuthenticationFilter for token blacklist checking
     * @param connectionFactory Reactive Redis connection factory
     * @return ReactiveRedisTemplate configured with String serializers
     */
    @Bean
    public ReactiveRedisTemplate<String, String> reactiveRedisTemplate(
            ReactiveRedisConnectionFactory connectionFactory
    ){
        StringRedisSerializer serializer = new StringRedisSerializer();

        RedisSerializationContext<String, String> serializationContext =
                RedisSerializationContext.<String, String>newSerializationContext()
                        .key(serializer)
                        .value(serializer)
                        .hashKey(serializer)
                        .hashValue(serializer)
                        .build();

        return new ReactiveRedisTemplate<>(connectionFactory, serializationContext);
    }
}
