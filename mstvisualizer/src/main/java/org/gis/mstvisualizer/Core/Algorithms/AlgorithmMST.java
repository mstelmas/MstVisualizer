package org.gis.mstvisualizer.Core.Algorithms;

import lombok.Getter;
import org.gis.mstvisualizer.Core.Graph.Link;
import org.gis.mstvisualizer.Core.Simulation.Storage.AlgorithmEventStorage;
import org.gis.mstvisualizer.Core.Simulation.Storage.IAlgorithmEventStorage;

import java.util.LinkedList;
import java.util.Queue;
import java.util.stream.StreamSupport;

public abstract class AlgorithmMST {

    @Getter
    protected double weight = 0.0;

    @Getter
    protected IAlgorithmEventStorage algorithmEventStorage;

    @Getter
    protected String name;

    @Getter
    protected String description;

    public AlgorithmMST(final String name) {
        algorithmEventStorage = new AlgorithmEventStorage();

        this.name = name;
    }

    protected final Queue<Link> mst = new LinkedList<>();

    public Iterable<Link> links() {
        return mst;
    }

    public long count() {
        return StreamSupport.stream(links().spliterator(), false)
                .count();
    }
}
