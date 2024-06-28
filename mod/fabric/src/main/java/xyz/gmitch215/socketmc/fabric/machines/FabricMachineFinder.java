package xyz.gmitch215.socketmc.fabric.machines;

import xyz.gmitch215.socketmc.instruction.Machine;
import xyz.gmitch215.socketmc.machines.LogMessageMachine;
import xyz.gmitch215.socketmc.machines.MachineFinder;

import java.util.List;
import java.util.stream.Stream;

public final class FabricMachineFinder {

    private FabricMachineFinder() {}

    public static final List<Class<? extends Machine>> MACHINES = Stream.concat(MachineFinder.SHARED_MACHINES.stream(), Stream.of(
            PingMachine.class,
            DrawTextMachine.class,
            DrawShapeMachine.class,
            DrawBufferMachine.class,
            LogMessageMachine.class,
            DrawTextureMachine.class,
            OpenBookAndQuillMachine.class,
            OpenScreenMachine.class,
            CloseScreenMachine.class,
            RenderingMachine.class,
            DrawBeaconBeamMachine.class,
            DrawContextMachine.class,
            DisplayToastMachine.class
    )).toList();

}
