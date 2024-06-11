package me.gamercoder215.socketmc.fabric.machines;

import me.gamercoder215.socketmc.fabric.FabricSocketMC;
import me.gamercoder215.socketmc.instruction.InstructionId;
import me.gamercoder215.socketmc.instruction.Machine;
import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public final class FabricMachineFinder {

    private FabricMachineFinder() {}

    private static final List<Class<? extends Machine>> MACHINES = Arrays.asList(
            PingMachine.class,
            DrawTextMachine.class,
            DrawShapeMachine.class,
            PlayAudioMachine.class,
            DrawBufferMachine.class,
            LogMessageMachine.class,
            DrawTextureMachine.class,
            OpenBookAndQuillMachine.class,
            OpenScreenMachine.class,
            CloseScreenMachine.class,
            RenderingMachine.class
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
                        FabricSocketMC.print(e);
                        return null;
                    }
                })
                .orElse(null);
    }

}
