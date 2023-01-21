package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DIOSubsystem extends SubsystemBase {
    DigitalInput button = new DigitalInput(2);
    DigitalInput limitSwitch = new DigitalInput(0);

    public boolean getButtonState() {
        if (button.get()) {
            return false;
        } else {
            return true;
        }
    }

    public boolean getLimitSwitchState() {
        if (limitSwitch.get()) {
            return false;
        } else {
            return true;
        }
    }

}