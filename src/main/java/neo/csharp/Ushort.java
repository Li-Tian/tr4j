package neo.csharp;

public class Ushort extends Number implements Comparable<Ushort> {

    public static final int MIN_VALUE = 0;
    public static final int MAX_VALUE = 0xFFFF;
    public static final Ushort MIN_VALUE_2 = new Ushort(MIN_VALUE);
    public static final Ushort MAX_VALUE_2 = new Ushort(MAX_VALUE);
    public static final int BYTES = Short.BYTES;
    public static final int SIZE = Short.SIZE;

    public static final Ushort ZERO = new Ushort(0);
    public static final Ushort ONE = new Ushort(1);

    private final char internal;

    public Ushort(int intBits) {
        if (intBits > 65535 || intBits < 0) {
            throw new RuntimeException("value is out of range of Ushort");
        }
        internal = (char) intBits;
    }

    public Ushort(String s) {
        this(Integer.parseUnsignedInt(s));
    }

    public Ushort(char charBits) {
        internal = charBits;
    }

    public Ushort(short shortBits) {
        internal = (char) shortBits;
    }

    public static Ushort add(Ushort x, Ushort y) {
        return new Ushort(x.internal + y.internal);
    }

    public static Ushort subtract(Ushort x, Ushort y) {
        return new Ushort(x.internal - y.internal);
    }

    public static Ushort multiply(Ushort x, Ushort y) {
        return new Ushort(x.internal * y.internal);
    }

    public static Ushort divide(Ushort x, Ushort y) {
        int intBits = Integer.divideUnsigned(x.internal, y.internal);
        return new Ushort(intBits);
    }

    public static Ushort remainder(Ushort x, Ushort y) {
        int r = Integer.remainderUnsigned(x.internal, y.internal);
        return new Ushort(r);
    }

    public static int compare(Ushort x, Ushort y) {
        return Character.compare(x.internal, y.internal);
    }

    public static Ushort parseUshort(String s) {
        return new Ushort(s);
    }

    public static Ushort parseUshort(String s, int radix) {
        return new Ushort(Integer.parseUnsignedInt(s, radix));
    }

    public static Ushort max(Ushort x, Ushort y) {
        return compare(x, y) < 0 ? y : x;
    }

    public static Ushort min(Ushort x, Ushort y) {
        return compare(x, y) < 0 ? x : y;
    }

    public static Ushort valueOf(String s) {
        return parseUshort(s);
    }

    public char charBit() {
        return internal;
    }

    public short shortBits() {
        return (short) internal;
    }

    public Ushort add(Ushort y) {
        return Ushort.add(this, y);
    }

    public Ushort subtract(Ushort y) {
        return Ushort.subtract(this, y);
    }

    public Ushort multiply(Ushort y) {
        return Ushort.multiply(this, y);
    }

    public Ushort divide(Ushort y) {
        return Ushort.divide(this, y);
    }

    @Override
    public int compareTo(Ushort o) {
        return compare(this, o);
    }

    @Override
    public int intValue() {
        return internal;
    }

    @Override
    public long longValue() {
        return internal;
    }

    @Override
    public float floatValue() {
        return internal;
    }

    @Override
    public double doubleValue() {
        return internal;
    }

    public Uint uintValue() {
        return new Uint(internal);
    }

    public Ulong ulongValue() {
        return new Ulong(internal);
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Ushort) {
            Ushort y = (Ushort) o;
            return internal == y.internal;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return internal;
    }

    @Override
    public String toString() {
        return Integer.toUnsignedString(internal);
    }

}
