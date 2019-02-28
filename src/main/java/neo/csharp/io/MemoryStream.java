package neo.csharp.io;

import java.io.ByteArrayInputStream;

public class MemoryStream extends ByteArrayInputStream {

    public MemoryStream(byte[] buf) {
        super(buf);
    }

    public int getPosition() {
        return pos;
    }

    public void seek(int pos) {
        reset();
        skip(pos);
    }
}
