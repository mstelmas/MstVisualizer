package org.gis.mstvisualizer.Core.Simulation.Storage;

import org.gis.mstvisualizer.Core.Simulation.Events.AlgorithmEvent;

import java.util.ArrayList;
import java.util.List;

public class AlgorithmEventStorage implements IAlgorithmEventStorage {

    private List<AlgorithmEvent> algorithmEvents= new ArrayList<>();

    @Override
    public void addEvent(AlgorithmEvent algorithmEvent) {
        algorithmEvents.add(algorithmEvent);
    }

    @Override
    public boolean isEmpty() {
        return algorithmEvents.isEmpty();
    }

    @Override
    public long count() {
        return algorithmEvents.size();
    }

    @Override
    public String toString() {
        final StringBuilder stringBuilder = new StringBuilder();

        algorithmEvents.forEach(stringBuilder::append);
        return stringBuilder.toString();
    }
}
