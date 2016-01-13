package org.gis.mstvisualizer.Core.Simulation.Events;

import lombok.Getter;
import org.gis.mstvisualizer.Core.Graph.Vertex;

public class AlgorithmVertexEvent extends AlgorithmEvent {

    @Getter
    final Vertex vertex;

    public AlgorithmVertexEvent(final Vertex vertex) {
        super(AlgorithmEventType.VERTEX_EVENT);
        this.vertex = vertex;
    }
}
