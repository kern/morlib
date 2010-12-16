package com.bhrobotics.morlib;

import java.util.Stack;
import java.util.Enumeration;

public class Queue extends Stack {
    public void add(Event event, Listener listener) {
        push(new QueueItem(event, listener));
    }
    
    public void clear() {
        removeAllElements();
    }
    
    public void flush() {
        QueueItem[] currentTickItems = {};
        copyInto(currentTickItems);
        clear();
        
        for(int i = 0; i < size(); i++) {
            QueueItem item = currentTickItems[i];
            item.getListener().run(item.getEvent());
        }
    }
}