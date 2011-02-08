package com.bhrobotics.morlib;

import java.util.Hashtable;

public abstract class Filter implements Listener {
    protected EventEmitter emitter = new EventEmitter();
    
    public void bound(String event, EventEmitter emitter) {}
    public void unbound(String event, EventEmitter emitter) {}
    
    public EventEmitter getEmitter() {
        return emitter;
    }
    
    public void bind(String event, Listener listener) {
        emitter.bind(event, listener);
    }
    
    public void unbind(String event, Listener listener) {
        emitter.unbind(event, listener);
    }
    
    public void trigger(String name) {
        emitter.trigger(name);
    }
    
    public void trigger(String name, Hashtable data) {
        emitter.trigger(name, data);
    }
    
    public void trigger(String name, boolean flush) {
        emitter.trigger(name, flush);
    }
    
    public void trigger(String name, Hashtable data, boolean flush) {
        emitter.trigger(name, data, flush);
    }
    
    public void trigger(Event event) {
        emitter.trigger(event);
    }
    
    public void trigger(Event event, boolean flush) {
        emitter.trigger(event, flush);
    }
}