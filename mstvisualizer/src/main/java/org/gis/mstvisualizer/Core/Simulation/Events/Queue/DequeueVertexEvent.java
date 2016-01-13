package org.gis.mstvisualizer.Core.Simulation.Events.Queue;


import org.gis.mstvisualizer.Core.Graph.Vertex;
import org.gis.mstvisualizer.Core.Simulation.Events.AlgorithmEdgeEvent;
import org.gis.mstvisualizer.Core.Simulation.Events.AlgorithmEvent;
import org.gis.mstvisualizer.Core.Simulation.Events.AlgorithmEventType;
import org.gis.mstvisualizer.Core.Simulation.Events.AlgorithmVertexEvent;

public class DequeueVertexEvent extends AlgorithmVertexEvent {

    public DequeueVertexEvent(final Vertex vertex) {
        super(vertex);
    }

    @Override
    public String toString() {
        return super.toString() + "DequeueVertexEvent (V: " + getVertex() + ")\n";
    }
}
