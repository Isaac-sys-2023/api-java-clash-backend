package com.example.api_java_backend.controller;

import com.example.api_java_backend.service.ClashApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 *
 * @author chichimon
 */

@RestController
@RequestMapping("/api/players")
@RequiredArgsConstructor
public class PlayerController {

    private final ClashApiService clashApiService;

    @GetMapping("/{playerTag}")
    public Mono<ResponseEntity<String>> getPlayerByTag(@PathVariable String playerTag,
            @RequestParam(required = false) String limit,
            @RequestParam(required = false) String after,
            @RequestParam(required = false) String before) {

        StringBuilder endpointBuilder = new StringBuilder("/players/%23" + playerTag);
        String endpoint = endpointBuilder.toString();

        return clashApiService.getData(endpoint)
                .map(ResponseEntity::ok)
                .onErrorResume(e -> Mono.just(ResponseEntity.internalServerError()
                .body("{\"error\": \"Error fetching players: " + e.getMessage().replace("\"", "'") + "\"}")));
    }
    
    @GetMapping("/{playerTag}/upcomingchests")
    public Mono<ResponseEntity<String>> getChestsPlayerByTag(@PathVariable String playerTag) {

        StringBuilder endpointBuilder = new StringBuilder("/players/%23" + playerTag + "/upcomingchests");
        String endpoint = endpointBuilder.toString();

        return clashApiService.getData(endpoint)
                .map(ResponseEntity::ok)
                .onErrorResume(e -> Mono.just(ResponseEntity.internalServerError()
                .body("{\"error\": \"Error fetching players: " + e.getMessage().replace("\"", "'") + "\"}")));
    }
    
    @GetMapping("/{playerTag}/battlelog")
    public Mono<ResponseEntity<String>> getBattlesPlayerByTag(@PathVariable String playerTag) {

        StringBuilder endpointBuilder = new StringBuilder("/players/%23" + playerTag + "/battlelog");
        String endpoint = endpointBuilder.toString();

        return clashApiService.getData(endpoint)
                .map(ResponseEntity::ok)
                .onErrorResume(e -> Mono.just(ResponseEntity.internalServerError()
                .body("{\"error\": \"Error fetching players: " + e.getMessage().replace("\"", "'") + "\"}")));
    }
}
