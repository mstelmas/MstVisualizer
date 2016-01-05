package org.gis.mstvisualizer.Core.Simulation.Events.Queue;


import org.gis.mstvisualizer.Core.Simulation.Events.AlgorithmEvent;

public class DequeueVertexEvent extends AlgorithmEvent {
    final int vertex;

    public DequeueVertexEvent(final int vertex) {
        this.vertex = vertex;
    }

    @Override
    public String toString() {
        return super.toString() + "DequeueVertexEvent (V: " + vertex + ")\n";
    }
}
