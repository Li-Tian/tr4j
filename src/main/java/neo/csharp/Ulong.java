package neo.csharp;

import java.math.BigInteger;

public class Ulong extends Number implements Comparable<Ulong> {

    /**
     * 0
     */
    public static final BigInteger MIN_VALUE = BigInteger.ZERO;
    /**
     * 2^64-1
     */
    public static final BigInteger MAX_VALUE = new BigInteger("18446744073709551615");
    public static final int BYTES = Long.BYTES;
    public static final int SIZE = Long.SIZE;

    public static final Ulong ZERO = new Ulong(0L);
    public static final Ulong ONE = new Ulong(1L);

    private final long internal;

    public Ulong(long longBits) {
        internal = longBits;
    }

    public Ulong(String s) {
        internal = Long.parseUnsignedLong(s);
    }

    public static Ulong add(Ulong x, Ulong y) {
        return new Ulong(x.internal + y.internal);
    }

    public static Ulong subtract(Ulong x, Ulong y) {
        return new Ulong(x.internal - y.internal);
    }

    public static Ulong multiply(Ulong x, Ulong y) {
        return new Ulong(x.internal * y.internal);
    }

    public static Ulong divide(Ulong x, Ulong y) {
        return new Ulong(Long.divideUnsigned(x.internal, y.internal));
    }

    public static int compare(Ulong x, Ulong y) {
        return Long.compareUnsigned(x.internal, y.internal);
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

    public static Ulong parseUlong(String s, int radix) {
        return new Ulong(Long.parseUnsignedLong(s, radix));
    }

    public static Ulong remainder(Ulong x, Ulong y) {
        long r = Long.remainderUnsigned(x.internal, y.internal);
        return new Ulong(r);
    }

    public static Ulong rotateLeft(Ulong x, int distance) {
        long value = Long.rotateLeft(x.internal, distance);
        return new Ulong(value);
    }

    public static Ulong rotateRight(Ulong x, int distance) {
        long value = Long.rotateRight(x.internal, distance);
        return new Ulong(value);
    }

    public static Ulong shiftLeft(Ulong x, int distance) {
        long value = x.internal << distance;
        return new Ulong(value);
    }

    public static Ulong shiftRight(Ulong x, int distance) {
        long value = x.internal >>> distance;
        return new Ulong(value);
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

    public static String toString(Ulong x, int radix) {
        return Long.toUnsignedString(x.internal, radix);
    }

    public static Ulong valueOf(String s) {
        return parseUlong(s);
    }

    public static Ulong and(Ulong x, Ulong y) {
        return new Ulong(x.internal & y.internal);
    }

    public static Ulong or(Ulong x, Ulong y) {
        return new Ulong(x.internal | y.internal);
    }

    public static Ulong bitNot(Ulong x) {
        return new Ulong(~x.internal);
    }

    public static Ulong xor(Ulong x, Ulong y) {
        return new Ulong(x.internal ^ y.internal);
    }

    public long longBits() {
        return internal;
    }

    public Ulong add(Ulong y) {
        return Ulong.add(this, y);
    }

    public Ulong subtract(Ulong y) {
        return Ulong.subtract(this, y);
    }

    public Ulong multiply(Ulong y) {
        return Ulong.multiply(this, y);
    }

    public Ulong divide(Ulong y) {
        return Ulong.divide(this, y);
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
     *
     * @return 强制转型的值.
     */
    public int intValue() {
        return (int) internal;
    }

    @Override
    public long longValue() {
        return 0;
    }

    /**
     * 强制转型.
     *
     * @return 强制转型的值.
     */
    public Uint uintValue() {
        return new Uint((int) internal);
    }

    public BigInteger bigIntegerValue() {
        return new BigInteger(toString());
    }

    public Ulong rotateLeft(int distance) {
        return Ulong.rotateLeft(this, distance);
    }

    public Ulong rotateRight(int distance) {
        return Ulong.rotateRight(this, distance);
    }

    public Ulong shiftLeft(int distance) {
        return Ulong.shiftLeft(this, distance);
    }

    public Ulong shiftRight(int distance) {
        return Ulong.shiftRight(this, distance);
    }

    @Override
    public String toString() {
        return Long.toUnsignedString(internal);
    }

    public Ulong and(Ulong y) {
        return Ulong.and(this, y);
    }

    public Ulong or(Ulong y) {
        return Ulong.or(this, y);
    }

    public Ulong bitNot() {
        return Ulong.bitNot(this);
    }

    public Ulong xor(Ulong y) {
        return Ulong.xor(this, y);
    }
}
