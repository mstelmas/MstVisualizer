package org.gis.mstvisualizer.Visualizer;

import org.gis.mstvisualizer.Core.Graph.WeightedGraph;
import org.gis.mstvisualizer.Core.Simulation.Storage.IAlgorithmEventStorage;

public class GraphVisualizer {

    private final WeightedGraph G;

    private final IAlgorithmEventStorage algorithmEventStorage;

    public GraphVisualizer(final WeightedGraph G, final IAlgorithmEventStorage algorithmEventStorage) {
        this.algorithmEventStorage = algorithmEventStorage;
        this.G = G;
    }

    public void run() {

        /* For now, just print out all the algorithm events */

        System.out.println("Total number of events: " + algorithmEventStorage.count());
        System.out.println(algorithmEventStorage);
    }
}
