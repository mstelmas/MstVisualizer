package org.gis.mstvisualizer.Core.Simulation.Events.Mst;


import org.gis.mstvisualizer.Core.Graph.Link;
import org.gis.mstvisualizer.Core.Simulation.Events.AlgorithmEvent;

public class AddEdgeToMstEvent extends AlgorithmEvent {

    final Link edge;

    public AddEdgeToMstEvent(final Link edge) {
        this.edge = edge;
    }

    @Override
    public String toString() {
        return super.toString() + "AddEdgeToMstEvent (Edge: " + edge + ")\n";
    }
}

