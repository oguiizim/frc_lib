package frc.lib.control;

import edu.wpi.first.networktables.GenericEntry;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInLayouts;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;

public class TunableNumber implements Tunable<Double> {

    private final GenericEntry entry;

    private double currentValue, lastValue;

    public TunableNumber(
            String tab,
            String layout,
            String name,
            double defaultValue) {
        this.entry = Shuffleboard.getTab(tab).getLayout(layout, BuiltInLayouts.kList).add(name, defaultValue)
                .getEntry();
        this.currentValue = defaultValue;
        this.lastValue = defaultValue;
    }

    @Override
    public Double get() {
        currentValue = entry.getDouble(currentValue);
        return currentValue;
    }

    @Override
    public boolean hasChanged() {
        double value = get();

        if (value != lastValue) {
            lastValue = value;
            return true;
        }
        return false;
    }

}
