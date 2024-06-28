package xyz.gmitch215.socketmc.util.math;

import org.jetbrains.annotations.NotNull;
import org.joml.Quaternionf;
import org.joml.Vector3f;

/**
 * Utility functional interface to convert a rotation amount to a {@link Quaternionf}. Commonly represents an axis of rotation.
 */
@FunctionalInterface
public interface Axis {

    /**
     * Represents the X axis on the positive side.
     */
    Axis X_POSITIVE = f -> new Quaternionf().rotationX(f);

    /**
     * Represents the Y axis on the negative side.
     */
    Axis X_NEGATIVE = f -> new Quaternionf().rotationX(-f);

    /**
     * Represents the Y axis on the positive side.
     */
    Axis Y_POSITIVE = f -> new Quaternionf().rotationY(f);

    /**
     * Represents the Y axis on the negative side.
     */
    Axis Y_NEGATIVE = f -> new Quaternionf().rotationY(-f);

    /**
     * Represents the Z axis on the positive side.
     */
    Axis Z_POSITIVE = f -> new Quaternionf().rotationZ(f);

    /**
     * Represents the Z axis on the negative side.
     */
    Axis Z_NEGATIVE = f -> new Quaternionf().rotationZ(-f);

    /**
     * Creates an axis of rotation from the given {@link Vector3f}.
     * @param axis The axis to rotate around.
     * @return The new axis.
     * @throws IllegalArgumentException if the axis is null
     */
    @NotNull
    static Axis of(@NotNull Vector3f axis) throws IllegalArgumentException {
        if (axis == null) throw new IllegalArgumentException("Axis cannot be null");
        return f -> new Quaternionf().rotationAxis(f, axis);
    }

    /**
     * Rotates the axis by the given radians.
     * @param radians The amount to rotate the axis by.
     * @return The new axis.
     */
    @NotNull
    Quaternionf rotation(float radians);

}
