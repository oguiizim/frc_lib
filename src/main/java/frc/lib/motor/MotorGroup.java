package frc.lib.motor;

import edu.wpi.first.units.measure.Voltage;

public class MotorGroup {
    private final MotorIO leader, follower;

    public MotorGroup(MotorIO leader, MotorIO follower) {
        this.leader = leader;
        this.follower = follower;
    }

    public void setPercent(double percent) {
        leader.setPercent(percent);
        follower.setPercent(percent);
    }

    public void setPosition(double position) {
        leader.setPosition(position);
        follower.setPosition(position);
    }

    public void setPosition(double position, double ff) {
        leader.setPosition(position, ff);
        follower.setPosition(position, ff);
    }

    public void setVelocity(double velocity) {
        leader.setVelocity(velocity);
        follower.setVelocity(velocity);
    }

    public void setVelocity(double velocity, double ff) {
        leader.setVelocity(velocity, ff);
        follower.setVelocity(velocity, ff);
    }

    public void setVoltage(double voltage) {
        leader.setVoltage(voltage);
        follower.setVoltage(voltage);
    }

    public void setVoltage(Voltage voltage) {
        leader.setVoltage(voltage);
        follower.setVoltage(voltage);
    }
}
