package neo.csharp.common;

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
