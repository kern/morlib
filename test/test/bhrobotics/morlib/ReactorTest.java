package test.bhrobotics.morlib;

import junit.framework.*;
import org.mockito.*;
import com.bhrobotics.morlib.Reactor;
import com.bhrobotics.morlib.EventEmitter;

public class ReactorTest extends TestCase {
    public void testCtor() {
        Reactor reactor = new Reactor();
        Assert.assertNotNull(reactor);
    }
    
    public void testQueue() {
        Reactor reactor = new Reactor();
        Assert.assertNotNull(reactor.getQueue());
    }
    
    public void testProcess() {
        Reactor reactor = new Reactor();
        Assert.assertNotNull(reactor.getProcess());
    }
    
    public void testTicking() {
        Reactor reactor = new Reactor();
        
        EventEmitter process = (EventEmitter) Mockito.mock(EventEmitter.class);
        reactor.setProcess(process);
        
        reactor.start();
        
        Assert.assertFalse(reactor.isTicking());
        
        reactor.startTicking();
        Assert.assertTrue(reactor.isTicking());
        ((EventEmitter) Mockito.verify(process)).emit("start");
        
        reactor.stopTicking();
        Assert.assertFalse(reactor.isTicking());
        ((EventEmitter) Mockito.verify(process)).emit("stop");
    }
}