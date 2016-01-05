package org.gis.mstvisualizer.Core.Simulation.Events;

public class VertexVisitedEvent extends AlgorithmEvent {
    final int vertex;

    public VertexVisitedEvent(final int vertex) {
        this.vertex = vertex;
    }

    @Override
    public String toString() {
        return super.toString() + "VertexVisitedEvent (V: " + vertex + ")\n";
    }
}
