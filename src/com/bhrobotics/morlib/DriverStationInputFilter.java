package com.bhrobotics.morlib;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStationEnhancedIO;
import java.util.Hashtable;

public class DriverStationInputFilter extends Filter {
    private DriverStationEnhancedIO ds = DriverStation.getInstance().getEnhancedIO();
    private EventEmitter emitter       = new EventEmitter();
    
    private static final int ANALOGS  = 8;
    private static final int DIGITALS = 16;
    private static final int ACCELS   = 3;
    private static final int BUTTONS  = 6;
    
    private DSAnalogInput[] analogs   = new DSAnalogInput[ANALOGS];
    private DSDigitalInput[] digitals = new DSDigitalInput[DIGITALS];
    private DSAccelInput[] accels     = new DSAccelInput[ACCELS];
    private DSButtonInput[] buttons   = new DSButtonInput[BUTTONS];
    
    public DriverStationInputFilter() {
        for (int i = 0; i < ANALOGS; i++) {
            analogs[i] = new DSAnalogInput(i + 1);
        }
        
        for (int j = 0; j < DIGITALS; j++) {
            digitals[j] = new DSDigitalInput(j + 1);
        }
        
        accels[0] = new DSAccelInput(DriverStationEnhancedIO.tAccelChannel.kAccelX);
        accels[1] = new DSAccelInput(DriverStationEnhancedIO.tAccelChannel.kAccelY);
        accels[2] = new DSAccelInput(DriverStationEnhancedIO.tAccelChannel.kAccelZ);
        
        for (int j = 0; j < BUTTONS; j++) {
            buttons[j] = new DSButtonInput(j + 1);
        }
    }
    
    public void handle(Event event) {
        updateAllAnalogs(true);
        updateAllDigitals(true);
        updateAllAccels(true);
        updateAllButtons(true);
    }
    
    public void bound(EventEmitter emitter, String event) {
        updateAllAnalogs(false);
        updateAllDigitals(false);
        updateAllAccels(false);
        updateAllButtons(false);
    }
    
    public void unbound(EventEmitter emitter, String event) {}
    
    public EventEmitter getEmitter() {
        return emitter;
    }
    
    public void updateAllAnalogs(boolean shouldEmit) {
        for (int j = 0; j < ANALOGS; j++) {
            analogs[j].update(shouldEmit);
        }
    }
    
    public void updateAllDigitals(boolean shouldEmit) {
        for (int j = 0; j < DIGITALS; j++) {
            digitals[j].update(shouldEmit);
        }
    }
    
    public void updateAllAccels(boolean shouldEmit) {
        for (int j = 0; j < ACCELS; j++) {
            accels[j].update(shouldEmit);
        }
    }
    
    public void updateAllButtons(boolean shouldEmit) {
        for (int j = 0; j < BUTTONS; j++) {
            buttons[j].update(shouldEmit);
        }
    }
    
    private class DSAnalogInput {
        private int channel;
        private double oldValue;
        
        public DSAnalogInput(int c) {
            channel = c;
        }
        
        public void update(boolean shouldEmit) {
            try {
                double newValue = ds.getAnalogIn(channel);
                
                if (oldValue != newValue && shouldEmit) {
                    Hashtable data = new Hashtable();
                    data.put("oldValue", new Double(oldValue));
                    data.put("newValue", new Double(newValue));
                    
                    trigger("changeAnalog" + channel, data);
                }
                
                oldValue = newValue;
            } catch (DriverStationEnhancedIO.EnhancedIOException e) {
                // Ignore.
            }
        }
    }
    
    private class DSDigitalInput {
        private int channel;
        private boolean oldValue;
        
        public DSDigitalInput(int c) {
            channel = c;
        }
        
        public void update(boolean shouldEmit) {
            try {
                boolean newValue = ds.getDigital(channel);
                
                if (oldValue != newValue && shouldEmit) {
                    Hashtable data = new Hashtable();
                    data.put("oldValue", new Boolean(oldValue));
                    data.put("newValue", new Boolean(newValue));
                    
                    trigger("changeDigital" + channel, data);
                }
                
                oldValue = newValue;
            } catch (DriverStationEnhancedIO.EnhancedIOException e) {
                // Ignore.
            }
        }
    }
    
    private class DSAccelInput {
        private DriverStationEnhancedIO.tAccelChannel channel;
        private double oldValue;
        
        public DSAccelInput(DriverStationEnhancedIO.tAccelChannel c) {
            channel = c;
        }
        
        public void update(boolean shouldEmit) {
            try {
                double newValue = ds.getAcceleration(channel);
                
                if (oldValue != newValue && shouldEmit) {
                    Hashtable data = new Hashtable();
                    data.put("oldValue", new Double(oldValue));
                    data.put("newValue", new Double(newValue));
                    
                    trigger("changeAccel" + channel, data);
                }
                
                oldValue = newValue;
            } catch (DriverStationEnhancedIO.EnhancedIOException e) {
                // Ignore.
            }
        }
    }
    
    private class DSButtonInput {
        private int channel;
        private boolean oldValue;
        
        public DSButtonInput(int c) {
            channel = c;
        }
        
        public void update(boolean shouldEmit) {
            try {
                boolean newValue = ds.getButton(channel);
                
                if (oldValue != newValue && shouldEmit) {
                    Hashtable data = new Hashtable();
                    data.put("oldValue", new Boolean(oldValue));
                    data.put("newValue", new Boolean(newValue));
                    
                    trigger("changeButton" + channel, data);
                }
                
                oldValue = newValue;
            } catch (DriverStationEnhancedIO.EnhancedIOException e) {
                // Ignore.
            }
        }
    }
}
