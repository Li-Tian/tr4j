package neo.csharp;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

import neo.log.notr.TR;

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
        int intValue = Float.floatToRawIntBits(value);
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
        TR.enter();
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
        TR.exit();
        return buff.toByteArray();
    }

    /**
     * 解码7bit 整数。解码格式见 get7BitEncodedBytes
     *
     * @param inputStream 输入字节流
     * @return 解码出来的整数
     * @throws IOException IO异常
     */
    public static int decode7BitEncodedInt(InputStream inputStream) throws IOException {
        TR.enter();
        int value = 0;
        boolean foundLeadingZero = false;
        int i = 0;
        for (i = 0; i < 5 && !foundLeadingZero; i++) {
            int temp = inputStream.read();
            if (temp < 0x80) {
                foundLeadingZero = true;
            }
            temp &= 0x7F;
            temp <<= (i * 7);
            value |= temp;
        }
        if (!foundLeadingZero) {
            TR.exit();
            throw new IOException("7bit encoding format error");
        }
        return TR.exit(value);
    }

    /**
     * VarInt 编码。取值范围是 [0 , 2^63-1]. 根据首字节判定存储格式<br/> 小于 0xfd	1字节 unsigned byte<br/> 小于等于 0xffff	3字节
     * 0xfd + unsigned short<br/> 小于等于 0xffffffff	5字节 0xfe + unsigned int <br/> 大于
     * 0xffffffff	9字节	0xff + long
     *
     * @param value 值
     * @return 编码后的字节数组
     */
    public static byte[] getVarIntEncodedBytes(long value) {
        TR.enter();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        if (value < 0) {
            TR.exit();
            throw new IllegalArgumentException("VarInt out of range");
        }
        if (value < 0xFD) {
            stream.write((byte) value);
        } else if (value <= 0xFFFF) {
            stream.write((byte) 0xFD);
            byte[] temp = getBytes((short) value);
            stream.write(temp, 0, temp.length);
        } else if (value <= 0xFFFFFFFFL) {
            stream.write((byte) 0xFE);
            byte[] temp = getBytes((int) value);
            stream.write(temp, 0, temp.length);
        } else {
            stream.write((byte) 0xFF);
            byte[] temp = getBytes(value);
            stream.write(temp, 0, temp.length);
        }
        TR.exit();
        return stream.toByteArray();
    }

    /**
     * 解码 VarInt。细节见 getVarIntAsBytes()
     *
     * @param inputStream 输入字节流
     * @return 解码结果，用long来表示 unsigned long.
     * @throws IOException IO 异常
     */
    public static long decodeVarInt(InputStream inputStream) throws IOException {
        TR.enter();
        long fb = inputStream.read();
        if (fb < 0 || fb > 255) {
            TR.exit();
            throw new IOException("Wrong format for VarInt");
        }
        long value;
        if (fb == 0xFDL) {
            byte[] temp = new byte[2];
            inputStream.read(temp);
            short tempValue = Short.reverseBytes(ByteBuffer.wrap(temp).getShort());
            value = tempValue & 0xFFFFL;
        } else if (fb == 0xFEL) {
            byte[] temp = new byte[4];
            inputStream.read(temp);
            int tempValue = Integer.reverseBytes(ByteBuffer.wrap(temp).getInt());
            value = tempValue & 0xFFFFFFFFL;
        } else if (fb == 0xFFL) {
            byte[] temp = new byte[8];
            inputStream.read(temp);
            value = Long.reverseBytes(ByteBuffer.wrap(temp).getLong());
        } else {
            value = fb;
        }
        return TR.exit(value);
    }

    /**
     * merge a, b
     *
     * @param a 待合并byte数组
     * @param b 待合并byte数组
     * @return merge后的byte数组
     */
    public static byte[] merge(byte[] a, byte[] b) {
        byte[] c = new byte[a.length + b.length];
        System.arraycopy(a, 0, c, 0, a.length);
        System.arraycopy(b, 0, c, a.length, b.length);
        return c;
    }

    /**
     * merge a, b
     *
     * @param a 待合并byte
     * @param b 待合并byte数组
     * @return merge后的byte数组
     */
    public static byte[] merge(byte a, byte[] b) {
        byte[] c = new byte[1 + b.length];
        c[0] = a;
        System.arraycopy(b, 0, c, 1, b.length);
        return c;
    }

    /**
     * 获取bytes数组切片
     *
     * @param src        待切片数组
     * @param beginIndex 开始为止(包括）
     * @param endIndex   截至为止（不包括）
     */
    public static byte[] subBytes(byte[] src, int beginIndex, int endIndex) {
        if (beginIndex < 0 || beginIndex > endIndex || endIndex > src.length) {
            throw new IllegalArgumentException();
        }
        byte[] sub = new byte[endIndex - beginIndex];

        System.arraycopy(src, beginIndex, sub, 0, endIndex - beginIndex);
        return sub;
    }

    /**
     * 将byte数组转16进制字符串
     */
    public static String toHexString(byte[] value) {
        StringBuilder sb = new StringBuilder();
        for (byte b : value) {
            int v = Byte.toUnsignedInt(b);
            sb.append(Integer.toHexString(v >>> 4));
            sb.append(Integer.toHexString(v & 0x0f));
        }
        return sb.toString();
    }

    /**
     * reverse a byte array
     */
    public static byte[] reverse(byte[] v) {
        byte[] result = new byte[v.length];
        for (int i = 0; i < v.length; i++) {
            result[i] = v[v.length - i - 1];
        }
        return result;
    }

    public static byte[] hexToBytes(String value) {
        if (value == null || value.length() == 0) {
            return new byte[0];
        }

        if (value.startsWith("0x")) {
            value = value.substring(2);
        }

        if (value.length() % 2 == 1) {
            throw new IllegalArgumentException();
        }
        byte[] result = new byte[value.length() / 2];
        for (int i = 0; i < result.length; i++) {
            result[i] = (byte) Integer.parseInt(value.substring(i * 2, i * 2 + 2), 16);
        }
        return result;
    }

}
