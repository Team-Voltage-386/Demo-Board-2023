package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import frc.robot.Constants;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.motorcontrol.Spark;

import edu.wpi.first.networktables.GenericEntry;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class MotorTest extends SubsystemBase{
    /*Motor1=CanSparkMax
    *Motor2=Spark
    *Motor3=TalonSRX
    */
    private WPI_TalonSRX motor3 = new WPI_TalonSRX(Constants.OperatorConstants.CANTalonSRX);
    private CANSparkMax motor1 = new CANSparkMax(1, MotorType.kBrushless);
    private Spark motor2 = new Spark(Constants.OperatorConstants.kOGSparkPort);

    public MotorTest ()
    {}

    public void driveMotorStop1()
    {
        motor1.set(0);
    }

    public void driveMotorStop2()
    {
        motor2.set(0);
    }

    public void setDrivePower1(double v)
    {
        if(v>0.5||v<-0.5)
        {
            motor1.set(v);
        }
        else
        {
            driveMotorStop1();
        }
    }

    public void setDrivePower2(double v)
    {
        if(v>0.5||v<-0.5)
        {
            motor2.set(v);
        }
        else
        {
            driveMotorStop2();
        }
    }

    @Override
    public void periodic() 
    {
        updateWidgets();
    }




    private final GenericEntry mot1Enc = Shuffleboard.getTab("main").add("mot 1 enc", 0).withPosition(1,1).withSize(1,1).getEntry();
    
    private void updateWidgets() 
    {
        mot1Enc.setDouble(motor1.getEncoder().getPosition());
    }
} 