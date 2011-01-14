package com.bhrobotics.morlib;

import junit.framework.*;
import org.mockito.*;

public class QueueItemTest extends TestCase {
    public void testCtor() {
        QueueItem item = new QueueItem(null, null);
        Assert.assertNotNull(item);
    }
    
    public void testEvent() {
        Event e = new Event("name", null);
        QueueItem item = new QueueItem(e, null);
        Assert.assertSame(e, item.getEvent());
    }
    
    public void testListener() {
        Listener l = new Listener() {
            public void handle(Event e) {
                
            }
        };
        
        QueueItem item = new QueueItem(null, l);
        Assert.assertSame(l, item.getListener());
    }
}