package com.bhrobotics.morlib;

import junit.framework.*;

public class HelloListenerTest extends TestCase {
    public void testCtor() {
        HelloListener hello = new HelloListener();
        assertNotNull(hello);
    }
    
    public void testTriggered() {
        HelloListener hello = new HelloListener();
        assertFalse(hello.wasTriggered());
        hello.handle(null);
        assertTrue(hello.wasTriggered());
    }
}