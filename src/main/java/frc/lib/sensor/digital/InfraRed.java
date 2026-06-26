package frc.lib.sensor.digital;

import edu.wpi.first.wpilibj.DigitalInput;

public class InfraRed implements DigitalSensorIO{

    private DigitalInput input;
    private String sensorName;
    private boolean rev;

    public InfraRed(int channel, String name, boolean reversed){
        input = new DigitalInput(channel);
        this.sensorName = name;
        this.rev = reversed;
    }

    @Override
    public String getName() {
        return sensorName;
    }

    @Override
    public boolean isTriggered() {
        if(rev){
            return !input.get();
        }
        return input.get();
    }
    
}
