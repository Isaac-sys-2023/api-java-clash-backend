package com.example.api_java_backend.controller;

/**
*
* @author chichimon
*/

import com.example.api_java_backend.service.ClashApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/api/locations")
@RequiredArgsConstructor
public class LocationController {

    @Autowired
    private ObjectMapper objectMapper;

    private final ClashApiService clashApiService;

    @GetMapping
    public Mono<ResponseEntity<String>> getLocations(@RequestParam(required = false) String limit,
            @RequestParam(required = false) String after,
            @RequestParam(required = false) String before) {

        StringBuilder endpointBuilder = new StringBuilder("/locations");
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
                .map(json -> clashApiService.addFlagsToLocations(json))
                .map(ResponseEntity::ok)
                .onErrorResume(e -> Mono.just(ResponseEntity.internalServerError()
                .body("{\"error\": \"Error fetching locations: " + e.getMessage().replace("\"", "'") + "\"}")));
    }

    @GetMapping("/{locationId}")
    public Mono<ResponseEntity<String>> getLocationById(@PathVariable String locationId) {
        String endpoint = "/locations/" + locationId;
        return proxyRequest(endpoint);
    }
    
    @GetMapping("/{locationId}/rankings/clans")
    public Mono<ResponseEntity<String>> getRankingClansByLocation(@PathVariable String locationId,
            @RequestParam(required = false) String limit,
            @RequestParam(required = false) String after,
            @RequestParam(required = false) String before) {
        StringBuilder endpointBuilder = new StringBuilder("/locations/" + locationId + "/rankings/clans");
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
        return proxyRequest(endpoint);
    }
    
    @GetMapping("/{locationId}/rankings/players")
    public Mono<ResponseEntity<String>> getRankingPlayersByLocation(@PathVariable String locationId,
            @RequestParam(required = false) String limit,
            @RequestParam(required = false) String after,
            @RequestParam(required = false) String before) {
        StringBuilder endpointBuilder = new StringBuilder("/locations/" + locationId + "/rankings/players");
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
        return proxyRequest(endpoint);
    }
    
    @GetMapping("/{locationId}/rankings/clanwars")
    public Mono<ResponseEntity<String>> getRankingClanWarsByLocation(@PathVariable String locationId,
            @RequestParam(required = false) String limit,
            @RequestParam(required = false) String after,
            @RequestParam(required = false) String before) {
        StringBuilder endpointBuilder = new StringBuilder("/locations/" + locationId + "/rankings/clanwars");
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
        return proxyRequest(endpoint);
    }
    
    @GetMapping("/{locationId}/pathoflegend/players")
    public Mono<ResponseEntity<String>> getRankingPathOfLegendsByLocation(@PathVariable String locationId,
            @RequestParam(required = false) String limit,
            @RequestParam(required = false) String after,
            @RequestParam(required = false) String before) {
        StringBuilder endpointBuilder = new StringBuilder("/locations/" + locationId + "/pathoflegend/players");
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
        return proxyRequest(endpoint);
    }

    // Método para centralizar la obtención de datos y el manejo de errores
    private Mono<ResponseEntity<String>> proxyRequest(String endpoint) {
        return clashApiService.getData(endpoint)
                .map(ResponseEntity::ok)
                .onErrorResume(e -> {
                    try {
                        String errorJson = objectMapper.writeValueAsString(Map.of("error", e.getMessage()));
                        return Mono.just(ResponseEntity.internalServerError().body(errorJson));
                    } catch (Exception ex) {
                        return Mono.just(ResponseEntity.internalServerError().body("{\"error\": \"Unexpected error\"}"));
                    }
                });
    }
}
