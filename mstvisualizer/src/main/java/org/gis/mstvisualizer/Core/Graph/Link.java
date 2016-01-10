package org.gis.mstvisualizer.Core.Graph;

import lombok.Getter;
import lombok.Setter;

import java.awt.*;

public class Link implements Comparable<Link>{

    @Getter
    private final Double weight;

    @Getter @Setter
    private Color color;

    public Link(double weight){
        this.weight = weight;
        this.color = Color.BLACK;
    }

    public Link(double weight, Color color){
        this.weight = weight;
        this.color = color;
    }

    @Override
    public String toString(){
        return this.weight.toString();
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
