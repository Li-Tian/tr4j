package neo.csharp;

import org.junit.Test;

import static org.junit.Assert.*;

public class BitConverterTest {

    @Test
    public void toBoolean() {
        boolean value = true;
        assertEquals(value, BitConverter.toBoolean(BitConverter.getBytes(value)));
    }

    @Test
    public void toChar() {
        char value = 'a';
        assertEquals(value, BitConverter.toChar(BitConverter.getBytes(value)));
    }

    @Test
    public void toShort() {
        short value = -2;
        assertEquals(value, BitConverter.toShort(BitConverter.getBytes(value)));
    }

    @Test
    public void toUshort() {
        Ushort value = new Ushort(3);
        assertEquals(value, BitConverter.toUshort(BitConverter.getBytes(value)));
    }

    @Test
    public void toInt() {
        int value = -3;
        assertEquals(value, BitConverter.toInt(BitConverter.getBytes(value)));
    }

    @Test
    public void toUint() {
        Uint value = new Uint(4);
        assertEquals(value, BitConverter.toUint(BitConverter.getBytes(value)));
    }

    @Test
    public void toLong() {
        long value = -4;
        assertEquals(value, BitConverter.toLong(BitConverter.getBytes(value)));
    }

    @Test
    public void toUlong() {
        Ulong value = new Ulong(5);
        assertEquals(value, BitConverter.toUlong(BitConverter.getBytes(value)));
    }

    @Test
    public void toFloat() {
        float value = -4.1F;
        assertEquals(value, BitConverter.toFloat(BitConverter.getBytes(value)), 0.0F);
    }

    @Test
    public void toDouble() {
        double value = -5.2;
        assertEquals(value, BitConverter.toDouble(BitConverter.getBytes(value)), 0.0);
    }
}