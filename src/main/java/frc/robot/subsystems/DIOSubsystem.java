package frc.robot.subsystems;

import com.ctre.phoenix.sensors.WPI_PigeonIMU;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DIOSubsystem extends SubsystemBase {
    DigitalInput button = new DigitalInput(2);
    DigitalInput limitSwitch = new DigitalInput(0);
    Ultrasonic ultraSon = new Ultrasonic(8, 9);
    WPI_PigeonIMU gyro = new WPI_PigeonIMU(12);

    double[] ypr = new double[3];

    public DIOSubsystem() {
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

    public int getUltraDistanceInch() {
        return (int) ultraSon.getRangeInches();
    }

    public double getGyroAngle() {
        return gyro.getAngle();
    }

    public double[] getGyroYPR() {
        gyro.getYawPitchRoll(ypr);
        return ypr;
    }

    @Override
    public void periodic() {
        getGyroYPR();
        SmartDashboard.putBoolean("Ultrasonic Range Valid", ultraSon.isRangeValid());
        SmartDashboard.putNumber("Ultrasonic Distance", ultraSon.getRangeInches());
        SmartDashboard.putBoolean("Ultrasonic Enabled", ultraSon.isEnabled());
        SmartDashboard.putNumber("Gyro Angle", gyro.getAngle());

        SmartDashboard.putNumber("Gyro Yaw", ypr[0]);
        SmartDashboard.putNumber("Gyro Pitch", ypr[1]);
        SmartDashboard.putNumber("Gyro Roll", ypr[2]);
    }
}