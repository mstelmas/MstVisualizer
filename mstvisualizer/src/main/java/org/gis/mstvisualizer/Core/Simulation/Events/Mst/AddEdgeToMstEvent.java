package org.gis.mstvisualizer.Core.Simulation.Events.Mst;


import org.gis.mstvisualizer.Core.Graph.Link;
import org.gis.mstvisualizer.Core.Simulation.Events.AlgorithmEdgeEvent;
import org.gis.mstvisualizer.Core.Simulation.SimulationConstants;

public class AddEdgeToMstEvent extends AlgorithmEdgeEvent {

    public AddEdgeToMstEvent(final Link edge) {
        super(edge);
    }

    @Override
    public void onExecute() {
        getEdge().setColor(SimulationConstants.EDGE_MST_COLOR);
    }

    @Override
    public String toString() {
        return super.toString() + "AddEdgeToMstEvent (Edge: " + getEdge() + ")\n";
    }

    @Override
    public String getDescription() {
        return "Dodanie krawędzi: " + getEdge() + " do MST";
    }
}

