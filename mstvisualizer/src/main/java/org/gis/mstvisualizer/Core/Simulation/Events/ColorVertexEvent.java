package org.gis.mstvisualizer.Core.Simulation.Events;

import lombok.Getter;
import org.gis.mstvisualizer.Core.Graph.Vertex;

import java.awt.*;

public class ColorVertexEvent extends AlgorithmVertexEvent {

    @Getter
    private final Color color;

    public ColorVertexEvent(final Vertex vertex, final Color color) {
        super(vertex);
        this.color = color;
    }

    @Override
    public void onExecute() {
        vertex.setColor(this.color);
    }

    @Override
    public String getDescription() {
        return null;
    }
}
