package frc.lib.control;

import edu.wpi.first.networktables.GenericEntry;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardContainer;

public class TunableNumber {

    private final GenericEntry entry;
    private double lastValue;

    public TunableNumber(
            ShuffleboardContainer container,
            String name,
            double defaultValue) {

        entry = container
                .add(name, defaultValue)
                .getEntry();

        lastValue = defaultValue;
    }

    public double get() {
        return entry.getDouble(lastValue);
    }

    public boolean hasChanged() {

        double current = get();

        if (current != lastValue) {
            lastValue = current;
            return true;
        }

        return false;
    }

}
