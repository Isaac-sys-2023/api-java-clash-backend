package com.example.api_java_backend.service;

/**
 *
 * @author chichimon
 */
import com.example.api_java_backend.util.EvolutionData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

@Service
@RequiredArgsConstructor
public class ClashApiService {

    private final ObjectMapper objectMapper = new ObjectMapper();

    private final WebClient clashApiClient;

    public Mono<String> getData(String endpoint) {
        return clashApiClient.get()
                .uri(endpoint)
                .retrieve()
                .bodyToMono(String.class);
    }

    public String addFlagsToLocations(String json) {
        try {
            JsonNode root = objectMapper.readTree(json);
            JsonNode items = root.get("items");

            if (items != null && items.isArray()) {
                for (JsonNode item : items) {
                    boolean isCountry = item.path("isCountry").asBoolean(false);
                    String countryCode = item.path("countryCode").asText(null);

                    if (isCountry && countryCode != null && !countryCode.isEmpty()) {
                        String flagUrl = "https://flagcdn.com/w320/" + countryCode.toLowerCase() + ".png";
                        ((ObjectNode) item).put("flagUrl", flagUrl);
                    } else {
                        ((ObjectNode) item).put("flagUrl", (String) null);
                    }
                }
            }

            return objectMapper.writeValueAsString(root);

        } catch (Exception e) {
            e.printStackTrace();
            return json; // Si algo falla, retorna el JSON original sin modificar
        }
    }

    public String addEvoCiclesToCards(String json) {
        try {
            JsonNode root = objectMapper.readTree(json);
            JsonNode items = root.get("items");

            if (items != null && items.isArray()) {
                for (JsonNode item : items) {
                    boolean isEvo = item.has("maxEvolutionLevel") && item.path("maxEvolutionLevel").asInt(0) > 0;;
                    int id = item.path("id").asInt(-1);

                    if (isEvo && id != -1) {
                        Integer evolutionCycle = EvolutionData.getEvolutionCycle(id);
                        System.out.println("ID: " + id + ", EvolutionCycle: " + evolutionCycle);
                        if (evolutionCycle != null) {
                            ((ObjectNode) item).put("evolutionCycle", evolutionCycle);
                        }
                    }
                }
            }

            return objectMapper.writeValueAsString(root);
        } catch (Exception e) {
            e.printStackTrace();
            return json;
        }
    }
}
