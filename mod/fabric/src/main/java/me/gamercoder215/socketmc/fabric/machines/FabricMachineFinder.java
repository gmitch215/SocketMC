package me.gamercoder215.socketmc.fabric.machines;

import me.gamercoder215.socketmc.fabric.FabricSocketMC;
import me.gamercoder215.socketmc.instruction.InstructionId;
import me.gamercoder215.socketmc.instruction.Machine;
import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public final class FabricMachineFinder {

    private FabricMachineFinder() {}

    public static Set<Class<?>> getMachines() {
        InputStream stream = FabricMachineFinder.class.getClassLoader()
                .getResourceAsStream("me/gamercoder215/socketmc/fabric/machines");
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        return reader.lines()
                .filter(line -> line.endsWith(".class"))
                .map(line -> {
                    try {
                        return Class.forName("me.gamercoder215.socketmc.fabric.machines." + line);
                    } catch (ClassNotFoundException e) {
                        FabricSocketMC.print(e);
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .filter(Machine.class::isAssignableFrom)
                .collect(Collectors.toUnmodifiableSet());
    }

    public static Machine getMachine(@NotNull String id) {
        return getMachines().stream()
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
