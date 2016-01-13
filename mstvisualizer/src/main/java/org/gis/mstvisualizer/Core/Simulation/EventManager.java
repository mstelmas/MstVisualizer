package org.gis.mstvisualizer.Core.Simulation;

import org.gis.mstvisualizer.Core.Simulation.Events.AlgorithmEvent;
import org.gis.mstvisualizer.Core.Simulation.Storage.IAlgorithmEventStorage;

public class EventManager implements IEventManager {

    private final IAlgorithmEventStorage algorithmEventStorage;
    private int currentEventIndex;

    public EventManager(IAlgorithmEventStorage algorithmEventStorage) {
        this.algorithmEventStorage = algorithmEventStorage;
        this.currentEventIndex = algorithmEventStorage.count()-1;
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
            algorithmEvent.onExecute();
            return algorithmEvent;
        } else {
            return null;
        }
    }

    @Override
    public AlgorithmEvent prevEvent() {
        if(currentEventIndex - 1 >= 0) {
            getCurrentEvent().onRevert();
            currentEventIndex--;
            return getCurrentEvent();
        } else {
            return null;
        }
    }

    @Override
    public void firstEvent() {

        while( currentEventIndex > 0 ){
            algorithmEventStorage.getEvent(currentEventIndex).onRevert();
            currentEventIndex--;
        }
    }

    @Override
    public void lastEvent() {
        while( currentEventIndex < algorithmEventStorage.count() - 1 ){
            algorithmEventStorage.getEvent(currentEventIndex).onExecute();
            currentEventIndex++;
        }
    }

    @Override
    public int totalEvents() {
        return algorithmEventStorage.count();
    }
}
