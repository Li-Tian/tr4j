package neo.csharp;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;

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

    @Test
    public void testGet7BitEncodedBytes() {
        int value = 0;
        assertArrayEquals(new byte[]{0}, BitConverter.get7BitEncodedBytes(value));
        value = -1;
        assertArrayEquals(
                new byte[]{(byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0x0F,},
                BitConverter.get7BitEncodedBytes(value));
    }

    @Test
    public void testDecode7BitEncodedInt() {
        int[] values = {0, 1, -1, Integer.MAX_VALUE, Integer.MIN_VALUE, 255, 256, 65535, 65536};
        try {
            for (int value : values) {
                byte[] temp = BitConverter.get7BitEncodedBytes(value);
                ByteArrayInputStream inputStream = new ByteArrayInputStream(temp);
                assertEquals(value, BitConverter.decode7BitEncodedInt(inputStream));
            }
        } catch (IOException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testDecode7BitEncodedInt2() {
        byte[] data = new byte[]{
                (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF};
        try {
            ByteArrayInputStream inputStream = new ByteArrayInputStream(data);
            BitConverter.decode7BitEncodedInt(inputStream);
            fail();
        } catch (IOException e) {
        }
    }
}