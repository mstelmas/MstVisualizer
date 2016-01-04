package org.gis.mstvisualizer.Core.Algorithms;

import lombok.Getter;
import org.gis.mstvisualizer.Core.Graph.Edge;
import org.gis.mstvisualizer.Core.Simulation.Storage.IAlgorithmEventStorage;

import java.util.LinkedList;
import java.util.Queue;

public abstract class AlgorithmMST {

    @Getter
    protected double weight;

    @Getter
    protected IAlgorithmEventStorage algorithmEventStorage;

    protected Queue<Edge> mst = new LinkedList<Edge>();

    public Iterable<Edge> edges() {
        return mst;
    }
}
