package frc.lib.vision;

import java.util.Optional;

import frc.lib.pose.PoseEstimator;
import frc.lib.vision.types.VisionPoseEstimate;

public class VisionOdometry {

    private final VisionManager vision;
    private final PoseEstimator estimator;

    public VisionOdometry(VisionManager vision, PoseEstimator estimator) {
        this.vision = vision;
        this.estimator = estimator;
    }

    public void update() {
        Optional<VisionPoseEstimate> estimate = vision.getBestPoseEstimate(estimator.getYaw());

        if (estimate.isEmpty())
            return;

        VisionPoseEstimate pose = estimate.get();

        if (!isReliable(pose))
            return;

        double std = calculateStd(pose);

        estimator.setVisionStdDevs(std, std, 999999);

        estimator.addVisionMeasurement(pose.pose(), pose.timestamp());
    }

    private boolean isReliable(VisionPoseEstimate pose) {

        if (pose.tagCount() == 0)
            return false;

        if (Math.abs(estimator.getAngularVelocityDegPerSec()) > 120)
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
