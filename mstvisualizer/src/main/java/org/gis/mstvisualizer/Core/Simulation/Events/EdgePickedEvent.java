package org.gis.mstvisualizer.Core.Simulation.Events;

import org.gis.mstvisualizer.Core.Graph.Link;

public class EdgePickedEvent extends AlgorithmEvent {
    final Link edge;

    public EdgePickedEvent(final Link edge) {
        this.edge = edge;
    }

    @Override
    public String toString() {
        return super.toString() + "EdgePickedEvent (Edge: " + edge + ")\n";
    }
}
