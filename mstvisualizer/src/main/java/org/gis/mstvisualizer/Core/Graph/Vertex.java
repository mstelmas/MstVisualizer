package org.gis.mstvisualizer.Core.Graph;

import lombok.Getter;
import lombok.Setter;

import java.awt.*;

public class Vertex {

    @Getter
    private final int v;

    @Getter @Setter
    private Color color;

    public Vertex(final int v) {
        this.v = v;
        this.color = Color.WHITE;
    }

    public Vertex(final int v, final Color color) {
        this.v = v;
        this.color = color;
    }

    @Override
    public String toString() {
        return String.valueOf(v);
    }
}
