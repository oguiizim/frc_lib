package frc.lib.motor;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.PositionVoltage;
import com.ctre.phoenix6.controls.VelocityVoltage;
import com.ctre.phoenix6.controls.VoltageOut;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;

import edu.wpi.first.units.measure.Voltage;
import frc.lib.util.NeutralMode;

public class TalonFXMotor implements MotorIO {

    private final String motorName;
    private final TalonFX motor;
    private final TalonFXConfiguration config;

    private final VoltageOut voltageReq = new VoltageOut(0);
    private final PositionVoltage positionReq = new PositionVoltage(0);
    private final VelocityVoltage velocityReq = new VelocityVoltage(0);

    public TalonFXMotor(int id, String name) {
        this.motorName = name;
        motor = new TalonFX(id);
        config = new TalonFXConfiguration();

        this.applyConfig();
    }

    @Override
    public String getName() {
        return motorName;
    }

    @Override
    public void setPercent(double percent) {
        motor.set(percent);
    }

    @Override
    public void setPosition(double position) {
        motor.setControl(positionReq.withPosition(position));
    }

    @Override
    public void setPosition(double position, double ff) {
        motor.setControl(positionReq.withPosition(position).withFeedForward(ff));
    }

    @Override
    public void setVelocity(double velocity) {
        motor.setControl(velocityReq.withVelocity(velocity));
    }

    @Override
    public void setVelocity(double velocity, double ff) {
        motor.setControl(velocityReq.withVelocity(velocity).withFeedForward(ff));
    }

    @Override
    public void setVoltage(double voltage) {
        motor.setControl(voltageReq.withOutput(voltage));
    }

    @Override
    public void setVoltage(Voltage voltage) {
        motor.setControl(voltageReq.withOutput(voltage));
    }

    @Override
    public double getVelocity() {
        return motor.getVelocity().getValueAsDouble();
    }

    @Override
    public double getPosition() {
        return motor.getPosition().getValueAsDouble();
    }

    @Override
    public double getVoltage() {
        return motor.getMotorVoltage().getValueAsDouble();
    }

    @Override
    public double getTemperature() {
        return motor.getDeviceTemp().getValueAsDouble();
    }

    @Override
    public double getCurrent() {
        return motor.getSupplyCurrent().getValueAsDouble();
    }

    @Override
    public void configurePID(double kP, double kI, double kD) {
        config.Slot0.kP = kP;
        config.Slot0.kI = kI;
        config.Slot0.kD = kD;
    }

    @Override
    public void configureFeedFoward(double kS, double kV, double kA, double kG) {
        config.Slot0.kS = kS;
        config.Slot0.kV = kV;
        config.Slot0.kA = kA;
        config.Slot0.kG = kG;
    }

    @Override
    public void configureFeedFoward(double kS, double kV, double kA) {
        config.Slot0.kS = kS;
        config.Slot0.kV = kV;
        config.Slot0.kA = kA;
    }

    @Override
    public void setInverted(boolean inverted) {
        config.MotorOutput.Inverted = inverted
                ? InvertedValue.Clockwise_Positive
                : InvertedValue.CounterClockwise_Positive;
    }

    @Override
    public void setIdleMode(NeutralMode mode) {
        config.MotorOutput.NeutralMode = mode == NeutralMode.BRAKE
                ? NeutralModeValue.Brake
                : NeutralModeValue.Coast;
    }

    @Override
    public void setCurrentLimit(int amps) {
        config.CurrentLimits.SupplyCurrentLimit = amps;
        config.CurrentLimits.SupplyCurrentLimitEnable = true;
        config.CurrentLimits.StatorCurrentLimit = amps;
        config.CurrentLimits.StatorCurrentLimitEnable = true;
    }

    @Override
    public void setRampRate(double seconds) {
        config.OpenLoopRamps.DutyCycleOpenLoopRampPeriod = seconds;
        config.ClosedLoopRamps.VoltageClosedLoopRampPeriod = seconds;
    }

    @Override
    public void setMechanismRatio(double ratio) {
        config.Feedback.RotorToSensorRatio = 1.0;
        config.Feedback.SensorToMechanismRatio = ratio;
    }

    @Override
    public void applyConfig() {
        motor.getConfigurator().apply(config);
    }

    @Override
    public void stop() {
        motor.stopMotor();
    }

    @Override
    public void resetPosition(double position) {
        motor.setPosition(position);
    }

}
