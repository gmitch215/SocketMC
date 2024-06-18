package me.gamercoder215.socketmc.forge.machines;

import me.gamercoder215.socketmc.instruction.Machine;
import me.gamercoder215.socketmc.machines.LogMessageMachine;
import me.gamercoder215.socketmc.machines.MachineFinder;

import java.util.List;
import java.util.stream.Stream;

public final class ForgeMachineFinder {

    private ForgeMachineFinder() {}

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
            DrawBeaconBeamMachine.class
    )).toList();

}
