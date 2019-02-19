import neo.log.tr.TR;
import org.junit.Test;

public class TRTest {

    @Test
    public void testTrace() {
        TR.enter();
        try {
            TR.debug(aMethod());
        } catch (Exception e) {
            TR.warn("a warn message");
            TR.error("an error message");
            TR.warn(e);
            //TR.error(e);
        }
        TR.fixMe("%s", "This is confidential!");
        TR.exit();
    }

    private int aMethod() throws Exception {
        TR.enter();
        TR.debug("Here I am.");
        TR.debug("A number is %d", 1);
        TR.debug();
        TR.info("some info");
        boolean condition = true; // false;
        if (condition) {
            TR.exit();
            throw new Exception("Something is wrong.");
        }
        return TR.exit(1);
    }

}
