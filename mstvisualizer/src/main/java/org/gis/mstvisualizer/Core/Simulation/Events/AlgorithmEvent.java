package org.gis.mstvisualizer.Core.Simulation.Events;

import lombok.Getter;

public abstract class AlgorithmEvent {

    @Getter
    private final AlgorithmEventType algorithmEventType;

    public AlgorithmEvent(final AlgorithmEventType algorithmEventType) {
        this.algorithmEventType = algorithmEventType;
    }

    @Override
    public String toString() {
        return "AlgorithmEvent: ";
    }
}
