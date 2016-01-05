package org.gis.mstvisualizer.Core.Simulation.Events;


public class VertexPickedEvent extends AlgorithmEvent {
    final int vertex;

    public VertexPickedEvent(final int vertex) {
        this.vertex = vertex;
    }

    @Override
    public String toString() {
        return super.toString() + "VertexPickedEvent (V: " + vertex + ")\n";
    }
}
