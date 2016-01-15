package org.gis.mstvisualizer.Core.Simulation.Events;

import org.gis.mstvisualizer.Core.Graph.Vertex;
import org.gis.mstvisualizer.Core.Simulation.SimulationConstants;

public class VertexVisitedEvent extends AlgorithmVertexEvent {

    public VertexVisitedEvent(final Vertex vertex) {
        super(vertex);
    }

    @Override
    public void onExecute() {
        vertex.setColor(SimulationConstants.VERTEX_VISITED_COLOR);
    }

    @Override
    public String toString() {
        return super.toString() + "VertexVisitedEvent (V: " + vertex + ")\n";
    }

    @Override
    public String getDescription() {
        return "Odwiedzanie wierzchołka: " + vertex.getV();
    }
}
