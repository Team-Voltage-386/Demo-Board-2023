package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.SparkAbsoluteEncoder;
import com.revrobotics.SparkAbsoluteEncoder.Type;

import edu.wpi.first.wpilibj.PowerDistribution;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.shuffleboard.SimpleWidget;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class MotorTest extends SubsystemBase {
    private Spark motorA;
    private PowerDistribution PDP;
   
    private ShuffleboardTab motorTab;

    private SimpleWidget motorCurrent;
    private SimpleWidget motorPercent;

    public MotorTest() {
        PDP = new PowerDistribution();
        motorTab = Shuffleboard.getTab("Motor Controls");
        motorCurrent = motorTab.add("Current", 0.0);
        motorPercent = motorTab.add("Motor Percentage", 0.0);
        motorA = new Spark(3);
    }

    @Override
    public void periodic() {
        motorCurrent.getEntry().setDouble(PDP.getCurrent(6));
        motorA.set(motorPercent.getEntry().getDouble(0));
    }
}