package org.gis.mstvisualizer.Core.Simulation.Events.Mst;


import org.gis.mstvisualizer.Core.Graph.Link;
import org.gis.mstvisualizer.Core.Simulation.Events.AlgorithmEdgeEvent;

public class AddEdgeToMstEvent extends AlgorithmEdgeEvent {

    public AddEdgeToMstEvent(final Link edge) {
        super(edge);
    }

    @Override
    public String toString() {
        return super.toString() + "AddEdgeToMstEvent (Edge: " + getEdge() + ")\n";
    }
}

