package xyz.gmitch215.socketmc.machines;

import xyz.gmitch215.socketmc.SocketMC;
import xyz.gmitch215.socketmc.instruction.InstructionId;
import xyz.gmitch215.socketmc.instruction.Machine;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public final class MachineFinder {

    private MachineFinder() {}

    public static final List<Class<? extends Machine>> SHARED_MACHINES = Arrays.asList(
            OpenLinkMachine.class,
            MailtoMachine.class,
            PlayAudioMachine.class,
            LogMessageMachine.class,
            NarrateMachine.class,
            OSBeepMachine.class,
            ExternalPopupMachine.class,
            ExternalMessageMachine.class,
            SetWindowTitleMachine.class,
            ShowPlayersMachine.class,
            HidePlayersMachine.class
    );

    public static Machine getMachine(@NotNull Collection<Class<? extends Machine>> machines, @NotNull String id) {
        return machines.stream()
                .filter(clazz -> clazz.isAnnotationPresent(InstructionId.class))
                .filter(clazz -> clazz.getAnnotation(InstructionId.class).value().equals(id))
                .findFirst()
                .map(clazz -> {
                    try {
                        Field instance = clazz.getDeclaredField("MACHINE");
                        return (Machine) instance.get(null);
                    } catch (NoSuchFieldException | IllegalAccessException e) {
                        SocketMC.print(e);
                        return null;
                    }
                })
                .orElse(null);
    }

}
