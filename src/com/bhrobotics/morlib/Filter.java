package com.bhrobotics.morlib;

import java.util.Hashtable;
import java.util.Vector;

public abstract class Filter implements Listener {
    protected EventEmitter emitter = new EventEmitter();
    
    public EventEmitter getEmitter() {
        return emitter;
    }
    
    public Hashtable getListeners() {
        return emitter.getListeners();
    }
    
    public Vector getListeners(String event) {
        return emitter.getListeners(event);
    }
    
    public Vector getListeners(String event, boolean includeAll) {
        return emitter.getListeners(event, includeAll);
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