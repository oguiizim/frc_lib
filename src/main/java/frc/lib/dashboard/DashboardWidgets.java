package frc.lib.dashboard;

import java.util.ArrayList;
import java.util.List;

import edu.wpi.first.wpilibj.shuffleboard.BuiltInLayouts;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardLayout;
import frc.lib.motor.MotorIO;

public final class DashboardWidgets {

    private static final List<DashboardWidget> widgets = new ArrayList<>();

    private DashboardWidgets() {
    }

    public static void motor(
            String name,
            MotorIO motor) {

        ShuffleboardLayout container = Shuffleboard.getTab(name).getLayout(motor.getName(), BuiltInLayouts.kList);
        widgets.add(new MotorWidget(container, motor));
    }

    public static void update() {

        for (DashboardWidget widget : widgets) {
            widget.update();
        }

    }

}
