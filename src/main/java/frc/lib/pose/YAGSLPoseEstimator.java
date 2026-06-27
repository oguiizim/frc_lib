package frc.lib.pose;

import edu.wpi.first.math.VecBuilder;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import swervelib.SwerveDrive;

public class YAGSLPoseEstimator implements PoseEstimator {

    private final SwerveDrive drive;

    public YAGSLPoseEstimator(SwerveDrive drive) {
        this.drive = drive;
    }

    @Override
    public Rotation2d getYaw() {
        return drive.getYaw();
    }

    @Override
    public double getAngularVelocityDegPerSec() {
        return drive.getGyro().getYawAngularVelocity().magnitude();
    }

    @Override
    public void setVisionStdDevs(double x, double y, double theta) {
        drive.setVisionMeasurementStdDevs(VecBuilder.fill(x, y, theta));
    }

    @Override
    public void addVisionMeasurement(Pose2d pose, double timestamp) {
        drive.addVisionMeasurement(pose, timestamp);
    }

}
