package com.mrdai.util;

/**
 * Mutable version of {@link java.math.BigInteger BigInteger}.
 */
public final class Bits implements Cloneable {
    private static final int[] ZERO_MAG = new int[0];

    private int signum;
    private int[] magnitude;

    /**
     * Creates a zero {@code Bits}.
     */
    public Bits() {
        signum = 0;
        magnitude = ZERO_MAG;
    }

    /**
     * Creates a {@code Bits} with the given {@code int} value.
     *
     * @param value the given {@code int} value.
     */
    public Bits(int value) {
        if (value == 0) {
            signum = 0;
            magnitude = ZERO_MAG;
            return;
        }
        if (value < 0) {
            signum = -1;
            value = -value;
        } else
            signum = 1;
        magnitude = new int[1];
        magnitude[0] = value;
    }

    /**
     * Creates a {@code Bits} with the given {@code long} value.
     *
     * @param value the given {@code long} value.
     */
    public Bits(long value) {
        if (value == 0) {
            signum = 0;
            magnitude = ZERO_MAG;
            return;
        }
        if (value < 0) {
            signum = -1;
            value = -value;
        } else
            signum = 1;
        int highWord = (int) (value >>> 32);
        if (highWord == 0) {
            magnitude = new int[1];
            magnitude[0] = (int) value;
        } else {
            magnitude = new int[2];
            magnitude[0] = highWord;
            magnitude[1] = (int) value;
        }
    }

    private Bits(int signum, int[] magnitude) {
        this.signum = signum;
        this.magnitude = magnitude;
    }

    /**
     * Minus this {@code Bits} with the given {@code Bits} and returns the result; neither {@code Bits} will be changed.
     *
     * @param other the given {@code Bits}.
     * @return the result of minus.
     */
    public Bits minus(Bits other) {
        return clone().minusAssign(other);
    }

    /**
     * Changes the value of this {@code Bits} to the result of this {@code Bits} minus the given {@code Bits};
     * equivalent to Java operator {@code -=}.
     *
     * @param other the given {@code Bits}.
     */
    public Bits minusAssign(Bits other) {
        if (other.signum == 0) return this;
        if (signum == 0) {
            signum = -other.signum;
            magnitude = other.magnitude.clone();
            return this;
        }
        if (signum != other.signum) return plusAssign(other.negation());
        else {
            // TODO Implement minus operation with same signum
            return this;
        }
    }

    /**
     * Adds this {@code Bits} and the given {@code Bits} and returns the result; neither {@code Bits} will be changed.
     *
     * @param other the given {@code Bits}.
     * @return the result of adding.
     */
    public Bits plus(Bits other) {
        return clone().plusAssign(other);
    }

    /**
     * Changes the value of this {@code Bits} to the result of this {@code Bits} plus the given {@code Bits};
     * equivalent to Java operator {@code +=}.
     *
     * @param other the given {@code Bits}.
     */
    public Bits plusAssign(Bits other) {
        if (other.signum == 0) return this;
        if (signum == 0) {
            signum = other.signum;
            magnitude = other.magnitude.clone();
            return this;
        }
        if (signum != other.signum) return minusAssign(other.negation());
        else {
            // TODO Implement plus operation with same signum
            return this;
        }
    }

    /**
     * Returns a new instance of {@code Bits}, which is the negation of this {@code Bits}.
     *
     * @return the negation of this {@code Bits}.
     */
    public Bits negation() {
        return clone().negate();
    }

    /**
     * Flips the signum of this {@code Bits} and returns it.
     *
     * @return this {@code Bits} after its signum being flipped.
     */
    public Bits negate() {
        signum = -signum;
        return this;
    }

    /**
     * Returns the result of this {@code Bits} shifted left for given number of steps.
     *
     * @param steps the given number of steps to shift.
     * @return the result of this {@code Bits} shifted left.
     */
    public Bits shiftLeft(int steps) {
        return clone().shiftLeftAssign(steps);
    }

    /**
     * Shifts this {@code Bits} left for given number of steps and returns it.
     *
     * @param steps the given number of steps.
     * @return this {@code Bits} after being shifted left.
     */
    public Bits shiftLeftAssign(int steps) {
        if (steps == 0 || signum == 0) return this;
        if (steps % 32 == 0) {
            int[] newMag = new int[magnitude.length + steps / 32];
            System.arraycopy(magnitude, 0, newMag, 0, magnitude.length);
            magnitude = newMag;
            return this;
        }
        int[] newMag = new int[magnitude.length + steps / 32 + 1];
        System.arraycopy(magnitude, 0, newMag, 0, magnitude.length);
        magnitude = newMag;
        return shiftRightAssign(32 - steps % 32);
    }

    /**
     * Returns the result of this {@code Bits} shifted right for given number of steps.
     *
     * @param steps the given number of steps to shift.
     * @return the result of this {@code Bits} shifted right.
     */
    public Bits shiftRight(int steps) {
        return clone().shiftRightAssign(steps);
    }

