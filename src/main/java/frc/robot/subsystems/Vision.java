package frc.robot.subsystems;

import java.util.ArrayList;
import java.util.List;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.lib.vision.VisionIO;
import frc.lib.vision.VisionManager;

public class Vision extends SubsystemBase {

    private final VisionManager manager;
    private final List<VisionIO> cameras;

    public Vision(VisionIO... cameras) {
        this.cameras = new ArrayList<>(List.of(cameras));
        this.manager = new VisionManager(cameras);
    }

    public VisionManager getManager() {
        return manager;
    }

}
