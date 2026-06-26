package frc.lib.vision;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import edu.wpi.first.math.geometry.Rotation2d;
import frc.lib.vision.types.VisionPoseEstimate;

public class VisionManager {

    private final List<VisionIO> cameras;

    public VisionManager(VisionIO... cameras) {
        this.cameras = new ArrayList<>(List.of(cameras));
    }

    public void addCameras(VisionIO cam) {
        cameras.add(cam);
    }

    public void removeCamera(VisionIO cam){
        cameras.remove(cam);
    }

    public List<VisionIO> getCameras() {
        return Collections.unmodifiableList(cameras);
    }

    public Optional<VisionPoseEstimate> getBestPoseEstimate(Rotation2d yaw) {
        VisionPoseEstimate best = null;

        for (VisionIO cam : cameras) {
            Optional<VisionPoseEstimate> est = cam.getPoseEstimate(yaw);

            if (est.isEmpty())
                continue;

            VisionPoseEstimate current = est.get();

            if (best == null) {
                best = current;
                continue;
            }

            if (isBetter(current, best))
                best = current;
        }

        return Optional.ofNullable(best);
    }

    public List<VisionPoseEstimate> getPoseEstimates(Rotation2d yaw) {
        List<VisionPoseEstimate> poses = new ArrayList<>();
        for (VisionIO cam : cameras) {
            Optional<VisionPoseEstimate> est = cam.getPoseEstimate(yaw);

            if (est.isEmpty())
                continue;

            VisionPoseEstimate current = est.get();
            poses.add(current);
        }
        return poses;
    }

    private boolean isBetter(VisionPoseEstimate a, VisionPoseEstimate b) {
        if (a.tagCount() != b.tagCount())
            return a.tagCount() > b.tagCount();

        return a.avgAmbiguity() < b.avgAmbiguity();
    }
}
