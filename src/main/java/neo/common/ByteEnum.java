package neo.common;

/**
 * Byte枚举类型定义
 */
public interface ByteEnum {
    /**
     * 获取 byte值
     */
    byte value();

    /**
     * 占用字节数大小
     */
    int BYTES = Byte.BYTES;

    /**
     * 从byte值中，解析出对应的类型
     *
     * @param ts   枚举范围
     * @param type 类型值
     * @param <T>  枚举泛型
     * @return T
     * @throws IllegalArgumentException 不存在的类型，当type值不存在时
     */
    static <T extends Enum & ByteEnum> T parse(T[] ts, byte type) {
        for (T t : ts) {
            if (t.value() == type) {
                return t;
            }
        }
        throw new IllegalArgumentException();
    }

    /**
     * 将类型列表转化成byte数组
     *
     * @param ts  类型列表
     * @param <T> 类型泛型
     * @return byte[]
     */
    static <T extends Enum & ByteEnum> byte[] toBytes(T[] ts) {
        byte[] values = new byte[ts.length];
        for (int i = 0; i < values.length; i++) {
            values[i] = ts[i].value();
        }
        return values;
    }
}
