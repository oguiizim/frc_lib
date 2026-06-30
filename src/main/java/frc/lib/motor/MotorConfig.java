package frc.lib.motor;

import frc.lib.util.NeutralMode;

public record MotorConfig(
    boolean inverted,
    NeutralMode idleMode,
    int currentLimit,
    double rampRate,
    double mechanismRatio
) {
}
