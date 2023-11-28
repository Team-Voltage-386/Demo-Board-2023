package frc.robot.subsystems;


import frc.robot.PID;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.CANSparkMax.ControlType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class MotorTest extends SubsystemBase{

    private CANSparkMax motor1 = new CANSparkMax(12, MotorType.kBrushless);
    private PID pidMotorControl = new PID("PID Values", motor1);
    private RelativeEncoder motor1Values = motor1.getEncoder();

    public MotorTest ()
    {
        
    }

    
    public void setRotation()
    {
        pidMotorControl.setReference(ControlType.kPosition);
    }

    public void setRotation(double RotationTarget)
    {
        pidMotorControl.setReference(RotationTarget, ControlType.kPosition);
    }

    @Override
    public void periodic() 
    {
        
    }
} 