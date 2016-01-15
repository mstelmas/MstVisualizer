package org.gis.mstvisualizer.Core.Simulation.Events;

import org.gis.mstvisualizer.Core.Graph.Link;
import org.gis.mstvisualizer.Core.Simulation.SimulationConstants;

public class EdgePickedEvent extends AlgorithmEdgeEvent {

    public EdgePickedEvent(final Link edge) {
       super(edge);
    }

    @Override
    public void onExecute() {
        edge.setColor(SimulationConstants.EDGE_PICKED_COLOR);
    }

    @Override
    public String toString() {
        return super.toString() + "EdgePickedEvent (Edge: " + edge + ")\n";
    }

    @Override
    public String getDescription() {
        return "Wybranie krawędzi: " + edge;
    }
}
