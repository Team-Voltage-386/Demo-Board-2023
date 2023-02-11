package frc.robot.subsystems;

import com.ctre.phoenix.sensors.WPI_PigeonIMU;

import edu.wpi.first.networktables.GenericEntry;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DutyCycleEncoder;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

//DIO are plugged in to ports on the left side of the RoboRIO
public class DIOSubsystem extends SubsystemBase {

    DigitalInput button = new DigitalInput(2);
    DigitalInput limitSwitch = new DigitalInput(0);
    Ultrasonic ultraSon = new Ultrasonic(8, 9);
    // Gyro is not really a DIO device. 12 is the gyro's CAN ID
    WPI_PigeonIMU gyro = new WPI_PigeonIMU(12);
    // The gyro has a method to fill in a list with yaw, pitch, roll
    double[] ypr = new double[3];
    private final DutyCycleEncoder absEnc = new DutyCycleEncoder(5);

    public DIOSubsystem() {
        // These two lines of code are necessary to set up the ultrasonic sensor
        ultraSon.setAutomaticMode(true);
        ultraSon.setEnabled(true);

        gyro.reset();
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

    public double getAbsEncValue() {
        return absEnc.get();
    }

    public int getUltraDistanceInch() {
        return (int) ultraSon.getRangeInches();
    }

    // This returns the gyro yaw
    public double getGyroAngle() {
        return gyro.getAngle();
    }

    public double[] getGyroYPR() {
        gyro.getYawPitchRoll(ypr);
        return ypr;
    }

    private static final ShuffleboardTab mainTab = Shuffleboard.getTab("Main");
    private static final GenericEntry absEncVal = mainTab.add("AbsEncoder", 0.0).getEntry();

    @Override
    public void periodic() {
        getGyroYPR();
        absEncVal.setDouble(absEnc.get());
        SmartDashboard.putBoolean("Ultrasonic Range Valid", ultraSon.isRangeValid());
        SmartDashboard.putNumber("Ultrasonic Distance", ultraSon.getRangeInches());
        SmartDashboard.putBoolean("Ultrasonic Enabled", ultraSon.isEnabled());
        SmartDashboard.putNumber("Gyro Angle", gyro.getAngle());
        SmartDashboard.putNumber("Gyro Yaw", ypr[0]);
        SmartDashboard.putNumber("Gyro Pitch", ypr[1]);
        SmartDashboard.putNumber("Gyro Roll", ypr[2]);

    }
}