    /**
     * Shifts this {@code Bits} right for given number of steps and returns it.
     *
     * @param steps the given number of steps.
     * @return this {@code Bits} after being shifted right.
     */
    public Bits shiftRightAssign(int steps) {
        if (steps == 0 || signum == 0) return this;
        if (steps >= 32)
            truncateEnding(steps / 32);
        if (signum == 0) return this;

        long temp = 0;
        for (int i = 0; i < magnitude.length; i++) {
            temp |= (((long) magnitude[i]) & 0xffffffffL) << (32 - steps);
            magnitude[i] = (int) (temp >>> 32);
            temp &= 0xffffffffL; // Cancel all the high-word bits
            temp <<= 32;
        }
        if (magnitude[0] == 0)
            truncateFirst();
        return this;
    }

    /**
     * Increments this {@code Bits} by {@code 1} and returns it.
     *
     * @return this {@code Bits} after being incremented.
     */
    public Bits increment() {
        if (signum == 0) {
            signum = 1;
            magnitude = new int[1];
            magnitude[0] = 1;
        } else if (signum == -1)
            decrementMagnitude();
        else
            incrementMagnitude();
        return this;
    }

    /**
     * Decrements this {@code Bits} by {@code 1} and returns it.
     *
     * @return this {@code Bits} after being decremented.
     */
    public Bits decrement() {
        if (signum == 0) {
            signum = -1;
            magnitude = new int[1];
            magnitude[0] = 1;
        } else if (signum == -1)
            incrementMagnitude();
        else
            decrementMagnitude();
        return this;
    }

    /**
     * Returns the bit count of this {@code Bits}.
     *
     * @return the bit count of this {@code Bits}.
     */
    public int getBitCount() {
        if (signum == 0) return 0;
        int result = 0;
        int first = magnitude[0];
        while (first != 0) {
            result++;
            first >>>= 1;
        }
        return result + 32 * (magnitude.length - 1);
    }

    /**
     * Returns the upper-case hexadecimal representation of this {@code Bits}.
     *
     * @return the upper-case hexadecimal representation of this {@code Bits}.
     */
    public String toHex() {
        if (signum == 0) return "0";
        StringBuilder builder = new StringBuilder();
        if (signum == -1) builder.append('-');
        builder.append(Integer.toHexString(magnitude[0]));
        for (int i = 1; i < magnitude.length; i++) {
            if (magnitude[i] == 0) {
                builder.append("00000000");
                continue;
            }
            String result = Integer.toHexString(magnitude[i]);
            for (int j = 0; j < 8 - result.length(); j++)
                builder.append('0');
            builder.append(result);
        }
        return builder.toString().toUpperCase();
    }

    private void incrementMagnitude() {
        int i = magnitude.length - 1;
        boolean carry = true;
        while (i >= 0 && carry) {
            if (magnitude[i] == 0xffffffff) {
                carry = true;
                magnitude[i] = 0;
            } else {
                carry = false;
                magnitude[i]++;
            }
            i--;
        }
        if (i == -1 && carry) { // Overflow
            int[] newMag = new int[magnitude.length + 1];
            newMag[0] = 1;
            System.arraycopy(magnitude, 0, newMag, 1, magnitude.length);
            magnitude = newMag;
        }
    }

    private void decrementMagnitude() {
        int i = magnitude.length - 1;
        boolean carry = true;
        while (i >= 0 && carry) {
            if (magnitude[i] == 0) {
                carry = true;
                magnitude[i] = 0xffffffff;
            } else {
                carry = false;
                magnitude[i]--;
            }
            i--;
        }
        if (magnitude[0] == 0)
            truncateFirst();
    }

    /**
     * Removes the left-most element from the {@code magnitude}.
     */
    private void truncateFirst() {
        if (magnitude.length <= 1) {
            signum = 0;
            magnitude = ZERO_MAG;
        } else {
            int[] newMag = new int[magnitude.length - 1];
            System.arraycopy(magnitude, 1, newMag, 0, magnitude.length - 1);
            magnitude = newMag;
            if (magnitude.length == 0) signum = 0;
        }
    }

    /**
     * Removes the given number of right-most elements from the {@code maginute}.
     *
     * @param size the given number of elements to be removed.
     */
    private void truncateEnding(int size) {
        if (magnitude.length <= size) {
            signum = 0;
            magnitude = ZERO_MAG;
        } else {
            int[] newMag = new int[magnitude.length - size];
            System.arraycopy(magnitude, 0, newMag, 0, newMag.length);
            magnitude = newMag;
        }
    }

    /**
     * Returns the upper-case hexadecimal representation of this {@code Bits} with minus sign {@code '-'} prepended if required.
     *
     * @return the upper-case hexadecimal representation of this {@code Bits}.
     * @see #toHex()
     */
    @Override
    public String toString() {
        return toHex();
    }

    @Override
    public Bits clone() {
        Bits clone;
        try {
            clone = (Bits) super.clone();
        } catch (CloneNotSupportedException ex) {
            clone = new Bits();
            clone.signum = signum;
        }
        clone.magnitude = magnitude.clone();
        return clone;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (obj instanceof Long) return equals(new Bits((Long) obj));
        if (obj instanceof Integer) return equals(new Bits((Integer) obj));
        if (!(obj instanceof Bits)) return false;
        Bits other = (Bits) obj;
        if (signum != other.signum || magnitude.length != other.magnitude.length)
            return false;
        for (int i = 0; i < magnitude.length; i++)
            if (magnitude[i] != other.magnitude[i])
                return false;
        return true;
    }
}
