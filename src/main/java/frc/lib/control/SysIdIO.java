package frc.lib.control;

import edu.wpi.first.units.Units;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Subsystem;
import edu.wpi.first.wpilibj2.command.sysid.SysIdRoutine;
import frc.lib.motor.MotorIO;

public class SysIdIO {

    private final SysIdRoutine routine;

    public SysIdIO(Subsystem sub, MotorIO motor) {
        routine = new SysIdRoutine(
                new SysIdRoutine.Config(),

                new SysIdRoutine.Mechanism(
                        volts -> motor.setVoltage(volts.in(Units.Volts)),
                        log -> {
                            log.motor(motor.getName()).voltage(Units.Volts.of(motor.getVoltage()))
                                    .angularPosition(Units.Rotations.of(motor.getPosition()))
                                    .angularVelocity(Units.RotationsPerSecond.of(motor.getVelocity() / 60.0));
                        },
                        sub));
    }

    public Command sysQuasistatic(SysIdRoutine.Direction direction) {
        return routine.quasistatic(direction);
    }

    public Command sysDynamic(SysIdRoutine.Direction direction) {
        return routine.dynamic(direction);
    }
}
