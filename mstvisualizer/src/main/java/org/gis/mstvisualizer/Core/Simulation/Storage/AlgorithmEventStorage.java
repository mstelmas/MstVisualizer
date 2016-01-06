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
    public AlgorithmEvent getEvent(final int event) {
        if(event < 0 || event >= count()) {
            throw new IllegalArgumentException();
        }

        return algorithmEvents.get(event);
    }

    @Override
    public boolean isEmpty() {
        return algorithmEvents.isEmpty();
    }

    @Override
    public int count() {
        return algorithmEvents.size();
    }

    @Override
    public String toString() {
        final StringBuilder stringBuilder = new StringBuilder();

        algorithmEvents.forEach(stringBuilder::append);
        return stringBuilder.toString();
    }
}
