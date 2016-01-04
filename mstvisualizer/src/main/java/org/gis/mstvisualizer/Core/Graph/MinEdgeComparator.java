package org.gis.mstvisualizer.Core.Graph;

import java.util.Comparator;

public class MinEdgeComparator implements Comparator<Edge>{
    public int compare(Edge o1, Edge o2) {
        return (o1.compareTo(o2));
    }
}
