package frc.lib.vision;

import java.util.Optional;

import edu.wpi.first.math.VecBuilder;
import frc.lib.vision.types.VisionPoseEstimate;
import swervelib.SwerveDrive;

public class VisionOdometry {

    private final VisionManager vision;

    public VisionOdometry(VisionManager vision) {
        this.vision = vision;
    }

    public void update(SwerveDrive drive) {
        Optional<VisionPoseEstimate> estimate = vision.getBestPoseEstimate(drive.getYaw());

        if (estimate.isEmpty())
            return;

        VisionPoseEstimate pose = estimate.get();

        if (!isReliable(pose, drive))
            return;

        double std = calculateStd(pose);

        drive.setVisionMeasurementStdDevs(VecBuilder.fill(std, std, 9999999));

        drive.addVisionMeasurement(pose.pose(), pose.timestamp());
    }

    private boolean isReliable(VisionPoseEstimate pose, SwerveDrive drive) {

        if (pose.tagCount() == 0)
            return false;

        if (Math.abs(drive.getGyro()
                .getYawAngularVelocity()
                .magnitude()) > 120)
            return false;

        if (pose.avgAmbiguity() > 0.4)
            return false;

        return true;
    }

    private double calculateStd(VisionPoseEstimate pose) {

        double std = 0.7;

        if (pose.tagCount() >= 2)
            std = 0.3;

        return std;
    }
}
