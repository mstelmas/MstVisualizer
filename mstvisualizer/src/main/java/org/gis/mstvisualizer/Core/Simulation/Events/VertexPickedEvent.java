package org.gis.mstvisualizer.Core.Simulation.Events;


import org.gis.mstvisualizer.Core.Graph.Vertex;
import org.gis.mstvisualizer.Core.Simulation.SimulationConstants;

public class VertexPickedEvent extends AlgorithmVertexEvent {

    public VertexPickedEvent(final Vertex vertex) {
        super(vertex);
    }

    @Override
    public void onExecute(){
        vertex.setColor(SimulationConstants.VERTEX_PICKED_COLOR);
    }

    @Override
    public String toString() {
        return super.toString() + "VertexPickedEvent (V: " + vertex + ")\n";
    }

    @Override
    public String getDescription() {
        return "Wybranie wierzchołka: " + vertex.getV();
    }
}
