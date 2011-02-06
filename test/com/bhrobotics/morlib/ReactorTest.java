package com.bhrobotics.morlib;

import junit.framework.*;

public class ReactorTest extends TestCase {
    private Reactor reactor = Reactor.getInstance();
    private Queue queue = reactor.getQueue();
    private EventEmitter process = reactor.getProcess();
    
    public void testCtor() {
        assertNotNull(reactor);
    }
    
    public void testQueue() {
        assertNotNull(queue);
    }
    
    public void testProcess() {
        assertNotNull(process);
    }
    
    public void testIsTicking() {
        assertFalse(reactor.isTicking());
        
        reactor.startTicking();
        assertTrue(reactor.isTicking());
        
        reactor.stopTicking();
        assertFalse(reactor.isTicking());
    }
    
    public void testForceTick() {
        StubListener listener = new StubListener();
        process.bind("tick", listener);
        
        reactor.forceTick();
        sleep(200);
        
        assertTrue(listener.received);
    }
    
    public void testTick() {
        StubListener startListener    = new StubListener();
        StubListener tickListener     = new StubListener();
        StubListener nextTickListener = new StubListener();
        StubListener stopListener     = new StubListener();
        process.bind("start", startListener);
        process.bind("tick", tickListener);
        process.bind("nextTick", nextTickListener);
        process.bind("stop", stopListener);
        
        reactor.startTicking();
        sleep(200);
        reactor.stopTicking();
        sleep(200);
        
        assertTrue(startListener.received);
        assertTrue(tickListener.received);
        assertTrue(nextTickListener.received);
        assertTrue(stopListener.received);
        assertTrue(process.getListeners("start").contains(startListener));
        assertTrue(process.getListeners("tick").contains(tickListener));
        assertFalse(process.getListeners("nextTick").contains(nextTickListener));
        assertTrue(process.getListeners("stop").contains(stopListener));
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