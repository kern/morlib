package com.bhrobotics.morlib;

import junit.framework.*;

public class HelloListenerTest extends TestCase {
    public void testCtor() {
        HelloListener hello = new HelloListener();
        assertNotNull(hello);
    }
    
    public void testTriggered() {
        HelloListener hello = new HelloListener();
        Assert.assertFalse(hello.wasTriggered());
        hello.handle(null);
        Assert.assertTrue(hello.wasTriggered());
    }
}