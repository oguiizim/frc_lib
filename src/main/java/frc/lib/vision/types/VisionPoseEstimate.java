package frc.lib.vision.types;

import edu.wpi.first.math.geometry.Pose2d;

public record VisionPoseEstimate(
                Pose2d pose,
                double timestamp,
                int tagCount,
                double avgAmbiguity,
                double avgDistance,
                String cameraName) {
}