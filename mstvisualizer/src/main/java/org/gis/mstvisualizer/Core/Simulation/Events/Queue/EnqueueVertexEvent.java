package org.gis.mstvisualizer.Core.Simulation.Events.Queue;

import org.gis.mstvisualizer.Core.Graph.Vertex;
import org.gis.mstvisualizer.Core.Simulation.Events.AlgorithmVertexEvent;
import org.gis.mstvisualizer.Core.Simulation.SimulationConstants;

public class EnqueueVertexEvent extends AlgorithmVertexEvent {

    public EnqueueVertexEvent(final Vertex vertex) {
        super(vertex);
    }

    @Override
    public void onExecute() {
        getVertex().setColor(SimulationConstants.VERTEX_ENQUEUE_COLOR);
    }

    @Override
    public String toString() {
        return super.toString() + "EnqueueVertexEvent (V: " + getVertex() + ")\n";
    }

    @Override
    public String getDescription() {
        return "Odłozenie wierzchołka: " + getVertex().getV() + " do kolejki";
    }
}
