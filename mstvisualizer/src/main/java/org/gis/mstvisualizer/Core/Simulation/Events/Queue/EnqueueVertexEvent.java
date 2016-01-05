package org.gis.mstvisualizer.Core.Simulation.Events.Queue;

import org.gis.mstvisualizer.Core.Simulation.Events.AlgorithmEvent;

public class EnqueueVertexEvent extends AlgorithmEvent {
    final int vertex;

    public EnqueueVertexEvent(final int vertex) {
        this.vertex = vertex;
    }

    @Override
    public String toString() {
        return super.toString() + "EnqueueVertexEvent (V: " + vertex + ")\n";
    }
}
