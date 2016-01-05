package org.gis.mstvisualizer.Core.Simulation.Events.Queue;

import org.gis.mstvisualizer.Core.Graph.Edge;
import org.gis.mstvisualizer.Core.Simulation.Events.AlgorithmEvent;

public class DequeueEdgeEvent extends AlgorithmEvent {
    final Edge edge;

    public DequeueEdgeEvent(final Edge edge) {
        this.edge = edge;
    }

    @Override
    public String toString() {
        return super.toString() + "DequeueEdgeEvent (Edge: " + edge + ")\n";
    }
}
