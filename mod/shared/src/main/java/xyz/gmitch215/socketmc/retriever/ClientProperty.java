package xyz.gmitch215.socketmc.retriever;

import java.util.function.Supplier;

public final class ClientProperty<T> {

    public final RetrieverType<T> type;
    public final Supplier<T> supplier;

    public ClientProperty(RetrieverType<T> type, Supplier<T> supplier) {
        this.type = type;
        this.supplier = supplier;
    }

}
