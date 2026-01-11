package com.bwromero.cinescope.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

@Configuration
public class TmdbConfig {

    @Value("${tmdb.base.url}")
    private String baseUrl;

    @Value("${tmdb.api.key}")
    private String apiKey;

    @Bean
    public WebClient tmdbWebClient() {
        return WebClient.builder() // We create the builder manually here
                .baseUrl(baseUrl)
                .filter((request, next) -> {
                    var newUri = UriComponentsBuilder.fromUri(request.url())
                            .queryParam("api_key", apiKey)
                            .build()
                            .toUri();

                    var newRequest = org.springframework.web.reactive.function.client.ClientRequest.from(request)
                            .url(newUri)
                            .build();

                    return next.exchange(newRequest);
                })
                .build();
    }
}
