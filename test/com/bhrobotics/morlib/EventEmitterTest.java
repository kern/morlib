package com.bhrobotics.morlib;

import junit.framework.*;
import org.mockito.*;

public class EventEmitterTest extends TestCase {
    public void testCtor() {
        EventEmitter emitter = new EventEmitter(null);
        Assert.assertNotNull(emitter);
    }
    
    public void testListeners() {
        EventEmitter emitter = new EventEmitter(null);
        Assert.assertNotNull(emitter.getListeners());
        
        Assert.assertTrue(emitter.getListeners().isEmpty());
        
        emitter.getListeners("foo");
        Assert.assertTrue(emitter.getListeners().containsKey("foo"));
        Assert.assertTrue(emitter.getListeners("foo").isEmpty());
        
        emitter.addListener("bar", null);
        Assert.assertEquals(1, emitter.getListeners("bar").size());
        Assert.assertTrue(emitter.getListeners("bar").contains(null));
        
        emitter.removeListener("bar", null);
        Assert.assertTrue(emitter.getListeners().containsKey("bar"));
        Assert.assertTrue(emitter.getListeners("bar").isEmpty());
    }
    
    public void testEmit() {
        Queue queue = (Queue) Mockito.mock(Queue.class);
        EventEmitter emitter = new EventEmitter(queue);
        
        Listener fooListener1 = (Listener) Mockito.mock(Listener.class);
        Listener fooListener2 = (Listener) Mockito.mock(Listener.class);
        Listener barListener = (Listener) Mockito.mock(Listener.class);
        
        emitter.addListener("foo", fooListener1);
        emitter.addListener("foo", fooListener2);
        emitter.addListener("bar", barListener);
        
        emitter.emit("foo");
        ((Queue) Mockito.verify(queue)).addTail((Event) Mockito.anyObject(), (Listener) Mockito.eq(fooListener1));
        ((Queue) Mockito.verify(queue)).addTail((Event) Mockito.anyObject(), (Listener) Mockito.eq(fooListener2));
        Assert.assertEquals(2, emitter.getListeners("foo").size());
        
        emitter.emit("bar", true);
        ((Queue) Mockito.verify(queue)).addTail((Event) Mockito.anyObject(), (Listener) Mockito.eq(barListener));
        Assert.assertTrue(emitter.getListeners("bar").isEmpty());
        
        emitter.emit("baz");
    }
}