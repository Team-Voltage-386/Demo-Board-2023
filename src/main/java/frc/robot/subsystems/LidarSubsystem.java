package frc.robot.subsystems;

import edu.wpi.first.networktables.GenericEntry;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.Rev2mDistanceSensor;
import com.revrobotics.Rev2mDistanceSensor.Port;
import com.revrobotics.Rev2mDistanceSensor.Unit;

public class LidarSubsystem extends SubsystemBase {

    private Rev2mDistanceSensor sens;

    public LidarSubsystem() {
        sens = new Rev2mDistanceSensor(Port.kOnboard);
        sens.setDistanceUnits(Unit.kMillimeters);
        sens.setAutomaticMode(true);
    }

    @Override
    public void periodic() {
        updateWidgets();
    }

    private static final ShuffleboardTab tab = Shuffleboard.getTab("Main");
    private static final GenericEntry distanceWidget = tab.add("dist", 0).getEntry();
    private void updateWidgets() {
        distanceWidget.setDouble(sens.getRange());
    }
    
}
