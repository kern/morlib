package com.bhrobotics.morlib;

import junit.framework.*;

public class ControlListenerTest extends TestCase {
    public void testCtor() {
        ControlListener control = new ControlListener();
        Assert.assertNotNull(control);
    }
}