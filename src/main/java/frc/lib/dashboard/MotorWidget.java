package frc.lib.dashboard;

import edu.wpi.first.networktables.GenericEntry;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInLayouts;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardContainer;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardLayout;
import frc.lib.motor.MotorIO;

public class MotorWidget implements DashboardWidget {

    private final MotorIO motor;

    private final GenericEntry position;
    private final GenericEntry velocity;
    private final GenericEntry current;
    private final GenericEntry voltage;
    private final GenericEntry temperature;

    public MotorWidget(
            ShuffleboardContainer container,
            MotorIO motor) {

        this.motor = motor;

        ShuffleboardLayout layout = container.getLayout(
                motor.getName(),
                BuiltInLayouts.kList);

        position = layout.add("Position", 0).getEntry();
        velocity = layout.add("Velocity", 0).getEntry();
        current = layout.add("Current", 0).getEntry();
        voltage = layout.add("Voltage", 0).getEntry();
        temperature = layout.add("Temperature", 0).getEntry();
    }

    @Override
    public void update() {
        position.setDouble(motor.getPosition());
        velocity.setDouble(motor.getVelocity());
        current.setDouble(motor.getCurrent());
        voltage.setDouble(motor.getVoltage());
        temperature.setDouble(motor.getTemperature());
    }

}
