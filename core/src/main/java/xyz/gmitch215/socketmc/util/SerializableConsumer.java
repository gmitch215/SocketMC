package xyz.gmitch215.socketmc.util;

import java.io.*;
import java.util.function.Consumer;

/**
 * Represents a {@link Consumer<T>} that is {@link Serializable}. This means that any actions performed in the consumer must only be operations that can be serialized.
 * <br><br>
 * For example, the following usage is not allowed:
 * <pre>{@code
 * String s = "Hello, World!";
 *
 * SerializableConsumer<String> consumer = s -> {
 *    System.out.println(s);
 *    System.out.println("Hello, World!");
 *    System.out.println("Goodbye, World!");
 * }
 * }</pre>
 * This is because the {@code System.out.println} method is not serializable. You cannot use or construct non-serializable objects inside of the consumer, and must instead
 * carry out all non-serializable actions outside the consumer. This means that any spigot-side stuff like calling events or sending packets cannot be done inside the consumer.
 * <br><br>
 * You also can't call any methods or use fields outside of the consumer, since they will not be updated when the consumer is deserialized. For example:
 * <pre>{@code
 * private String field = "Hello, World!";
 *
 * public void someMethod() {
 *     SerializableConsumer<String> consumer = s -> {
 *         field = s;
 *     };
 * }
 * }</pre>
 *
 * In this case, the {@code field} will not be updated when the consumer is deserialized, because the code will be executed on the user client.
 * <br><br>
 * Instead, you should use a {@link SerializableConsumer} like this:
 *
 * <pre>{@code
 * String str = "";
 *
 * SerializableConsumer<String> consumer = s -> {
 *     s = s + "Hello, World!";
 *     s = s + " ";
 *     s = s + "Goodbye, World!";
 * };
 *
 * consumer.accept(str);
 * System.out.println(str); // Prints "Hello, World! Goodbye, World!"
 * }</pre>
 *
 * If you need to perform an operation that is not serializable using a {@link SerializableConsumer} (e.g. with Screens), try utilizing other methods such as the Event or Instruction API.
 * @param <T> The type of the input to the operation
 */
@FunctionalInterface
public interface SerializableConsumer<T> extends Consumer<T>, Serializable {

    /**
     * Serializes this consumer into a byte array.
     * @return Byte Array
     */
    default byte[] toByteArray() {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);

            oos.writeObject(this);
            oos.close();

            return baos.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Deserializes a consumer from a byte array.
     * @param bytes Byte Array
     * @return Deserialized Consumer
     * @param <T> The type of the input to the operation
     */
    @SuppressWarnings("unchecked")
    static <T> SerializableConsumer<T> fromByteArray(byte[] bytes) {
        try {
            ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
            ObjectInputStream ois = new ObjectInputStream(bais);

            return (SerializableConsumer<T>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}
