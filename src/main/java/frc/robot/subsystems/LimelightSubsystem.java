package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.networktables.GenericEntry;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

public class LimelightSubsystem extends SubsystemBase {
    NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
    NetworkTableEntry tx = table.getEntry("tx");
    NetworkTableEntry ty = table.getEntry("ty");
    NetworkTableEntry ta = table.getEntry("ta");

    double x;
    double y;
    double area;
    double xInRadians;
    double yInRadians;

    double distanceX;
    double distanceY;

    double limelightMountAngle;
    double limelightHeight;
    double targetHeight;

    double xToRadians;
    double yToRadians;

    public LimelightSubsystem() {
    }

    @Override
    public void periodic() {
        x = tx.getDouble(0.0);
        y = ty.getDouble(0.0);
        area = ta.getDouble(0.0);
        // SmartDashboard.putNumber("Offset X", x);
        // SmartDashboard.putNumber("Offset Y", y);
        // SmartDashboard.putNumber("Targe Area", area);
        // Convert X and Y to radians to use with Math.tan
        xInRadians = x * (3.14159 / 180);
        yInRadians = y * (3.14159 / 180);
        // Use offset angle to calculate distances to target
        distanceX = (limelightHeight - targetHeight);
    }

    private final GenericEntry LimelightX = Shuffleboard.getTab("VisionTesting").add("Target X Offset", 0)
            .withPosition(1, 1)
            .withSize(1, 1).getEntry();
    private final GenericEntry LimelightY = Shuffleboard.getTab("VisionTesting").add("Target Y Offset", 0)
            .withPosition(2, 1)
            .withSize(1, 1).getEntry();

    public void updateWidgets() {
        LimelightX.setDouble(x);
        LimelightY.setDouble(y);
    }
}
