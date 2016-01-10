package org.gis.mstvisualizer.Core.Simulation.Events;

import lombok.Getter;
import org.gis.mstvisualizer.Core.Graph.Vertex;

public class VertexVisitedEvent extends AlgorithmVertexEvent {

    public VertexVisitedEvent(final Vertex vertex) {
        super(vertex);
    }

    @Override
    public String toString() {
        return super.toString() + "VertexVisitedEvent (V: " + vertex + ")\n";
    }
}
