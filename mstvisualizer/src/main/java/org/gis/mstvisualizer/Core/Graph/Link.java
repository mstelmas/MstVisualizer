package org.gis.mstvisualizer.Core.Graph;

import lombok.Getter;
import lombok.Setter;
import org.gis.mstvisualizer.Core.Simulation.SimulationConstants;

import java.awt.*;

public class Link implements Comparable<Link>{

    @Getter
    private final Double weight;

    private final Vertex w;

    private final Vertex v;

    @Getter @Setter
    public Color color;

    @Getter @Setter
    public Stroke stroke;

    public Link(final Vertex w, final Vertex v, final double weight){
        this(w, v, weight, SimulationConstants.EDGE_DEFAULT_COLOR);
    }

    public Link(final Vertex w, final Vertex v, final double weight, final Color color){
        this.w = w;
        this.v = v;
        this.weight = weight;
        this.color = color;

        this.stroke = new BasicStroke(2);
    }

    @Override
    public String toString(){
        return w.getV() + " - " + v.getV() + " (" + weight.toString() + " )";
    }

    public int compareTo(Link that) {
        if(this.weight < that.getWeight())
            return -1;
        else if(this.weight > that.getWeight())
            return 1;
        else
            return 0;
    }
}
