package com.bwromero.cinescope.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.ClientRequest;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Configuration
public class TmdbConfig {

    @Value("${tmdb.base.url}")
    private String baseUrl;

    @Value("${tmdb.api.key}")
    private String apiKey;

    @Bean
    public WebClient tmdbWebClient() {
        return WebClient.builder()
                .baseUrl(baseUrl)
                .filter((request, next) -> {
                    URI url = UriComponentsBuilder.fromUri(request.url())
                            .queryParam("api_key", apiKey)
                            .build(true)
                            .toUri();
                    return next.exchange(ClientRequest.from(request).url(url).build());
                })
                .build();
    }
}
