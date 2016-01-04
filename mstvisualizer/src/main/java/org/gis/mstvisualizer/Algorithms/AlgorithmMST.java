package org.gis.mstvisualizer.Algorithms;

import lombok.Getter;
import org.gis.mstvisualizer.Graph.Edge;

import java.util.LinkedList;
import java.util.Queue;

public abstract class AlgorithmMST {
    @Getter
    protected double weight;

    protected Queue<Edge> mst = new LinkedList<Edge>();

    public abstract Iterable<Edge> edges();
}
