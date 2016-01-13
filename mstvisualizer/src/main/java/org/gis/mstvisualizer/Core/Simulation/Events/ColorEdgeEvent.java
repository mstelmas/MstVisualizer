package org.gis.mstvisualizer.Core.Simulation.Events;

import lombok.Getter;
import org.gis.mstvisualizer.Core.Graph.Link;

import java.awt.*;

public class ColorEdgeEvent extends AlgorithmEdgeEvent {

    @Getter
    private final Color color;

    @Override
    public void onExecute() {
        edge.setColor(this.color);
    }

    public ColorEdgeEvent(final Link edge, final Color color) {
        super(edge);
        this.color = color;
    }
}
