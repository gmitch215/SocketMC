package me.gamercoder215.socketmc.forge.machines;

import me.gamercoder215.socketmc.forge.ForgeSocketMC;
import me.gamercoder215.socketmc.instruction.InstructionId;
import me.gamercoder215.socketmc.instruction.Machine;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

public final class ForgeMachineFinder {

    private ForgeMachineFinder() {}

    private static final List<Class<? extends Machine>> MACHINES = Arrays.asList(
            PingMachine.class,
            DrawTextMachine.class,
            DrawShapeMachine.class,
            PlayAudioMachine.class,
            DrawBufferMachine.class,
            LogMessageMachine.class,
            DrawTextureMachine.class
    );

    public static Machine getMachine(@NotNull String id) {
        return MACHINES.stream()
                .filter(clazz -> clazz.isAnnotationPresent(InstructionId.class))
                .filter(clazz -> clazz.getAnnotation(InstructionId.class).value().equals(id))
                .findFirst()
                .map(clazz -> {
                    try {
                        Field instance = clazz.getDeclaredField("MACHINE");
                        return (Machine) instance.get(null);
                    } catch (NoSuchFieldException | IllegalAccessException e) {
                        ForgeSocketMC.print(e);
                        return null;
                    }
                })
                .orElse(null);
    }

}
