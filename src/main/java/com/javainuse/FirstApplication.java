package com.javainuse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import reactor.util.context.Context;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;

@SpringBootApplication
public class FirstApplication {

    private static Logger log = LoggerFactory.getLogger(FirstApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(FirstApplication.class, args);
    }

    @Bean
    public RouterFunction<ServerResponse> router() {

        return RouterFunctions
                .route(GET("/test"), serverRequest -> {
                    log.info("log in start");

                    Flux<String> flux = Flux.just("test")
                            .doOnNext(s -> log.info("log in doOnNext"))
                            .map(s -> {
                                log.info("log in map");
                                return s;
                            })
//                            .subscribeOn(Schedulers.elastic())
                            .flatMap(s ->
                            {
                                log.info("log in flatMap");
                                return Mono.subscriberContext().map(c -> {
                                    log.info("log in subscriberContext");
                                    return s + " " + c.getOrDefault("context", "no_data");
                                });
                            })
                            .subscriberContext(Context.of("context", "context_data " + System.currentTimeMillis()));

                    log.info("log in end");

                    return ServerResponse
                            .ok()
                            .body(flux, String.class);
                });
    }

}
