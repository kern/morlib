package com.bhrobotics.morlib;

import junit.framework.*;
import java.util.Hashtable;

public class FilterTest extends TestCase {
    Filter filter;
    
    public void setUp() {
        filter = new Filter() {
            public EventEmitter getEmitter() {
                return new EventEmitter();
            }
            
            public void handle(Event event) {}
            public void bound(EventEmitter emitter, String event) {}
            public void unbound(EventEmitter emitter, String event) {}
        };
    }
    
    public void testCtor() {
        assertNotNull(filter);
    }
    
    public void testEmitter() {
        assertNotNull(filter.getEmitter());
    }
    
    public void testBind() {
        Listener listener = new Listener() {
            public void handle(Event event) {}
            public void bound(EventEmitter emitter, String event) {}
            public void unbound(EventEmitter emitter, String event) {}
        };
        
        filter.bind("foo", listener);
        filter.unbind("foo", listener);
    }
    
    public void testTrigger() {
        Listener listener = new Listener() {
            public void handle(Event event) {}
            public void bound(EventEmitter emitter, String event) {}
            public void unbound(EventEmitter emitter, String event) {}
        };
        
        Event event = new Event("foo", new Hashtable());
        
        filter.trigger("foo");
        filter.trigger("foo", new Hashtable());
        filter.trigger("foo", true);
        filter.trigger("foo", new Hashtable(), true);
        filter.trigger(event);
        filter.trigger(event, true);
    }
    
    public void testListeners() {
        filter.getListeners();
        filter.getListeners("foo");
        filter.getListeners("foo", true);
    }
}