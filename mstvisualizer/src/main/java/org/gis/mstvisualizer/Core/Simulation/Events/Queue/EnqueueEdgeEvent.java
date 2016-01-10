package org.gis.mstvisualizer.Core.Simulation.Events.Queue;

import org.gis.mstvisualizer.Core.Graph.Link;
import org.gis.mstvisualizer.Core.Simulation.Events.AlgorithmEdgeEvent;
import org.gis.mstvisualizer.Core.Simulation.Events.AlgorithmEvent;
import org.gis.mstvisualizer.Core.Simulation.Events.AlgorithmEventType;

public class EnqueueEdgeEvent extends AlgorithmEdgeEvent {

    public EnqueueEdgeEvent(final Link edge) {
        super(edge);
    }

    @Override
    public String toString() {
        return super.toString() + "EnqueueEdgeEvent (Edge: " + getEdge() + ")\n";
    }
}
