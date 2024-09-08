package xyz.gmitch215.socketmc.retriever;

import xyz.gmitch215.socketmc.SocketMC;
import xyz.gmitch215.socketmc.config.ModPermission;
import xyz.gmitch215.socketmc.spigot.SocketPlugin;

import java.util.*;
import java.util.function.Supplier;

@SuppressWarnings("unchecked")
public final class Retriever {

    public static final Set<ClientProperty<?>> PROPERTIES = Set.of(
            create(RetrieverType.OPERATING_SYSTEM, OS::current),
            create(RetrieverType.AVAILABLE_PROCESSORS, Runtime.getRuntime()::availableProcessors),
            create(RetrieverType.PLUGIN_PERMISSIONS, () -> {
                Map<SocketPlugin, Set<ModPermission>> permissions = new HashMap<>();
                for (SocketPlugin plugin : SocketMC.plugins()) {
                    Set<ModPermission> perms = new HashSet<>();
                    for (ModPermission perm : ModPermission.values())
                        if (SocketMC.isPermissionEnabled(plugin, perm)) perms.add(perm);

                    permissions.put(plugin, perms);
                }

                return permissions;
            }),
            create(RetrieverType.FREE_MEMORY, Runtime.getRuntime()::freeMemory),
            create(RetrieverType.TOTAL_MEMORY, Runtime.getRuntime()::totalMemory),
            create(RetrieverType.MAX_MEMORY, Runtime.getRuntime()::maxMemory),
            create(RetrieverType.HIDDEN_PLAYERS, () -> SocketMC.INSTANCE.get().getHiddenPlayers().toArray(UUID[]::new))
    );

    //<editor-fold desc="Properties" defaultstate="collapsed">
    private Retriever() {}

    public static <T> ClientProperty<T> create(RetrieverType<T> type, Supplier<T> supplier) {
        return new ClientProperty<>(type, supplier);
    }

    public static <T> Supplier<T> supplier(RetrieverType<T> type, Set<ClientProperty<?>> properties) {
        for (ClientProperty<?> property : properties) {
            if (property.type.equals(type))
                return (Supplier<T>) property.supplier;
        }

        return null;
    }

    public static <T> T value(RetrieverType<T> type, Set<ClientProperty<?>> properties) {
        Supplier<T> supplier = supplier(type, properties);
        if (supplier == null) return null;

        return supplier.get();
    }

    //</editor-fold>
}
