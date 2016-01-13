package org.gis.mstvisualizer.Core.Simulation.Events;

import lombok.Getter;
import org.gis.mstvisualizer.Core.Graph.Vertex;
import org.gis.mstvisualizer.Core.Simulation.SimulationConstants;

import java.awt.*;

public class AlgorithmVertexEvent extends AlgorithmEvent {

    @Getter
    final Vertex vertex;

    final Color vertexStartColor;

    public AlgorithmVertexEvent(final Vertex vertex) {
        super(AlgorithmEventType.VERTEX_EVENT);
        this.vertex = vertex;
        this.vertexStartColor = vertex.getColor();
        onExecute();
    }

    @Override
    public void onExecute() {
        this.vertex.setColor(SimulationConstants.VERTEX_DEFAULT_COLOR);
    }

    @Override
    public void onRevert() {
        this.vertex.setColor(this.vertexStartColor);
    }
}
