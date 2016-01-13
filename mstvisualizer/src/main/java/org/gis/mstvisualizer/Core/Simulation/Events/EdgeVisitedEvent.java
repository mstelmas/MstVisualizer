package org.gis.mstvisualizer.Core.Simulation.Events;

import lombok.Getter;
import org.gis.mstvisualizer.Core.Graph.Link;

public class EdgeVisitedEvent extends AlgorithmEdgeEvent {

    public EdgeVisitedEvent(final Link edge) {
        super(edge);
    }

    @Override
    public String toString() {
        return super.toString() + "EdgeVisitedEvent (Edge: " + edge + ")\n";
    }
}
