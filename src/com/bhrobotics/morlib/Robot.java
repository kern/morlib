package com.bhrobotics.morlib;

import edu.wpi.first.wpilibj.SimpleRobot;

public class Robot extends SimpleRobot {
    private Reactor reactor = new Reactor();
    
    public void autonomous() {
        reactor.startTicking();
        getProcess().emit("startAutonomous");
        
        while(isAutonomous() && isSystemActive()) {
            getWatchdog().feed();
        }
        
        getProcess().emit("stopAutonomous");
        reactor.stopTicking();
    }
    
    public void operatorControl() {
        reactor.startTicking();
        getProcess().emit("startOperatorControl");
        
        while(isOperatorControl() && isSystemActive()) {
            getWatchdog().feed();
        }
        
        getProcess().emit("stopOperatorControl");
        reactor.stopTicking();
    }
    
    public Reactor getReactor() {
        return reactor;
    }
    
    public EventEmitter getProcess() {
        return reactor.getProcess();
    }
}