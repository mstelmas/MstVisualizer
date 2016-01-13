package org.gis.mstvisualizer.Core.Simulation.Events.Queue;


import org.gis.mstvisualizer.Core.Graph.Vertex;
import org.gis.mstvisualizer.Core.Simulation.Events.AlgorithmVertexEvent;
import org.gis.mstvisualizer.Core.Simulation.SimulationConstants;

public class DequeueVertexEvent extends AlgorithmVertexEvent {

    public DequeueVertexEvent(final Vertex vertex) {
        super(vertex);
    }

    @Override
    public void onExecute() {
        getVertex().setColor(SimulationConstants.VERTEX_DEQUEUE_COLOR);
    }

    @Override
    public String toString() {
        return super.toString() + "DequeueVertexEvent (V: " + getVertex() + ")\n";
    }
}
