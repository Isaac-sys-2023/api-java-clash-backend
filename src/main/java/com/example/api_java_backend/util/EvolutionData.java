package com.example.api_java_backend.util;

/**
 *
 * @author chichimon
 */

import java.util.HashMap;
import java.util.Map;

public class EvolutionData {

    private static final Map<Integer, Integer> evolutionCycles = new HashMap<>();

    static {
        evolutionCycles.put(26000000, 2);  // Knight - ciclo 2
        evolutionCycles.put(26000001, 2);  // Archers - ciclo 2
        evolutionCycles.put(26000008, 1);  // Barbarians - ciclo 1
        evolutionCycles.put(26000024, 1);  // Royal Giant - ciclo 1
        evolutionCycles.put(26000064, 2);  // Firecracker - ciclo 2
        evolutionCycles.put(26000010, 2);  // Skeletons - ciclo 2
        evolutionCycles.put(27000002, 2);  // Mortar - ciclo 2
        evolutionCycles.put(26000047, 1);  // Royal Recruits - ciclo 1
        evolutionCycles.put(26000049, 2);  // Bats - ciclo 2
        evolutionCycles.put(26000030, 2);  // Ice Spirit - ciclo 2
        evolutionCycles.put(26000011, 2);  // Valkyrie - ciclo 2
        evolutionCycles.put(26000013, 2);  // Bomber - ciclo 2
        evolutionCycles.put(26000058, 2);  // Wall Breakers - ciclo 2
        evolutionCycles.put(27000006, 2);  // Tesla - ciclo 2
        evolutionCycles.put(28000008, 2);  // Zap - ciclo 2
        evolutionCycles.put(26000036, 2);  // Battle Ram - ciclo 2
        evolutionCycles.put(26000017, 1);  // Wizard - ciclo 1
        evolutionCycles.put(28000004, 2);  // Goblin Barrel - ciclo 2
        evolutionCycles.put(26000060, 1);  // Goblin Giant - ciclo 1
        evolutionCycles.put(27000013, 2);  // Goblin Drill - ciclo 2
        evolutionCycles.put(27000012, 1);  // Goblin Cage - ciclo 1
        evolutionCycles.put(26000004, 1);  // P.E.K.K.A - ciclo 1
        evolutionCycles.put(26000055, 1);  // Mega Knight - ciclo 1
        evolutionCycles.put(26000063, 1);  // Electro Dragon - ciclo 1
        evolutionCycles.put(26000014, 2);  // Musketeer - ciclo 2
        evolutionCycles.put(27000000, 2);  // Cannon - ciclo 2
        evolutionCycles.put(28000017, 2);  // Giant Snowball - ciclo 2
        evolutionCycles.put(26000040, 2);  // Dart Goblin - ciclo 2
        evolutionCycles.put(26000035, 2);  // Lumberjack - ciclo 2
        evolutionCycles.put(26000044, 2);  // Hunter - ciclo 2
        evolutionCycles.put(26000045, 1);  // Executioner - ciclo 1
        evolutionCycles.put(26000007, 1);  // Witch - ciclo 1
        evolutionCycles.put(26000037, 2);  // Inferno Dragon - ciclo 2
        evolutionCycles.put(26000056, 2);  // SkeletonBarrel - ciclo 2
    }

    public static Integer getEvolutionCycle(int cardId) {
        return evolutionCycles.get(cardId);
    }
}