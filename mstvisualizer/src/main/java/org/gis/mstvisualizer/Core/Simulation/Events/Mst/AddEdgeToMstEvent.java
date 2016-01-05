package org.gis.mstvisualizer.Core.Simulation.Events.Mst;


import org.gis.mstvisualizer.Core.Graph.Edge;
import org.gis.mstvisualizer.Core.Simulation.Events.AlgorithmEvent;

public class AddEdgeToMstEvent extends AlgorithmEvent {

    final Edge edge;

    public AddEdgeToMstEvent(final Edge edge) {
        this.edge = edge;
    }

    @Override
    public String toString() {
        return super.toString() + "AddEdgeToMstEvent (Edge: " + edge + ")\n";
    }
}

