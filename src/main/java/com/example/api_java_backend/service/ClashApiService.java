package com.example.api_java_backend.service;

/**
 *
 * @author chichimon
 */

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ClashApiService {
    private final WebClient clashApiClient;

    public Mono<String> getData(String endpoint) {
        return clashApiClient.get()
                .uri(endpoint)
                .retrieve()
                .bodyToMono(String.class);
    }
}
