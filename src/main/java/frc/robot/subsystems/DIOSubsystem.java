package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DIOSubsystem extends SubsystemBase {
    DigitalInput button = new DigitalInput(2);
    DigitalInput limitSwitch = new DigitalInput(0);
    Ultrasonic ultraSon = new Ultrasonic(8, 9);

    public DIOSubsystem() {
        ultraSon.setAutomaticMode(true);
        ultraSon.setEnabled(true);
    }

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

    public int getUltraDistanceInch() {
        return (int) ultraSon.getRangeInches();
    }

    @Override
    public void periodic() {
        SmartDashboard.putBoolean("Ultrasonic Range Valid", ultraSon.isRangeValid());
        SmartDashboard.putNumber("Ultrasonic Distance", ultraSon.getRangeInches());
        SmartDashboard.putBoolean("Ultrasonic Enabled", ultraSon.isEnabled());
    }
}