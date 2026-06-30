package frc.lib.motor;

import com.revrobotics.spark.SparkClosedLoopController;
import com.revrobotics.spark.SparkFlex;
import com.revrobotics.spark.ClosedLoopSlot;
import com.revrobotics.spark.FeedbackSensor;
import com.revrobotics.spark.SparkBase.ControlType;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.config.SparkFlexConfig;

import edu.wpi.first.units.measure.Voltage;
import frc.lib.util.NeutralMode;

public class SparkFlexMotor implements MotorIO {

    private final String motorName;
    private final SparkFlex motor;
    private final SparkClosedLoopController pid;
    private final SparkFlexConfig cfg = new SparkFlexConfig();

    public SparkFlexMotor(int id, String name, MotorConfig config) {
        this.motorName = name;
        motor = new SparkFlex(id, MotorType.kBrushless);
        pid = motor.getClosedLoopController();
        this.setCurrentLimit(config.currentLimit());
        this.setRampRate(config.rampRate());
        this.setIdleMode(config.idleMode());
        this.setInverted(config.inverted());
        this.setMechanismRatio(config.mechanismRatio());

        cfg.closedLoop.feedbackSensor(FeedbackSensor.kPrimaryEncoder);
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
        pid.setSetpoint(position, ControlType.kPosition);
    }

    @Override
    public void setPosition(double position, double ff) {
        pid.setSetpoint(position, ControlType.kPosition, ClosedLoopSlot.kSlot0, ff);
    }

    @Override
    public void setVelocity(double velocity) {
        pid.setSetpoint(velocity, ControlType.kVelocity);
    }

    @Override
    public void setVelocity(double velocity, double ff) {
        pid.setSetpoint(velocity, ControlType.kVelocity, ClosedLoopSlot.kSlot0, ff);
    }

    @Override
    public void setVoltage(double voltage) {
        motor.setVoltage(voltage);
    }

    @Override
    public void setVoltage(Voltage voltage) {
        motor.setVoltage(voltage);
    }

    @Override
    public double getVelocity() {
        return motor.getEncoder().getVelocity();
    }

    @Override
    public double getPosition() {
        return motor.getEncoder().getPosition();
    }

    @Override
    public double getVoltage() {
        return motor.getAppliedOutput() * motor.getBusVoltage();
    }

    @Override
    public double getTemperature() {
        return motor.getMotorTemperature();
    }

    @Override
    public double getCurrent() {
        return motor.getOutputCurrent();
    }

    @Override
    public void configurePID(double kP, double kI, double kD) {
        cfg.closedLoop.pid(kP, kI, kD);
    }

    @Override
    public void configureFeedForward(double kS, double kV, double kA, double kG) {
        cfg.closedLoop.feedForward.svag(kS, kV, kA, kG);
    }

    @Override
    public void configureFeedForward(double kS, double kV, double kA) {
        cfg.closedLoop.feedForward.sva(kS, kV, kA);
    }

    @Override
    public void setInverted(boolean inverted) {
        cfg.inverted(inverted);
    }

    @Override
    public void setIdleMode(NeutralMode mode) {
        cfg.idleMode(mode == NeutralMode.BRAKE
                ? IdleMode.kBrake
                : IdleMode.kCoast);
    }

    @Override
    public void setCurrentLimit(int amps) {
        cfg.smartCurrentLimit(amps);
    }

    @Override
    public void setRampRate(double seconds) {
        cfg.openLoopRampRate(seconds);
        cfg.closedLoopRampRate(seconds);
    }

    @Override
    public void setMechanismRatio(double ratio) {
        cfg.encoder.positionConversionFactor(1.0 / ratio);
        cfg.encoder.velocityConversionFactor(1.0 / ratio / 60.0);
    }

    @SuppressWarnings("removal")
    @Override
    public void applyConfig() {
        motor.configure(cfg, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    }

    @Override
    public void stop() {
        motor.stopMotor();
    }

    @Override
    public void resetPosition(double position) {
        motor.getEncoder().setPosition(position);
    }
}
