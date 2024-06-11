package me.gamercoder215.socketmc.fabric.screen;

import me.gamercoder215.socketmc.screen.Positionable;
import me.gamercoder215.socketmc.util.SerializableConsumer;

import java.util.Collection;
import java.util.Set;

public interface FabricWidget {

    void socketMC$addClickListeners(Collection<SerializableConsumer<Positionable>> listener);

    Set<SerializableConsumer<Positionable>> socketMC$getClickListeners();

}
