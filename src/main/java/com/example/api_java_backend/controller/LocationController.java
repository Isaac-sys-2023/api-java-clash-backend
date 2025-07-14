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

@RestController
@RequestMapping("/api/locations")
@RequiredArgsConstructor
public class LocationController {

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

    
}
