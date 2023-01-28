package frc.robot.subsystems;

import com.ctre.phoenix.sensors.WPI_PigeonIMU;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import frc.robot.Constants;

public class IODigitalSubsystem extends SubsystemBase
{
    private DigitalInput button= new DigitalInput(Constants.OperatorConstants.kPushButton);
    private DigitalInput Switch = new DigitalInput(Constants.OperatorConstants.kLimitSwitchPort);
    public boolean buttonState, limitState;
    public double ultraDist, gyroYaw, gyroRoll, gyroPitch, gyroAngle;

    Ultrasonic ultraSon = new Ultrasonic(Constants.OperatorConstants.UltraPingDIOPort, Constants.OperatorConstants.UltraEchoDIOPort);
    WPI_PigeonIMU gyro = new WPI_PigeonIMU(Constants.OperatorConstants.kIMUid);

    public IODigitalSubsystem()
    {
        ultraSon.setAutomaticMode(true);
        ultraSon.setEnabled(true);
        gyro.reset();
    }

    public boolean getButtonState() 
    {
        return buttonState;
    }

    public boolean getLimitSwitchState() 
    {
        return limitState;
    }
    
    public double getGyroAngle ()
    {
        return gyroAngle;
    }

    public double getUltraDistance ()
    {
        return ultraDist;
    }

    public double getGyroYaw ()
    {
        return gyroYaw;
    }

    public double getGyroRoll ()
    {
        return gyroRoll;
    }

    public double getGyroPitch ()
    {
        return gyroPitch;
    }

    public void periodic() 
    {
        // This method will be called once per scheduler run
        buttonState=button.get();
        limitState=Switch.get();
        ultraDist=ultraSon.getRangeInches();
        gyroYaw=gyro.getYaw();
        gyroRoll=gyro.getRoll();
        gyroPitch=gyro.getPitch();
        gyroAngle=gyro.getAngle();
        
    }
}
