package frc.lib.motor;

import edu.wpi.first.units.measure.Voltage;
import frc.lib.utils.NeutralMode;

public interface MotorIO {
    String getName();

    void setPercent(double percent);

    void setPosition(double position);
    void setPosition(double position, double ff);

    void setVelocity(double velocity);
    void setVelocity(double velocity, double ff);

    void setVoltage(double voltage);
    void setVoltage(Voltage voltage);

    double getVelocity();
    double getPosition();
    double getVoltage();
    double getTemperature();
    double getCurrent();

    void configurePID(
        double kP,
        double kI,
        double kD
    );

    void configureFeedFoward(
        double kS,
        double kV,
        double kA,
        double kG
    );

    void configureFeedFoward(
        double kS,
        double kV,
        double kA
    );

    void setInverted(boolean inverted);
    void setIdleMode(NeutralMode mode);
    void setCurrentLimit(int amps);
    void setRampRate(double seconds);
    void setMechanismRatio(double ratio);

    void applyConfig();

    void stop();

    void resetPosition(double position);
}
