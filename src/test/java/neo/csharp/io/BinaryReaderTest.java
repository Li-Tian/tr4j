package neo.csharp.io;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import neo.csharp.Uint;
import neo.csharp.Ulong;
import neo.csharp.Ushort;

import static org.junit.Assert.*;

public class BinaryReaderTest {
    @Test
    public void readBoolean() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        BinaryWriter writer = new BinaryWriter(out);
        writer.writeBoolean(true);
        writer.writeBoolean(false);
        ByteArrayInputStream input = new ByteArrayInputStream(out.toByteArray());
        BinaryReader reader = new BinaryReader(input);
        assertEquals(true, reader.readBoolean());
        assertEquals(false, reader.readBoolean());
    }

    @Test
    public void readByte() {
        byte[] data = {0, 1, -1, 127, -128};
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        BinaryWriter writer = new BinaryWriter(out);
        for (byte b : data) {
            writer.writeByte(b);
        }
        ByteArrayInputStream input = new ByteArrayInputStream(out.toByteArray());
        BinaryReader reader = new BinaryReader(input);
        for (byte b : data) {
            assertEquals(b, (byte) reader.readByte());
        }
    }

    @Test
    public void readChar() {
        char[] data = {0, 1, 127, 128, 255};
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        BinaryWriter writer = new BinaryWriter(out);
        for (char b : data) {
            writer.writeChar(b);
        }
        ByteArrayInputStream input = new ByteArrayInputStream(out.toByteArray());
        BinaryReader reader = new BinaryReader(input);
        for (char b : data) {
            assertEquals(b, reader.readChar());
        }
    }

    @Test
    public void readDouble() {
        double[] data = {0, 1, 1.1, -1, Double.MAX_VALUE, Double.MIN_VALUE};
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        BinaryWriter writer = new BinaryWriter(out);
        for (double b : data) {
            writer.writeDouble(b);
        }
        ByteArrayInputStream input = new ByteArrayInputStream(out.toByteArray());
        BinaryReader reader = new BinaryReader(input);
        for (double b : data) {
            assertEquals(b, reader.readDouble(), 0.0);
        }
    }

    @Test
    public void readFloat() {
        float[] data = {0.0F, 1.0F, 1.1F, -1.0F, Float.MAX_VALUE, Float.MIN_VALUE};
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        BinaryWriter writer = new BinaryWriter(out);
        for (float b : data) {
            writer.writeFloat(b);
        }
        ByteArrayInputStream input = new ByteArrayInputStream(out.toByteArray());
        BinaryReader reader = new BinaryReader(input);
        for (float b : data) {
            assertEquals(b, reader.readFloat(), 0.0F);
        }
    }

    @Test
    public void readInt() {
        int[] data = {0, 1, -1, Integer.MAX_VALUE, Integer.MIN_VALUE};
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        BinaryWriter writer = new BinaryWriter(out);
        for (int b : data) {
            writer.writeInt(b);
        }
        ByteArrayInputStream input = new ByteArrayInputStream(out.toByteArray());
        BinaryReader reader = new BinaryReader(input);
        for (int b : data) {
            assertEquals(b, reader.readInt());
        }
    }

    @Test
    public void readLong() {
        long[] data = {0, 1, -1, Long.MAX_VALUE, Long.MIN_VALUE};
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        BinaryWriter writer = new BinaryWriter(out);
        for (long b : data) {
            writer.writeLong(b);
        }
        ByteArrayInputStream input = new ByteArrayInputStream(out.toByteArray());
        BinaryReader reader = new BinaryReader(input);
        for (long b : data) {
            assertEquals(b, reader.readLong());
        }
    }

    @Test
    public void readShort() {
        short[] data = {0, 1, -1, Short.MAX_VALUE, Short.MIN_VALUE};
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        BinaryWriter writer = new BinaryWriter(out);
        for (short b : data) {
            writer.writeInt(b);
        }
        ByteArrayInputStream input = new ByteArrayInputStream(out.toByteArray());
        BinaryReader reader = new BinaryReader(input);
        for (short b : data) {
            assertEquals(b, reader.readInt());
        }
    }

    @Test
    public void readUshort() {
        Ushort[] data = {
                new Ushort(0),
                new Ushort(1),
                new Ushort(2),
                new Ushort(Ushort.MAX_VALUE),
                new Ushort(Ushort.MIN_VALUE)
        };
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        BinaryWriter writer = new BinaryWriter(out);
        for (Ushort b : data) {
            writer.writeUshort(b);
        }
        ByteArrayInputStream input = new ByteArrayInputStream(out.toByteArray());
        BinaryReader reader = new BinaryReader(input);
        for (Ushort b : data) {
            assertEquals(b, reader.readUshort());
        }
    }

    @Test
    public void readUint() {
        Uint[] data = {
                new Uint(0),
                new Uint(1),
                new Uint(-1),
                new Uint((int) Uint.MAX_VALUE),
                new Uint((int) Uint.MIN_VALUE)
        };
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        BinaryWriter writer = new BinaryWriter(out);
        for (Uint b : data) {
            writer.writeUint(b);
        }
        ByteArrayInputStream input = new ByteArrayInputStream(out.toByteArray());
        BinaryReader reader = new BinaryReader(input);
        for (Uint b : data) {
            assertEquals(b, reader.readUint());
        }
    }

    @Test
    public void readUlong() {
        Ulong[] data = {
                new Ulong(0),
                new Ulong(1),
                new Ulong(-1),
                new Ulong(Ulong.MAX_VALUE.longValue()),
                new Ulong(Ulong.MIN_VALUE.longValue())
        };
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        BinaryWriter writer = new BinaryWriter(out);
        for (Ulong b : data) {
            writer.writeUlong(b);
        }
        ByteArrayInputStream input = new ByteArrayInputStream(out.toByteArray());
        BinaryReader reader = new BinaryReader(input);
        for (Ulong b : data) {
            assertEquals(b, reader.readUlong());
        }
    }

    @Test
    public void readUTF() {
        byte[] buf = new byte[1024];
        String[] data = {
                "",
                "A",
                "中文",
                " ",
                " A中文 ",
                new String(buf)
        };
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        BinaryWriter writer = new BinaryWriter(out);
        for (String b : data) {
            writer.writeUTF(b);
        }
        ByteArrayInputStream input = new ByteArrayInputStream(out.toByteArray());
        BinaryReader reader = new BinaryReader(input);
        for (String b : data) {
            assertEquals(b, reader.readUTF());
        }
    }

    @Test
    public void readSerializable() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        BinaryWriter writer = new BinaryWriter(out);
        DummySerializableTestObj dummy = new DummySerializableTestObj();
        dummy.init();
        writer.writeSerializable(dummy);
        writer.flush();
        ByteArrayInputStream input = new ByteArrayInputStream(out.toByteArray());
        BinaryReader reader = new BinaryReader(input);
        assertEquals(dummy, reader.readSerializable(DummySerializableTestObj::new));
        writer.close();
    }

    @Test
    public void testReadSerializableArray() {
        DummySerializableTestObj[] array = new DummySerializableTestObj[3];
        for (int i = 0; i < array.length; i++) {
            array[i] = new DummySerializableTestObj();
            array[i].init();
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        BinaryWriter writer = new BinaryWriter(out);
        writer.writeArray(array);
        writer.flush();
        ByteArrayInputStream input = new ByteArrayInputStream(out.toByteArray());
        BinaryReader reader = new BinaryReader(input);
        assertArrayEquals(array, reader.readArray(DummySerializableTestObj[]::new, DummySerializableTestObj::new));
        writer.close();
    }

    @Test
    public void testReadBytesWithGrouping() {
        String[] samples = {
                "",
                "1",
                "12",
                "0123456789ABCDE",
                "0123456789ABCDEF",
                "0123456789ABCDEFG",
                "0123456789ABCDEF0123456789ABCDE",
                "0123456789ABCDEF0123456789ABCDEF",
                "0123456789ABCDEF0123456789ABCDEFG",
        };
        int length[] = {
                0, 1, 2, 15, 16, 17, 31, 32, 33
        };
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        BinaryWriter writer = new BinaryWriter(out);
        for (int i = 0; i < samples.length; i++) {
            byte[] data = samples[i].getBytes();
            assertEquals(length[i], data.length);
            writer.writeBytesWithGrouping(data);
            writer.flush();
        }
        ByteArrayInputStream input = new ByteArrayInputStream(out.toByteArray());
        BinaryReader reader = new BinaryReader(input);
        for (int i = 0; i < samples.length; i++) {
            byte[] data = samples[i].getBytes();
            assertArrayEquals(data, reader.readBytesWithGrouping());
        }
    }

    @Test
    public void testReadFixedString() {
        String[] samples = {
                "",
                "1",
                "12",
                "0123456789ABCDE",
                "0123456789ABCDEF",
                "0123456789ABCDEFG",
                "0123456789ABCDEF0123456789ABCDE",
                "0123456789ABCDEF0123456789ABCDEF",
                "0123456789ABCDEF0123456789ABCDEFG",
        };
        int length[] = {
                33, 34, 100
        };
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        BinaryWriter writer = new BinaryWriter(out);
        for (String sample : samples) {
            for (int len : length) {
                writer.writeFixedString(sample, len);
                writer.flush();
            }
        }
        ByteArrayInputStream input = new ByteArrayInputStream(out.toByteArray());
        BinaryReader reader = new BinaryReader(input);
        for (String sample : samples) {
            for (int len : length) {
                assertEquals(sample, reader.readFixedString(len));
            }
        }
    }

    @Test
    public void testReadVarBytes() {
        String[] samples = {
                "",
                "1",
                "12",
                "0123456789ABCDE",
                "0123456789ABCDEF",
                "0123456789ABCDEFG",
                "0123456789ABCDEF0123456789ABCDE",
                "0123456789ABCDEF0123456789ABCDEF",
                "0123456789ABCDEF0123456789ABCDEFG",
                "中文"
        };
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        BinaryWriter writer = new BinaryWriter(out);
        for (String sample : samples) {
            writer.writeVarBytes(sample.getBytes());
        }
        ByteArrayInputStream input = new ByteArrayInputStream(out.toByteArray());
        BinaryReader reader = new BinaryReader(input);
        for (String sample : samples) {
            assertEquals(sample, new String(reader.readVarBytes()));
        }
    }

    @Test
    public void testReadVarInt() {
        long[] samples = {
                0, 1, 2, Long.MAX_VALUE, //Long.MIN_VALUE, -1, -2,
        };
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        BinaryWriter writer = new BinaryWriter(out);
        for (long sample : samples) {
            writer.writeVarInt(sample);
        }
        ByteArrayInputStream input = new ByteArrayInputStream(out.toByteArray());
        BinaryReader reader = new BinaryReader(input);
        for (long sample : samples) {
            assertEquals(sample, reader.readVarInt().longValue());
        }
    }

    @Test
    public void testReadVarString() {
        String[] samples = {
                "",
                "1",
                "12",
                "0123456789ABCDE",
                "0123456789ABCDEF",
                "0123456789ABCDEFG",
                "0123456789ABCDEF0123456789ABCDE",
                "0123456789ABCDEF0123456789ABCDEF",
                "0123456789ABCDEF0123456789ABCDEFG",
                "中文"
        };
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        BinaryWriter writer = new BinaryWriter(out);
        for (String sample : samples) {
            writer.writeVarString(sample);
        }
        ByteArrayInputStream input = new ByteArrayInputStream(out.toByteArray());
        BinaryReader reader = new BinaryReader(input);
        for (String sample : samples) {
            assertEquals(sample, reader.readVarString());
        }
        reader.close();
    }

    @Test
    public void testReadRawBytes() {
        String[] samples = {
                "",
                "1",
                "12",
                "0123456789ABCDE",
                "0123456789ABCDEF",
                "0123456789ABCDEFG",
                "0123456789ABCDEF0123456789ABCDE",
                "0123456789ABCDEF0123456789ABCDEF",
                "0123456789ABCDEF0123456789ABCDEFG",
        };
        int length[] = {
                0, 1, 2, 15, 16, 17, 31, 32, 33
        };
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        BinaryWriter writer = new BinaryWriter(out);
        for (int i = 0; i < samples.length; i++) {
            byte[] data = samples[i].getBytes();
            assertEquals(length[i], data.length);
            writer.write(data);
            writer.flush();
        }
        ByteArrayInputStream input = new ByteArrayInputStream(out.toByteArray());
        BinaryReader reader = new BinaryReader(input);
        for (int i = 0; i < samples.length; i++) {
            byte[] data = samples[i].getBytes();
            assertArrayEquals(data, reader.readFully(length[i]));
        }
    }

    @Test public void testWriteRaw() {
        byte[] data = {0, 1, 2, 3, 4};
        byte[] expected = {1, 2, 3};
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        BinaryWriter writer = new BinaryWriter(out);
        writer.write(data, 1, 3);
        writer.flush();
        assertArrayEquals(expected, out.toByteArray());
    }

    @Test public void testReadFully() {
        byte[] data = new byte[0];
        ByteArrayInputStream inputStream = new ByteArrayInputStream(data);
        BinaryReader reader = new BinaryReader(inputStream);
        try {
            byte[] buf = new byte[0];
            reader.readFully(buf, 0, 0);
            reader.readFully(buf);
            reader.readFully(0);
        } catch (Exception e) {
            fail();
        }
    }

    static class DummySerializableTestObj implements ISerializable {

        boolean aBoolean;
        byte aByte;
        short aShort;
        char aChar;
        int anInt;
        long aLong;
        float aFloat;
        double aDouble;
        Ushort aUshort;
        Uint aUint;
        Ulong aUlong;

        public void init() {
            aBoolean = false;
            aShort = 1;
            aChar = 2;
            anInt = 3;
            aLong = 4;
            aFloat = 5;
            aDouble = 6;
            aUshort = new Ushort(7);
            aUint = new Uint(8);
            aUlong = new Ulong(9);
        }

        @Override
        public int size() {
            int s = 0;
            s += 1;//Boolean
            s += Byte.BYTES;
            s += Short.BYTES;
            s += Character.BYTES;
            s += Integer.BYTES;
            s += Long.BYTES;
            s += Float.BYTES;
            s += Double.BYTES;
            s += Ushort.BYTES;
            s += Uint.BYTES;
            s += Ulong.BYTES;
            return s;
        }

        @Override
        public void serialize(BinaryWriter writer) {
            writer.writeBoolean(aBoolean);
            writer.writeShort(aShort);
            writer.writeChar(aChar);
            writer.writeInt(anInt);
            writer.writeLong(aLong);
            writer.writeFloat(aFloat);
            writer.writeDouble(aDouble);
            writer.writeUshort(aUshort);
            writer.writeUint(aUint);
            writer.writeUlong(aUlong);
        }

        @Override
        public void deserialize(BinaryReader reader) {
            aBoolean = reader.readBoolean();
            aShort = reader.readShort();
            aChar = reader.readChar();
            anInt = reader.readInt();
            aLong = reader.readLong();
            aFloat = reader.readFloat();
            aDouble = reader.readDouble();
            aUshort = reader.readUshort();
            aUint = reader.readUint();
            aUlong = reader.readUlong();
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            DummySerializableTestObj that = (DummySerializableTestObj) o;

            if (aBoolean != that.aBoolean) return false;
            if (aByte != that.aByte) return false;
            if (aShort != that.aShort) return false;
            if (aChar != that.aChar) return false;
            if (anInt != that.anInt) return false;
            if (aLong != that.aLong) return false;
            if (Float.compare(that.aFloat, aFloat) != 0) return false;
            if (Double.compare(that.aDouble, aDouble) != 0) return false;
            if (aUshort != null ? !aUshort.equals(that.aUshort) : that.aUshort != null)
                return false;
            if (aUint != null ? !aUint.equals(that.aUint) : that.aUint != null) return false;
            return aUlong != null ? aUlong.equals(that.aUlong) : that.aUlong == null;
        }

        @Override
        public int hashCode() {
            int result;
            long temp;
            result = (aBoolean ? 1 : 0);
            result = 31 * result + (int) aByte;
            result = 31 * result + (int) aShort;
            result = 31 * result + (int) aChar;
            result = 31 * result + anInt;
            result = 31 * result + (int) (aLong ^ (aLong >>> 32));
            result = 31 * result + (aFloat != +0.0f ? Float.floatToIntBits(aFloat) : 0);
            temp = Double.doubleToLongBits(aDouble);
            result = 31 * result + (int) (temp ^ (temp >>> 32));
            result = 31 * result + (aUshort != null ? aUshort.hashCode() : 0);
            result = 31 * result + (aUint != null ? aUint.hashCode() : 0);
            result = 31 * result + (aUlong != null ? aUlong.hashCode() : 0);
            return result;
        }
    }

}