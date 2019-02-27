package neo.csharp;

public class Uint extends Number implements Comparable<Uint> {

    public static final long MIN_VALUE = 0L;
    public static final long MAX_VALUE = 0xFFFFFFFFL;
    public static final int BYTES = Integer.BYTES;
    public static final int SIZE = Integer.SIZE;

    public static final Uint ZERO = new Uint(0);
    public static final Uint ONE = new Uint(1);

    private final int internal;

    public Uint(int intBits) {
        internal = intBits;
    }

    public Uint(String s) {
        internal = Integer.parseUnsignedInt(s);
    }

    public static Uint add(Uint x, Uint y) {
        return new Uint(x.internal + y.internal);
    }

    public static Uint subtract(Uint x, Uint y) {
        return new Uint(x.internal - y.internal);
    }

    public static Uint multiply(Uint x, Uint y) {
        return new Uint(x.internal * y.internal);
    }

    public static Uint divide(Uint x, Uint y) {
        return new Uint(Integer.divideUnsigned(x.internal, y.internal));
    }

    public static int compare(Uint x, Uint y) {
        return Integer.compareUnsigned(x.internal, y.internal);
    }

    public static Uint max(Uint x, Uint y) {
        return compare(x, y) < 0 ? y : x;
    }

    public static Uint min(Uint x, Uint y) {
        return compare(x, y) < 0 ? x : y;
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

    public static Uint rotateRight(Uint x, int distance) {
        int value = Integer.rotateRight(x.internal, distance);
        return new Uint(value);
    }

    public static Uint shiftLeft(Uint x, int distance) {
        int value = x.internal << distance;
        return new Uint(value);
    }

    public static Uint shiftRight(Uint x, int distance) {
        int value = x.internal >>> distance;
        return new Uint(value);
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

    public static String toString(Uint x, int radix) {
        return Integer.toUnsignedString(x.internal, radix);
    }

    public static Uint valueOf(String s) {
        return parseUint(s);
    }

    public static Uint and(Uint x, Uint y) {
        return new Uint(x.internal & y.internal);
    }

    public static Uint or(Uint x, Uint y) {
        return new Uint(x.internal | y.internal);
    }

    public static Uint bitNot(Uint x) {
        return new Uint(~x.internal);
    }

    public static Uint xor(Uint x, Uint y) {
        return new Uint(x.internal ^ y.internal);
    }

    public int intBits() {
        return internal;
    }

    public Uint add(Uint y) {
        return Uint.add(this, y);
    }

    public Uint subtract(Uint y) {
        return Uint.subtract(this, y);
    }

    public Uint multiply(Uint y) {
        return Uint.multiply(this, y);
    }

    public Uint divide(Uint y) {
        return Uint.divide(this, y);
    }

    @Override
    public int compareTo(Uint o) {
        return compare(this, o);
    }

    @Override
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

    @Override
    public float floatValue() {
        return (float) longValue();
    }

    @Override
    public int hashCode() {
        return internal;
    }

    /**
     * 强行转换。
     *
     * @return 强行转换值。
     */
    @Override
    public int intValue() {
        return internal;
    }

    @Override
    public long longValue() {
        return internal & 0x00000000FFFFFFFFL;
    }

    public Ulong ulongValue() {
        return new Ulong(longValue());
    }

    public Uint rotateLeft(int distance) {
        return Uint.rotateLeft(this, distance);
    }

    public Uint rotateRight(int distance) {
        return Uint.rotateRight(this, distance);
    }

    public Uint shiftLeft(int distance) {
        return Uint.shiftLeft(this, distance);
    }

    public Uint shiftRight(int distance) {
        return Uint.shiftRight(this, distance);
    }

    @Override
    public String toString() {
        return Integer.toUnsignedString(internal);
    }

    public Uint and(Uint y) {
        return Uint.and(this, y);
    }

    public Uint or(Uint y) {
        return Uint.or(this, y);
    }

    public Uint bitNot() {
        return Uint.bitNot(this);
    }

    public Uint xor(Uint y) {
        return Uint.xor(this, y);
    }
}
