package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;

import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.shuffleboard.SimpleWidget;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class MotorTest extends SubsystemBase{

    private CANSparkMax motorA = new CANSparkMax(12, MotorType.kBrushless);
    private CANSparkMax motorB = new CANSparkMax(11, MotorType.kBrushless);

    private double OldA=0, OldB=0;
    private ShuffleboardTab motorTab;

    private SimpleWidget MotorAShuffable, MotorBShuffable; 

    public MotorTest()
    {
        motorTab = Shuffleboard.getTab("Motor Controls");
        MotorAShuffable = motorTab.add("Motor A Percent", 0);
        MotorBShuffable = motorTab.add("Motor B Percent", 0);
    }

    @Override
    public void periodic() 
    {
        double A = MotorAShuffable.getEntry().getDouble(0);
        double B = MotorBShuffable.getEntry().getDouble(0);

        if (A>100)
        {
            A=100;
        }
        if (A<-100)
        {
            A=-100;
        }

        if (B>100)
        {
            B=100;
        }
        if (B<-100)
        {
            B=-100;
        }

        if (A!=OldA)
        {

            OldA=A;
            motorA.set(OldA/100);
        }
        if (B!=OldB)
        {
            OldB=B;
            motorB.set(OldB/100);
        }

    }
} 