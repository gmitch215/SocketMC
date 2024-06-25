package xyz.gmitch215.socketmc.retriever.util.hardware;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.Serializable;

/**
 * Represents a hardware device on the client's computer.
 */
public interface Hardware extends Serializable {

    /**
     * <p>Gets the display name of the device.</p>
     * <p>This is shown in the operating system's application manager (i.e. Task Manager).</p>
     * @return Full Device Name
     */
    @NotNull
    String getName();

    /**
     * Gets the version of the device.
     * @return The version of the device
     */
    @NotNull
    String getVersion();

    /**
     * Gets the serial number of the device.
     * @return The serial number of the device, or null if not available.
     */
    @Nullable
    String getSerial();

    /**
     * Gets the manufacturer of the device.
     * @return The manufacturer of the device, or null if not available.
     */
    @Nullable
    String getManufacturer();

}
