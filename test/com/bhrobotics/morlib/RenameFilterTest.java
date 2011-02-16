package com.bhrobotics.morlib;

import junit.framework.*;
import com.bhrobotics.morlib.EventEmitter;
import com.bhrobotics.morlib.Listener;
import com.bhrobotics.morlib.Reactor;
import com.bhrobotics.morlib.Event;
import java.util.Hashtable;

public class RenameFilterTest extends TestCase{
    RenameFilter renameFilter;
    
    public void setUp() {
        renameFilter = new RenameFilter();
    }
    
    public void testCtor() {
        assertNotNull(renameFilter);
    }
    
    public void testEmitter() {
        assertNotNull(renameFilter.getEmitter());
    }
    
    public void testAddLink() {
        Event event = new Event("foo", new Hashtable());
        
        renameFilter.addLink("foo", "test");
        renameFilter.addLink("bar", event);
        
        assertEquals("test", renameFilter.getLinks().get("foo"));
        assertEquals(event, renameFilter.getLinks().get("bar"));
    }
    
    public void testFilterEvents() {
        StubListener listener0 = new StubListener();
        StubListener listener1 = new StubListener();
        StubListener listener2 = new StubListener();
        
        renameFilter.bind("0", listener0);
        renameFilter.bind("1", listener1);
        renameFilter.bind("2", listener2);
        
        Event event0 = new Event("0", null);
        Event event1 = new Event("4", null);
        Event event2 = new Event("5", null);
        Event event3 = new Event("2", null);
        
        renameFilter.addLink("4", "1");
        renameFilter.addLink("5", event3);
        
        renameFilter.handle(event0);
        renameFilter.handle(event1);
        renameFilter.handle(event2);
        Reactor.tick();
        
        assertTrue(listener0.received);
        assertTrue(listener1.received);
        assertTrue(listener2.received);
    }
    
    private class StubListener implements Listener {
        public boolean received;
        
        public void handle(Event event) {
            received = true;
        }
        
        public void bound(EventEmitter emitter, String event) {}
        public void unbound(EventEmitter emitter, String event) {}
    }
}