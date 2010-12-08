package com.bhrobotics.morlib;

import java.util.HashMap;
import java.util.Vector;

public class EventEmitter {
    private HashMap listeners = new HashMap();
    
    public HashMap getListeners() {
        return listeners;
    }
    
    public void setListeners(HashMap l) {
        listeners = l;
    }
    
    public void addListener(String event, Listener listener) {
        if(listeners.containsKey(event)) {
            listeners.get(event).add(listener);
        } else {
            Vector v = new Vector();
            v.add(listener);
            
            listeners.put(event, v);
        }
    }
    
    public void removeListener(String event, Listener listener) {
        if(!listeners.containsKey(event)) {
            listeners.get(event).remove(listener);
        }
    }
    
    public void emit(String event, Object obj) {
        if(listeners.containsKey(event)) {
            Iterator i = listeners.get(event).iterator();
            while(i.hasNext()) {
                Listener listener = (Listener) i.next();
                listener.run();
            }
        }
    }
}