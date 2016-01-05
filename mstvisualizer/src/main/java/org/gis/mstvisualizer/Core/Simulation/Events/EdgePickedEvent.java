package org.gis.mstvisualizer.Core.Simulation.Events;

import org.gis.mstvisualizer.Core.Graph.Edge;

public class EdgePickedEvent extends AlgorithmEvent {
    final Edge edge;

    public EdgePickedEvent(final Edge edge) {
        this.edge = edge;
    }

    @Override
    public String toString() {
        return super.toString() + "EdgePickedEvent (Edge: " + edge + ")\n";
    }
}
