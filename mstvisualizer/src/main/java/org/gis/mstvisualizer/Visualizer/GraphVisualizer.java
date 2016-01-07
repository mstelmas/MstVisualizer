package org.gis.mstvisualizer.Visualizer;

import edu.uci.ics.jung.graph.Graph;
import org.gis.mstvisualizer.Core.Simulation.EventManager;
import org.gis.mstvisualizer.Core.Simulation.IEventManager;
import org.gis.mstvisualizer.Core.Simulation.Storage.IAlgorithmEventStorage;

public final class GraphVisualizer {

    private final Graph G;

    private final IAlgorithmEventStorage algorithmEventStorage;

    private final IEventManager eventManager;

    public GraphVisualizer(final Graph G, final IAlgorithmEventStorage algorithmEventStorage) {
        this.algorithmEventStorage = algorithmEventStorage;
        this.G = G;
        this.eventManager = new EventManager(algorithmEventStorage);
    }

    public void run() {

        /* For now, just print out all the algorithm events */

        System.out.println("Total number of events: " + algorithmEventStorage.count());
        System.out.println(algorithmEventStorage);
    }
}
