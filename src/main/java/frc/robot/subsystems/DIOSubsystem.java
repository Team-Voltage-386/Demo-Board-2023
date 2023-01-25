package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DIOSubsystem extends SubsystemBase {
    DigitalInput button = new DigitalInput(2);
    DigitalInput limitSwitch = new DigitalInput(0);
    Ultrasonic ultraSon = new Ultrasonic(8, 9);

    public DIOSubsystem() {
        ultraSon.ping();
        ultraSon.setAutomaticMode(true);
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
        System.out.println(ultraSon.getRangeInches());
        int dist = (int) ultraSon.getRangeInches();
        System.out.println(dist);
        return dist;
    }

    @Override
    public void periodic() {
        ultraSon.ping();
    }
}