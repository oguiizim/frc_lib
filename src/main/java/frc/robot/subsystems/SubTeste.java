package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.lib.motor.MotorIO;
import frc.lib.motor.VictorSPXMotor;

public class SubTeste extends SubsystemBase {

    private MotorIO motor = new VictorSPXMotor(0, "Victor de Cria");
    
}
