package org.gis.mstvisualizer.Core.Simulation.Events;


import org.gis.mstvisualizer.Core.Graph.Link;
import org.gis.mstvisualizer.Core.Simulation.SimulationConstants;

public class EdgeSkipEvent extends AlgorithmEdgeEvent {
    public EdgeSkipEvent(final Link edge) {
        super(edge);
    }

    @Override
    public void onExecute() {
        edge.setColor(SimulationConstants.EDGE_SKIP_COLOR);
    }

    @Override
    public String toString() {
        return super.toString() + "EdgeSkipEvent (Edge: " + edge + ")\n";
    }

    @Override
    public String getDescription() {
        return "Pominięcie krawędzi: " + edge;
    }
}
