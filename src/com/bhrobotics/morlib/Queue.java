package com.bhrobotics.morlib;

import java.util.Vector;
import java.util.Enumeration;

public class Queue extends Vector {
    public QueueItem getHead() {
        QueueItem item = (QueueItem) firstElement();
        removeElement(item);
        return item;
    }
    
    public void addHead(Event event, Listener listener) {
        insertElementAt(new QueueItem(event, listener), 0);
    }
    
    public QueueItem getTail() {
        QueueItem item = (QueueItem) lastElement();
        removeElement(item);
        return item;
    }
    
    public void addTail(Event event, Listener listener) {
        addElement(new QueueItem(event, listener));
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
            item.getListener().handle(item.getEvent());
        }
    }
}