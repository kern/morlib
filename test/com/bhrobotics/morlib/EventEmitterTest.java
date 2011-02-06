package com.bhrobotics.morlib;

import junit.framework.*;
import org.mockito.*;

public class EventEmitterTest extends TestCase {
    private Queue queue = Reactor.getInstance().getQueue();
    private EventEmitter emitter;
    
    public void setUp() {
        emitter = new EventEmitter();
    }
    
    public void testCtor() {
        assertNotNull(emitter);
    }
    
    public void testBindUnbindListener() {
        StubListener listener = new StubListener();
        
        assertFalse(listener.boundReceived);
        assertFalse(listener.unboundReceived);
        
        emitter.bind("bar", listener);
        assertEquals(1, emitter.getListeners("bar").size());
        assertTrue(emitter.getListeners("bar").contains(listener));
        assertTrue(listener.boundReceived);
        assertFalse(listener.unboundReceived);
        
        emitter.unbind("bar", listener);
        assertTrue(emitter.getListeners().containsKey("bar"));
        assertTrue(emitter.getListeners("bar").isEmpty());
        assertTrue(listener.boundReceived);
        assertTrue(listener.unboundReceived);
    }
    
    public void testTriggerNoFlush() {
        StubListener listener1 = new StubListener();
        StubListener listener2 = new StubListener();
        emitter.bind("foo", listener1);
        emitter.bind("foo", listener2);
        
        emitter.trigger("foo");
        
        assertEquals(2, queue.size());
        assertSame(listener1, queue.getHead().getListener());
        assertSame(listener2, queue.getTail().getListener());
        assertEquals(2, emitter.getListeners("foo").size());
    }
    
    public void testTriggerFlush() {
        StubListener listener = new StubListener();
        emitter.bind("bar", listener);
        
        emitter.trigger("bar", true);
        
        assertEquals(1, queue.size());
        assertSame(listener, queue.getHead().getListener());
        assertTrue(emitter.getListeners("bar").isEmpty());
    }
    
    public void testTriggerAll() {
        StubListener listener = new StubListener();
        emitter.bind("all", listener);
        
        emitter.trigger("bar", true);
        
        assertEquals(1, queue.size());
        assertSame(listener, queue.getHead().getListener());
        assertEquals(1, emitter.getListeners("bar", true).size());
        assertEquals(1, emitter.getListeners("all").size());
    }
    
    private class StubListener implements Listener {
        public boolean boundReceived = false;
        public boolean unboundReceived = false;
        public boolean received = false;
        
        public void handle(Event event) {
            received = true;
        }
        
        public void bound(String event, EventEmitter emitter) {
            boundReceived = true;
        }
        
        public void unbound(String event, EventEmitter emitter) {
            unboundReceived = true;
        }
    }
}