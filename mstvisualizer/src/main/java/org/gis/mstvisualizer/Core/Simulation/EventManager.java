package org.gis.mstvisualizer.Core.Simulation;

import org.gis.mstvisualizer.Core.Graph.Link;
import org.gis.mstvisualizer.Core.Graph.Vertex;
import org.gis.mstvisualizer.Core.Simulation.Events.*;
import org.gis.mstvisualizer.Core.Simulation.Storage.IAlgorithmEventStorage;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class EventManager implements IEventManager {

    private final IAlgorithmEventStorage algorithmEventStorage;

    private int currentEventIndex;

    private Map<Vertex, Stack<AlgorithmEvent>> vertexStackMap = new HashMap<>();
    private Map<Link, Stack<AlgorithmEvent>> linkStackMap = new HashMap<>();


    public EventManager(IAlgorithmEventStorage algorithmEventStorage) {
        this.algorithmEventStorage = algorithmEventStorage;
        this.currentEventIndex = 0;
    }

    @Override
    public AlgorithmEvent getCurrentEvent() {
        return algorithmEventStorage.getEvent(currentEventIndex);
    }

    @Override
    public AlgorithmEvent nextEvent() {
        if(currentEventIndex + 1 < algorithmEventStorage.count()) {
            currentEventIndex++;

            final AlgorithmEvent algorithmEvent = getCurrentEvent();

            if(algorithmEvent.getAlgorithmEventType() == AlgorithmEventType.EDGE_EVENT) {
                final AlgorithmEdgeEvent algorithmEdgeEvent = (AlgorithmEdgeEvent)algorithmEvent;
                final Link edge = algorithmEdgeEvent.getEdge();

                if(linkStackMap.get(edge) == null) {
                    linkStackMap.put(edge, new Stack<>());
                    linkStackMap.get(edge).push(new ColorEdgeEvent(edge, SimulationConstants.EDGE_DEFAULT_COLOR));
                }

                linkStackMap.get(edge).push(algorithmEvent);

            } else if(algorithmEvent.getAlgorithmEventType() == AlgorithmEventType.VERTEX_EVENT) {
                final AlgorithmVertexEvent algorithmVertexEvent = (AlgorithmVertexEvent)algorithmEvent;
                final Vertex vertex = algorithmVertexEvent.getVertex();

                if(vertexStackMap.get(vertex) == null) {
                    vertexStackMap.put(vertex, new Stack<>());
                    vertexStackMap.get(vertex).push(new ColorVertexEvent(vertex, SimulationConstants.VERTEX_DEFAULT_COLOR));
                }

                vertexStackMap.get(vertex).push(algorithmEvent);
            }

            return algorithmEvent;
        } else {
            return null;
        }
    }

    @Override
    public AlgorithmEvent prevEvent() {
        if(currentEventIndex - 1 >= 0) {

            final AlgorithmEvent algorithmEvent = getCurrentEvent();
            currentEventIndex--;

            if(algorithmEvent.getAlgorithmEventType() == AlgorithmEventType.VERTEX_EVENT) {

                final AlgorithmVertexEvent algorithmVertexEvent = (AlgorithmVertexEvent)algorithmEvent;
                vertexStackMap.get(algorithmVertexEvent.getVertex()).pop();

                return vertexStackMap.get(algorithmVertexEvent.getVertex()).peek();
            } else if (algorithmEvent.getAlgorithmEventType() == AlgorithmEventType.EDGE_EVENT) {
                final AlgorithmEdgeEvent algorithmEdgeEvent = (AlgorithmEdgeEvent) algorithmEvent;
                linkStackMap.get(algorithmEdgeEvent.getEdge()).pop();
                return linkStackMap.get(algorithmEdgeEvent.getEdge()).peek();
            } else {
                return algorithmEvent;
            }

        } else {
            return null;
        }
    }

    @Override
    public void firstEvent() {
        currentEventIndex = 0;
    }

    @Override
    public void lastEvent() {
        currentEventIndex = algorithmEventStorage.count() - 1;
    }

    @Override
    public int totalEvents() {
        return algorithmEventStorage.count();
    }
}
