package frc.lib.sensor.digital;

import edu.wpi.first.wpilibj.DigitalInput;

public class InfraRed implements DigitalSensorIO {

    private DigitalInput input;
    private String sensorName;
    private boolean inverted;

    public InfraRed(int channel, String name, boolean inverted) {
        input = new DigitalInput(channel);
        this.sensorName = name;
        this.inverted = inverted;
    }

    @Override
    public String getName() {
        return sensorName;
    }

    @Override
    public int getBinary() {
        if (isTriggered()) {
            return 1;
        }
        return 0;
    }

    @Override
    public boolean isTriggered() {
        boolean measurement = this.inverted ? !this.input.get() : this.input.get();
        return measurement;
    }

}
