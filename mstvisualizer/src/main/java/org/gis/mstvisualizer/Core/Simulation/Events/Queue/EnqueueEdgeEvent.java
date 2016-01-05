package org.gis.mstvisualizer.Core.Simulation.Events.Queue;

import org.gis.mstvisualizer.Core.Graph.Edge;
import org.gis.mstvisualizer.Core.Simulation.Events.AlgorithmEvent;

public class EnqueueEdgeEvent extends AlgorithmEvent {
    final Edge edge;

    public EnqueueEdgeEvent(final Edge edge) {
        this.edge = edge;
    }

    @Override
    public String toString() {
        return super.toString() + "EnqueueEdgeEvent (Edge: " + edge + ")\n";
    }
}
