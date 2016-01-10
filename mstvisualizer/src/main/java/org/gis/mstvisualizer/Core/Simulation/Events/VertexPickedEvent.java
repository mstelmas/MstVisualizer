package org.gis.mstvisualizer.Core.Simulation.Events;


import org.gis.mstvisualizer.Core.Graph.Vertex;

public class VertexPickedEvent extends AlgorithmVertexEvent {

    public VertexPickedEvent(final Vertex vertex) {
        super(vertex);
    }

    @Override
    public String toString() {
        return super.toString() + "VertexPickedEvent (V: " + vertex + ")\n";
    }
}
