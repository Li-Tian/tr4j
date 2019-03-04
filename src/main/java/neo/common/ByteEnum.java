package neo.common;

import neo.log.notr.TR;

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
        TR.fixMe("Enum 数量很多时，这个方法效率很低。最好改写成查哈希表。");
        for (T t : ts) {
            if (t.value() == type) {
                return t;
            }
        }
        throw new IllegalArgumentException();
    }
}
