package com.bhrobotics.morlib;

import java.util.Hashtable;
import java.util.Timer;

public class TimeoutEmitter extends EventEmitter {
    private EventEmitter process = Reactor.getInstance().getProcess();
    private Timer timer = new Timer();
}