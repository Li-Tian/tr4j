package neo.log.log4j2;

import com.sun.jna.Library;

public interface Kernel32 extends Library
{
    public void OutputDebugStringA(String Text);
}
