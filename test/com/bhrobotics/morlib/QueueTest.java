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
        QueueItem head = (QueueItem) Mockito.mock(QueueItem.class);
        QueueItem tail = (QueueItem) Mockito.mock(QueueItem.class);
        
        Event headEvent = (Event) Mockito.mock(Event.class);
        Event tailEvent = (Event) Mockito.mock(Event.class);
        
        Listener headListener = (Listener) Mockito.mock(Listener.class);
        Listener tailListener = (Listener) Mockito.mock(Listener.class);
        
        Mockito.when(head.getEvent()).thenReturn(headEvent);
        Mockito.when(tail.getEvent()).thenReturn(tailEvent);
        
        Mockito.when(head.getListener()).thenReturn(headListener);
        Mockito.when(tail.getListener()).thenReturn(tailListener);
        
        queue.addTail(head);
        queue.addTail(tail);
        
        Assert.assertEquals(2, queue.size());
        
        queue.flush();
        
        Assert.assertEquals(0, queue.size());
        
        ((Listener) Mockito.verify(headListener)).handle(headEvent);
        ((Listener) Mockito.verify(tailListener)).handle(tailEvent);
    }
}