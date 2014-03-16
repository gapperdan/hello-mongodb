package com.gapperdan.hellomongo.util;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UtilTest  {

    @Test
    public void shouldMakeOnlyTheFirstCharUpperCase() {
        assertEquals("Example", Util.upperFirstCharOnly("EXAMPLE"));
        assertEquals("Another", Util.upperFirstCharOnly("another"));
        assertEquals("More", Util.upperFirstCharOnly("More"));
    }
}
