package com.bhrobotics.morlib;

import junit.framework.*;

public class EchoListenerTest extends TestCase {
    public void testCtor() {
        EchoListener echo = new EchoListener();
        assertNotNull(echo);
    }
    
    public void testTriggered() {
        EchoListener echo = new EchoListener();
        assertFalse(echo.wasTriggered());
        echo.handle(new Event("test", null));
        assertTrue(echo.wasTriggered());
    }
}