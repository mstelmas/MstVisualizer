package org.gis.mstvisualizer.Core.Simulation.Events.Queue;

import org.gis.mstvisualizer.Core.Graph.Link;
import org.gis.mstvisualizer.Core.Simulation.Events.AlgorithmEvent;

public class DequeueEdgeEvent extends AlgorithmEvent {
    final Link edge;

    public DequeueEdgeEvent(final Link edge) {
        this.edge = edge;
    }

    @Override
    public String toString() {
        return super.toString() + "DequeueEdgeEvent (Edge: " + edge + ")\n";
    }
}
