package frc.lib.control;

public interface Tunable<T> {
    T get();

    boolean hasChanged();
}
