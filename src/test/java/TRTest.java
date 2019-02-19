import neo.log.tr.TR;
import org.junit.Test;

public class TRTest {

    @Test
    public void testTrace() {
        TR.enter();
        TR.debug(aMethod());
        TR.exit();
    }

    private int aMethod() {
        TR.enter();
        TR.debug();
        TR.debug("Here I am.");
        TR.debug("A number is %d", 1);
        TR.info("some info");
        TR.warn("a warn message");
        //TR.warn(new Exception("warn"));
        TR.error("an error message");
        //TR.error(new Exception("error"));
        return TR.exit(1);
    }

}
