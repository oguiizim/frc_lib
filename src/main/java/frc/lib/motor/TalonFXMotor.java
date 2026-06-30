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
    private final TalonFXConfiguration cfg;

    private double targetPosition, targetVelocity, targetPercent;

    private final VoltageOut voltageReq = new VoltageOut(0);
    private final PositionVoltage positionReq = new PositionVoltage(0);
    private final VelocityVoltage velocityReq = new VelocityVoltage(0);

    public TalonFXMotor(int id, String name, MotorConfig config) {
        this.motorName = name;
        motor = new TalonFX(id);
        cfg = new TalonFXConfiguration();
        this.setCurrentLimit(config.currentLimit());
        this.setRampRate(config.rampRate());
        this.setIdleMode(config.idleMode());
        this.setInverted(config.inverted());
        this.setMechanismRatio(config.mechanismRatio());

        this.applyConfig();
    }

    @Override
    public String getName() {
        return motorName;
    }

    @Override
    public void setPercent(double percent) {
        motor.set(percent);
        this.targetPercent = percent;
        this.targetPosition = Double.NaN;
        this.targetVelocity = Double.NaN;
    }

    @Override
    public void setPosition(double position) {
        if (this.getPosition() != position) {
            motor.setControl(positionReq.withPosition(position));
        }
        this.targetPercent = Double.NaN;
        this.targetPosition = position;
        this.targetVelocity = Double.NaN;
    }

    @Override
    public void setPosition(double position, double ff) {
        if (this.getPosition() != position) {
            motor.setControl(positionReq.withPosition(position).withFeedForward(ff));
        }
        this.targetPercent = Double.NaN;
        this.targetPosition = position;
        this.targetVelocity = Double.NaN;
    }

    @Override
    public void setVelocity(double velocity) {
        if (this.getVelocity() != velocity) {
            motor.setControl(velocityReq.withVelocity(velocity));
        }
        this.targetPercent = Double.NaN;
        this.targetPosition = Double.NaN;
        this.targetVelocity = velocity;
    }

    @Override
    public void setVelocity(double velocity, double ff) {
        if (this.getVelocity() != velocity) {
            motor.setControl(velocityReq.withVelocity(velocity).withFeedForward(ff));
        }
        this.targetPercent = Double.NaN;
        this.targetPosition = Double.NaN;
        this.targetVelocity = velocity;
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
        cfg.Slot0.kP = kP;
        cfg.Slot0.kI = kI;
        cfg.Slot0.kD = kD;
    }

    @Override
    public void configureFeedForward(double kS, double kV, double kA, double kG) {
        cfg.Slot0.kS = kS;
        cfg.Slot0.kV = kV;
        cfg.Slot0.kA = kA;
        cfg.Slot0.kG = kG;
    }

    @Override
    public void configureFeedForward(double kS, double kV, double kA) {
        cfg.Slot0.kS = kS;
        cfg.Slot0.kV = kV;
        cfg.Slot0.kA = kA;
    }

    @Override
    public void setInverted(boolean inverted) {
        cfg.MotorOutput.Inverted = inverted
                ? InvertedValue.Clockwise_Positive
                : InvertedValue.CounterClockwise_Positive;
    }

    @Override
    public void setIdleMode(NeutralMode mode) {
        cfg.MotorOutput.NeutralMode = mode == NeutralMode.BRAKE
                ? NeutralModeValue.Brake
                : NeutralModeValue.Coast;
    }

    @Override
    public void setCurrentLimit(int amps) {
        cfg.CurrentLimits.SupplyCurrentLimit = amps;
        cfg.CurrentLimits.SupplyCurrentLimitEnable = true;
        cfg.CurrentLimits.StatorCurrentLimit = amps;
        cfg.CurrentLimits.StatorCurrentLimitEnable = true;
    }

    @Override
    public void setRampRate(double seconds) {
        cfg.OpenLoopRamps.DutyCycleOpenLoopRampPeriod = seconds;
        cfg.ClosedLoopRamps.VoltageClosedLoopRampPeriod = seconds;
    }

    @Override
    public void setMechanismRatio(double ratio) {
        cfg.Feedback.RotorToSensorRatio = 1.0;
        cfg.Feedback.SensorToMechanismRatio = ratio;
    }

    @Override
    public void applyConfig() {
        motor.getConfigurator().apply(cfg);
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
