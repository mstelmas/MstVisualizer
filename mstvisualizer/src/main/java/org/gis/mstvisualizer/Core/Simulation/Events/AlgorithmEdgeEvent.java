package org.gis.mstvisualizer.Core.Simulation.Events;

import lombok.Getter;
import org.gis.mstvisualizer.Core.Graph.Link;

public class AlgorithmEdgeEvent extends AlgorithmEvent {

    @Getter
    final Link edge;

    public AlgorithmEdgeEvent(final Link edge) {
        super(AlgorithmEventType.EDGE_EVENT);
        this.edge = edge;
    }
}

