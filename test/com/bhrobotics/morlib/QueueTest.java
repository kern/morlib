package com.bhrobotics.morlib;

import junit.framework.*;
import org.mockito.*;

public class QueueTest extends TestCase {
    public Queue queue;
    
    public void setUp() {
        queue = new Queue();
    }
    
    public void testHead() {
        QueueItem head = new QueueItem(null, null);
        
        queue.addHead(head);
        Assert.assertSame(head, queue.getHead());
        
        queue.addHead(null, null);
        Assert.assertNotSame(head, queue.getHead());
    }
    
    public void testTail() {
        QueueItem tail = new QueueItem(null, null);
        
        queue.addTail(tail);
        Assert.assertSame(tail, queue.getTail());
        
        queue.addTail(null, null);
        Assert.assertNotSame(tail, queue.getTail());
    }
    
    public void testClear() {
        for(int i = 0; i < 5; i++) {
            queue.addTail(null, null);
        }
        
        Assert.assertEquals(5, queue.size());
        
        queue.clear();
        
        Assert.assertEquals(0, queue.size());
    }
    
    public void testOrder() {
        QueueItem head = new QueueItem(null, null);
        QueueItem tail = new QueueItem(null, null);
        
        queue.addTail(head);
        queue.addTail(tail);
        
        Assert.assertSame(head, queue.getHead());
        Assert.assertSame(tail, queue.getTail());
        
        queue.clear();
        
        queue.addHead(tail);
        queue.addHead(head);
        
        Assert.assertSame(head, queue.getHead());
        Assert.assertSame(tail, queue.getTail());
    }
    
    public void testFlush() {
        Event headEvent = new Event(null, null);
        Event tailEvent = new Event(null, null);
        
        StubListener headListener = new StubListener();
        StubListener tailListener = new StubListener();
        
        QueueItem head = new QueueItem(headEvent, headListener);
        QueueItem tail = new QueueItem(tailEvent, tailListener);
        
        queue.addTail(head);
        queue.addTail(tail);
        Assert.assertEquals(2, queue.size());
        
        queue.flush();
        Assert.assertEquals(0, queue.size());
        
        Assert.assertSame(headEvent, headListener.event);
        Assert.assertSame(tailEvent, tailListener.event);
    }
    
    private class StubListener implements Listener {
        public Event event;
        
        public void handle(Event event) {
            this.event = event;
        }
        
        public void bound(String event, EventEmitter emitter) {}
        public void unbound(String event, EventEmitter emitter) {}
    }
}