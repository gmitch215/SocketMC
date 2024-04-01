package me.gamercoder215.socketmc.instruction;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Unmodifiable;

import java.io.*;
import java.util.List;
import java.util.Objects;

/**
 * Represents a SocketMC Instruction to be sent to the client.
 *
 * All instructions are serialized and sent over the network as byte arrays. They contain an {@linkplain #getId() id} and a list of {@linkplain #getParameters() parameters}
 * that are serialized and sent through the player's channel.
 */
public final class Instruction implements Serializable {

    // Instruction IDs

    /**
     * Instruction to verify SocketMC is present.
     */
    public static final String PING = "ping";

    @Serial
    private static final long serialVersionUID = -4177824277470078500L;

    private final String id;
    private final List<Object> parameters;

    private Instruction(String id, List<Object> parameters) {
        this.id = id;
        this.parameters = parameters;
    }

    /**
     * Gets the ID of this instruction.
     * @return Instruction ID
     */
    @NotNull
    public String getId() {
        return id;
    }

    /**
     * Gets an immutable copy of this instruction's parameters.
     * @return Instruction Parameters
     */
    @Unmodifiable
    @NotNull
    public List<Object> getParameters() {
        return List.copyOf(parameters);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Instruction that = (Instruction) o;
        return Objects.equals(id, that.id) && Objects.equals(parameters, that.parameters);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, parameters);
    }

    @Override
    public String toString() {
        return "Instruction{" +
                "id='" + id + '\'' +
                ", parameters=" + parameters +
                '}';
    }

    // Instruction Creation

    /**
     * Creates a {@link #PING} instruction.
     * @return Ping Instruction
     */
    @NotNull
    public static Instruction ping() {
        return new Instruction(PING, List.of());
    }

    // Serialization

    /**
     * Serializes this Instruction to a byte array to be passed over the network.
     * @return Byte Array Representation
     */
    @NotNull
    public byte[] toByteArray() {
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            ObjectOutputStream outw = new ObjectOutputStream(out);
            outw.writeObject(this);

            return out.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException("Failed to serialize Instruction", e);
        }
    }

    /**
     * Deserializes an Instruction from a byte array received over the network.
     * @param instruction Byte Array Representation
     * @return Deserialized Instruction
     */
    @Nullable
    public static Instruction fromByteArray(@NotNull byte[] instruction) {
        try {
            ByteArrayInputStream in = new ByteArrayInputStream(instruction);
            ObjectInputStream inw = new ObjectInputStream(in);

            return (Instruction) inw.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException("Failed to deserialize Instruction", e);
        }
    }
}
