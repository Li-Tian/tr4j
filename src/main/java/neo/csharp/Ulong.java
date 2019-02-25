package neo.csharp;

import java.math.BigInteger;

public class Ulong implements Comparable<Ulong> {

    /**
     * 0
     */
    public static final BigInteger MIN_VALUE = BigInteger.ZERO;
    /**
     * 2^64-1
     */
    public static final BigInteger MAX_VALUE = new BigInteger("18446744073709551615");

    private final long internal;

    public Ulong(long longBits) {
        internal = longBits;
    }

    public Ulong(String s) {
        internal = Long.parseUnsignedLong(s);
    }

    public long longBits() {
        return internal;
    }

    public static Ulong add(Ulong x, Ulong y) {
        return new Ulong(x.internal + y.internal);
    }

    public Ulong add(Ulong y) {
        return Ulong.add(this, y);
    }

    public static Ulong subtract(Ulong x, Ulong y) {
        return new Ulong(x.internal - y.internal);
    }

    public Ulong subtract(Ulong y) {
        return Ulong.subtract(this, y);
    }

    public static Ulong multiply(Ulong x, Ulong y) {
        return new Ulong(x.internal * y.internal);
    }

    public Ulong multiply(Ulong y) {
        return Ulong.multiply(this, y);
    }

    public static Ulong divide(Ulong x, Ulong y) {
        return new Ulong(Long.divideUnsigned(x.internal, y.internal));
    }

    public Ulong divide(Ulong y) {
        return Ulong.divide(this, y);
    }

    public static int compare(Ulong x, Ulong y) {
        return Long.compareUnsigned(x.internal, y.internal);
    }

    @Override
    public int compareTo(Ulong o) {
        return compare(this, o);
    }

    public double doubleValue() {
        return bigIntegerValue().doubleValue();
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Ulong) {
            Ulong y = (Ulong) o;
            return internal == y.internal;
        }
        return false;
    }

    public float floatValue() {
        return bigIntegerValue().floatValue();
    }

    @Override
    public int hashCode() {
        return Long.hashCode(internal);
    }

    /**
     * 强制转型.
     * @return 强制转型的值.
     */
    public int intValue() {
        return (int) internal;
    }

    /**
     * 强制转型.
     * @return 强制转型的值.
     */
    public Uint uintValue() {
        return new Uint((int) internal);
    }

    public BigInteger bigIntegerValue() {
        return new BigInteger(toString());
    }

    public static Ulong max(Ulong x, Ulong y) {
        return compare(x, y) < 0 ? y : x;
    }

    public static Ulong min(Ulong x, Ulong y) {
        return compare(x, y) < 0 ? x : y;
    }

    public static Ulong parseUlong(String s) {
        return new Ulong(s);
    }

    public static Ulong remainder(Ulong x, Ulong y) {
        long r = Long.remainderUnsigned(x.internal, y.internal);
        return new Ulong(r);
    }

    public static Ulong rotateLeft(Ulong x, int distance) {
        long value = Long.rotateLeft(x.internal, distance);
        return new Ulong(value);
    }

    public Ulong rotateLeft(int distance) {
        return Ulong.rotateLeft(this, distance);
    }

    public static Ulong rotateRight(Ulong x, int distance) {
        long value = Long.rotateRight(x.internal, distance);
        return new Ulong(value);
    }

    public Ulong rotateRight(int distance) {
        return Ulong.rotateRight(this, distance);
    }

    public static Ulong shiftLeft(Ulong x, int distance) {
        long value = x.internal << distance;
        return new Ulong(value);
    }

    public Ulong shiftLeft(int distance) {
        return Ulong.shiftLeft(this, distance);
    }

    public static Ulong shiftRight(Ulong x, int distance) {
        long value = x.internal >>> distance;
        return new Ulong(value);
    }

    public Ulong shiftRight(int distance) {
        return Ulong.shiftRight(this, distance);
    }

    public static String toBinaryString(Ulong x) {
        return Long.toBinaryString(x.internal);
    }

    public static String toHexString(Ulong x) {
        return Long.toHexString(x.internal);
    }

    public static String toOctalString(Ulong x) {
        return Long.toOctalString(x.internal);
    }

    @Override
    public String toString() {
        return Long.toUnsignedString(internal);
    }

    public static String toString(Ulong x, int radix) {
        return Long.toUnsignedString(x.internal, radix);
    }

    public static Ulong valueOf(String s) {
        return parseUlong(s);
    }


    public static Ulong and(Ulong x, Ulong y) {
        return new Ulong(x.internal & y.internal);
    }

    public Ulong and(Ulong y) {
        return Ulong.and(this, y);
    }

    public static Ulong or(Ulong x, Ulong y) {
        return new Ulong(x.internal | y.internal);
    }

    public Ulong or(Ulong y) {
        return Ulong.or(this, y);
    }

    public static Ulong bitNot(Ulong x) {
        return new Ulong(~x.internal);
    }

    public Ulong bitNot() {
        return Ulong.bitNot(this);
    }

    public static Ulong xor(Ulong x, Ulong y) {
        return new Ulong(x.internal ^ y.internal);
    }

    public Ulong xor(Ulong y) {
        return Ulong.xor(this, y);
    }
}
