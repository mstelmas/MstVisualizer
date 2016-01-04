package org.gis.mstvisualizer.Visualizer;

import org.gis.mstvisualizer.Core.Graph.WeightedGraph;
import org.gis.mstvisualizer.Core.Simulation.Storage.IAlgorithmEventStorage;

public class GraphVisualizer {

    private final IAlgorithmEventStorage algorithmEventStorage;
    private final WeightedGraph G;

    public GraphVisualizer(final WeightedGraph G, final IAlgorithmEventStorage algorithmEventStorage) {
        this.algorithmEventStorage = algorithmEventStorage;
        this.G = G;
    }
}
