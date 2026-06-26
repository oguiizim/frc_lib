package frc.lib.vision;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import edu.wpi.first.math.geometry.Rotation2d;
import frc.lib.util.LEDModes;
import frc.lib.vision.LimelightHelpers.RawFiducial;
import frc.lib.vision.types.VisionPoseEstimate;
import frc.lib.vision.types.VisionTarget;

public class LimelightCamera implements VisionIO {

    private final String name;
    private double pitch, roll, forward, up, side;

    public LimelightCamera(String name) {
        this.name = name;
    }

    @Override
    public boolean hasTarget() {
        return LimelightHelpers.getTV(name);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public List<VisionTarget> getTargets() {
        RawFiducial[] fiducials = LimelightHelpers.getRawFiducials(name);
        List<VisionTarget> targets = new ArrayList<>();
        for (RawFiducial raw : fiducials) {
            targets.add(new VisionTarget(raw));
        }
        return targets;
    }

    @Override
    public Optional<VisionTarget> getBestTarget() {
        List<VisionTarget> targets = getTargets();
        return targets.isEmpty()
                ? Optional.empty()
                : Optional.of(targets.get(0));
    }

    @Override
    public void setPipeline(int pipeline) {
        LimelightHelpers.setPipelineIndex(name, pipeline);
    }

    @Override
    public void setLEDMode(LEDModes mode) {
        switch (mode) {
            case ON:
                LimelightHelpers.setLEDMode_ForceOn(name);
                break;

            case OFF:
                LimelightHelpers.setLEDMode_ForceOff(name);
                break;

            case BLINK:
                LimelightHelpers.setLEDMode_ForceBlink(name);
                break;

            default:
                LimelightHelpers.setLEDMode_ForceOn(name);
                break;
        }
    }

    @Override
    public void setRobotOrientation(double yaw, double pitch, double roll) {
        LimelightHelpers.SetRobotOrientation(name, yaw, 0, pitch, 0, roll, 0);
        this.pitch = pitch;
        this.roll = roll;
    }

    @Override
    public void setCameraPose(double forward, double side, double up, double roll, double pitch, double yaw) {
        LimelightHelpers.setCameraPose_RobotSpace(name, forward, side, up, roll, pitch, yaw);
        this.forward = forward;
        this.side = side;
        this.up = up;
        this.pitch = pitch;
        this.roll = roll;
    }

    @Override
    public Optional<VisionPoseEstimate> getPoseEstimate(Rotation2d yaw) {
        LimelightHelpers.SetRobotOrientation(name, yaw.getDegrees(), 0, pitch, 0, roll, 0);
        LimelightHelpers.PoseEstimate mt2 = LimelightHelpers.getBotPoseEstimate_wpiBlue_MegaTag2(name);

        if (mt2 == null || mt2.tagCount == 0) {
            return Optional.empty();
        }

        double avgAmbiguity = 0, avgDistance = 0;

        if (mt2.rawFiducials != null && mt2.rawFiducials.length > 0) {
            double ambiguity, distance;
            ambiguity = distance = 0;
            for (var f : mt2.rawFiducials) {
                ambiguity += f.ambiguity;
                distance += f.distToRobot;
            }
            avgDistance = distance / mt2.rawFiducials.length;
            avgAmbiguity = ambiguity / mt2.rawFiducials.length;
        }

        return Optional.of(
                new VisionPoseEstimate(
                        mt2.pose,
                        mt2.timestampSeconds,
                        mt2.tagCount,
                        avgAmbiguity,
                        avgDistance,
                        this.name));
    }
}
