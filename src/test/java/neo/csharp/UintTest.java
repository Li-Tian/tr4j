package neo.csharp;

import org.junit.Test;

import static org.junit.Assert.*;

public class UintTest {

    @Test
    public void parseUint() {
        String[] values = {
                "0",
                "1",
                "65535",
                "4294967295",
                "FF",
                "FFFF"
        };
        int[] radix = {
            10, 10, 10, 10, 16, 16
        };
        long[] expected = {
                0, 1, 65535, 4294967295L, 255, 65535
        };
        for(int i = 0; i < values.length; i++) {
            assertEquals(expected[i], Uint.parseUint(values[i], radix[i]).longValue());
        }
    }

    @Test
    public void testMinMaxValue() {
        assertTrue(Ushort.compare(Ushort.MIN_VALUE_2, Ushort.MAX_VALUE_2) < 0);
        assertTrue(Uint.compare(Uint.MIN_VALUE_2, Uint.MAX_VALUE_2) < 0);
        assertTrue(Ulong.compare(Ulong.MIN_VALUE_2, Ulong.MAX_VALUE_2) < 0);
    }
}