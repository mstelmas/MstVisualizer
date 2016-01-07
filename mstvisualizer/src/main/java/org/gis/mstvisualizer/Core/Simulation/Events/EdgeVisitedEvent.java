package org.gis.mstvisualizer.Core.Simulation.Events;

import org.gis.mstvisualizer.Core.Graph.Link;

public class EdgeVisitedEvent extends AlgorithmEvent {
    final Link edge;

    public EdgeVisitedEvent(final Link edge) {
        this.edge = edge;
    }

    @Override
    public String toString() {
        return super.toString() + "EdgeVisitedEvent (Edge: " + edge + ")\n";
    }
}
