package xyz.gmitch215.socketmc.screen;

import org.jetbrains.annotations.NotNull;

import java.io.*;

/**
 * Represents a screen that can be displayed to the user.
 * <strong>This class is not legal for implementation!</strong>
 */
public abstract class AbstractScreen implements Narratable {

    @Serial
    private static final long serialVersionUID = 5312276945725639371L;

    /**
     * Constructs a new screen.
     */
    protected AbstractScreen() {}

    /**
     * Gets the title of this screen.
     * @return Title in JSON Format
     */
    @NotNull
    public abstract String getTitleJSON();

    @Override
    public String getNarrationMessageJSON() {
        return getTitleJSON();
    }

    /**
     * Serializes this screen to a byte array.
     * @return Byte Array
     */
    @NotNull
    public final byte[] toByteArray() {
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
     * Deserializes a screen from a byte array.
     * @param bytes Byte Array
     * @return Deserialized Screem
     */
    @NotNull
    public static AbstractScreen fromByteArray(byte[] bytes) {
        try {
            ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
            ObjectInputStream ois = new ObjectInputStream(bais);

            ois.readObject();
            return (AbstractScreen) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}
