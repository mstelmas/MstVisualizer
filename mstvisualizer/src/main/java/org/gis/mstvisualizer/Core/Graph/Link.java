package org.gis.mstvisualizer.Core.Graph;

import lombok.Getter;

public class Link implements Comparable<Link>{
    @Getter
    private final Double weight;

    public Link(double weight){
        this.weight = weight;
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
