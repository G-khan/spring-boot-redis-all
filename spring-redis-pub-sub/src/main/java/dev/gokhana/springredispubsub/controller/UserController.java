package dev.gokhana.springredispubsub.controller;

import dev.gokhana.springredispubsub.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.connection.ReactiveSubscription;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.ReactiveRedisMessageListenerContainer;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.UUID;

@RestController
public class UserController {

    // logger
    public static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private final ReactiveRedisTemplate<String, User> reactiveTemplate;

    private final ReactiveRedisMessageListenerContainer reactiveMsgListenerContainer;

    private final ChannelTopic topic;

    public UserController(ReactiveRedisTemplate<String, User> reactiveTemplate, ReactiveRedisMessageListenerContainer reactiveMsgListenerContainer, ChannelTopic topic) {
        this.reactiveTemplate = reactiveTemplate;
        this.reactiveMsgListenerContainer = reactiveMsgListenerContainer;
        this.topic = topic;
    }

    // create user method with post mapping
    @PostMapping("/messages/users")
    public Mono<Long> createUser(@RequestBody User user) {
        logger.info("createUser called with user: {}", user);
        user.setId(UUID.randomUUID().toString());
        return reactiveTemplate.convertAndSend(topic.getTopic(), user);
    }

    @GetMapping(path = "/messages/users", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Object> receiveUsers() {
        logger.info("Starting to receive users from {}", topic.getTopic());
        return reactiveMsgListenerContainer
                .receive(Collections.singletonList(topic),
                        reactiveTemplate.getSerializationContext().getKeySerializationPair(),
                        reactiveTemplate.getSerializationContext().getValueSerializationPair())
                .log()
                .map(ReactiveSubscription.Message::getMessage)
                .map(message -> {
                    logger.info("Received message: {}", message);
                    return message;
                });
    }



}
