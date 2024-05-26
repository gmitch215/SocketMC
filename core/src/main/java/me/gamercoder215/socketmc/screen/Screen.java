package me.gamercoder215.socketmc.screen;

import me.gamercoder215.socketmc.instruction.util.Text;
import org.jetbrains.annotations.NotNull;

import java.io.*;

/**
 * Represents a screen that can be displayed to the user.
 */
public abstract class Screen implements Serializable {

    @Serial
    private static final long serialVersionUID = 5312276945725639371L;

    /**
     * Constructs a new screen.
     */
    protected Screen() {}

    /**
     * Gets the title of this screen.
     * @return Title in JSON Format
     */
    @NotNull
    public abstract String getTitleJSON();

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
    public static Screen fromByteArray(byte[] bytes) {
        try {
            ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
            ObjectInputStream ois = new ObjectInputStream(bais);

            ois.readObject();
            return (Screen) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}
