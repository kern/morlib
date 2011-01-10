package test.com.bhrobotics.morlib;

import junit.framework.*;
import java.util.Hashtable;
import com.bhrobotics.morlib.Event;

public class EventTest extends TestCase {
    public void testCtor() {
        Event event = new Event(null, null);
        Assert.assertNotNull(event);
    }
    
    public void testName() {
        Event event = new Event("foo", null);
        Assert.assertEquals("foo", event.getName());
    }
    
    public void testData() {
        Hashtable h = new Hashtable();
        Event event = new Event(null, h);
        Assert.assertEquals(h, event.getData());
    }
}