package com.bhrobotics.morlib;

import junit.framework.*;

public class FilterTest extends TestCase {
    Filter filter;
    
    public void setUp() {
        filter = new Filter() {
            public void handle(Event event) {}
        };
    }
    
    public void testCtor() {
        assertNotNull(filter);
    }
    
    public void testEmitter() {
        assertNotNull(filter.getEmitter());
    }
}