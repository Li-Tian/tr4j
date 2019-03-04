package neo.csharp.io;

/**
 * 对象的2进制格式序列化和反序列化方法接口
 */
public interface ISerializable {
    /**
     * 2进制格式序列化的大小
     */
    int size();

    /**
     * 对象的2进制格式序列化方法
     *
     * @param writer 二进制输出器
     */
    void serialize(BinaryWriter writer);

    /**
     * 对象的2进制格式反序列化方法
     *
     * @param reader 二进制读入器
     */
    void deserialize(BinaryReader reader);
}
