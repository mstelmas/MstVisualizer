package org.gis.mstvisualizer.Core.Simulation.Events;

import org.gis.mstvisualizer.Core.Graph.Link;

public class EdgePickedEvent extends AlgorithmEdgeEvent {

    public EdgePickedEvent(final Link edge) {
       super(edge);
    }

    @Override
    public String toString() {
        return super.toString() + "EdgePickedEvent (Edge: " + edge + ")\n";
    }
}
