package com.bhrobotics.morlib;

import java.util.Vector;

public class Reactor extends Thread {
    private boolean running      = false;
    private boolean forceTick    = false;
    private Queue queue          = new Queue();
    private EventEmitter process = new EventEmitter(queue);
    
    public void run() {
        while(true) {
            if(running || forceTick) {
                tick();
            } else {
                try {
                    wait(500);
                } catch(InterruptedException e) {
                    // Ignored.
                }
            }
            
            forceTick = false;
        }
    }
    
    public void startTicking() {
        process.emit("start");
        running = true;
        forceTick();
    }
    
    public void stopTicking() {
        process.emit("stop");
        running = false;
        forceTick();
    }
    
    public void tick() {
        process.emit("tick");
        process.emit("nextTick", true);
        queue.flush();
    }
    
    public boolean isRunning() {
        return running;
    }
    
    public void forceTick() {
        forceTick = true;
        notify();
    }
    
    public Queue getQueue() {
        return queue;
    }
    
    public EventEmitter getProcess() {
        return process;
    }
}