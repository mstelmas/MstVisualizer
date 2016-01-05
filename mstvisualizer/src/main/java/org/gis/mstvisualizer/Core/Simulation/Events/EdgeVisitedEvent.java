package org.gis.mstvisualizer.Core.Simulation.Events;

import org.gis.mstvisualizer.Core.Graph.Edge;

public class EdgeVisitedEvent extends AlgorithmEvent {
    final Edge edge;

    public EdgeVisitedEvent(final Edge edge) {
        this.edge = edge;
    }

    @Override
    public String toString() {
        return super.toString() + "EdgeVisitedEvent (Edge: " + edge + ")\n";
    }
}
