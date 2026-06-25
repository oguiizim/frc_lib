package frc.lib.utils;

public class UnsupportedFeature {
    public static void unsupported(String controller, String feature){
        throw new UnsupportedOperationException(controller + " does not support " + feature);
    }
}
