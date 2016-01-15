package org.gis.mstvisualizer.Core.Simulation.Events;

import lombok.Getter;
import org.gis.mstvisualizer.Core.Graph.Link;
import org.gis.mstvisualizer.Core.Simulation.SimulationConstants;

import java.awt.*;

public class AlgorithmEdgeEvent extends AlgorithmEvent {

    @Getter
    final Link edge;

    final Color edgeStartColor;

    public AlgorithmEdgeEvent(final Link edge) {
        super(AlgorithmEventType.EDGE_EVENT);
        this.edge = edge;
        this.edgeStartColor = edge.getColor();
        onExecute();
    }

    @Override
    public void onExecute() {
        this.edge.setColor(SimulationConstants.EDGE_DEFAULT_COLOR);
    }

    @Override
    public void onRevert() {
        this.edge.setColor(this.edgeStartColor);
    }

    @Override
    public String getDescription() {
        return null;
    }

}

