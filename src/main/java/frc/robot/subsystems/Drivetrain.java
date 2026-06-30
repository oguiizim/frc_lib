package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.lib.pose.PoseEstimator;
import frc.lib.pose.YAGSLPoseEstimator;
import frc.lib.vision.VisionOdometry;
import swervelib.SwerveDrive;

public class Drivetrain extends SubsystemBase {

    private final SwerveDrive drive;
    private final VisionOdometry visionOdometry;
    private final PoseEstimator estimator;

    public Drivetrain(Vision vision) {
        this.drive = new SwerveDrive(null, null, 0, null);
        this.estimator = new YAGSLPoseEstimator(drive);
        this.visionOdometry = new VisionOdometry(vision.getManager(), estimator);
    }

    @Override
    public void periodic() {
        visionOdometry.update();
    }
}
