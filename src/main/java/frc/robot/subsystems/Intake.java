package frc.robot.subsystems;

import edu.wpi.first.wpilibj.shuffleboard.BuiltInLayouts;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardLayout;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.lib.control.TunablePID;
import frc.lib.dashboard.DashboardWidgets;
import frc.lib.motor.MotorConfig;
import frc.lib.motor.MotorIO;
import frc.lib.motor.SparkMaxMotor;
import frc.lib.util.NeutralMode;

public class Intake extends SubsystemBase {

    ShuffleboardLayout layout = Shuffleboard.getTab("Control").getLayout("Intake Arm PID", BuiltInLayouts.kList);

    private final MotorIO armMotor;
    private final TunablePID armPid;

    public Intake(int armId) {
        this.armMotor = new SparkMaxMotor(armId, "Intake Arm Motor", new MotorConfig(
                false,
                NeutralMode.BRAKE,
                40,
                0.5,
                1.0));
        this.armPid = new TunablePID(layout, 1.0, 0.0, 0.0);

        DashboardWidgets.motor("Motor Telemetry", armMotor);
    }

    @Override
    public void periodic() {
        armPid.applyIfChanged(armMotor);
    }
}
