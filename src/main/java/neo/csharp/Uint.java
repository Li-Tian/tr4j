package neo.csharp;

public class Uint implements Comparable<Uint> {

    public static final long MIN_VALUE = 0L;
    public static final long MAX_VALUE = 0xFFFFFFFFL;

    public static final Uint ZERO = new Uint(0);
    public static final Uint ONE = new Uint(1);

    private final int internal;

    public Uint(int intBits) {
        internal = intBits;
    }

    public Uint(String s) {
        internal = Integer.parseUnsignedInt(s);
    }

    public int intBits() {
        return internal;
    }

    public static Uint add(Uint x, Uint y) {
        return new Uint(x.internal + y.internal);
    }

    public Uint add(Uint y) {
        return Uint.add(this, y);
    }

    public static Uint subtract(Uint x, Uint y) {
        return new Uint(x.internal - y.internal);
    }

    public Uint subtract(Uint y) {
        return Uint.subtract(this, y);
    }

    public static Uint multiply(Uint x, Uint y) {
        return new Uint(x.internal * y.internal);
    }

    public Uint multiply(Uint y) {
        return Uint.multiply(this, y);
    }

    public static Uint divide(Uint x, Uint y) {
        return new Uint(Integer.divideUnsigned(x.internal, y.internal));
    }

    public Uint divide(Uint y) {
        return Uint.divide(this, y);
    }

    public static int compare(Uint x, Uint y) {
        return Integer.compareUnsigned(x.internal, y.internal);
    }

    @Override
    public int compareTo(Uint o) {
        return compare(this, o);
    }

    public double doubleValue() {
        return (double) longValue();
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Uint) {
            Uint y = (Uint) o;
            return internal == y.internal;
        }
        return false;
    }

    public float floatValue() {
        return (float) longValue();
    }

    @Override
    public int hashCode() {
        return internal;
    }

    public long longValue() {
        return internal & 0x00000000FFFFFFFFL;
    }

    public Ulong ulongValue() {
        return new Ulong(longValue());
    }

    public static Uint max(Uint x, Uint y) {
        int maxValue = (int) Long.max(x.longValue(), y.longValue());
        return new Uint(maxValue);
    }

    public static Uint min(Uint x, Uint y) {
        int minValue = (int) Long.min(x.longValue(), y.longValue());
        return new Uint(minValue);
    }

    public static Uint parseUint(String s) {
        return new Uint(s);
    }

    public static Uint remainder(Uint x, Uint y) {
        int r = Integer.remainderUnsigned(x.internal, y.internal);
        return new Uint(r);
    }

    public static Uint rotateLeft(Uint x, int distance) {
        int value = Integer.rotateLeft(x.internal, distance);
        return new Uint(value);
    }

    public Uint rotateLeft(int distance) {
        return Uint.rotateLeft(this, distance);
    }

    public static Uint rotateRight(Uint x, int distance) {
        int value = Integer.rotateRight(x.internal, distance);
        return new Uint(value);
    }

    public Uint rotateRight(int distance) {
        return Uint.rotateRight(this, distance);
    }

    public static Uint shiftLeft(Uint x, int distance) {
        int value = x.internal << distance;
        return new Uint(value);
    }

    public Uint shiftLeft(int distance) {
        return Uint.shiftLeft(this, distance);
    }

    public static Uint shiftRight(Uint x, int distance) {
        int value = x.internal >>> distance;
        return new Uint(value);
    }

    public Uint shiftRight(int distance) {
        return Uint.shiftRight(this, distance);
    }

    public static String toBinaryString(Uint x) {
        return Integer.toBinaryString(x.internal);
    }

    public static String toHexString(Uint x) {
        return Integer.toHexString(x.internal);
    }

    public static String toOctalString(Uint x) {
        return Integer.toOctalString(x.internal);
    }

    @Override
    public String toString() {
        return Integer.toUnsignedString(internal);
    }

    public static String toString(Uint x, int radix) {
        return Integer.toUnsignedString(x.internal, radix);
    }

    public static Uint valueOf(String s) {
        return parseUint(s);
    }

    public static Uint and(Uint x, Uint y) {
        return new Uint(x.internal & y.internal);
    }

    public Uint and(Uint y) {
        return Uint.and(this, y);
    }

    public static Uint or(Uint x, Uint y) {
        return new Uint(x.internal | y.internal);
    }

    public Uint or(Uint y) {
        return Uint.or(this, y);
    }

    public static Uint bitNot(Uint x) {
        return new Uint(~x.internal);
    }

    public Uint bitNot() {
        return Uint.bitNot(this);
    }

    public static Uint xor(Uint x, Uint y) {
        return new Uint(x.internal ^ y.internal);
    }

    public Uint xor(Uint y) {
        return Uint.xor(this, y);
    }
}
