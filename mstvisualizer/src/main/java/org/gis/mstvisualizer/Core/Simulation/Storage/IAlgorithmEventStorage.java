package org.gis.mstvisualizer.Core.Simulation.Storage;

import org.gis.mstvisualizer.Core.Simulation.Events.AlgorithmEvent;

public interface IAlgorithmEventStorage {
    void addEvent(final AlgorithmEvent algorithmEvent);
}
