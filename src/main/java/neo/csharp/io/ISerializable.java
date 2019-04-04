package neo.csharp.io;

/**
 * Serializable interface, provides serialization and deserialization method.
 */
public interface ISerializable {
    /**
     * get the size of storage
     */
    int size();

    /**
     * Serialization method
     *
     * @param writer BinaryWriter
     */
    void serialize(BinaryWriter writer);

    /**
     * Deserialization method
     *
     * @param reader BinaryReader
     */
    void deserialize(BinaryReader reader);
}
