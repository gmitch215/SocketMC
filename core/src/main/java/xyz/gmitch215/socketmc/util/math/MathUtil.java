package xyz.gmitch215.socketmc.util.math;

/**
 * A utility class for replicating math operations used in Minecraft.
 */
public final class MathUtil {

    private MathUtil() { throw new UnsupportedOperationException(); }

    /**
     * Method for linear interpolation of floats
     * @param delta A value usually between 0 and 1 that indicates the percentage of the lerp. (0 will give the start value and 1 will give the end value)
     * @param start Start value for the lerp
     * @param end End value for the lerp
     */
    public static float lerp(float delta, float start, float end) {
        return start + delta * (end - start);
    }

    /**
     * Method for linear interpolation of doubles
     * @param delta A value usually between 0 and 1 that indicates the percentage of the lerp. (0 will give the start value and 1 will give the end value)
     * @param start Start value for the lerp
     * @param end End value for the lerp
     */
    public static double lerp(double delta, double start, double end) {
        return start + delta * (end - start);
    }

    /**
     * Rounds the given value up to a multiple of factor.
     * @return The smallest integer multiple of factor that is greater than or equal to the value
     */
    public static int roundToward(int value, int factor) {
        return positiveCeilDiv(value, factor) * factor;
    }

    /**
     * Returns the smallest (closest to negative infinity) int value that is greater than or equal to the algebraic quotient.
     * @see Math#floorDiv(int, int)
     */
    public static int positiveCeilDiv(int x, int y) {
        return -Math.floorDiv(-x, y);
    }
}
