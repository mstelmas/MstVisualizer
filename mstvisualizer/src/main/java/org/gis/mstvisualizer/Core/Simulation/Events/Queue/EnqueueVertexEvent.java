package org.gis.mstvisualizer.Core.Simulation.Events.Queue;

import org.gis.mstvisualizer.Core.Graph.Vertex;
import org.gis.mstvisualizer.Core.Simulation.Events.AlgorithmEvent;
import org.gis.mstvisualizer.Core.Simulation.Events.AlgorithmEventType;
import org.gis.mstvisualizer.Core.Simulation.Events.AlgorithmVertexEvent;

public class EnqueueVertexEvent extends AlgorithmVertexEvent {

    public EnqueueVertexEvent(final Vertex vertex) {
        super(vertex);
    }

    @Override
    public String toString() {
        return super.toString() + "EnqueueVertexEvent (V: " + getVertex() + ")\n";
    }
}
