package test.bhrobotics.morlib;

import junit.framework.*;
import com.bhrobotics.morlib.Reactor;

public class ReactorTest extends TestCase {
    public void testCtor() {
        Reactor reactor = new Reactor();
        Assert.assertNotNull(reactor);
    }
}