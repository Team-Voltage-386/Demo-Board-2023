package frc.robot.subsystems;

import com.ctre.phoenix.sensors.WPI_PigeonIMU;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DIOSubsystem extends SubsystemBase {
    WPI_PigeonIMU gyro = new WPI_PigeonIMU(13);

    public DIOSubsystem() {
    }

    public double gyroHeading() {
        return gyro.getAngle();
    }

    @Override
    public void periodic() {
        SmartDashboard.putNumber("Gyro Angle", gyro.getAngle());
    }
}
