package neo.csharp.io;

public interface ISerializable {
    int size();

    void serialize(BinaryWriter writer);

    void deserialize(BinaryReader reader);
}
