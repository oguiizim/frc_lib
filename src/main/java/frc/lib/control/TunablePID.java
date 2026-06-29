package frc.lib.control;

import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardContainer;
import frc.lib.motor.MotorIO;

public class TunablePID {

    private final TunableNumber p;
    private final TunableNumber i;
    private final TunableNumber d;

    public TunablePID(
            ShuffleboardContainer container,
            double p,
            double i,
            double d) {

        this.p = new TunableNumber(container, "P", p);
        this.i = new TunableNumber(container, "I", i);
        this.d = new TunableNumber(container, "D", d);
    }

    public double getP() {
        return p.get();
    }

    public double getI() {
        return i.get();
    }

    public double getD() {
        return d.get();
    }

    public boolean hasChanged() {
        return p.hasChanged()
                || i.hasChanged()
                || d.hasChanged();
    }

    public void applyIfChanged(MotorIO motor){
        if(!hasChanged()){
            return ;
        }
        motor.configurePID(getP(), getI(), getD());
    }

}