package frc.robot.subsystems;

import edu.wpi.first.wpilibj.shuffleboard.BuiltInLayouts;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardLayout;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.lib.control.TunablePID;
import frc.lib.motor.MotorIO;
import frc.lib.motor.TalonFXMotor;

public class Intake extends SubsystemBase {
    private final MotorIO arm;
    private TunablePID armPid;

    public Intake(int armId) {
        this.arm = new TalonFXMotor(armId, "Intake Arm Motor", 1.0);
        ShuffleboardLayout armLayout = Shuffleboard.getTab("Control").getLayout("Intake Arm PID", BuiltInLayouts.kList);
        this.armPid = new TunablePID(armLayout, 1.0, 0.0, 0.0);
    }

    @Override
    public void periodic() {
        armPid.applyIfChanged(arm);
    }
}
