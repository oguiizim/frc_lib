// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.lib.vision.LimelightCamera;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Vision;

public class RobotContainer {
  private final Vision visionSub;
  private final Intake intakeSub;
  private final Drivetrain drivetrainSub;
  private final CommandXboxController driver;

  public RobotContainer() {
    this.driver = new CommandXboxController(0);
    this.intakeSub = new Intake(9);
    this.visionSub = new Vision(
        new LimelightCamera("cam1"),
        new LimelightCamera("cam2"));
    this.drivetrainSub = new Drivetrain(visionSub);
    configureBindings();
  }

  private void configureBindings() {
  }

  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
  }
}
