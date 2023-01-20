package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.networktables.GenericEntry;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class MotorTest extends SubsystemBase{

    private CANSparkMax motor1 = new CANSparkMax(1, MotorType.kBrushless);

    public MotorTest ()
    {}

    public void driveMotorStop()
    {
        motor1.set(0);
    }

    public void setDrivePower1(double v)
    {
        if(v>0.1||v<-0.1)
        {
            motor1.set(v);
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