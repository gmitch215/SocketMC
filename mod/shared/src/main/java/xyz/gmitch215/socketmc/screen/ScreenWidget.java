package xyz.gmitch215.socketmc.screen;

import xyz.gmitch215.socketmc.util.SerializableConsumer;

import java.util.Collection;
import java.util.Set;

public interface ScreenWidget {

    void socketMC$addClickListeners(Collection<SerializableConsumer<Positionable>> listener);

    Set<SerializableConsumer<Positionable>> socketMC$getClickListeners();

}
