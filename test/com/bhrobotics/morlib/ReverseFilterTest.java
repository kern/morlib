package com.bhrobotics.morlib;

import junit.framework.*;

public class ReverseFilterTest extends TestCase {
    public void testCtor() {
        ReverseFilter rev = new ReverseFilter();
        assertNotNull(rev);
    }
    
    public void testReverse() {
        ReverseFilter rev = new ReverseFilter();
        StubListener listener = new StubListener();
        // rev.
    }
    
    private class StubListener implements Listener {
        public boolean received = false;
        
        public void handle(Event event) {
            received = true;
        }
        
        public void bound(EventEmitter emitter, String event) {}
        public void unbound(EventEmitter emitter, String event) {}
    }
}