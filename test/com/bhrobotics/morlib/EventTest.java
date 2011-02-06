package com.bhrobotics.morlib;

import junit.framework.*;
import org.mockito.*;
import java.util.Hashtable;

public class EventTest extends TestCase {
    public void testCtor() {
        Event event = new Event(null, null);
        assertNotNull(event);
    }
    
    public void testName() {
        Event event = new Event("foo", null);
        assertEquals("foo", event.getName());
    }
    
    public void testData() {
        Hashtable h = new Hashtable();
        Event event = new Event(null, h);
        assertEquals(h, event.getData());
    }
}