package neo.csharp;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/**
 * 从C#移植过来的工具，统一使用 little endian
 */
public class BitConverter {

    /**
     * 布尔值转字节数组
     *
     * @param value true, false
     * @return true : 01, false : 00
     */
    public static byte[] getBytes(boolean value) {
        return new byte[]{(byte) (value ? 1 : 0)};
    }

    /**
     * 从C#移植过来，统一使用 little endian
     *
     * @param value short整数
     * @return little endian 表示的数组
     */
    public static byte[] getBytes(short value) {
        return new byte[]{
                (byte) value,
                (byte) (value >>> 8)};
    }

    /**
     * 从C#移植过来，统一使用 little endian
     *
     * @param value char
     * @return little endian 表示的数组
     */
    public static byte[] getBytes(char value) {
        return new byte[]{
                (byte) value,
                (byte) (value >>> 8)};
    }

    /**
     * 从C#移植过来，统一使用 little endian
     *
     * @param value short整数
     * @return little endian 表示的数组
     */
    public static byte[] getBytes(Ushort value) {
        return getBytes(value.shortBits());
    }

    /**
     * 从C#移植过来，统一使用 little endian
     *
     * @param value 整数
     * @return little endian 表示的数组
     */
    public static byte[] getBytes(int value) {
        return new byte[]{
                (byte) value,
                (byte) (value >>> 8),
                (byte) (value >>> 16),
                (byte) (value >>> 24)
        };
    }

    /**
     * 从C#移植过来，统一使用 little endian
     *
     * @param value 整数
     * @return little endian 表示的数组
     */
    public static byte[] getBytes(Uint value) {
        return getBytes(value.intBits());
    }

    /**
     * 从C#移植过来，统一使用 little endian
     *
     * @param value 整数
     * @return little endian 表示的数组
     */
    public static byte[] getBytes(long value) {
        return new byte[]{
                (byte) value,
                (byte) (value >>> 8),
                (byte) (value >>> 16),
                (byte) (value >>> 24),
                (byte) (value >>> 32),
                (byte) (value >>> 40),
                (byte) (value >>> 48),
                (byte) (value >>> 56)
        };
    }

    /**
     * 从C#移植过来，统一使用 little endian
     *
     * @param value 整数
     * @return little endian 表示的数组
     */
    public static byte[] getBytes(Ulong value) {
        return getBytes(value.longBits());
    }

    /**
     * 从C#移植过来，统一使用 little endian
     *
     * @param value double浮点数
     * @return little endian 表示的数组
     */
    public static byte[] getBytes(double value) {
        long longValue = Double.doubleToRawLongBits(value);
        return getBytes(longValue);
    }

    /**
     * 从C#移植过来，统一使用 little endian
     *
     * @param value float浮点数
     * @return little endian 表示的数组
     */
    public static byte[] getBytes(float value) {
        long intValue = Float.floatToRawIntBits(value);
        return getBytes(intValue);
    }

    /**
     * 字节数组转布尔值
     *
     * @param value      字节数组
     * @param startIndex 数组下标
     * @return true : 非零, false : 零
     */
    public static boolean toBoolean(byte[] value, int startIndex) {
        return value[startIndex] != 0;
    }

    /**
     * 字节数组转布尔值
     *
     * @param value 字节数组
     * @return true : 非零, false : 零
     */
    public static boolean toBoolean(byte[] value) {
        return value[0] != 0;
    }

    /**
     * little endian
     *
     * @param value in little endian
     * @return value
     */
    public static char toChar(byte[] value) {
        return Character.reverseBytes(ByteBuffer.wrap(value).getChar());
    }

    /**
     * little endian
     *
     * @param value in little endian
     * @return value
     */
    public static short toShort(byte[] value) {
        return Short.reverseBytes(ByteBuffer.wrap(value).getShort());
    }

    /**
     * little endian
     *
     * @param value in little endian
     * @return value
     */
    public static Ushort toUshort(byte[] value) {
        return new Ushort(toShort(value));
    }

    /**
     * little endian
     *
     * @param value in little endian
     * @return value
     */
    public static int toInt(byte[] value) {
        return Integer.reverseBytes(ByteBuffer.wrap(value).getInt());
    }

    /**
     * little endian
     *
     * @param value in little endian
     * @return value
     */
    public static Uint toUint(byte[] value) {
        return new Uint(toInt(value));
    }

    /**
     * little endian
     *
     * @param value in little endian
     * @return value
     */
    public static long toLong(byte[] value) {
        return Long.reverseBytes(ByteBuffer.wrap(value).getLong());
    }

    /**
     * little endian
     *
     * @param value in little endian
     * @return value
     */
    public static Ulong toUlong(byte[] value) {
        return new Ulong(toLong(value));
    }

    /**
     * little endian
     *
     * @param value in little endian
     * @return value
     */
    public static float toFloat(byte[] value) {
        return Float.intBitsToFloat(toInt(value));
    }

    /**
     * little endian
     *
     * @param value in little endian
     * @return value
     */
    public static double toDouble(byte[] value) {
        return Double.longBitsToDouble(toLong(value));
    }

    /**
     * 7 bit 编码整数。使用 little endian, 每次取出7bit, 如果剩下的bit 全部为0，则将开头位标记为1，否则标记为0。
     *
     * @return 7 bit 编码。
     */
    public static byte[] get7BitEncodedBytes(int value) {
        ByteArrayOutputStream buff = new ByteArrayOutputStream();
        do {
            int temp = value & 0x7F;
            value &= 0xFFFFFF80;
            if (value != 0) {
                temp |= 0x80;
            }
            buff.write(temp);
            value >>>= 7;
        } while (value != 0);
        return buff.toByteArray();
    }

    /**
     * 解码7bit 整数。解码格式见 get7BitEncodedBytes
     * @param inputStream 输入字节流
     * @return 解码出来的整数
     * @throws IOException IO异常
     */
    public static int decode7BitEncodedInt(InputStream inputStream) throws IOException {
        int value = 0;
        boolean foundLeadingZero = false;
        int i = 0;
        for (i = 0; i < 5 && !foundLeadingZero; i++){
            int temp = inputStream.read();
            if (temp < 0x80) {
                foundLeadingZero = true;
            }
            temp &= 0x7F;
            temp <<= (i * 7);
            value |= temp;
        }
        if (!foundLeadingZero) {
            throw new IOException("7bit encoding format error");
        }
        return value;
    }
}
