package neo.csharp;

import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import static org.junit.Assert.*;

public class BitConverterTest {

    @Test
    public void toBoolean() {
        boolean[] values = {true, false};
        for (boolean value : values) {
            assertEquals(value, BitConverter.toBoolean(BitConverter.getBytes(value)));
        }
    }

    @Test
    public void toChar() {
        char[] values = {'a', '1', '中', '\n'};
        for (char value : values) {
            assertEquals(value, BitConverter.toChar(BitConverter.getBytes(value)));
        }
    }

    @Test
    public void toShort() {
        short[] values = {0, 1, -1, Short.MAX_VALUE, Short.MIN_VALUE};
        for (short value : values) {
            assertEquals(value, BitConverter.toShort(BitConverter.getBytes(value)));
        }
    }

    @Test
    public void toUshort() {
        Ushort[] values = {
                new Ushort(0),
                new Ushort(1),
                new Ushort(2),
                new Ushort(Ushort.MAX_VALUE),
                new Ushort(Ushort.MIN_VALUE)
        };
        for (Ushort value : values) {
            assertEquals(value, BitConverter.toUshort(BitConverter.getBytes(value)));
        }
    }

    @Test
    public void toInt() {
        int[] data = {0, 1, -1, Integer.MAX_VALUE, Integer.MIN_VALUE};
        for (int value : data) {
            assertEquals(value, BitConverter.toInt(BitConverter.getBytes(value)));
        }
    }

    @Test
    public void toUint() {
        Uint[] data = {
                new Uint(0),
                new Uint(1),
                new Uint(-1),
                new Uint((int) Uint.MAX_VALUE),
                new Uint((int) Uint.MIN_VALUE)
        };
        for (Uint value : data) {
            assertEquals(value, BitConverter.toUint(BitConverter.getBytes(value)));
        }
    }

    @Test
    public void toLong() {
        long[] data = {0, 1, -1, Long.MAX_VALUE, Long.MIN_VALUE};
        for (long value : data) {
            assertEquals(value, BitConverter.toLong(BitConverter.getBytes(value)));
        }
    }

    @Test
    public void toUlong() {
        Ulong[] data = {
                new Ulong(0),
                new Ulong(1),
                new Ulong(-1),
                new Ulong(Ulong.MAX_VALUE.longValue()),
                new Ulong(Ulong.MIN_VALUE.longValue())
        };
        for (Ulong value : data) {
            assertEquals(value, BitConverter.toUlong(BitConverter.getBytes(value)));
        }
    }

    @Test
    public void toFloat() {
        float[] data = {0.0F, 1.0F, 1.1F, -1.0F, Float.MAX_VALUE, Float.MIN_VALUE};
        for (float value : data) {
            assertEquals(value, BitConverter.toFloat(BitConverter.getBytes(value)), 0.0F);
        }
    }

    @Test
    public void toDouble() {
        double[] data = {0, 1, 1.1, -1, Double.MAX_VALUE, Double.MIN_VALUE};
        for (double value : data) {
            assertEquals(value, BitConverter.toDouble(BitConverter.getBytes(value)), 0.0);
        }
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

    @Test
    public void testMerge() {
        byte[] a = new byte[]{0x01, 0x02};
        byte[] b = new byte[]{0x03, 0x04};
        byte[] c = BitConverter.merge(a, b);
        Assert.assertEquals(4, c.length);
        Assert.assertEquals(0x01, c[0]);
        Assert.assertEquals(0x02, c[1]);
        Assert.assertEquals(0x03, c[2]);
        Assert.assertEquals(0x04, c[3]);

        byte d = 0x05;
        c = BitConverter.merge(d, a);
        Assert.assertEquals(3, c.length);
        Assert.assertEquals(0x05, c[0]);
        Assert.assertEquals(0x01, c[1]);
        Assert.assertEquals(0x02, c[2]);
    }

    @Test
    public void testVarIntEncoding() {
        long[] values = {0, 1, 2, 128, 255, 65535, Integer.MAX_VALUE, Long.MAX_VALUE};
        int[] length = {1, 1, 1, 1, 3, 3, 5, 9};
        try {
            for (int i = 0; i < values.length; i++) {
                byte[] temp = BitConverter.getVarIntEncodedBytes(values[i]);
                assertEquals(length[i], temp.length);
                ByteArrayInputStream inputStream = new ByteArrayInputStream(temp);
                assertEquals(values[i], BitConverter.decodeVarInt(inputStream));
            }
        } catch (IOException e) {
            fail(e.getMessage());
        }
    }


    @Test
    public void subBytes() {
        byte[] sub = BitConverter.subBytes(new byte[]{0x01, 0x02, 0x03, 0x04}, 1, 3);
        Assert.assertArrayEquals(new byte[]{0x02, 0x03}, sub);
    }

    @Test
    public void toHexString() {
        String hexString = BitConverter.toHexString(new byte[]{0x01, 0x02, 0x03, 0x04});
        Assert.assertEquals("01020304", hexString);
    }

    @Test
    public void reverse() {
        byte[] old = new byte[]{0x01, 0x02, 0x03, 0x04};
        byte[] reverse = BitConverter.reverse(old);
        Assert.assertArrayEquals(new byte[]{0x04, 0x03, 0x02, 0x01}, reverse);
    }

    @Test
    public void hexToBytes() {
        byte[] bytes = BitConverter.hexToBytes("0x01020304");
        Assert.assertArrayEquals(new byte[]{0x01, 0x02, 0x03, 0x04}, bytes);
    }

    @Test
    public void addBytes() {
        byte[] bytes1 = new byte[]{0x01, 0x02};
        byte[] bytes2 = new byte[]{0x03, 0x04};

        byte[] bytes3 = BitConverter.addBytes(bytes1, bytes2);
        Assert.assertArrayEquals(new byte[]{0x01, 0x02, 0x03, 0x04}, bytes3);
    }
}