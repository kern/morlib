package test.bhrobotics.morlib;

import junit.framework.*;
import org.mockito.*;
import edu.wpi.first.wpilibj.Timer;
import com.bhrobotics.morlib.Reactor;
import com.bhrobotics.morlib.EventEmitter;
import com.bhrobotics.morlib.Queue;

public class ReactorTest extends TestCase {
    public void testCtor() {
        Reactor reactor = new Reactor();
        Assert.assertNotNull(reactor);
    }
    
    public void testQueue() {
        Reactor reactor = new Reactor();
        Assert.assertNotNull(reactor.getQueue());
        
        Queue queue = (Queue) Mockito.mock(Queue.class);
        reactor.setQueue(queue);
        Assert.assertSame(queue, reactor.getQueue());
    }
    
    public void testProcess() {
        Reactor reactor = new Reactor();
        Assert.assertNotNull(reactor.getProcess());
        
        EventEmitter process = (EventEmitter) Mockito.mock(EventEmitter.class);
        reactor.setProcess(process);
        Assert.assertSame(process, reactor.getProcess());
    }
    
    public void testStartStopTicking() {
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
    
    public void testForceTick() {
        Reactor reactor = new Reactor();
        
        EventEmitter process = (EventEmitter) Mockito.mock(EventEmitter.class);
        reactor.setProcess(process);
        
        reactor.start();
        
        reactor.forceTick();
        Timer.delay(0.25);
        ((EventEmitter) Mockito.verify(process)).emit("tick");
    }
    
    public void testTick() {
        Reactor reactor = new Reactor();
        
        EventEmitter process = (EventEmitter) Mockito.mock(EventEmitter.class);
        reactor.setProcess(process);
        
        Queue queue = (Queue) Mockito.mock(Queue.class);
        reactor.setQueue(queue);
        
        reactor.start();
        reactor.startTicking();
        ((EventEmitter) Mockito.verify(process)).emit("start");
        
        Timer.delay(0.25);
        
        ((EventEmitter) Mockito.verify(process, Mockito.atLeastOnce())).emit("tick");
        ((EventEmitter) Mockito.verify(process, Mockito.atLeastOnce())).emit("nextTick", true);
        ((Queue) Mockito.verify(queue, Mockito.atLeastOnce())).flush();
    }
}