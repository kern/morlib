package com.bhrobotics.morlib;

import junit.framework.*;
import org.mockito.*;

public class QueueItemTest extends TestCase {
    public void testCtor() {
        QueueItem item = new QueueItem(null, null);
        assertNotNull(item);
    }
    
    public void testEvent() {
        Event e = new Event("name", null);
        QueueItem item = new QueueItem(e, null);
        assertSame(e, item.getEvent());
    }
    
    public void testListener() {
        Listener l = new Listener() {
            public void handle(Event event) {}
            public void bound(String event, EventEmitter emitter) {}
            public void unbound(String event, EventEmitter emitter) {}
        };
        
        QueueItem item = new QueueItem(null, l);
        assertSame(l, item.getListener());
    }
}