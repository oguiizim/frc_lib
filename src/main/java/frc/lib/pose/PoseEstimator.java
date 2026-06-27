package frc.lib.pose;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;

public interface PoseEstimator {

    Rotation2d getYaw();

    double getAngularVelocityDegPerSec();

    void setVisionStdDevs(double x, double y, double theta);

    void addVisionMeasurement(Pose2d pose, double timestamp);
    
}
