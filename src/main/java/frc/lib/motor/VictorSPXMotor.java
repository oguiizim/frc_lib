package frc.lib.motor;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.units.measure.Voltage;
import frc.lib.util.UnsupportedFeature;

public class VictorSPXMotor implements MotorIO {

    private final String motorName;
    private final VictorSPX motor;

    public VictorSPXMotor(int id, String name) {
        this.motorName = name;
        motor = new VictorSPX(id);
    }

    @Override
    public String getName() {
        return motorName;
    }

    @Override
    public void setPercent(double percent) {
        motor.set(ControlMode.PercentOutput, percent);
    }

    @Override
    public void setPosition(double position) {
        UnsupportedFeature.unsupported("Victor SPX", "setPosition()");
    }

    @Override
    public void setPosition(double position, double ff) {
        UnsupportedFeature.unsupported("Victor SPX", "setPosition(double, double)");
    }

    @Override
    public void setVelocity(double velocity) {
        UnsupportedFeature.unsupported("Victor SPX", "setVelocity()");
    }

    @Override
    public void setVelocity(double velocity, double ff) {
        UnsupportedFeature.unsupported("Victor SPX", "setVelocity(double, double)");
    }

    @Override
    public void setVoltage(double voltage) {
        motor.set(ControlMode.PercentOutput, voltage / 12.0);
    }

    @Override
    public void setVoltage(Voltage voltage) {
        setVoltage(voltage.baseUnitMagnitude());
    }

    @Override
    public double getVelocity() {
        UnsupportedFeature.unsupported("Victor SPX", "getVelocity()");
        return 0;
    }

    @Override
    public double getPosition() {
        UnsupportedFeature.unsupported("Victor SPX", "getPosition()");
        return 0;
    }

    @Override
    public double getVoltage() {
        return motor.getMotorOutputPercent() * motor.getBusVoltage();
    }

    @Override
    public double getTemperature() {
        UnsupportedFeature.unsupported("Victor SPX", "getTemperature()");
        return 0;
    }

    
    @Override
    public double getCurrent() {
        UnsupportedFeature.unsupported("Victor SPX", "getCurrent()");
        return 0;
    }

    @Override
    public void configurePID(double kP, double kI, double kD) {
        UnsupportedFeature.unsupported("Victor SPX", "configurePID()");
    }

    @Override
    public void configureFeedForward(double kS, double kV, double kA, double kG) {
        UnsupportedFeature.unsupported("Victor SPX", "configureFeedForward()");
    }

    @Override
    public void configureFeedForward(double kS, double kV, double kA) {
        UnsupportedFeature.unsupported("Victor SPX", "configureFeedForward()");
    }

    @Override
    public void setInverted(boolean inverted) {
        motor.setInverted(inverted);
    }

    @Override
    public void setIdleMode(frc.lib.util.NeutralMode mode) {
        motor.setNeutralMode(
                mode == frc.lib.util.NeutralMode.BRAKE
                        ? NeutralMode.Brake
                        : NeutralMode.Coast);
    }

    @Override
    public void setCurrentLimit(int amps) {

    }

    @Override
    public void setRampRate(double seconds) {
        motor.configOpenloopRamp(seconds);
        motor.configClosedloopRamp(seconds);
    }

    @Override
    public void setMechanismRatio(double ratio) {
    }

    @Override
    public void applyConfig() {
    }

    @Override
    public void stop() {
        motor.neutralOutput();
    }

    @Override
    public void resetPosition(double position) {
        UnsupportedFeature.unsupported("Victor SPX", "resetPosition()");
    }
}