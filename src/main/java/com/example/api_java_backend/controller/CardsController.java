package com.example.api_java_backend.controller;

import com.example.api_java_backend.service.ClashApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 *
 * @author chichimon
 */

@RestController
@RequestMapping("/api/cards")
@RequiredArgsConstructor
public class CardsController {    
    private final ClashApiService clashApiService;
    
    @GetMapping
    public Mono<ResponseEntity<String>> getCards(@RequestParam(required = false) String limit,
            @RequestParam(required = false) String after,
            @RequestParam(required = false) String before) {

        StringBuilder endpointBuilder = new StringBuilder("/cards");
        boolean hasParam = false;

        if (limit != null) {
            endpointBuilder.append(hasParam ? "&" : "?").append("limit=").append(limit);
            hasParam = true;
        }
        if (after != null) {
            endpointBuilder.append(hasParam ? "&" : "?").append("after=").append(after);
            hasParam = true;
        }
        if (before != null) {
            endpointBuilder.append(hasParam ? "&" : "?").append("before=").append(before);
        }

        String endpoint = endpointBuilder.toString();

        return clashApiService.getData(endpoint)
                .map(json -> clashApiService.addEvoCiclesToCards(json))
                .map(ResponseEntity::ok)
                .onErrorResume(e -> Mono.just(ResponseEntity.internalServerError()
                .body("{\"error\": \"Error fetching cards: " + e.getMessage().replace("\"", "'") + "\"}")));
    }
}
