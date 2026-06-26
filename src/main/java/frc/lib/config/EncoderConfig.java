package frc.lib.config;

public record EncoderConfig(
        double positionFactor,
        double velocityFactor,
        boolean inverted) {
}
