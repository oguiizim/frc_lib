package frc.lib.vision.types;

import frc.lib.vision.LimelightHelpers.RawFiducial;

public record VisionTarget(
        int id,
        double tx,
        double ty,
        double ta,
        double ambiguity) {
    public VisionTarget(RawFiducial raw) {
        this(
                raw.id,
                raw.txnc,
                raw.tync,
                raw.ta,
                raw.ambiguity);
    }
}