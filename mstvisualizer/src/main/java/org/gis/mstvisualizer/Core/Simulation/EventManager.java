package org.gis.mstvisualizer.Core.Simulation;

import org.gis.mstvisualizer.Core.Simulation.Events.AlgorithmEvent;
import org.gis.mstvisualizer.Core.Simulation.Storage.IAlgorithmEventStorage;

public class EventManager implements IEventManager {

    private final IAlgorithmEventStorage algorithmEventStorage;

    private int currentEventIndex;

    public EventManager(IAlgorithmEventStorage algorithmEventStorage) {
        this.algorithmEventStorage = algorithmEventStorage;
        this.currentEventIndex = 0;
    }

    @Override
    public AlgorithmEvent getCurrentEvent() {
        return algorithmEventStorage.getEvent(currentEventIndex);
    }

    @Override
    public void nextEvent() {
        if(currentEventIndex + 1 < algorithmEventStorage.count()) {
            currentEventIndex++;
        }
    }

    @Override
    public void prevEvent() {
        if(currentEventIndex - 1 >= 0) {
            currentEventIndex--;
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
