/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
                .map(ResponseEntity::ok)
                .onErrorResume(e -> Mono.just(ResponseEntity.internalServerError()
                .body("{\"error\": \"Error fetching players: " + e.getMessage().replace("\"", "'") + "\"}")));
    }
    
    @GetMapping("/{playerTag}/upcomingchests")
    public Mono<ResponseEntity<String>> getChestsPlayerByTag(@PathVariable String playerTag,
            @RequestParam(required = false) String limit,
            @RequestParam(required = false) String after,
            @RequestParam(required = false) String before) {

        StringBuilder endpointBuilder = new StringBuilder("/players/%23" + playerTag + "/upcomingchests");
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
                .map(ResponseEntity::ok)
                .onErrorResume(e -> Mono.just(ResponseEntity.internalServerError()
                .body("{\"error\": \"Error fetching players: " + e.getMessage().replace("\"", "'") + "\"}")));
    }
}
