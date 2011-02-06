package com.bhrobotics.morlib;

import junit.framework.*;
import java.util.Hashtable;

public class TimeoutEmitterTest extends TestCase {
    public void setUp() {
        Reactor.getInstance().startTicking();
    }
    
    public void tearDown() {
        Reactor.getInstance().stopTicking();
    }
    
    public void testCtor() {
        TimeoutEmitter timeout = new TimeoutEmitter();
        assertNotNull(timeout);
    }
    
    public void testSchedule() {
        TimeoutEmitter timeout = new TimeoutEmitter();
        StubListener listener = new StubListener();
        timeout.bind("timeout", listener);
        
        timeout.schedule("timeout", 0.5);
        sleep(200);
        assertFalse(listener.received);
        sleep(500);
        assertTrue(listener.received);
    }
    
    private void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            // Ignore.
        }
    }
    
    private class StubListener implements Listener {
        public boolean received = false;
        
        public void handle(Event event) {
            received = true;
        }
        
        public void bound(String event, EventEmitter emitter) {}
        public void unbound(String event, EventEmitter emitter) {}
    }
}