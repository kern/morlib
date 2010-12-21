package test.bhrobotics.morlib;

import junit.framework.*;
import com.bhrobotics.morlib.EventEmitter;

public class EventEmitterTest extends TestCase {
    public void testCtor() {
        EventEmitter emitter = new EventEmitter(null);
        Assert.assertNotNull(emitter);
    }
}