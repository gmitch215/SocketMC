package xyz.gmitch215.socketmc.util.math;

import java.util.Iterator;
import java.util.NoSuchElementException;

// Recreated from com/mojang/math/Divisor.java

/**
 * An Integer Iterator through a division operation.
 */
public final class Divisor implements Iterator<Integer> {

    private final int denominator;
    private final int quotient;
    private final int mod;
    private int returnedParts;
    private int remainder;

    /**
     * Creates a new divisor for the given numerator and denominator.
     * @param numerator The numerator
     * @param denominator The denominator
     */
    public Divisor(int numerator, int denominator) {
        this.denominator = denominator;
        if (denominator > 0) {
            this.quotient = numerator / denominator;
            this.mod = numerator % denominator;
        } else {
            this.quotient = 0;
            this.mod = 0;
        }
    }

    @Override
    public boolean hasNext() {
        return this.returnedParts < this.denominator;
    }

    @Override
    public Integer next() {
        if (!hasNext()) throw new NoSuchElementException();

        int i = this.quotient;
        remainder += mod;
        if (remainder >= denominator) {
            remainder -= denominator;
            i++;
        }

        returnedParts++;
        return i;
    }

    /**
     * Returns an iterable for the division of the numerator and denominator.
     * @param numerator The numerator
     * @param denominator The denominator
     * @return An iterable for the division of the numerator and denominator
     */
    public static Iterable<Integer> asIterable(int numerator, int denominator) {
        return () -> new Divisor(numerator, denominator);
    }

}
