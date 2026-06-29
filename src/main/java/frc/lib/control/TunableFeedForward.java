package frc.lib.control;

import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardContainer;
import frc.lib.motor.MotorIO;

public class TunableFeedForward {

    private final TunableNumber s, v, a, g;

    public TunableFeedForward(
            ShuffleboardContainer container,
            double s,
            double v,
            double a,
            double g) {

        this.s = new TunableNumber(container, "S", s);
        this.v = new TunableNumber(container, "V", v);
        this.a = new TunableNumber(container, "A", a);
        this.g = new TunableNumber(container, "G", g);
    }

    public double getS() {
        return s.get();
    }

    public double getV() {
        return v.get();
    }

    public double getA() {
        return a.get();
    }

    public double getG() {
        return g.get();
    }

    public boolean hasChanged() {
        return s.hasChanged()
                || v.hasChanged()
                || a.hasChanged()
                || g.hasChanged();
    }

    public void applyIfChanged(MotorIO motor) {
        if (!hasChanged())
            return;

        if (g.get() == 0.0)
            motor.configureFeedForward(getS(), getV(), getA());
        else
            motor.configureFeedForward(getS(), getV(), getA(), getG());
    }
}
