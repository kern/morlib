package com.bhrobotics.morlib;

import junit.framework.*;
import java.util.Hashtable;

public class TimeoutEmitterTest extends TestCase {
    TimeoutEmitter timeout;
    
    public void setUp() {
        Reactor.getInstance().startTicking();
        timeout = new TimeoutEmitter();
    }
    
    public void tearDown() {
        Reactor.getInstance().stopTicking();
    }
    
    public void testCtor() {
        assertNotNull(timeout);
    }
    
    public void testSchedule() {
        StubListener listener = new StubListener();
        timeout.bind("timeout", listener);
        
        timeout.schedule("timeout", 0.5);
        sleep(200);
        assertFalse(listener.received);
        sleep(500);
        assertTrue(listener.received);
    }
    
    public void testCancel() {
        StubListener listener = new StubListener();
        timeout.bind("timeout", listener);
        timeout.schedule("timeout", 0.5);
        sleep(200);
        timeout.cancelAll();
        sleep(500);
        assertFalse(listener.received);
        
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