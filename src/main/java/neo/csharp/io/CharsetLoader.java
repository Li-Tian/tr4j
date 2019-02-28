package neo.csharp.io;

import java.nio.charset.Charset;

import neo.log.notr.TR;

public class CharsetLoader {

    public static final Charset UTF_8;

    /**
     * 初始化项目配置
     */
    static {
        TR.enter();
        Charset charset = null;
        try {
            charset = Charset.forName("UTF-8");
        } catch (RuntimeException e) {
            TR.error("UTF-8 is not supported : %s", e.getMessage());
            System.exit(1);
        }
        UTF_8 = charset;
        TR.exit();
    }
}
