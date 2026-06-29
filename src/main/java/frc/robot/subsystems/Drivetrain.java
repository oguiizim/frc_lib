package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.lib.pose.PoseEstimator;
import frc.lib.pose.YAGSLPoseEstimator;
import frc.lib.vision.LimelightCamera;
import frc.lib.vision.VisionManager;
import frc.lib.vision.VisionOdometry;
import swervelib.SwerveDrive;

public class Drivetrain extends SubsystemBase {

    private final SwerveDrive drive;
    private final VisionOdometry visionOdometry;
    private final VisionManager visionManager;
    private final PoseEstimator estimator;
    private final LimelightCamera cam1 = new LimelightCamera("limelight1");
    private final LimelightCamera cam2 = new LimelightCamera("gap");

    public Drivetrain() {
        this.drive = new SwerveDrive(null, null, 0, null);
        this.estimator = new YAGSLPoseEstimator(drive);
        this.visionManager = new VisionManager(
                cam1,
                cam2);
        this.visionOdometry = new VisionOdometry(visionManager, estimator);
    }

    @Override
    public void periodic() {
        visionOdometry.update();
    }
}
