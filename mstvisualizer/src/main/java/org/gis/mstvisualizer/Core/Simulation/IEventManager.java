package org.gis.mstvisualizer.Core.Simulation;

import org.gis.mstvisualizer.Core.Simulation.Events.AlgorithmEvent;

public interface IEventManager {
    /* Event related methods */
    AlgorithmEvent getCurrentEvent();
    AlgorithmEvent nextEvent();
    AlgorithmEvent prevEvent();
    void firstEvent();
    void lastEvent();

    int totalEvents();

}
